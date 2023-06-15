package agh.ics.sr.Server;

import agh.ics.sr.controlPlane.messages.AMessage;

public interface IServerControlPlane {
    void sendToAll(AMessage message);

    void sendToAllExcept(AMessage message, int clientId);

    void removeClient(int clientId);
}
