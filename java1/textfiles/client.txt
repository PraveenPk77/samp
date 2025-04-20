import java.io.*;
import java.net.*;
 class client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server.");

            // File path in Windows
            File file = new File("C:\\java1\\cat\\lab.txt");  
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }

            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.writeUTF(line);
            }

            out.writeUTF("END"); // Indicate end of file
            fileReader.close();
            out.close();
            socket.close();

            System.out.println("File sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
