import java.net.*;
import java.io.*;

public class Lab1_original_client{
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		try{
			
			Socket socket = new Socket("localhost",9876);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

            // start reading from the socket as soon as a connection is accepted
            RunnableSocketReader sock_reader = new RunnableSocketReader(socket, "Server");
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

