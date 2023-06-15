import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JavaUdpServerNumbers {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");



        int portNumber = 9008;
        try (DatagramSocket socket = new DatagramSocket(portNumber)) {
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                Arrays.fill(receiveBuffer, (byte) 0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                int received = ByteBuffer
                        .wrap(receivePacket.getData(),receivePacket.getOffset(), receivePacket.getLength())
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .getInt();
                System.out.printf("Received %d from %s, %d\n", received, receivePacket.getAddress(), receivePacket.getPort());

                int to_send = received + 1;

                System.out.printf("Sending %d to %s, %d\n",to_send,receivePacket.getAddress(), receivePacket.getPort());
                byte[] responseBuffer = ByteBuffer
                        .allocate(4)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putInt(to_send)
                        .array();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, receivePacket.getAddress(), receivePacket.getPort());

                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
