package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ClientJoinedMessage extends AMessage {
    private final int clientId;
    private final ByteBuffer buffer;

    public ClientJoinedMessage(int clientId) {
        this.clientId = clientId;
        buffer = ByteBuffer
                .allocate(5)
                .order(ByteOrder.BIG_ENDIAN)
                .put(0, type)
                .putInt(1, clientId);
    }

    @Override
    public boolean isForClient() {
        return true;
    }

    @Override
    public AMessage consume(InputStream stream) throws IOException {
        byte[] buff = stream.readNBytes(4);
        if (buff.length != 4) {
            throw new EOFException();
        }
        int clientId = ByteBuffer
                .wrap(buff)
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
        return new ClientJoinedMessage(clientId);
    }

    @Override
    public void produce(OutputStream stream) throws IOException {
        stream.write(buffer.array());
    }

    public int getClientId() {
        return clientId;
    }
}
