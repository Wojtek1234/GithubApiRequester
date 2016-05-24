package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement

/**
 *
 */


class AskElementsRecyclerAdapter : RecyclerView.Adapter<AskElementVH>() {

    var listOfElements: List<AskElement>

    init {
        listOfElements = listOf();
    }

    override fun onBindViewHolder(holder: AskElementVH?, position: Int) {
        holder?.bind(listOfElements[position], { a, b -> })
    }

    override fun getItemCount() = listOfElements.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AskElementVH? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.askelement_viewholder, parent, false)
        return AskElementVH(view)
    }

    fun addElements(list: List<AskElement>) {
        listOfElements = list;
        this.notifyDataSetChanged()
    }
}