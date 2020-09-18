package experiment;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

public class ThreadNestFiber {
//    Fiber fiber = new Fiber();

    public static void main(String[] args) throws java.util.concurrent.ExecutionException, InterruptedException {
        System.out.println("hello fiber");

        long start = System.currentTimeMillis();
        int size = 1000;

//        Fiber<Void>[] fibers = new Fiber[size];

//        SuspendableRunnable sr = new SuspendableRunnable() {
//            @Override
//            public void run() throws SuspendExecution, InterruptedException {
//                calc();
//            }
//        };

        Thread[] threads = new Thread[10];





//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < fibers.length ; i++) {
//                    fibers[i] = new Fiber<Void>(sr);
//
//                }
//
//                for (int i = 0; i < fibers.length; i++) {
//                    fibers[i].start();
//                }
////
////                for (int i = 0; i < fibers.length; i++) {
////                    fibers[i].join();
////                }
//            }
//        };

        FiberRun r = new FiberRun();

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(r);

        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            r.run();
//            for (int j = 0; j < r.fibers.length; j++) {
//                System.out.println(r.fibers.length);
//                r.fibers[i].start();
//            }
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static void calc(){
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += i;
                System.out.println(result);
            }
        }
        System.out.println("hello-kd");
    }


    static class FiberRun implements Runnable{
        int size = 1000;

        Fiber<Void>[] fibers = new Fiber[size];

        SuspendableRunnable sr = new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                calc();
            }
        };


        @Override
        public void run() {
            for (int i = 0; i < fibers.length ; i++) {
                fibers[i] = new Fiber<Void>(sr);
//                System.out.println();
            }

//            for (int i = 0; i < fibers.length; i++) {
//                fibers[i].start();
//            }
        }
    }

}
