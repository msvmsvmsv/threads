import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class LivelockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LivelockExample livelock = new LivelockExample();
        new Thread(() -> {
            try {
                livelock.operation1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                livelock.operation2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }

    public void operation1() throws InterruptedException {
        while (true) {
            lock1.tryLock(50, TimeUnit.MILLISECONDS);

            System.out.println("lock1 acquired, trying to acquire lock2.");
            sleep(50);

            if (lock2.tryLock()) {
                System.out.println("lock2 acquired.");
            } else {
                System.out.println("cannot acquire lock2, releasing lock1.");
                lock1.unlock();
                continue;
            }

            System.out.println("executing first operation.");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() throws InterruptedException {
        while (true) {
            lock2.tryLock(50, TimeUnit.MILLISECONDS);
            System.out.println("lock2 acquired, trying to acquire lock1.");
            sleep(50);

            if (lock1.tryLock()) {
                System.out.println("lock1 acquired.");
            } else {
                System.out.println("cannot acquire lock1, releasing lock2.");
                lock2.unlock();
                continue;
            }

            System.out.println("executing second operation.");
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    // helper methods

}