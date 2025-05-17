import java.util.Scanner;

public class Hammingc {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of data bits: ");
        int m = sc.nextInt();
        int r = 0;

        while (Math.pow(2, r) < (m + r + 1)) {
            r++;
        }

        int totalbits = m + r;
        int[] hammingcode = new int[totalbits + 1];

        System.out.print("Enter " + m + " data bits: ");
        for (int i = 1, j = 0; i <= totalbits; i++) {
            if ((i & (i - 1)) == 0) {
                hammingcode[i] = 0; // Placeholder for parity bit
            } else {
                hammingcode[i] = sc.nextInt(); // Data bit
            }
        }

        // Calculate parity bits
        for (int i = 1; i <= totalbits; i *= 2) {
            int parity = 0;
            for (int k = i; k <= totalbits; k += 2 * i) {
                for (int p = k; p < k + i && p <= totalbits; p++) {
                    if (p != i) {
                        parity ^= hammingcode[p];
                    }
                }
            }
            hammingcode[i] = parity;
        }

        System.out.print("Generated Hamming code: ");
        for (int i = 1; i <= totalbits; i++) {
            System.out.print(hammingcode[i] + " ");
        }
    }
}
