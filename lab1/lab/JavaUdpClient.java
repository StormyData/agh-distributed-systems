import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class JavaUdpClient {

    public static void main(String args[]) throws Exception
    {
        System.out.println("JAVA UDP CLIENT");

        byte[] recvBuffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(recvBuffer, recvBuffer.length);

        int portNumber = 9008;
        int clientPortNumber = 9009;
        try (DatagramSocket socket = new DatagramSocket(clientPortNumber)) {
            InetAddress address = InetAddress.getByName("localhost");
            byte[] sendBuffer = "Ping Java Udp".getBytes(StandardCharsets.UTF_8);

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
            socket.send(sendPacket);

            socket.receive(responsePacket);
            byte[] data = new byte[responsePacket.getLength()];
            System.arraycopy(responsePacket.getData(), responsePacket.getOffset(), data, 0, responsePacket.getLength());
            String msg = new String(data, StandardCharsets.UTF_8);
            System.out.println("received response: " + msg);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
