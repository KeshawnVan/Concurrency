package basis;

/**
 * 当一个线程调用共享变量的wait()方法被阻塞挂起后或者调用sleep()，如果其他线程中断了该线程，则线程会抛出InterruptedException
 * 注意：调用线程的interrupt()方法并不一定能够终止线程，如果线程在阻塞状态，会抛出InterruptedException异常，而InterruptedException是受检异常
 * 如果catch到InterruptedException，并且没有抛出该异常的话，当前线程并不会终止
 */
public class WaitAndInterrupted {

    private static Object object = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(() -> {
            System.out.println("waitThread start");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waitThread after InterruptedException");
            }
        });

        Thread sleepThread = new Thread(() -> {
            System.out.println("sleepThread start");
            try {
                Thread.sleep(1000 + 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("sleepThread after InterruptedException");
            }
        });

        waitThread.start();
        sleepThread.start();

        waitThread.interrupt();
        sleepThread.interrupt();
    }
}
