package org.javarefresher.threading;
// extend thread class, or implement Runnable interface
public class Thread1 extends Thread {
    /**
     *
     */
    @Override
    public void run() {
        for (int i = 0; i <= 100000; i ++) {
            System.out.println("world");
        }
    }

}
