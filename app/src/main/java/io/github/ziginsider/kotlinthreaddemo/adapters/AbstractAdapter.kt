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
        private val layoutResId: Int,
        private val bindHolder: View.(T) -> Unit)
    : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    class Holder (itemView: View) : RecyclerView.ViewHolder(itemView)

    private var itemClick: T.() -> Unit = {}

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent inflate layoutResId
        val viewHolder = Holder(view)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }

    protected open fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }
}
