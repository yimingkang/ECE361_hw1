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
                System.out.println("Client said: " + str);
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
                RunnableSocketReader sock_reader = new RunnableSocketReader(socket);
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


