public class 21mis1055HammingDistance {

    public static void main(String[] args) {
        String originalData = "1100101"; // Original data
        String receivedData = "1110101"; // Received data with errors

        // Calculate the Hamming distance
        int hammingDistance = calculateHammingDistance(originalData, receivedData);

        System.out.println("Hamming Distance: " + hammingDistance);
    }

    private static int calculateHammingDistance(String originalData, String receivedData) {
        if (originalData.length() != receivedData.length()) {
            throw new IllegalArgumentException("Data lengths are not equal.");
        }

        int distance = 0;
        for (int i = 0; i < originalData.length(); i++) {
            if (originalData.charAt(i) != receivedData.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }
}
