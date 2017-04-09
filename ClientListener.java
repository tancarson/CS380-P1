import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientListener extends Thread{
	Socket socket;
	public ClientListener(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			OutputStream os = socket.getOutputStream();
	        PrintStream out = new PrintStream(os, true, "UTF-8");
	        
	        while(true){
	        	String message = ChatClient.messageQueue.take();
	        	if (message.equals("exit")) break;
	        	out.println(message);
	        	System.out.println("Message sent: " + message);
	        }
	        socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
