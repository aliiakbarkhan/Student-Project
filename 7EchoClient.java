import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;

public class EchoClient {
    public static void main(String args[]) {
        String serverAddress = "localhost";
        int port = 12345;

        try (
            Socket socket = new Socket(serverAddress, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            for (int i = 0; i < 5; i++) {
                String message = "Ping" + i;
                Instant startTime = Instant.now();
                writer.println(message);
                String response = reader.readLine();
                Instant endTime = Instant.now();
                long rtt = Duration.between(startTime, endTime).toMillis();
                System.out.println("Received: " + response + " | RTT: " + rtt + "ms");
                Thread.sleep(1000); // 1 second delay
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
    }
}
