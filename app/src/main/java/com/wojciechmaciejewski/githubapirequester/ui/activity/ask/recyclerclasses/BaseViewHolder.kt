package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement

/**
 *
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(askElement: AskElement, click: (Int, id: Long) -> Unit)
}