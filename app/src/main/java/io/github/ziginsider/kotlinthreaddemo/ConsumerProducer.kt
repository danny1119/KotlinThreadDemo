package io.github.ziginsider.kotlinthreaddemo

import android.util.Log

import java.util.LinkedList

/**
 * Created by zigin on 07.01.2018.
 */

class ConsumerProducer {
    val list = LinkedList<Int>()

    private val LIMIT = 10
    private val lock = java.lang.Object()

    private val TAG = "Zig"

    @Throws(InterruptedException::class)
    fun produce() {
        var value = 0

        Log.d(TAG, "produce: start")

        while (true) {
            synchronized(lock) {
                Log.d(TAG, "produce: in synchronized block:")
                while (list.size >= LIMIT) {
                    Log.d(TAG, "produce: lock.wait() list.size = " + list.size.toString())
                    lock.wait()
                }
                Log.d(TAG, "produce: old value = $value")
                Thread.sleep(1000)
                value += 1
                list.add(value)
                Log.d(TAG, "produce: new value = $value")
                lock.notify()
            }
        }
    }

    @Throws(InterruptedException::class)
    fun consume() {

        Log.d(TAG, "consume: start")

        while (true) {
            synchronized(lock) {
                Log.d(TAG, "consume: in synchronized block:")
                while (list.size < 1) {
                    Log.d(TAG, "consume: lock.wait() list.size = " + list.size.toString())
                    lock.wait()
                }
                Thread.sleep(500)
                val value = list.removeFirst()
                Log.d(TAG, "consume: removeFirst() new list.size = " + list.size.toString())
                lock.notify()
                Log.d(TAG, "consume: return value = $value")
            }
        }
    }

    fun getList(): String {
        var s = ""
        for (value in list) {
            s += value.toString()
        }

        return s
    }

}
