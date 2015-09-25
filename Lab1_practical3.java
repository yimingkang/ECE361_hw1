import java.net.*;
import java.io.*;
public class Lab1_practical3 {
    public static void server() throws IOException{
        int port_num = 9876;
        int quit = 0;
        System.out.println("Starting server at port " + port_num);
        ServerSocket serversSocket = new java.net.ServerSocket(port_num);

        while(quit == 0){
                Socket socket = serversSocket.accept();
                BufferedReader socket_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream()); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                writer.writeBytes("GOOD MORNING!" + "\r\n");
                System.out.println("Got a connection!"); 
                while(quit ==  0){
                    String str = socket_reader.readLine();
                    if(str == null){
                            break;
                    }
                    System.out.println("Client said: " + str);
                    str = reader.readLine();
                    writer.writeBytes(str + "\r\n");
                }
                System.out.println("Connection lost!");
                socket.close();
        }
    }


	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
        server();
		try{
			
			Socket socket = new Socket("localhost",9876);
			BufferedReader socket_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            long startTime = -1;
			
			while(true){
			String str = socket_reader.readLine();
            long endTime = System.currentTimeMillis();
            if (startTime != -1){
                // Compute RTT
                long rtt = endTime - startTime;
                System.out.println("RTT: " + rtt + "ms");
            }
			System.out.println(str);
			str = reader.readLine();
			writer.writeBytes(str + "\r\n");
            // Measure after writeBytes
            startTime = System.currentTimeMillis();
			if(str.equalsIgnoreCase("quit"))
				break;
            }
			socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}


