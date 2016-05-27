package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.askelement_viewholder.view.*

/**

 */
class AskElementVH(itemView: View) : BaseViewHolder(itemView) {
    val imageView: ImageView
    val titleTextView: TextView
    val urlTextView: TextView
    val idTextView: TextView

    init {
        imageView = this.itemView.repoUserImageView;
        titleTextView = this.itemView.askElementTitle;
        urlTextView = this.itemView.askElementHomeUrl
        idTextView = this.itemView.askElementId
    }

}
