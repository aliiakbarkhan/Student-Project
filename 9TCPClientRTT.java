import java.io.*;
import java.net.Socket;

public class TCPClientRTT {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream output = socket.getOutputStream()) {

            System.out.println("Connected to server. Type messages (type 'exit' to quit):");
            String message;

            while (!(message = userInput.readLine()).equalsIgnoreCase("exit")) {
                long startTime = System.nanoTime();
                output.write(message.getBytes());
                output.flush();

                char[] buffer = new char[1024];
                int charsRead = serverInput.read(buffer);
                String response = new String(buffer, 0, charsRead);

                long endTime = System.nanoTime();
                double rtt = (endTime - startTime) / 1_000_000.0;

                System.out.println("Server replied: " + response.trim());
                System.out.printf("RTT: %.2f ms%n", rtt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
