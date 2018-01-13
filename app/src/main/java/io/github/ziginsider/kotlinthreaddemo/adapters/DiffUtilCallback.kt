package io.github.ziginsider.kotlinthreaddemo.adapters

import android.support.v7.util.DiffUtil

/**
 * Created by zigin on 13.01.2018.
 */
internal class DiffUtilCallback<T>(private val oldItems: List<T>,
                                   private val newItems: List<T>): DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]
}