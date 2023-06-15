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

class TextMessageTest {
    private static final byte expectedType = AMessageEnumerator.getMessageType(TextMessage.class);

    @Test
    void isForClient() {
        TextMessage message = new TextMessage("", -1);
        assertTrue(message.isForClient());
    }

    @Test
    void isForServer() {
        TextMessage message = new TextMessage("", -1);
        assertTrue(message.isForServer());
    }

    @Test
    void getType() {
        TextMessage message = new TextMessage("", -1);
        assertEquals(expectedType, message.getType());
    }

    @Test
    void consume() {
        TextMessage message = new TextMessage("", -1);
        byte[] data = new byte[]{0, 0, 0, 1, 0, 0, 0, 4, 'A', 'B', 'C', 'd'};
        InputStream stream = new ByteArrayInputStream(data);
        AtomicReference<AMessage> result = new AtomicReference<>();
        assertDoesNotThrow(() -> result.set(message.consume(stream)));
        assertInstanceOf(TextMessage.class, result.get());
        TextMessage unpacked = (TextMessage) result.get();
        assertEquals(1, unpacked.getClientId());
        assertEquals("ABCd", unpacked.getMessage());

        byte[] data2 = new byte[]{0, 0, 0};
        InputStream stream2 = new ByteArrayInputStream(data2);
        assertThrows(EOFException.class, () -> message.consume(stream2));
        byte[] data3 = new byte[]{0, 0, 0, 1, 0, 0, 0, 4, 'A'};
        InputStream stream3 = new ByteArrayInputStream(data3);
        assertThrows(EOFException.class, () -> message.consume(stream3));
    }

    @Test
    void produce() {
        TextMessage message = new TextMessage("DEADBEEF", 5);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> message.produce(stream));
        assertArrayEquals(new byte[]{expectedType, 0, 0, 0, 5, 0, 0, 0, 8, 'D', 'E', 'A', 'D', 'B', 'E', 'E', 'F'},
                stream.toByteArray());
    }
}