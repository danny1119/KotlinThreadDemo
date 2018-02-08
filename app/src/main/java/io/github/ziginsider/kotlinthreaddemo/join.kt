package io.github.ziginsider.kotlinthreaddemo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.concurrent.locks.Lock

/**
 * Created by zigin on 09.01.2018.
 */

//print list
fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: ((T) -> String)? = null
    ): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        //result.append(transform(element))
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }

    result.append(postfix)
    return result.toString()
}

//QuickSort
fun <T : Comparable<T>> Collection<T>.qSort(): Collection<T> =
    if (size < 2) this
    else {
        val pivot = first()
        val (smaler, greater) = drop(1).partition { it <= pivot }
        smaler.qSort() + pivot + greater.qSort()
    }

//inflate view
infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

//synchronized
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

