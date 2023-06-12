import java.util.Arrays;

public class reedsolomon21mis1055 {

    private static final int GF_SIZE = 256;

    private final int[] generator;
    private final int[] parity;

    public reedsolomon21mis1055(int[] generator, int[] parity) {
        this.generator = generator;
        this.parity = parity;
    }

    public int[] encode(int[] data) {
        int[] coefficients = new int[data.length + parity.length];
        System.arraycopy(data, 0, coefficients, 0, data.length);
        for (int i = 0; i < parity.length; i++) {
            for (int j = 0; j < data.length; j++) {
                coefficients[i + data.length] += generator[j] * data[i];
            }
        }
        return coefficients;
    }

    public int[] decode(int[] coefficients, int numErrors) {
        int[] data = new int[coefficients.length];
        for (int i = 0; i < data.length; i++) {
            int value = 0;
            for (int j = 0; j < coefficients.length; j++) {
                value += coefficients[j] ;
            }
            data[i] = value;
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] generator = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        int[] parity = {1, 1, 1, 1};

        reedsolomon21mis1055 reedSolomon = new reedsolomon21mis1055(generator, parity);
        int[] encodedData = reedSolomon.encode(data);

        // Corrupt some of the data
        encodedData[0] = 9;
        encodedData[1] = 10;

        int[] decodedData = reedSolomon.decode(encodedData, 2);

        System.out.println("Original data: " + Arrays.toString(data));
        System.out.println("Encoded data: " + Arrays.toString(encodedData));
        System.out.println("Decoded data: " + Arrays.toString(decodedData));
    }
}
