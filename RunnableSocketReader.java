import java.net.*;
import java.io.*;

class RunnableSocketReader implements Runnable {
    private Thread thisThread;
    private Socket socket;
    private String targetName;

    RunnableSocketReader(Socket soc, String name){
        socket = soc;
        targetName = name;
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
                System.out.println(targetName + " said: " + str);
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
