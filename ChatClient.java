import java.util.concurrent.ArrayBlockingQueue;

public class ChatClient {
	public static  ClientListener clientListener;
	public static  ServerListener serverListener;
	public static ArrayBlockingQueue<String> messageQueue;
	
	public static void main(String[] args){
		messageQueue = new ArrayBlockingQueue<String>(10);
		MainGUI.startGui();
	}
}
