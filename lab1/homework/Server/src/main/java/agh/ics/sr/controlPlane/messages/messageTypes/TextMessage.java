package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class TextMessage extends AMessage {

    private final int clientId;
    private final String message;
    private final ByteBuffer buffer;

    public TextMessage(String message, int clientId) {
        this.message = message;
        this.clientId = clientId;
        byte[] encodedMessage = message.getBytes(StandardCharsets.UTF_8);
        buffer = ByteBuffer
                .allocate(9 + encodedMessage.length)
                .order(ByteOrder.BIG_ENDIAN)
                .put(0, type)
                .putInt(1, clientId)
                .putInt(5, encodedMessage.length)
                .put(9, encodedMessage);
    }

    @Override
    public boolean isForClient() {
        return true;
    }

    @Override
    public boolean isForServer() {
        return true;
    }

    @Override
    public AMessage consume(InputStream stream) throws IOException {
        byte[] sizeBytes = stream.readNBytes(8);
        if (sizeBytes.length != 8) {
            throw new EOFException();
        }
        var buff = ByteBuffer
                .wrap(sizeBytes)
                .order(ByteOrder.BIG_ENDIAN);
        int clientId = buff.getInt(0);
        int messageLength = buff.getInt(4);
        byte[] messageBytes = stream.readNBytes(messageLength);
        if (messageBytes.length != messageLength) {
            throw new EOFException();
        }
        return new TextMessage(new String(messageBytes, StandardCharsets.UTF_8), clientId);
    }

    @Override
    public void produce(OutputStream stream) throws IOException {
        stream.write(buffer.array());
    }

    public String getMessage() {
        return message;
    }

    public int getClientId() {
        return clientId;
    }
}
