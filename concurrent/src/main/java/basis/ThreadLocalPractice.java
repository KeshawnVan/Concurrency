package basis;

/**
 * 使用InheritableThreadLocal子线程只能拿到线程启动时父线程threadLocal中的副本，后续两者对threadLocal的操作互不影响
 * Thread init时
 * if (inheritThreadLocals && parent.inheritableThreadLocals != null)
 * this.inheritableThreadLocals = ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
 */
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
                System.out.println("child thread sleep 1000ms");
                sleep(1000);
                System.out.println("child thread get " + threadLocal.get());
                threadLocal.set("child");
                System.out.println("child thread set");
            }).start();
            sleep(100);
            System.out.println("parent thread set parent new");
            threadLocal.set("parent new");
            sleep(2000);
            System.out.println("parent thread get " + threadLocal.get());
        }).start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
