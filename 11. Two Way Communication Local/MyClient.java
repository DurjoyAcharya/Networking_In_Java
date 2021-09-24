

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient{
	public static void main(String[] args)throws IOException {
	var s=new Socket("localhost",6666);
	var dout=new DataOutputStream(s.getOutputStream());
	var din=new DataInputStream(s.getInputStream());
	var scan=new Scanner(System.in);
	while(true)
	{
		System.out.println("Enter Your message.");
		String str=scan.nextLine();
		dout.writeUTF(str);
		dout.flush();
		if(str.equals("bye"))
		{
			dout.close();
			s.close();
			break;
		}
		String str1=(String)din.readUTF();
		System.out.println("Server--->"+str1);
		}
	}
}