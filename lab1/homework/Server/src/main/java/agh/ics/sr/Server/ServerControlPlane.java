package agh.ics.sr.Server;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.ClientLeftMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class ServerControlPlane extends Thread implements IServerControlPlane , AutoCloseable{
    private static final Logger logger = Logger.getLogger(ServerControlPlane.class.getCanonicalName());
    private final Map<Integer, ServerClient> clients = new HashMap<>();
    private final ReadWriteLock clientsLock = new ReentrantReadWriteLock();
    private final ServerSocket masterControlSocket;
    private final IServerDataPlane dataPlane;

    private final InetSocketAddress multicastAddress;
    private int lastClientId = 0;

    public ServerControlPlane(int port, IServerDataPlane dataPlane, InetSocketAddress multicastAddress) throws IOException {
        masterControlSocket = new ServerSocket(port);
        this.dataPlane = dataPlane;
        this.multicastAddress = multicastAddress;
        masterControlSocket.setReuseAddress(true);
    }

    @Override
    public void run(){
        logger.info("Starting data plane");
        while (true) {
            try {
                Socket client = masterControlSocket.accept();
                int clientId = lastClientId++;
                logger.info(String.format("client joined, assigning id %d", clientId));
                dataPlane.registerClient(clientId, client.getRemoteSocketAddress());
                var serverClient = new ServerClient(client, clientId, this, multicastAddress);
                clients.put(clientId, serverClient);
                serverClient.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendToAll(AMessage message) {
        var readLock = clientsLock.readLock();
        readLock.lock();
        try {
            clients
                    .values()
                    .parallelStream()
                    .forEach(client -> client.sendMessage(message));
        } finally {
            readLock.unlock();
        }
    }

    public void sendToAllExcept(AMessage message, int exceptClientId) {
        var readLock = clientsLock.readLock();
        readLock.lock();
        try {
            clients
                    .entrySet()
                    .parallelStream()
                    .filter(entry -> entry.getKey() != exceptClientId)
                    .forEach(entry -> entry.getValue().sendMessage(message));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void removeClient(int clientId) {
        var writeLock = clientsLock.writeLock();
        writeLock.lock();
        logger.info(String.format("client with id %d left", clientId));
        try {
            dataPlane.deregisterClient(clientId);
            clients.remove(clientId);
            sendToAllExcept(new ClientLeftMessage(clientId), clientId);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void close() throws Exception {
        masterControlSocket.close();
    }
}
