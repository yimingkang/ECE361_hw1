import java.net.*;
import java.io.*;
public class Lab1_original_client{

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		try{
			
			Socket socket = new Socket("localhost",9876);
			BufferedReader socket_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			
			while(true){
    			String str = socket_reader.readLine();
	    		System.out.println("Server said: " + str);
		    	str = reader.readLine();
			    writer.writeBytes(str + "\r\n");
			    if(str.equalsIgnoreCase("quit"))
				    break;
            }
			socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}
