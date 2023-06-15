package agh.ics.sr.controlPlane.messages;

import agh.ics.sr.controlPlane.messages.messageTypes.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MessageParser {
    private static final Map<Byte, AMessage> messageTypes;

    static {
        List<AMessage> messageTypesList = null;
        try {
            messageTypesList = List.of(
                    new ServerHelloMessage(-1, new InetSocketAddress(InetAddress.getByName("129.0.0.1"),10)),
                    new ConnectionClosedMessage(),
                    new ClientJoinedMessage(-1),
                    new TextMessage("", -1),
                    new ClientLeftMessage(-1)
            );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        assert messageTypesList.size() == AMessageEnumerator.getCount();
        messageTypes = messageTypesList
                .stream()
                .collect(Collectors.toMap(
                        AMessage::getType,
                        Function.identity()
                ));
    }

    public static AMessage parseMessage(InputStream stream) throws IOException {
        int type = stream.read();
        if (type == -1)
            return new ConnectionClosedMessage();
        var messageType = messageTypes.get((byte) type);
        try {
            return messageType.consume(stream);

        } catch (EOFException e) {
            return new ConnectionClosedMessage();
        }
    }
}
