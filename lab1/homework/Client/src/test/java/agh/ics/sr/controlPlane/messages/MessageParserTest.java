package agh.ics.sr.controlPlane.messages;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.AMessageEnumerator;
import agh.ics.sr.controlPlane.messages.MessageParser;
import agh.ics.sr.controlPlane.messages.messageTypes.ClientJoinedMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.ConnectionClosedMessage;
import agh.ics.sr.controlPlane.messages.messageTypes.TextMessage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class MessageParserTest {

    @Test
    void parseMessage() {

        byte[] data = new byte[]
                {
                        AMessageEnumerator.getMessageType(TextMessage.class), 0, 0, 0, 1, 0, 0, 0, 4, 'A', 'B', 'C', 'd'
                };
        InputStream stream = new ByteArrayInputStream(data);
        AtomicReference<AMessage> result = new AtomicReference<>();
        assertDoesNotThrow(() -> result.set(MessageParser.parseMessage(stream)));
        assertInstanceOf(TextMessage.class, result.get());
        TextMessage unpacked = (TextMessage) result.get();
        assertEquals(1, unpacked.getClientId());
        assertEquals("ABCd", unpacked.getMessage());

        byte[] data2 = new byte[]{AMessageEnumerator.getMessageType(TextMessage.class), 0, 0, 0};
        InputStream stream2 = new ByteArrayInputStream(data2);
        AtomicReference<AMessage> result2 = new AtomicReference<>();
        assertDoesNotThrow(() -> result2.set(MessageParser.parseMessage(stream)));
        assertInstanceOf(ConnectionClosedMessage.class, result2.get());


        byte[] data3 = new byte[]
                {
                        AMessageEnumerator.getMessageType(ClientJoinedMessage.class), 0, 0, 0, 1};
        InputStream stream3 = new ByteArrayInputStream(data3);
        AtomicReference<AMessage> result3 = new AtomicReference<>();
        assertDoesNotThrow(() -> result3.set(MessageParser.parseMessage(stream3)));
        assertInstanceOf(ClientJoinedMessage.class, result3.get());
        ClientJoinedMessage unpacked3 = (ClientJoinedMessage) result3.get();
        assertEquals(1, unpacked3.getClientId());
    }
}