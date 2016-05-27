package com.wojciechmaciejewski.githubapirequester.model.dto

import android.view.View
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementVH


/**
 *
 */


interface AskElementInterface {
    val id:Long;
    fun handleViewHolder(baseViewHolder: AskElementVH, picasso: Picasso, click: (String, String?, pairToTransit: android.util.Pair<View, String>) -> Unit)
}