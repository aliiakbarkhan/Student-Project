import java.net.*;

public class Hclientudp {
    public static void main(String args[]) {
        final String serverAddress = "localhost";
        final int serverPort = 9876;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            String message = "Hello, Server from client 1";
            byte[] sendData = message.getBytes();

            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);
            clientSocket.send(sendPacket);

            System.out.println("Message sent to server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
