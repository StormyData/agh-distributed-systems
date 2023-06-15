package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.AMessageEnumerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionClosedMessageTest {
    private static final byte expectedType = AMessageEnumerator.getMessageType(ConnectionClosedMessage.class);

    @Test
    void isForClient() {
        var message = new ConnectionClosedMessage();
        assertTrue(message.isForClient());
    }

    @Test
    void isForServer() {
        var message = new ConnectionClosedMessage();
        assertTrue(message.isForServer());
    }

    @Test
    void getType() {
        var message = new ConnectionClosedMessage();
        assertEquals(expectedType, message.getType());
    }

    @Test
    void consume() {
        var message = new ConnectionClosedMessage();
        byte[] data = new byte[]{};
        InputStream stream = new ByteArrayInputStream(data);
        AtomicReference<AMessage> result = new AtomicReference<>();
        assertDoesNotThrow(() -> result.set(message.consume(stream)));
        assertInstanceOf(ConnectionClosedMessage.class, result.get());
    }

    @Test
    void produce() {
        var message = new ConnectionClosedMessage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> message.produce(stream));
        assertArrayEquals(new byte[]{expectedType}, stream.toByteArray());
    }
}