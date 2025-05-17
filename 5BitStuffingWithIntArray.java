import java.util.Scanner;

public class BitStuffingWithIntArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of bits: ");
        int n = scanner.nextInt();

        int[] inputData = new int[n];
        System.out.print("Enter binary data (space-separated): ");
        for (int i = 0; i < n; i++) {
            inputData[i] = scanner.nextInt();
        }

        int[] stuffedData = new int[2 * n];
        int j = 0, count = 0;
        String FLAG = "01111110";

        // Bit stuffing
        for (int i = 0; i < n; i++) {
            stuffedData[j++] = inputData[i];
            if (inputData[i] == 1) {
                count++;
                if (count == 5) {
                    stuffedData[j++] = 0;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        System.out.print("Transmitted Frame: " + FLAG + " ");
        for (int i = 0; i < j; i++) {
            System.out.print(stuffedData[i] + " ");
        }
        System.out.println(FLAG);

        // Bit destuffing
        int[] receivedFrame = new int[j];
        System.arraycopy(stuffedData, 0, receivedFrame, 0, j);

        int[] destuffedData = new int[j];
        j = 0;
        count = 0;

        for (int i = 0; i < receivedFrame.length; i++) {
            destuffedData[j++] = receivedFrame[i];
            if (receivedFrame[i] == 1) {
                count++;
                if (count == 5 && i + 1 < receivedFrame.length && receivedFrame[i + 1] == 0) {
                    i++; // Skip stuffed 0
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        System.out.print("Received Data: ");
        for (int i = 0; i < j; i++) {
            System.out.print(destuffedData[i] + " ");
        }

        scanner.close();
    }
}
