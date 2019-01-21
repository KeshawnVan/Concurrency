package basis;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnSafe {

    private volatile int value = 0;

    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long offset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("value"));
        TestUnSafe testUnSafe = new TestUnSafe();
        boolean swapResult = unsafe.compareAndSwapInt(testUnSafe, offset, 0, 1);
        System.out.println(swapResult);
        System.out.println(testUnSafe.value);
    }
}
