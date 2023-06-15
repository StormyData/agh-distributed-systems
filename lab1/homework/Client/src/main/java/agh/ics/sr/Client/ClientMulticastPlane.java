package agh.ics.sr.Client;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ClientMulticastPlane extends Thread implements AutoCloseable, IClientDataPlane, ClientControlPlane.IServerHelloListener {

    interface IMulticastPlaneListener {
        void onMulticastReceived(int fromClientId, byte[] data);
    }
    private final IClientControlPlane controlPlane;
    private MulticastSocket masterSocket = null;
    private InetSocketAddress group;
    private final NetworkInterface networkInterface;
    private final List<IMulticastPlaneListener> listeners = new ArrayList<>();

    private final Semaphore semaphore = new Semaphore(0);
    public ClientMulticastPlane(IClientControlPlane controlPlane, NetworkInterface networkInterface) throws IOException {

        this.controlPlane = controlPlane;
        this.networkInterface = networkInterface;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            masterSocket = new MulticastSocket(group.getPort());
            masterSocket.setNetworkInterface(networkInterface);
            masterSocket.joinGroup(group, networkInterface);

            byte[] data  = new byte[65536];
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                masterSocket.receive(packet);
                if(packet.getLength() < 5)
                    continue;
                int fromClientId = ByteBuffer.wrap(packet.getData(), 0, 4)
                        .order(ByteOrder.BIG_ENDIAN)
                        .getInt();
                if(fromClientId == controlPlane.getClientId())
                    continue;
                byte[] currentData = new byte[packet.getLength() - 4];
                System.arraycopy(packet.getData(), packet.getOffset() + 4, currentData, 0, packet.getLength() - 4);
                synchronized (listeners) {
                    listeners.forEach(listener -> listener.onMulticastReceived(fromClientId, currentData));
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendData(byte[] data) throws IOException {
        Integer clientId = controlPlane.getClientId();
        if (clientId == null || masterSocket == null) {
            System.out.println("Cannot send data until the server assigns client id");
            return;
        }
        byte[] newBuffer = ByteBuffer.allocate(data.length + 4)
                .order(ByteOrder.BIG_ENDIAN)
                .putInt(0, clientId)
                .put(4, data)
                .array();
        DatagramPacket packet = new DatagramPacket(newBuffer, newBuffer.length, group);
        masterSocket.send(packet);
    }

    @Override
    public void close() throws IOException {
        masterSocket.leaveGroup(group, networkInterface);
        masterSocket.close();
    }

    public void registerListener(IMulticastPlaneListener listener){
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void deregisterListener(IMulticastPlaneListener listener){
        synchronized (listeners){
            listeners.remove(listener);
        }
    }
    @Override
    public void onServerHello(int client_id, SocketAddress multicastAddress) {
        semaphore.release();
        group = controlPlane.getMulticastSockAddr();

    }

}
