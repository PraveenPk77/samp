import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Student {
    int rollNo;
    String name;
    int cgpa;
    String department;

    Student(int rollNo, String name, int cgpa, String department) {
        this.rollNo = rollNo;
        this.name = name;
        this.cgpa = cgpa;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", CGPA: " + cgpa + ", Dept: " + department;
    }
}

public class txt1server {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for a client...");

            // Accept only one client, then terminate
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(socket.getInputStream());

            String line;
            while (!(line = in.readUTF()).equals("END")) {
                String[] parts = line.trim().split("\\s+"); // Correctly handle spaces

                if (parts.length == 4) { // Ensures exactly 4 fields (RollNo Name CGPA Department)
                    try {
                        int rollNo = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int cgpa = Integer.parseInt(parts[2]);
                        String department = parts[3];

                        students.add(new Student(rollNo, name, cgpa, department));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid data (Number Format Error): " + line);
                    }
                } else {
                    System.out.println("Incorrect format: " + line);
                }
            }

            // Print received data
            System.out.println("\n Received Student Data:");
            for (Student s : students) {
                System.out.println(s);
            }

            // 🔹 Declare count variables before using them
            int mechCount = 0, itCount = 0, civilCount = 0, eceCount = 0, cseCount = 0;

            // 🔹 Switch case inside loop to count students per department
            for (Student s : students) {
                switch (s.department.toUpperCase()) {
                    case "MECH": {
                        mechCount++;
                        System.out.println(s.name + " is from Mechanical Engineering.");
                        break;
                    }
                    case "IT": {
                        itCount++;
                        System.out.println(s.name + " is from Information Technology.");
                        break;
                    }
                    case "CIVIL": {
                        civilCount++;
                        System.out.println(s.name + " is from Civil Engineering.");
                        break;
                    }
                    case "ECE": {
                        eceCount++;
                        System.out.println(s.name + " is from Electronics & Communication Engineering.");
                        break;
                    }
                    case "CSE": {
                        cseCount++;
                        System.out.println(s.name + " is from Computer Science Engineering.");
                        break;
                    }
                    default: {
                        System.out.println("Unknown department: " + s.department);
                    }
                }
            }

            // 🔹 Print department count
            System.out.println("\n Student Count per Department:");
            System.out.println("MECH: " + mechCount);
            System.out.println("IT: " + itCount);
            System.out.println("CIVIL: " + civilCount);
            System.out.println("ECE: " + eceCount);
            System.out.println("CSE: " + cseCount);

            // 🔹 Print Students with CGPA > 8
            System.out.println("\n Students with CGPA Above 8:");
            for (Student s : students) {
                if (s.cgpa > 8) {
                    System.out.println("Roll No: " + s.rollNo);
                    System.out.println("Name: " + s.name);
                    System.out.println("CGPA: " + s.cgpa);
                    System.out.println("Department: " + s.department);
                    System.out.println("------------------------"); // Separator for clarity
                }
            }

            // Close connections
            in.close();
            socket.close();
            serverSocket.close(); // Ensure the server stops
            System.out.println(" Server terminated after processing one client.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
