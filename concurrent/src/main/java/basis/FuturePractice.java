package basis;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FuturePractice {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("callable start");
            return "ok";
        });
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
