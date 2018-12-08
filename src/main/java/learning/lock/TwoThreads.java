package learning.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreads {
    private static int count;
    private static Lock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("偶数：" + count++);

                    c1.await();
                    c2.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("奇数：" + count++);

                    c1.signalAll();
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
