package io.github.ziginsider.kotlinthreaddemo.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zigin on 12.01.2018.
 */
class Kadapter<T>(items: List<T>,
                  layoutResId: Int,
                  bindHolder: View.(T) -> Unit)
    : AbstractAdapter<T>(items, layoutResId, bindHolder) {

    private var itemClick: T.() -> Unit = {}

    constructor(items: List<T>,
                layoutResId: Int,
                bindHolder: View.(T) -> Unit,
                itemClick: T.() -> Unit = {}) : this(items, layoutResId, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }

//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        super.onBindViewHolder(holder, position)
//    }
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

