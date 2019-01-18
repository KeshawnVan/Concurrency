package basis;

public class Interrupt {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
            }
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(Thread.interrupted());
            System.out.println(Thread.interrupted());
            System.out.println(Thread.currentThread().isInterrupted());
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        thread.join();
    }
}
