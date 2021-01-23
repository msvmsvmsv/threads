package com.company;

public class TwoThreadSynchronized {
    public static int counter = 0;

    public static void main(String args[]) {
        final StringBuilder s = new StringBuilder();
        new Thread() {
            public void run() {
                synchronized (s) {
                    do {
                        s.append("A");
                        System.out.println(s);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.err.print(e);
                        }
                    } while (TwoThreadSynchronized.counter++ < 2);
                } // конец synchronized
            }
        }.start();
        new Thread() {
            public void run() {
                synchronized (s) {
                    while (TwoThreadSynchronized.counter++ < 6) {
                        s.append("B");
                        System.out.println(s);
                    }
                } // конец synchronized
            }
        }.start();
    }
}
