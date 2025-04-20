import java.io.*;
import java.net.*;
public class client1
{ 
	public static void main(String args[])
	{
		try {
			System.out.println("clinet connected");
			Socket soc=new Socket("localhost",3333);
			DataInputStream din=new DataInputStream(soc.getInputStream());
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				System.out.println("Enter name:");
				String name=in.readLine();
				System.out.println("enter age");
				String age=in.readLine();
				dout.writeUTF(name);
				dout.writeUTF(age);
				String message=din.readUTF();
				System.out.println(message);
				
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}