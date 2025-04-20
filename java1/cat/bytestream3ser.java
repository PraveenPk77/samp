package bytestrweam;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Server is running... Waiting for clients...");
            ServerSocket ss = new ServerSocket(9988);

            while (true) { // Keep accepting multiple clients
                Socket soc = ss.accept();
                System.out.println("Client connected!");

                // Handle each client in a new thread
                ClientHandler clientThread = new ClientHandler(soc);
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Thread class to handle multiple clients
class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Input and Output Streams
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // Read data from client
            byte[] buffer = new byte[1024];
            int receivedLen = in.read(buffer);
            String receivedMsg = new String(buffer, 0, receivedLen);

            // Split received data (Assuming format: Name|Age|BloodGroup)
            String[] details = receivedMsg.split("\\|");
            String name = details[0];
            String age = details[1];
            String bloodGroup = details[2];

            System.out.println("Received from client:");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Blood Group: " + bloodGroup);

            // Send acknowledgment message
            String reply = "Server received your details, " + name;
            out.write(reply.getBytes());
            System.out.println("Reply sent to client.");

            // Closing resources
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
