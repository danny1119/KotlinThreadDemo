package io.github.ziginsider.kotlinthreaddemo.adapters

import android.view.View

/**
 * Created by zigin on 12.01.2018.
 */
class Kadapter<T>(items: List<T>,
                  layoutResId: Int,
                  private val bindHolder: View.(T) -> Unit)
    : AbstractAdapter<T>(items, layoutResId) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }
}