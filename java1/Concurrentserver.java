import java.net.*;
import java.io.*;

class Concurrentserver{

public static void main(String args[])throws Exception{
ServerSocket ss=null;
try { 
ss=new ServerSocket(3333);
ss.setReuseAddress(true); 

    while (true) { 

	Socket s=ss.accept();

	System.out.println("New client connected" + s.getInetAddress().getHostAddress()); 

 	/******create a new thread object ******************/
        ClientHandler clientSock = new ClientHandler(s); 
  
        /******This thread will handle the client separately*****/ 
        new Thread(clientSock).start(); 
           
    } 
}catch (IOException e) { 
            e.printStackTrace(); 
} 
finally { 
      if (ss != null) { 
            try { 
                    ss.close(); 
                } 
                catch (IOException e) { 
                    e.printStackTrace(); 
                } 
        } 
      } 
} 
}


class ClientHandler implements Runnable { 
        private final Socket clientSocket; 
  
        // Constructor 
        public ClientHandler(Socket socket) 
        { 
            this.clientSocket = socket; 
        } 
  
        public void run() 
        { 
           DataInputStream din=null;
	   DataOutputStream dout=null;
           BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            try { 
                    
                din=new DataInputStream(clientSocket.getInputStream());
		dout=new DataOutputStream(clientSocket.getOutputStream());
  		
               	while(true)
			{
			 	String name=din.readUTF();
			 	System.out.println("the name is"+name);
			 	String age=din.readUTF();
                                int age1=Integer.parseInt(age);
                                System.out.println(age1+1);
			 	System.out.println("the age is"+age);
			 	System.out.println("DO YOU WANT TO STOP \n yes for stop \n no");
			 	String con=br.readLine();
			 	if(con.equalsIgnoreCase("YES"))
			 	{
			 		break;
			 	}
                         
			 	System.out.println("enter hi to client");
			 	String message="hi";
			 	dout.writeUTF(message);
                     
			}
		
             dout.flush();
             }
            
            catch (IOException e) { 
                e.printStackTrace(); 
            } 
            finally { 
                try { 
                    if (dout != null) { 
                        dout.close(); 
                    } 
                    if (din != null) { 
                        din.close(); 
                        clientSocket.close(); 
                    } 
                } 
                catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
    } 
