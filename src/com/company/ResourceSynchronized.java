package com.company;

import java.io.*;
public class ResourceSynchronized {
    private FileWriter fileWriter;
    public ResourceSynchronized(String file) throws IOException {
// проверка наличия файла
        fileWriter = new FileWriter(file, true);
    }
    public synchronized void writing(String str, int i) {
        try {
            fileWriter.append(str + i);
            System.out.print(str + i);
            Thread.sleep((long)(Math.random() * 50));
            fileWriter.append("->" + i + " ");
            System.out.print("->" + i + " ");
        } catch (IOException e) {
            System.err.print("ошибка файла: " + e);
        } catch (InterruptedException e) {
            System.err.print("ошибка потока: " + e);
        }
    }
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.print("ошибка закрытия файла: " + e);
        }
    }
}

class SyncThread extends Thread {
    private ResourceSynchronized rs;
    public SyncThread(String name, ResourceSynchronized rs) {
        super(name);
        this.rs = rs;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            rs.writing(getName(), i); // место срабатывания синхронизации
        }
    }
}


 class SynchroRun {
    public static void main(String[ ] args) {
        ResourceSynchronized s = null;
        try {
            s = new ResourceSynchronized("data\\result.txt");
            SyncThread t1 = new SyncThread("First", s);
            SyncThread t2 = new SyncThread("Second", s);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (IOException e) {
            System.err.print("ошибка файла: " + e);
        } catch (InterruptedException e) {
            System.err.print("ошибка потока: " + e);
        } finally {
            s.close();
        }
    }
}