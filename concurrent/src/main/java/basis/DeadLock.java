package basis;

public class DeadLock {

    private static final Object RESOURCE_A = new Object();

    private static final Object RESOURCE_B = new Object();

    public static void main(String[] args) throws Exception {

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 start");
            System.out.println("thread1 try get RESOURCE_A");
            synchronized (RESOURCE_A) {
                System.out.println("thread1 get RESOURCE_A success");
                System.out.println("thread1 sleep 2s");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 try get RESOURCE_B");
                synchronized (RESOURCE_B) {
                    System.out.println("thread1 get RESOURCE_B success");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 start");
            System.out.println("thread2 try get RESOURCE_B");
            synchronized (RESOURCE_B) {
                System.out.println("thread2 get RESOURCE_B success");
                System.out.println("thread2 sleep 2s");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 try get RESOURCE_A");
                synchronized (RESOURCE_A) {
                    System.out.println("thread2 get RESOURCE_A success");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
