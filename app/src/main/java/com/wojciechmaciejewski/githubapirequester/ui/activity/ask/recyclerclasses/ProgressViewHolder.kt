package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.view.View
import android.widget.ProgressBar
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import kotlinx.android.synthetic.main.progress_bar_viewholder.view.*

/**
 *
 */
class ProgressViewHolder(view: View) : BaseViewHolder(view) {

    private val progressBar: ProgressBar

    init {
        progressBar = view.progress_bar;
    }
    override fun bind(askElement: AskElement, click: (Int, Long) -> Unit) {
        throw UnsupportedOperationException()
    }

    fun setVisable(visable: Int) {
        progressBar.visibility = visable
    }
}