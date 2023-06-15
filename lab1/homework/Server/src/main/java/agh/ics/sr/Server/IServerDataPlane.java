package agh.ics.sr.Server;

import java.net.SocketAddress;

public interface IServerDataPlane {

    void registerClient(int clientId, SocketAddress address);
    void deregisterClient(int clientId);
}
