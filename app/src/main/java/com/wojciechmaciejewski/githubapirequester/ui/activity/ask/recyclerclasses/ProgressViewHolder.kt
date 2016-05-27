package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.progress_bar_viewholder.view.*

/**
 *
 */
class ProgressViewHolder(view: View) : BaseViewHolder(view) {

    private val progressBar: ProgressBar

    init {
        progressBar = view.progress_bar;
    }


    fun setVisability(visable: Int) {
        progressBar.visibility = visable
    }
}