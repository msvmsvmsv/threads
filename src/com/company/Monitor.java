package com.company;

import java.util.Scanner;
 class Payment {
    private int amount;
    private boolean close;
    public int getAmount() {
        return amount;
    }
    public boolean isClose() {
        return close;
    }
    public synchronized void doPayment() {
        try {
            System.out.println("Start payment:");
            while (amount <= 0) {
                this.wait(); // остановка потока и освобождение блокировки
// после возврата блокировки выполнение будет продолжено
            }
// код выполнения платежа
            close = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment is closed : " + close);
    }
    public void initAmount() {
        Scanner scan = new Scanner(System.in);
        amount = scan.nextInt();
    }
}
public class Monitor {
    public static void main(String[] args) throws InterruptedException {
        final Payment payment = new Payment();
        new Thread() {
            public void run() {
                payment.doPayment(); // вызов synchronized метода
            }
        }.start();
        Thread.sleep(200);
        synchronized (payment) { // 1-ый блок
            System.out.println("Init amount:");
            payment.initAmount();
            payment.notify(); // уведомление о возврате блокировки
        }
        synchronized (payment) { // 2-ой блок
            payment.wait(1_000);
            System.out.println("ok");
        }
    }
}
