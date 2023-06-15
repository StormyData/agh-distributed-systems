package agh.ics.sr.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ClientInputPlane extends Thread implements ClientDataPlane.IDataPlaneListener, ClientMulticastPlane.IMulticastPlaneListener {
    private final IClientControlPlane controlPlane;
    private final IClientDataPlane dataPlane;
    private final IClientDataPlane multicastPlane;

    public ClientInputPlane(IClientControlPlane controlPlane, IClientDataPlane dataPlane, IClientDataPlane multicastPlane){
        this.controlPlane = controlPlane;
        this.dataPlane = dataPlane;
        this.multicastPlane = multicastPlane;
    }
    @Override
    public void run() {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String message = stdin.readLine();
                String cutMessage = message.substring(1);
                switch (message.charAt(0)) {
                    case 'C' -> controlPlane.sendText(cutMessage);
                    case 'U' -> dataPlane.sendData(cutMessage.getBytes(StandardCharsets.UTF_8));
                    case 'M' -> multicastPlane.sendData(cutMessage.getBytes(StandardCharsets.UTF_8));
                    default -> System.out.println("Unknown command prefix");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onDataReceived(int fromClientId, byte[] data) {
        System.out.printf("U %d: >> %s <<\n", fromClientId, new String(data, StandardCharsets.UTF_8));
    }

    @Override
    public void onMulticastReceived(int fromClientId, byte[] data) {
        System.out.printf("M %d: >> %s <<\n", fromClientId, new String(data, StandardCharsets.UTF_8));
    }
}
