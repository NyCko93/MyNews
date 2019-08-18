package com.fossourier.nicolas.mynews.Views;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

// For RecyclerView
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title)
    TextView textView;
    @BindView(R.id.fragment_main_item_date)
    TextView date;


    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticles(Result articles){

        this.textView.setText(articles.getTitle());

        this.date.setText(articles.getPublishedDate());
    }

}
