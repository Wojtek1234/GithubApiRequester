package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.view.View
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement

/**
 *
 */
class ProgressViewHolder(view: View) : BaseViewHolder(view) {
    override fun bind(askElement: AskElement, click: (Int, Long) -> Unit) {
        throw UnsupportedOperationException()
    }
}