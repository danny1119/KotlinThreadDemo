package io.github.ziginsider.kotlinthreaddemo;

import android.util.Log;

import java.util.LinkedList;

/**
 * Created by zigin on 07.01.2018.
 */

public class ConsumerProducer {
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    private final String TAG = "Zig";

    public void produce() throws InterruptedException {
        int value = 0;

        Log.d(TAG, "produce: start");

        while (true) {
            synchronized (lock) {
                Log.d(TAG, "produce: in synchronized block:");
                while (list.size() >= LIMIT) {
                    Log.d(TAG, "produce: lock.wait() list.size = " + String.valueOf(list.size()));
                    lock.wait();
                }
                Log.d(TAG, "produce: old value = " + String.valueOf(value));
                Thread.sleep(1000);
                value = value + 1;
                list.add (value);
                Log.d(TAG, "produce: new value = " + String.valueOf(value));
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        Log.d(TAG, "consume: start");

        while (true) {
            synchronized (lock) {
                Log.d(TAG, "consume: in synchronized block:");
                while (list.size() < 1) {
                    Log.d(TAG, "consume: lock.wait() list.size = " + String.valueOf(list.size()));
                    lock.wait();
                }
                Thread.sleep(500);
                int value = list.removeFirst();
                Log.d(TAG, "consume: removeFirst() new list.size = " + String.valueOf(list.size()));
                lock.notify();
                Log.d(TAG, "consume: return value = " + String.valueOf(value));
            }
        }
    }

    public String getList() {
        String s = "";
           for (int value : list) {
               s = s + String.valueOf(value);
           }

        return s;
    }

}
