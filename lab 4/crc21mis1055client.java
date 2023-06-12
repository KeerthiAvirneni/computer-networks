import java.util.Scanner;

public class crc21mis1055client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the received data array: ");
        int size = scanner.nextInt();

        int[] receivedData = new int[size];
        System.out.println("Enter received data bits in the array one by one: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter bit " + (size - i) + ": ");
            receivedData[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the divisor array: ");
        size = scanner.nextInt();

        int[] divisor = new int[size];
        System.out.println("Enter divisor bits in the array one by one: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter bit " + (size - i) + ": ");
            divisor[i] = scanner.nextInt();
        }

        receiveData(receivedData, divisor);
    }

    static void receiveData(int[] data, int[] divisor) {
        int[] rem = divideDataWithDivisor(data, divisor);
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("Corrupted data received...");
                return;
            }
        }
        System.out.println("Data received without any error.");
    }

    static int[] divideDataWithDivisor(int[] oldData, int[] divisor) {
        int[] rem = new int[divisor.length];
        int i;
        int[] data = new int[oldData.length + divisor.length];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        System.arraycopy(data, 0, rem, 0, divisor.length);

        for (i = 0; i < oldData.length; i++) {
            if (rem[0] == 1) {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = exorOperation(rem[j], divisor[j]);
                }
            } else {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = exorOperation(rem[j], 0);
                }
            }
            rem[divisor.length - 1] = data[i + divisor.length];
        }
        return rem;
    }

    static int exorOperation(int x, int y) {
        if (x == y) {
            return 0;
        }
        return 1;
    }
}
