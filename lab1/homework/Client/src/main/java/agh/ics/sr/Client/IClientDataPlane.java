package agh.ics.sr.Client;

import java.io.IOException;

public interface IClientDataPlane {
    void sendData(byte[] data) throws IOException;
}
