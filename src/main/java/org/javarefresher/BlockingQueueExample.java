package org.javarefresher;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public void blockingQueue(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            int value = 0;
            try {
                while (true) {
                    blockingQueue.add(value);
                    System.out.println("Produced: " + value++);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    int value = blockingQueue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(1200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
