package com.company;

import java.util.concurrent.atomic.AtomicLong;

public class Broker extends Thread {
    private Market market;
    private static final int PAUSE = 500; // in millis
    public Broker(Market market) {
        this.market = market;
    }
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Current index: " + market.getIndex());
                Thread.sleep(PAUSE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final int NUMBER_BROKERS = 30;
    public static void main(String[ ] args) {
        Market market = new Market(new AtomicLong(100));
        market.start();
        for (int i = 0; i < NUMBER_BROKERS; i++) {
            new Broker(market).start();
        }
    }
}
