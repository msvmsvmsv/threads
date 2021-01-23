package com.company;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Testq {
    public static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(200);

    public static Random random = new Random();

    public static AtomicInteger handled = new AtomicInteger(0);

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (handled.get()<=10000) {
                try {
                    while (queue.size() == 0)
                        sleep(500);
                    if(handled.get()>10000)
                        break;
                    Integer a = queue.take();

                    System.out.println("a i "+handled.addAndGet(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (handled.get()<=10000) {
                try {
                    if (queue.size()>=100) {
                        while (queue.size()>80)
                            sleep(500);
                        if(handled.get()>10000)
                            break;
                    }
                    queue.put(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Consumer().start();
        new Consumer().start();
        new Producer().start();
        new Producer().start();
        new Producer().start();
    }

}

