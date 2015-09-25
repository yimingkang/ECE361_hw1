import java.net.*;
import java.io.*;

class RunnableSocketReader implements Runnable {
    private Thread thisThread;
    private Socket socket;
    RunnableSocketReader(Socket soc){
        socket = soc;
    }

    @Override 
    public void run() {
        try{ 
            BufferedReader socket_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String str = socket_reader.readLine();
                if(str == null){
                    break;
                }
                System.out.println("Server said: " + str);
            }
            System.out.println("Connection lost! Closing the socket...");
            socket.close();
        }
        catch (Exception e) {e.getStackTrace();}
        
    }

    public void start(){
        if (thisThread == null){
            thisThread = new Thread(this, "ReaderThread");
            thisThread.start();
        }

    }
}

public class Lab1_original_client{
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		try{
			
			Socket socket = new Socket("localhost",9876);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

            // start reading from the socket as soon as a connection is accepted
            RunnableSocketReader sock_reader = new RunnableSocketReader(socket);
            sock_reader.start();
			
			while(true){
		    	String str = reader.readLine();
			    writer.writeBytes(str + "\r\n");
			    if(str.equalsIgnoreCase("quit"))
				    break;
            }
			socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}

