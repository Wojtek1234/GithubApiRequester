package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import kotlinx.android.synthetic.main.askelement_viewholder.view.*

/**

 */
class AskElementVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView
    private val titleTextView: TextView
    private val urlTextView: TextView
    private val idTextView: TextView

    init {
        imageView = this.itemView.repoUserImageView;
        titleTextView = this.itemView.askElementTitle;
        urlTextView = this.itemView.askElementHomeUrl
        idTextView = this.itemView.askElementId
    }

    fun bind(askElement: AskElement, click: (Int, id: Long) -> Unit) {

        imageView.setImageResource(askElement.returnImageId())
        titleTextView.text = askElement.returnTitle()
        urlTextView.text = askElement.returnHomepage()
        idTextView.text = "${askElement.id}"
        this.itemView.setOnClickListener {
            click(askElement.elementType, askElement.id)
        }

    }

}
