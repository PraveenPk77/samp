
import java.io.*;

import java.net.*;
public class server1 
{ 
	public static void main(String args[])
	{
		try {
			System.out.println("server started");
			ServerSocket ss=new ServerSocket(9999);
			Socket soc=ss.accept();
			System.out.println("client connected");
			DataInputStream din=new DataInputStream(soc.getInputStream());
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
			 	String name=din.readUTF();
			 	System.out.println("the name is"+name);
			 	String age=din.readUTF();
                                int age1=Integer.parseInt(age);
                                System.out.println(age1+1);
			 	System.out.println("the age is"+age);
			 	System.out.println("DO YOU WANT TO STOP \n yes for stop \n no");
			 	String con=in.readLine();
			 	if(con.equalsIgnoreCase("YES"))
			 	{
			 		break;
			 	}
                         
			 	System.out.println("enter hi to client");
			 	String message="hi";
			 	dout.writeUTF(message);
                     
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
