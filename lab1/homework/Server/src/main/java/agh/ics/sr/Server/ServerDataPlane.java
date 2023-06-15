package agh.ics.sr.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class ServerDataPlane extends Thread implements IServerDataPlane, AutoCloseable{
    private static final Logger logger = Logger.getLogger(ServerDataPlane.class.getCanonicalName());
    private final Map<Integer, SocketAddress> clients = new HashMap<>();

    private final Map<SocketAddress, Integer> clientsReversed = new HashMap<>();
    private final ReadWriteLock clientsLock = new ReentrantReadWriteLock();
    private final DatagramSocket masterSocket;
    public ServerDataPlane(int port) throws SocketException {
        masterSocket = new DatagramSocket(port);

    }

    @Override
    public void run() {
        logger.info("Starting data plane");
        byte[] data  = new byte[65536];
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                masterSocket.receive(packet);
                int fromclientId = getClientIdFromPacket(packet);
                if(fromclientId == -1)
                    continue;//drop packets from unknown clients
                if(packet.getLength() < 5)
                    continue;//drop packets that cannot contain clientid and any data
                byte[] currentData = new byte[packet.getLength()];
                System.arraycopy(packet.getData(), packet.getOffset(), currentData, 0, packet.getLength());
                int reportedClientId = ByteBuffer.wrap(currentData, 0, 4)
                        .order(ByteOrder.BIG_ENDIAN)
                        .getInt();
                ByteBuffer.wrap(currentData, 0, 4)
                        .order(ByteOrder.BIG_ENDIAN)
                        .putInt(0, fromclientId);
                if(reportedClientId != fromclientId)
                {
                    logger.warning(String.format("Received data from id %d, with reported id %d", fromclientId, reportedClientId));
                }
                logger.info(String.format("receiving data from %d, length %d", fromclientId, currentData.length - 4));

                sendToAll(currentData, fromclientId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void sendToAll(byte[] data, int from){
        Lock readLock = clientsLock.readLock();
        readLock.lock();
        try{
            clients.entrySet()
                    .parallelStream()
                    .filter(entry -> entry.getKey() != from)
                    .forEach(entry -> {
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        packet.setSocketAddress(entry.getValue());
                        try {
                            masterSocket.send(packet);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }finally {
            readLock.unlock();
        }
    }
    private int getClientIdFromPacket(DatagramPacket packet){
        Lock readLock = clientsLock.readLock();
        readLock.lock();
        try{
            return clientsReversed.getOrDefault(packet.getSocketAddress(), -1);
        }finally {
            readLock.unlock();
        }
    }
    @Override
    public void registerClient(int clientId, SocketAddress address) {
        Lock writeLock = clientsLock.writeLock();
        writeLock.lock();
        try{
            clients.put(clientId, address);
            clientsReversed.put(address, clientId);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void deregisterClient(int clientId) {
        Lock writeLock = clientsLock.writeLock();
        writeLock.lock();
        try{
            var addr = clients.remove(clientId);
            clientsReversed.remove(addr);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public void close() throws Exception {
        masterSocket.close();
    }
}
