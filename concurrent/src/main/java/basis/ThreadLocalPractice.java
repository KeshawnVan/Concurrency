package basis;

public class ThreadLocalPractice {

    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("parent thread start");
            threadLocal.set("parent");
            System.out.println("parent thread set");
            new Thread(() -> {
                System.out.println("child thread start");
                System.out.println("child thread get " + threadLocal.get());
                threadLocal.set("child");
                System.out.println("child thread set");
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("parent thread get " + threadLocal.get());
        }).start();
    }
}
