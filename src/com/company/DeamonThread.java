package com.company;

public class DeamonThread extends Thread {
    public void run() {
        try {
            if (isDaemon()) {
                System.out.println("старт потока-демона");
                Thread.sleep(10_000); // заменить параметр на 1ПОТОКИ ВЫПОЛНЕНИЯ
            } else {
                System.out.println("старт обычного потока");
            }
        } catch (InterruptedException e) {
            System.err.print(e);
        } finally {
            if (!isDaemon()) {
                System.out.println("завершение обычного потока");
            } else {
                System.out.println("завершение потока-демона");
            }
        }
    }

    public static void main(String[ ] args) {
        DeamonThread usual = new DeamonThread();
        DeamonThread daemon = new DeamonThread();
        daemon.setDaemon(true);
        daemon.start();
        usual.start();
        System.out.println("последний оператор main");
    }
}