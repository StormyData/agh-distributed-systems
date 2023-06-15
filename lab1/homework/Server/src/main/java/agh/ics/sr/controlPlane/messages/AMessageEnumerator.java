package agh.ics.sr.controlPlane.messages;

import agh.ics.sr.controlPlane.messages.messageTypes.*;

import java.util.List;

public class AMessageEnumerator {
    private static final List<Class<? extends AMessage>> messageTypes = List.of(
            ServerHelloMessage.class,
            ConnectionClosedMessage.class,
            ClientJoinedMessage.class,
            ClientLeftMessage.class,
            TextMessage.class
    );

    public static byte getMessageType(Class<? extends AMessage> message) {
        return (byte) messageTypes.indexOf(message);
    }

    public static int getCount() {
        return messageTypes.size();
    }

}
