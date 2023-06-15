package agh.ics.sr.controlPlane.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AMessage {
    protected final byte type = AMessageEnumerator.getMessageType(this.getClass());

    public boolean isForClient() {
        return false;
    }

    public boolean isForServer() {
        return false;
    }

    public final byte getType() {
        return type;
    }

    public abstract AMessage consume(InputStream stream) throws IOException;

    public void produce(OutputStream stream) throws IOException {
        stream.write(type);
    }

}
