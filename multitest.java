class test implements Runnable{
    private Thread t;

    public void run(){
        System.out.println("Running...");
    }

    public void start(){
        if (t == null){      
                t = new Thread (this, "Test thread");
                t.start();
        }
    }
}

public class multitest{
    public static void main(String []args){
        test T1 = new test();
        T1.start();
        test T2 = new test();
        T2.start();

    }
}
