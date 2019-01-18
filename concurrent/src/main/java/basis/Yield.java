package basis;

public class Yield {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread() + ": " + i);
                if (i == 5) {
                    System.out.println(Thread.currentThread() + "begin yield");
                    /**
                     * 不调用yield时
                     * Thread[Thread-2,5,main]begin yield
                     * Thread[Thread-0,5,main]begin yield
                     * Thread[Thread-1,5,main]begin yield
                     * Thread[Thread-0,5,main]is over
                     * Thread[Thread-2,5,main]is over
                     * Thread[Thread-1,5,main]is over
                     */
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + "is over");
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
