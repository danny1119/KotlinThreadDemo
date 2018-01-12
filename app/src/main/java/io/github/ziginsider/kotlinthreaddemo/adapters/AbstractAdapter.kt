package io.github.ziginsider.kotlinthreaddemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.ziginsider.kotlinthreaddemo.inflate

/**
 * Created by zigin on 12.01.2018.
 */

abstract class AbstractAdapter<T> constructor(
        protected var itemList: List<T>,
        private val layoutResId: Int)
    : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    class Holder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent inflate layoutResId
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    protected open fun View.bind(item: T) {}
}
