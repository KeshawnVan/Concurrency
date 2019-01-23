package basis;

import java.util.concurrent.locks.LockSupport;

public class LockSupportPractice {

    public static void main(String[] args) {
        System.out.println("begin unpark");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("begin unpark");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("begin park");
        LockSupport.park();
        System.out.println("end park");
        System.out.println("begin park");
        LockSupport.park();
        System.out.println("end park");
    }
}
