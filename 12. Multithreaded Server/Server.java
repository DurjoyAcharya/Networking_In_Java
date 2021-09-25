import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public static void main(String[] args) throws IOException
	{
		var ss=new ServerSocket(6666);
		while(true)
		{
			var s=ss.accept();
			System.out.println("A new client is connected via "+s.getInetAddress());
			var dis=new DataInputStream(s.getInputStream());
			var dout=new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Enter the name of the client");
			String name=dis.readUTF();
			System.out.println("Assigning a new thread to the client");
			var obj=new ClientHandler();
			obj.setThread(s,name,dis,dout);
			new Thread(obj::run).start();
		}
	}
}
class ClientHandler implements Runnable{
	Socket s;
	DataInputStream dis;
	DataOutputStream dout;
	String strrecieved="",strsent="",name;
	public void setThread(Socket s,String name,DataInputStream dis,DataOutputStream dout)
	{
		this.s=s;
		this.dis=dis;
		this.dout=dout;
		this.name=name;
	}
	public void run() 
	{
		try
		{
			while(true)
			{
				dout.writeUTF("What is your name? Written already write Exit.");
				strrecieved=dis.readUTF();
				if(strrecieved.equals("Exit"))
				{
					System.out.println("Client "+this.s+" EXITED");
					this.s.close();
					this.dis.close();
					this.dout.close();
					break;
				}
				System.out.println("Client Messaged "+strrecieved);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}