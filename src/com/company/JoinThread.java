package com.company;

public class JoinThread extends Thread {
    public JoinThread(String str) { super();
        setName(str);
    }
    public void run () {
        String nameT = getName();
        System.out.println("Старт потока " + nameT);
        if (nameT.equals("First"))
        { try {
            sleep (10000); } catch (InterruptedException e) {
            e.printStackTrace ();
        }
            System.out.println("завершение потока " + nameT); }
        else if (nameT. equals("Second")) {
            try {
                sleep (1000);
            }catch(InterruptedException e){
                e.printStackTrace ();
            }
            System.out.println("завершение потока "	+  nameT);
        }
    }

    public static void main(String[] args) {
        JoinThread trl = new JoinThread("First");
        JoinThread tr2 = new JoinThread("Second");
        trl.start ();
        tr2.start ();
        try   {
            trl.join ();
            System.out.println("завершение main "	);
        }
        catch (InterruptedException e){
            e.printStackTrace(); }
//	join() не дает стартовать другому потоку до окончания текущего и не дает работать методу main() tr2.start();
    }
}

