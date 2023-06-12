import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.CRC32;

public class checksumserver21mis1055 {
    public static void main(String[] args) {
        int port = 12345;

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client connection...");

            // Accept incoming client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input and output streams for the socket
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Receive the message and checksum from the client
            String receivedMessage = inputStream.readUTF();
            long receivedChecksum = inputStream.readLong();

            // Calculate the checksum of the received message
            CRC32 checksumCalculator = new CRC32();
            checksumCalculator.update(receivedMessage.getBytes());
            long calculatedChecksum = checksumCalculator.getValue();

            // Check if the received checksum matches the calculated checksum
            boolean isChecksumValid = (receivedChecksum == calculatedChecksum);

            // Send the checksum validation result back to the client
            outputStream.writeBoolean(isChecksumValid);
            outputStream.flush();

            // Close the socket and streams
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
