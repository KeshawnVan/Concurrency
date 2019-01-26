package lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        final Lock lock = new NonReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();
        Queue<String> queue = new LinkedBlockingQueue<>();
        int queueSize = 10;
        Thread producer = new Thread(() -> {
            try {
                lock.lock();
                while (queue.size() == queueSize) {
                    notEmpty.await();
                }
                queue.add("element");
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                lock.lock();
                while (queue.isEmpty()) {
                    notFull.await();
                }
                String poll = queue.poll();
                System.out.println(poll);
                notEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        producer.start();
        consumer.start();
    }
}
