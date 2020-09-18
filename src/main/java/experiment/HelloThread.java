package experiment;

public class HelloThread {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Runnable r = new Runnable() {
            public void run() {
                clac();
            }
        };
        int size = 10000;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    static void clac(){
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += i;
            }
        }
    }
}
