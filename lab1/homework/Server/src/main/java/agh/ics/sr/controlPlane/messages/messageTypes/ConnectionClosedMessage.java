package agh.ics.sr.controlPlane.messages.messageTypes;

import agh.ics.sr.controlPlane.messages.AMessage;

import java.io.IOException;
import java.io.InputStream;

public class ConnectionClosedMessage extends AMessage {
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
        return new ConnectionClosedMessage();
    }
}
