package com.fossourier.nicolas.mynews.Views;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;


import butterknife.BindView;
import butterknife.ButterKnife;

// For RecyclerView
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView textTitle;
    @BindView(R.id.fragment_main_item_date) TextView textDate;
    @BindView(R.id.fragment_main_item_description) TextView textAbstract;
    @BindView(R.id.fragment_main_item_image) ImageView imageArticle;



    public ArticleViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);

    }
    public void updateWithArticles(Result listArticles) {

        this.textTitle.setText(listArticles.getTitle());
        this.textDate.setText(listArticles.getUpdatedDate());
        this.textAbstract.setText(listArticles.getByline());

    }

}
