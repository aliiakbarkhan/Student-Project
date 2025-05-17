import java.util.Scanner;

public class CharacterStuffing {
    public static void main(String[] args) {
        final String FLAG = "Flag";
        final String ESC = "ESC";
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message to send: ");
        String message = sc.nextLine();

        // Byte (Character) stuffing
        String stuffedData = message.replace(ESC, ESC + ESC).replace(FLAG, ESC + FLAG);
        String framedData = FLAG + stuffedData + FLAG;

        System.out.println("Framed data: " + framedData);

        // Byte (Character) destuffing
        String receivedData = framedData.substring(FLAG.length(), framedData.length() - FLAG.length());
        String destuffedData = receivedData.replace(ESC + FLAG, FLAG).replace(ESC + ESC, ESC);

        System.out.print("Extracted data: " + destuffedData);

        sc.close();
    }
}
