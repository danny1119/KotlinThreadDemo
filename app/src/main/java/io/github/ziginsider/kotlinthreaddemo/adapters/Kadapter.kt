package io.github.ziginsider.kotlinthreaddemo.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zigin on 12.01.2018.
 */
class Kadapter<T>(items: List<T>,
                  layoutResId: Int,
                  bindHolder: View.(T) -> Unit,
                  private var itemClick: T.() -> Unit = {})
    : AbstractAdapter<T>(items, layoutResId, bindHolder) {

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }
    
}

fun <T> RecyclerView.setUp(items: List<T>,
                            layoutResId: Int,
                            bindHolder: View.(T) -> Unit,
                            itemClick: T.() -> Unit = {},
                            manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context))
        : Kadapter<T> {
    val kadapter by lazy {
        Kadapter(items, layoutResId, {
            bindHolder(it)
        }, {
            itemClick()
        })
    }
    layoutManager = manager
    adapter = kadapter
    return kadapter
}

fun <T> RecyclerView.setUpIm(items: List<T>,
                              layoutResId: Int,
                              bindHolder: View.(T) -> Unit,
                              itemClick: T.() -> Unit = {},
                              manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context))
        : Kadapter<T> {
    return Kadapter(items, layoutResId, {
        bindHolder(it)
    }, {
        itemClick()
    }).apply {
        layoutManager = manager
        adapter = this
    }
}
