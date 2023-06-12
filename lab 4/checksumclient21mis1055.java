import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.zip.CRC32;

public class checksumclient21mis1055 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try {
            // Create a client socket
            Socket clientSocket = new Socket(host, port);
            System.out.println("Connected to server.");

            // Create input and output streams for the socket
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Create a message to send
            String message = "Hello, department in-charge!";

            // Calculate the checksum of the message
            CRC32 checksumCalculator = new CRC32();
            checksumCalculator.update(message.getBytes());
            long checksum = checksumCalculator.getValue();

            // Send the message and checksum to the server
            outputStream.writeUTF(message);
            outputStream.writeLong(checksum);
            outputStream.flush();

            // Receive the checksum validation result from the server
            boolean isChecksumValid = inputStream.readBoolean();

            // Check if the checksum validation result is true
            if (isChecksumValid) {
                System.out.println("Message transmitted without data loss.");
            } else {
                System.out.println("Data loss detected during transmission.");
            }

            // Close the socket and streams
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
