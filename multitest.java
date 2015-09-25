class test implements Runnable{
    private Thread t;
    private String thread_name;

    test(String name){
        thread_name = name;
    }

    @override
    public void run(){
        System.out.println("Running..." + thread_name);
    }

    @override
    public void start(){
        if (t == null){      
                t = new Thread (this, "Test thread");
                t.start();
        }
    }
}

public class multitest{
    public static void main(String []args){
        test T1 = new test("t1");
        T1.start();
        test T2 = new test("t2");
        T2.start();

    }
}
