package agh.ics.sr.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClientDataPlane extends Thread implements AutoCloseable, IClientDataPlane {
    interface IDataPlaneListener {
        void onDataReceived(int fromClientId, byte[] data);
    }
    private final IClientControlPlane controlPlane;
    private final DatagramSocket masterSocket;
    private final SocketAddress remoteAddress;
    private final List<IDataPlaneListener> listeners = new ArrayList<>();
    public ClientDataPlane(IClientControlPlane controlPlane) throws SocketException {

        this.controlPlane = controlPlane;
        remoteAddress = controlPlane.getRemoteSockAddr();
        masterSocket = new DatagramSocket(controlPlane.getLocalSockAddr());
    }

    @Override
    public void run() {
        try {
            byte[] data  = new byte[65536];
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                masterSocket.receive(packet);
                if(!remoteAddress.equals(packet.getSocketAddress()))
                    continue;
                if(packet.getLength() < 5)
                    continue;
                int fromClientId = ByteBuffer.wrap(packet.getData(), 0, 4)
                        .order(ByteOrder.BIG_ENDIAN)
                        .getInt();
                byte[] currentData = new byte[packet.getLength() - 4];
                System.arraycopy(packet.getData(), packet.getOffset() + 4, currentData, 0, packet.getLength() - 4);
                synchronized (listeners) {
                    listeners.forEach(listener -> listener.onDataReceived(fromClientId, currentData));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendData(byte[] data) throws IOException {
        Integer clientId = controlPlane.getClientId();
        if (clientId == null) {
            System.out.println("Cannot send data until the server assigns client id");
            return;
        }
        byte[] newBuffer = ByteBuffer.allocate(data.length + 4)
                .order(ByteOrder.BIG_ENDIAN)
                .putInt(0, clientId)
                .put(4, data)
                .array();
        DatagramPacket packet = new DatagramPacket(newBuffer, newBuffer.length, remoteAddress);
        masterSocket.send(packet);
    }

    @Override
    public void close(){
        masterSocket.close();
    }

    public void registerListener(IDataPlaneListener listener){
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void deregisterListener(IDataPlaneListener listener){
        synchronized (listeners){
            listeners.remove(listener);
        }
    }
}
