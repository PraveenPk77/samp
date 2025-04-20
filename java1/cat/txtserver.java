import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Student {
    String name;
    int cgpa;

    Student(String name, int cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }
    public String toString() {
        return "Name: " + name + ", CGPA: " + cgpa;
    }
}
class ClientHandler extends Thread {
    private Socket socket;
    private ArrayList<Student> students;

    public ClientHandler(Socket socket, ArrayList<Student> students) {
        this.socket = socket;
        this.students = students;
    }
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String line;
            while (!(line = in.readUTF()).equals("END")) {
                String[] parts = line.split(" ");
                if (parts.length == 4) { // Assuming format: RollNo Name CGPA Dept
               int rollNo = Integer.parseInt(parts[0]);
               String name = parts[1];
               int cgpa = Integer.parseInt(parts[2]);
                String dept = parts[3];

    synchronized (students) {
        students.add(new Student(rollNo, name, cgpa, dept));
    }
}

                    }
                }
            }

            System.out.println("Data received from client:");
            synchronized (students) {
                for (Student s : students) {
                    System.out.println(s);
                }
            }

            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class txtserver{
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected.");

                // Create a new thread for each client
                ClientHandler clientThread = new ClientHandler(socket, students);
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
