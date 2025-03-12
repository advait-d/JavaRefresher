package org.javarefresher.threading;

public class ThreadTester {
    public static void main(String[] args) {
        System.out.println("Main is starting");
        Thread world = new Thread1();
        world.start();
        System.out.println("Main is Exiting");
        for (int i = 0; i < 100000; i ++) {
            System.out.println("hello ");
        }
    }
}
