package basis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    while (queue.size() >= 100) {
                        wait(queue);
                    }
                    String value = String.valueOf(new Random().nextInt(100));
                    System.out.println("put " + value);
                    queue.add(value);
                    queue.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    while (queue.isEmpty()) {
                        wait(queue);
                    }
                    System.out.println("get " + queue.poll());
                    queue.notifyAll();
                }
            }
        }).start();
    }

    private static void wait(Queue<String> queue) {
        try {
            queue.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
