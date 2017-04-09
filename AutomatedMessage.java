
public class AutomatedMessage extends Thread{
	public int times = 0;
	public void run(){
		while(true){
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				ChatClient.messageQueue.put("I am a bot. Type ! + help to see options.");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
		}
	}
}
