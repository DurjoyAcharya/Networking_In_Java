import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient{
	public static void main(String[] args) throws IOException
	{
		var scan=new Scanner(System.in);
		var s=new Socket("localhost",5656);
		var dout=new DataOutputStream(s.getOutputStream());
		var dis=new DataInputStream(s.getInputStream());
		while(true)
		{
			System.out.println("Write Your message");
			String str=scan.nextLine();
			dout.writeUTF(str);
			dout.flush();
			if(str.equals("bye"))
			{
				dout.close();
				s.close();
				break;
			}
			String str1=(String)dis.readUTF();
			System.out.println("Server Messaged--->"+str1);
		}
	}
}