
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer{
	public static void main(String[] args) throws IOException
	{
		var ss=new ServerSocket(6666);
		var s=ss.accept();
		var dis=new DataInputStream(s.getInputStream());
		var dout=new DataOutputStream(s.getOutputStream());
		var scan=new Scanner(System.in);
		while(true)
		{
			String str=(String)dis.readUTF();
			System.out.println("Client--->"+str);
			if(str.equals("bye"))
			{
				System.out.println("Client Said Bye exiting");
				dis.close();
				dout.close();
				s.close();
				ss.close();
				break;
			}
			System.out.println("Enter Your Message");
			String str1=scan.nextLine();
			dout.writeUTF(str1);
			dout.flush();	
		}
	}
}