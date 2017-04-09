import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JTextArea;

public class ServerListener extends Thread{
	Socket socket;
	JTextArea chatBox;
	ArrayList<String> userList;
	public ServerListener(Socket socket, JTextArea chatBox){
		this.socket = socket;
		this.chatBox = chatBox;
		userList = new ArrayList<String>();
		userList.add("Bot");
	}
	
	public void run(){
		try{
			InputStream is = socket.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	        BufferedReader br = new BufferedReader(isr);
	        
	        while(true){
	        	String message = br.readLine();
	        	if (message == null){
	        		break;
	        	}
	        	chatBox.append(message + "\n");
	        	if(message.matches("\\[.{11}\\] .* connected\\.")){
	        		String name = message.substring(13).replaceAll(" connected.", "");
	        		userList.add(name);
	        		ChatClient.messageQueue.put("Hello " + name + ", welcome!");
	        	}
	        	if(message.matches("\\[.{11}\\] .* disconnected\\.")){
	        		String name = message.substring(13).replaceAll(" disconnected.", "");
	        		userList.remove(name);
	        	}
	        	if(message.contains("!help")){
	        		ChatClient.messageQueue.put("! + {time,users,creator,why}");
	        	}
	        	if(message.contains("!time")){
	        		DateFormat df = new SimpleDateFormat("hh:mm aaa");
	        		df.setTimeZone(TimeZone.getDefault());
	        		ChatClient.messageQueue.put("The current time is: " + df.format(new Date()));
	        	}
	        	if(message.contains("!users")){
	        		ChatClient.messageQueue.put("Current active users: " + String.join(",", userList));
	        	}
	        	if(message.contains("!creator")){
	        		ChatClient.messageQueue.put("My creator is Carson.");
	        	}
	        	if(message.contains("!why")){
	        		ChatClient.messageQueue.put("Carson was bored and it was easy to make me.");
	        	}
	        }
	        socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
