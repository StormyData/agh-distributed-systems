package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.AMessageEnumerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ClientLeftMessageTest {
    private static final byte expectedType = AMessageEnumerator.getMessageType(ClientLeftMessage.class);

    @Test
    void isForClient() {
        ClientLeftMessage message = new ClientLeftMessage(-1);
        assertTrue(message.isForClient());
    }

    @Test
    void isForServer() {
        ClientLeftMessage message = new ClientLeftMessage(-1);
        assertFalse(message.isForServer());
    }

    @Test
    void getType() {
        ClientLeftMessage message = new ClientLeftMessage(-1);
        assertEquals(expectedType, message.getType());
    }

    @Test
    void consume() {
        ClientLeftMessage message = new ClientLeftMessage(-1);
        byte[] data = new byte[]{0, 0, 0, 1};
        InputStream stream = new ByteArrayInputStream(data);
        AtomicReference<AMessage> result = new AtomicReference<>();
        assertDoesNotThrow(() -> result.set(message.consume(stream)));
        assertInstanceOf(ClientLeftMessage.class, result.get());
        ClientLeftMessage unpacked = (ClientLeftMessage) result.get();
        assertEquals(1, unpacked.getClientId());

        byte[] data2 = new byte[]{0, 0, 0};
        InputStream stream2 = new ByteArrayInputStream(data2);
        assertThrows(EOFException.class, () -> message.consume(stream2));
    }

    @Test
    void produce() {
        ClientLeftMessage message = new ClientLeftMessage(5);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> message.produce(stream));
        assertArrayEquals(new byte[]{expectedType, 0, 0, 0, 5}, stream.toByteArray());
    }
}