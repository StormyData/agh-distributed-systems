package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ServerHelloMessage extends AMessage {
    private final int clientId;
    private final InetSocketAddress multicastAddress;
    private final ByteBuffer buffer;

    public ServerHelloMessage(int clientId, InetSocketAddress multicastAddress) {
        boolean isIpv4 = multicastAddress.getAddress().getAddress().length == 4;
        buffer = ByteBuffer
                .allocate(5 + 1 + 4 +  (isIpv4 ? 4 : 16))
                .order(ByteOrder.BIG_ENDIAN)
                .put(0, type)
                .putInt(1, clientId)
                .put(5, (byte) (isIpv4 ? 1 : 0))
                .putInt(6, multicastAddress.getPort())
                .put(10, multicastAddress.getAddress().getAddress());
        this.clientId = clientId;
        this.multicastAddress = multicastAddress;
    }


    @Override
    public boolean isForClient() {
        return true;
    }

    @Override
    public AMessage consume(InputStream stream) throws IOException {
        byte[] arr = stream.readNBytes(9);
        if (arr.length != 9)
            throw new EOFException();
        ByteBuffer buffer1 =ByteBuffer
                .wrap(arr)
                .order(ByteOrder.BIG_ENDIAN);
        int clientId = buffer1
                .getInt(0);
        byte isIpv4 = buffer1.get(4);
        int port = buffer1.getInt(5);

        int addrLen = isIpv4 == 1 ? 4 : 16;
        byte[] addrBuffer = stream.readNBytes(addrLen);
        if (addrBuffer.length != addrLen)
            throw new EOFException();


        InetAddress address = InetAddress.getByAddress(addrBuffer);
        InetSocketAddress multicastAddress = new InetSocketAddress(address, port);
        return new ServerHelloMessage(clientId, multicastAddress);
    }

    @Override
    public void produce(OutputStream stream) throws IOException {
        stream.write(buffer.array());
    }

    public int getClientId() {
        return clientId;
    }

    public InetSocketAddress getMulticastAddress() {
        return multicastAddress;
    }
}
