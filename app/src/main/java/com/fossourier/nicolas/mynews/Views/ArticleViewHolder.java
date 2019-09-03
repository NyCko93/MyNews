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

// For RecyclerView
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

        // Display Abstract
        this.textContent.setText(listArticles.getAbstract());

        // Display PublishedDate
        this.textDate.setText(listArticles.getPublishedDate());

        // Display Section or SubSection if Section == null
        if (listArticles.getSection() == null) listArticles.getSubsection();
        else {
            this.textSection.setText(listArticles.getSection());
        }

        // display image per default
        imageArticle.setImageResource(nytimes_default);

//        // Display image or image per default if no image
//        if(listArticles.getMultimedia().size() > 1) {
//            glide.load(listArticles.getMultimedia()).apply(RequestOptions.circleCropTransform()).into(imageArticle);
//        }
//        else if (listArticles.getMultimedia().size() < 1){
//            glide.load(listArticles.getMedia()).apply(RequestOptions.circleCropTransform()).into(imageArticle);
//        }
//
//        else {
//            imageArticle.setImageResource(nytimes_default);
//        }
    }

}
