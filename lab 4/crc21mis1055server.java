import java.util.Scanner;
public class crc21mis1055server {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the data array: ");
        int size = scanner.nextInt();

        int[] data = new int[size];
        System.out.println("Enter data bits in the array one by one: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter bit " + (size - i) + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the divisor array: ");
        size = scanner.nextInt();

        int[] divisor = new int[size];
        System.out.println("Enter divisor bits in the array one by one: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter bit " + (size - i) + ": ");
            divisor[i] = scanner.nextInt();
        }

        int[] crc = divideDataWithDivisor(data, divisor);
        System.out.println("\nGenerated CRC code is: ");
        printArray(data);
        printArray(crc);
    }

    static int[] divideDataWithDivisor(int[] oldData, int[] divisor) {
        int[] rem = new int[divisor.length];
        int i;
        int[] data = new int[oldData.length + divisor.length];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        System.arraycopy(data, 0, rem, 0, divisor.length);

        for (i = 0; i < oldData.length; i++) {
            System.out.println((i + 1) + ".) First data bit is: " + rem[0]);
            System.out.print("Remainder: ");
            if (rem[0] == 1) {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = exorOperation(rem[j], divisor[j]);
                    System.out.print(rem[j - 1]);
                }
            } else {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = exorOperation(rem[j], 0);
                    System.out.print(rem[j - 1]);
                }
            }
            rem[divisor.length - 1] = data[i + divisor.length];
            System.out.println(rem[divisor.length - 1]);
        }
        return rem;
    }

    static int exorOperation(int x, int y) {
        if (x == y) {
            return 0;
        }
        return 1;
    }

    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num);
        }
    }
}
