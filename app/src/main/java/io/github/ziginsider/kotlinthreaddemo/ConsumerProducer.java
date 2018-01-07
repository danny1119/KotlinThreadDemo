package io.github.ziginsider.kotlinthreaddemo;

import java.util.LinkedList;

/**
 * Created by zigin on 07.01.2018.
 */

public class ConsumerProducer {
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add (value++);
                lock.notify();
            }
        }
    }

    public int consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                int value = list.removeFirst();
                lock.notify();
                return value;
            }
        }
    }
}
