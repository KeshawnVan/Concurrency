package basis;

public class Notify {

    private static final Object OBJECT = new Object();

    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(() -> {
            System.out.println("threadA start");
            synchronized (OBJECT) {
                System.out.println("threadA begin wait");
                try {
                    OBJECT.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA end wait");
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("threadB start");
            synchronized (OBJECT) {
                System.out.println("threadB begin wait");
                try {
                    OBJECT.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB end wait");
            }
        });

        Thread threadC = new Thread(() -> {
            System.out.println("threadC start");
            synchronized (OBJECT) {
                System.out.println("threadC begin notify");
                /**
                 * 调用notify()结果
                 * threadA start
                 * threadC start
                 * threadB start
                 * threadA begin wait
                 * threadB begin wait
                 * threadC begin notify
                 * threadC end notify
                 * threadA end wait
                 */
                OBJECT.notify();
                /**
                 * 调用notifyAll()结果
                 * threadA start
                 * threadA begin wait
                 * threadB start
                 * threadB begin wait
                 * threadC start
                 * threadC begin notify
                 * threadC end notify
                 * threadB end wait
                 * threadA end wait
                 * main over
                 */
//                OBJECT.notifyAll();
                System.out.println("threadC end notify");
            }
        });

        threadA.start();
        threadB.start();

        //不sleep的话可能会出现threadC先于threadB执行，导致notify之后再wait()，线程阻塞
        Thread.sleep(1000);

        threadC.start();


        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");

    }


}
