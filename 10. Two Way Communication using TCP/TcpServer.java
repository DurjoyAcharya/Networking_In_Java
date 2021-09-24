import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

class TcpServer{
	public static void main(String[] args) throws IOException
	{
		var ss=new ServerSocket(5656);
		var s=ss.accept();
		System.out.println("Client Connected....");
		var scan=new Scanner(System.in);
		var dout=new DataOutputStream(s.getOutputStream());
		var dis=new DataInputStream(s.getInputStream());
		while(true)
		{
			String str=dis.readUTF();
			System.out.println("Client--->"+str);
			if(str.equals("bye"))
			{
				System.out.println("Client Messaged .... BYE..Exiting");
				dis.close();
				s.close();
				ss.close();
				break;
			}
			System.out.println("Enter your message:");
			String str1=scan.nextLine();
			dout.writeUTF(str1);
			dout.flush();
			dout.close();
		}
	}
}