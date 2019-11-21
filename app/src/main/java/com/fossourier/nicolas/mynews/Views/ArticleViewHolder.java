package com.fossourier.nicolas.mynews.Views;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// ----------------------//
// ITEM OF RECYCLERVIEW  //
// ----------------------//
public class ArticleViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    @BindView(R.id.fragment_main_item_section) TextView textSection;
    @BindView(R.id.fragment_main_item_date) TextView textDate;
    @BindView(R.id.fragment_main_item_content) TextView textContent;
    @BindView(R.id.fragment_main_item_image) ImageView imageArticle;
    final ArticleAdapter.RecyclerViewOnClickListener recyclerViewOnClickListener;

    public ArticleViewHolder(View itemView, ArticleAdapter.RecyclerViewOnClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.recyclerViewOnClickListener = listener;
        itemView.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void updateWithArticles(Result listArticles, RequestManager glide) {

        // -----------------//
        //     ABSTRACT     //
        // -----------------//
        this.textContent.setText(listArticles.getAbstract());

        // -----------------//
        //  PUBLISHED DATE  //
        // -----------------//
        if (listArticles.getPublishedDate() != null) {
            this.textDate.setText(listArticles.getPublishedDate().substring(0, 10));
        }

        // ---------------------------//
        // SECTION AND/OR SUBSECTION //
        // -------------------------//
        if ((listArticles.getSubsection() != null) && (!listArticles.getSubsection().isEmpty())){
            this.textSection.setText(listArticles.getSection() + " > " + listArticles.getSubsection());}
        else {this.textSection.setText(listArticles.getSection());}

        // -----------------//
        //       IMAGE      //
        // -----------------//
        // For image getMultimedia
        if (listArticles.getMultimedia() != null && listArticles.getMultimedia().size() >= 1) {
            glide.load(listArticles.getMultimedia().get(0).getUrl())
                    .apply(RequestOptions.centerInsideTransform())
                    .into(imageArticle);
        }
        // For image getMedia
        else if(listArticles.getMedia() != null && listArticles.getMedia().size() >= 1) {
            glide.load(listArticles.getMedia().get(0).getMediaMetadata().get(0).getUrl())
                    .apply(RequestOptions.centerInsideTransform())
                    .into(imageArticle);
        }
        // If no Image
        else {
            glide.load(R.drawable.nytimes_default).into(imageArticle);
        }
    }

    @Override
    public void onClick(View v) {
        recyclerViewOnClickListener.recyclerViewOnClick(getAdapterPosition());
    }
}
