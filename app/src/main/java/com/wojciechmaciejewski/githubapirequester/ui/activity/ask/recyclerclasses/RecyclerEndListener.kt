package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class RecyclerEndListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == 0 && recyclerView != null) {
            val itemCount = recyclerView.adapter.itemCount
            val lastVisibleItemPosition = getLastVisibleItem(recyclerView)

            if (itemCount == lastVisibleItemPosition + 1) {

                onLoadMore()
            }
        }
    }

    private fun getLastVisibleItem(recyclerView: RecyclerView): Int {
        val lastVisibleItemPosition: Int
        try {
            lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        } catch (e: ClassCastException) {
            lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
        }

        return lastVisibleItemPosition
    }

    abstract fun onLoadMore()


}
