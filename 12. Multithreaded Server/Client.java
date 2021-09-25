import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	public static void main(String[] args) throws IOException
	{
		var s=new Socket("127.0.0.1",6666);
		var dout= new DataOutputStream(s.getOutputStream());
		var din=new DataInputStream(s.getInputStream());
		var scan=new Scanner(System.in);
		while(true)
		{
			String strrecived=din.readUTF();
			System.out.println("Server Messaged:"+strrecived);
			System.out.println("Write Your Message");
			String strtosend=scan.nextLine();
			dout.writeUTF(strtosend);
			dout.flush();
			if(strtosend.equals("Exit"))
			{
				System.out.println("Client "+s+" is exiting");
				s.close();
				din.close();
				dout.close();
				break;
			}
		}
	}
}