package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;
import agh.ics.sr.controlPlane.messages.AMessageEnumerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ServerHelloMessageTest {
    private static final byte expectedType = AMessageEnumerator.getMessageType(ServerHelloMessage.class);

    @Test
    void isForClient() throws UnknownHostException {
        ServerHelloMessage message = new ServerHelloMessage(-1, new InetSocketAddress(InetAddress.getByName("127.0.0.1"),5));
        assertTrue(message.isForClient());
    }

    @Test
    void isForServer() throws UnknownHostException {
        ServerHelloMessage message = new ServerHelloMessage(-1, new InetSocketAddress(InetAddress.getByName("127.0.0.1"),5));
        assertFalse(message.isForServer());
    }

    @Test
    void getType() throws UnknownHostException {
        ServerHelloMessage message = new ServerHelloMessage(-1, new InetSocketAddress(InetAddress.getByName("127.0.0.1"),5));
        assertEquals(expectedType, message.getType());
    }

    @Test
    void consume() throws UnknownHostException {
        ServerHelloMessage message = new ServerHelloMessage(-1,  new InetSocketAddress(InetAddress.getByName("127.0.0.1"),5));
        byte[] data = new byte[]{0, 0, 0, 1, 1, 0, 0, 0, 5, 127, 0, 0, 1};
        InputStream stream = new ByteArrayInputStream(data);
        AtomicReference<AMessage> result = new AtomicReference<>();
        assertDoesNotThrow(() -> result.set(message.consume(stream)));
        assertInstanceOf(ServerHelloMessage.class, result.get());
        ServerHelloMessage unpacked = (ServerHelloMessage) result.get();
        assertEquals(1, unpacked.getClientId());
        assertEquals(new InetSocketAddress(InetAddress.getByName("127.0.0.1"),5) , unpacked.getMulticastAddress());

        byte[] data2 = new byte[]{0, 0, 0};
        InputStream stream2 = new ByteArrayInputStream(data2);
        assertThrows(EOFException.class, () -> message.consume(stream2));
    }

    @Test
    void produce() throws UnknownHostException {
        ServerHelloMessage message = new ServerHelloMessage(5, new InetSocketAddress(InetAddress.getByName("129.0.0.1"),10));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> message.produce(stream));
        assertArrayEquals(new byte[]{expectedType, 0, 0, 0, 5, 1, 0, 0, 0, 10, (byte)129, 0, 0, 1}, stream.toByteArray());
    }
}