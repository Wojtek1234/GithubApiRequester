package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement

/**
 *
 */


class AskElementsRecyclerAdapter(val clickFunct: (Int, String) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    var listOfElements: List<AskElement>

    init {
        listOfElements = listOf();
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        if (position < itemCount - 1) holder?.bind(listOfElements[position], clickFunct)
    }

    override fun getItemCount() = listOfElements.size + 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder? {
        when (viewType) {
            TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.progress_bar_viewholder, parent, false)
                return ProgressViewHolder(view)
            }
            TYPE_ITEM -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.askelement_viewholder, parent, false)
                return AskElementVH(view)
            }
            else -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.askelement_viewholder, parent, false)
                return AskElementVH(view)
            }
        }
    }

    fun changeElements(list: List<AskElement>) {
        listOfElements = list;
        this.notifyDataSetChanged()
    }

    fun addToList(list: List<AskElement>) {
        listOfElements += list
        listOfElements = listOfElements.sortedBy { it.id }
        this.notifyItemRangeInserted(itemCount - 1, list.count())
    }

    fun clearList() {
        listOfElements = listOf()
        this.notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1)
            return TYPE_FOOTER
        return TYPE_ITEM
    }

    companion object {
        private val TYPE_FOOTER = 1;
        private val TYPE_ITEM = 0;
    }
}