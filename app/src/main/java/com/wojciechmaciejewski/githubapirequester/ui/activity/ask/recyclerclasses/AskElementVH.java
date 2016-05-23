package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wojciechmaciejewski.githubapirequester.R;
import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement;

/**
 *
 */
public class AskElementVH extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView urlTextView;
    private TextView idTextView;

    public AskElementVH(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.repoUserImageView);
        titleTextView = (TextView) itemView.findViewById(R.id.askElementTitle);
        urlTextView = (TextView) itemView.findViewById(R.id.askElementHomeUrl);
        idTextView = (TextView) itemView.findViewById(R.id.askElementId);
    }


    public void bind(AskElement element) {
        switch (element.getElementType()) {
            case AskElement.USER_TYPE:
                imageView.setImageResource(R.drawable.user_icon);
                break;
            case AskElement.REPO_TYPE:
                imageView.setImageResource(R.drawable.repo_icon);
                break;
        }
        titleTextView.setText(element.returnTitle());
        urlTextView.setText(element.returnHomepage());
        idTextView.setText(String.valueOf(element.getId()));
    }
}
