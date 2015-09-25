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
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream()); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                // start reading from the socket as soon as a connection is accepted
                RunnableSocketReader sock_reader = new RunnableSocketReader(socket, "Client");
                sock_reader.start();

                writer.writeBytes("GOOD MORNING!" + "\r\n");
                System.out.println("Got a connection!"); 
                while(quit ==  0){
                    String str = reader.readLine();
                    writer.writeBytes(str + "\r\n");
                }
        }
    }


	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
        server();
    }
}


