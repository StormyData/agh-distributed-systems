package agh.ics.sr.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public interface IClientControlPlane {
    Integer getClientId();

    void sendText(String text) throws IOException;

    SocketAddress getLocalSockAddr();

    SocketAddress getRemoteSockAddr();

    InetSocketAddress getMulticastSockAddr();
}
