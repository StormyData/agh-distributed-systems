package agh.ics.sr.Client;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.MessageParser;
import agh.ics.sr.controlPlane.messages.messageTypes.*;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class ClientControlPlane extends Thread implements AutoCloseable, IClientControlPlane {

    private Integer clientId = null;
    private InetSocketAddress multicastAddress = null;
    private final OutputStream outputStream;
    private final InputStream inputStream;

    private final Socket socket;
    private final List<IServerHelloListener> serverHelloListenerList = new ArrayList<>();

    public interface IServerHelloListener {
        void onServerHello(int client_id, SocketAddress multicastAddress);
    }
    public ClientControlPlane(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        socket.setReuseAddress(true);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    public void run() {
        while (true) {
            try {
                AMessage message = MessageParser.parseMessage(inputStream);
                if (!message.isForClient())
                    continue;
                if (message instanceof ConnectionClosedMessage) {
                    System.out.println("Connection Closed");
                    System.exit(0);
                }
                if (message instanceof TextMessage textMessage) {
                    for (String line : textMessage.getMessage().split("\\r?\\n")) {
                        System.out.printf("%d: %s\n", textMessage.getClientId(), line);
                    }
                }
                if (message instanceof ClientJoinedMessage clientJoinedMessage) {
                    System.out.printf("[Server]: Client %d joined\n", clientJoinedMessage.getClientId());
                }
                if (message instanceof ClientLeftMessage clientLeftMessage) {
                    System.out.printf("[Server]: Client %d, left\n", clientLeftMessage.getClientId());
                }
                if (message instanceof ServerHelloMessage serverHelloMessage) {

                    if (clientId == null) {
                        System.out.printf("[Server]: your id is %d\n", serverHelloMessage.getClientId());
                    } else  if(serverHelloMessage.getClientId() != clientId){
                        System.out.printf("[Server]: your new id is %d\n", serverHelloMessage.getClientId());
                    }
                    clientId = serverHelloMessage.getClientId();
                    multicastAddress = serverHelloMessage.getMulticastAddress();

                    System.out.printf("[Server]: the multicast address is %s\n", multicastAddress);
                    synchronized (serverHelloListenerList)
                    {
                        serverHelloListenerList.forEach(listener -> listener.onServerHello(clientId, multicastAddress));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Integer getClientId() {
        return clientId;
    }
    @Override
    public void sendText(String text) throws IOException {
        if (clientId == null) {
            System.out.println("Cannot send messages until the server assigns client id");
            return;
        }
        new TextMessage(text, clientId).produce(outputStream);
    }

    @Override
    public SocketAddress getLocalSockAddr() {
        return socket.getLocalSocketAddress();
    }
    @Override
    public SocketAddress getRemoteSockAddr(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    public InetSocketAddress getMulticastSockAddr() {
        return multicastAddress;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    public void registerListener(IServerHelloListener listener)
    {

        synchronized (serverHelloListenerList)
        {
            serverHelloListenerList.add(listener);
        }
    }
    public void deregisterListener(IServerHelloListener listener)
    {
        synchronized (serverHelloListenerList)
        {
            serverHelloListenerList.remove(listener);
        }
    }
}
