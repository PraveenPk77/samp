import java.net.*;
import java.io.*;
class Client2{
public static void main(String args[])throws Exception{
Socket s=new Socket("localhost",3333);
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
while(true)
{
 System.out.println("Enter name:");
 String name=br.readLine();
System.out.println("enter age");
String age=br.readLine();
dout.writeUTF(name);
dout.writeUTF(age);
String message=din.readUTF();
System.out.println(message);
				
 }
}}