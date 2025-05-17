import java.util.Scanner;

public class Checksum {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first binary string: ");
        String a = sc.next();
        System.out.print("Enter second binary string: ");
        String b = sc.next();

        if (a.length() != b.length()) {
            System.out.println("Wrong input strings");
            return;
        }

        int length = a.length();
        char[] sum = new char[length];
        char carry = '0';

        // Binary addition
        for (int i = length - 1; i >= 0; i--) {
            int bitSum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + (carry - '0');
            sum[i] = (char) ((bitSum % 2) + '0');
            carry = (bitSum > 1) ? '1' : '0';
        }

        System.out.println("Sum = " + carry + String.valueOf(sum));

        // One's complement
        for (int i = 0; i < length; i++) {
            sum[i] = (sum[i] == '0') ? '1' : '0';
        }
        carry = (carry == '1') ? '0' : '1';

        System.out.println("Checksum = " + carry + String.valueOf(sum));
    }
}
