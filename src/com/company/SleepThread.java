package com.company;

class SleepThread extends Thread {
    protected boolean done = false;
    public void run( ) {
        while (!done) {
            System.out.println("Running    "+ this.getName());
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
        System.out.println("Finished    " + this.getName());
    }
    public void shutDown( ) {
        done = true;
    }

    public static void main(String[] args) {
        SleepThread t0= new SleepThread();
        SleepThread t1= new SleepThread();
        SleepThread t2= new SleepThread();
        t0.start();
        t1.start();
        t2.start();
        t0.shutDown();
        t1.shutDown();


    }
}
