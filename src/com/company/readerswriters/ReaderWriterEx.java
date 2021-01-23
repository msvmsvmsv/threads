package com.company.readerswriters;

//  Readers/Writers with exclusive access.


class RWbasicEx {         // basic read or write (data; no synch)
    protected int data = 0;  // the "database"
    protected void read() {
        System.out.println("read:  " + data);
    }
    protected void write() {
        data++;
        System.out.println("wrote:  " + data);
    }
}

class RWexclusive extends RWbasicEx {  // exclusive read and write
    public synchronized void read() {
        System.out.println("read:  " + data);
    }
    public synchronized void write() {
        data++;
        System.out.println("wrote:  " + data);
    }
}

class ReaderEx extends Thread {
    int rounds;
    RWexclusive RW;
    public ReaderEx(int rounds, RWexclusive RW) {
        this.rounds = rounds;
        this.RW = RW;
    }
    public void run() {
        for (int i = 0; i<rounds; i++) {
            RW.read();
        }
    }
}

class WriterEx extends Thread {
    int rounds;
    RWexclusive RW;
    public WriterEx(int rounds, RWexclusive RW) {
        this.rounds = rounds;
        this.RW = RW;
    }
    public void run() {
        for (int i = 0; i<rounds; i++) {
            RW.write();
        }
    }
}

class MainEx {  // driver program
    static RWexclusive RW = new RWexclusive();
    public static void main(String[] arg) {
        int rounds =50;
        new ReaderEx(rounds, RW).start();
        new WriterEx(rounds, RW).start();
    }
}

