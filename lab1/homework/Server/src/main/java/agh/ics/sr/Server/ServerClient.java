package agh.ics.sr.Server;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.MessageParser;
import agh.ics.sr.controlPlane.messages.messageTypes.ClientJoinedMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.ConnectionClosedMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.ServerHelloMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.TextMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

public class ServerClient extends Thread {
    private static final Logger logger = Logger.getLogger(ServerClient.class.getCanonicalName());
    private final Socket socket;
    private final int clientId;
    private final IServerControlPlane server;

    private final InetSocketAddress multicastAddress;

    public ServerClient(Socket socket, int clientId, IServerControlPlane server, InetSocketAddress multicastAddress) {
        this.socket = socket;
        this.clientId = clientId;
        this.server = server;
        this.multicastAddress = multicastAddress;
    }

    @Override
    public void run() {
        OutputStream outputStream;
        InputStream inputStream;
        try {
            synchronized (socket) {
                outputStream = socket.getOutputStream();
                new ServerHelloMessage(clientId, multicastAddress).produce(outputStream);

            }
            server.sendToAll(new ClientJoinedMessage(clientId));
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                AMessage message = MessageParser.parseMessage(inputStream);
                if (!message.isForServer()) {
                    logger.warning(
                            String.format("server received non server targeted message %s from client %d",
                                    message.getClass().getCanonicalName(), clientId));
                    continue;
                }
                if (message instanceof ConnectionClosedMessage) {
                    server.removeClient(clientId);
                    return;
                }
                if (message instanceof TextMessage textMessage) {
                    if (textMessage.getClientId() != clientId) {
                        logger.warning(
                                String.format(
                                        "client sent a text message with client id %d different than their own (%d), correcting",
                                        clientId,
                                        textMessage.getClientId()
                                )
                        );
                    }
                    logger.info(String.format(
                            "Client %d: %s", clientId, textMessage.getMessage()
                    ));
                    server.sendToAllExcept(new TextMessage(textMessage.getMessage(), clientId), clientId);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(AMessage message) {
        synchronized (socket) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                message.produce(outputStream);
                outputStream.flush();

            } catch (SocketException e) {
                server.removeClient(clientId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
