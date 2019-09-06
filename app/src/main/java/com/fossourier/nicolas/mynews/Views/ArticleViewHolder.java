package com.fossourier.nicolas.mynews.Views;

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

import static com.fossourier.nicolas.mynews.R.drawable.nytimes_default;

// ----------------------//
// ITEM OF RECYCLERVIEW  //
// ----------------------//
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_section) TextView textSection;
    @BindView(R.id.fragment_main_item_date) TextView textDate;
    @BindView(R.id.fragment_main_item_content) TextView textContent;
    @BindView(R.id.fragment_main_item_image) ImageView imageArticle;

    private TextView title;
    private TextView articleAbstract;



    public ArticleViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);

    }
    public void updateWithArticles(Result listArticles, RequestManager glide) {

        // -----------------//
        //     ABSTRACT     //
        // -----------------//

        // Display Abstract
        this.textContent.setText(listArticles.getAbstract());

        // -----------------//
        //  PUBLISHED DATE  //
        // -----------------//

        // Display PublishedDate
        this.textDate.setText(listArticles.getPublishedDate());

        // ----------------------//
        // SECTION OR SUBSECTION //
        // ----------------------//

        // Display Section or SubSection if Section == null
        if (listArticles.getSection() == null) listArticles.getSubsection();
        else {
            this.textSection.setText(listArticles.getSection());
        }

        // -----------------//
        //       IMAGE      //
        // -----------------//

        // For image getMultimedia
        if (listArticles.getMultimedia() != null && listArticles.getMultimedia().size() >= 1) {
            glide.load(listArticles.getMultimedia().get(0).getUrl()).apply(RequestOptions.centerInsideTransform()).into(imageArticle);
        }
        // For image getMedia
        if (listArticles.getMedia() != null && listArticles.getMedia().size() >= 1) {
            glide.load(listArticles.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(RequestOptions.centerInsideTransform()).into(imageArticle);
        }

//        // For image per Default
//        imageArticle.setImageResource(nytimes_default);
    }

}
