package basis;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWritePractice {

    private static List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {

        list.add("I");
        list.add("am");
        list.add("fankx");
        list.add(".");

        Iterator<String> iterator = list.iterator();

        Thread thread = new Thread(() -> {
            list.set(2, "Fankx");
            list.remove(3);
        });

        thread.start();
        thread.join();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
