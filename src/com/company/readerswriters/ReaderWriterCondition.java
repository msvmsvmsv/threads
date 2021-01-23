package com.company.readerswriters;

import java.util.concurrent.locks.*;

class ReadersWriters {  // Readers/Writers
    int nr = 0;
    int data = 0;
    protected Lock lock = new ReentrantLock();
    protected Condition writer;
    protected Condition reader;

    public ReadersWriters() {
//		super();
        writer = lock.newCondition();
        reader = lock.newCondition();
    }

    public void read() throws InterruptedException {
        lock.lock();
        nr++;
        System.out.println(nr);
        try {
            while (nr == 0 || data == 0) {
                System.out.println("Блокируем reader");
                reader.await();
            }
            System.out.println("read:  " + data);
            nr--;
            System.out.println(nr);
            writer.signal();
        } finally {
            lock.unlock();
        }
    }

    public void write() throws InterruptedException {
        lock.lock();
        try {
            while (nr > 0 && data != 0)     //nr>0 ||data!=0
            {
                System.out.println("Блокируем writer");
                writer.await();
            }
            data++;
            System.out.println("wrote:  " + data);
            reader.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


