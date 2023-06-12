import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class httpserver21mis1055 {
    public static void main(String[] args) {
        int port = 1234; // Port number to listen on

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread to handle the client request
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Read the request from the client
            String request = reader.readLine();
            System.out.println("Request: " + request);

            // Send the response headers
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("\r\n");
            writer.flush();

            // Send the response body
            writer.write("<h1>Hello, KEERTHI AJ</h1>");
            writer.flush();

            // Close the connections
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
