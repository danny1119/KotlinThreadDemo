package io.github.ziginsider.kotlinthreaddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zigin on 09.01.2018.
 */

//print list
fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
    ): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
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


