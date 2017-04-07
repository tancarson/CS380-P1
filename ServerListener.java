import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ServerListener extends Thread{
	Socket socket;
	JTextArea chatBox;
	public ServerListener(Socket socket, JTextArea chatBox){
		this.socket = socket;
		this.chatBox = chatBox;
	}
	
	public void run(){
		try{
			InputStream is = socket.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	        BufferedReader br = new BufferedReader(isr);
	        
	        while(true){
	        	String message = br.readLine();
	        	if (message == null) break;
	        	chatBox.append(message + "\n");
	        }
	        socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
