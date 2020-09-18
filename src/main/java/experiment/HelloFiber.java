package experiment;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

public class HelloFiber {
//    Fiber fiber = new Fiber();

    public static void main(String[] args) throws java.util.concurrent.ExecutionException, InterruptedException {
        System.out.println("hello fiber");
        long start = System.currentTimeMillis();

        int size = 10000;

        Fiber<Void>[] fibers = new Fiber[size];

        SuspendableRunnable sr = new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                calc();
            }
        };



        for (int i = 0; i < fibers.length ; i++) {
            fibers[i] = new Fiber<Void>(sr);

        }

        for (int i = 0; i < fibers.length; i++) {
            fibers[i].start();
        }

        for (int i = 0; i < fibers.length; i++) {
            fibers[i].join();
        }


        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static void calc(){
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += i;
            }
        }
    }

}
