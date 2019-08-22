package com.fossourier.nicolas.mynews.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;



import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

// For RecyclerView
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    // FOR DATA
    private List<Result> listArticles;
    private ViewHolder viewHolder;
    private int position;
    public TextView textView;


    // CONSTRUCTOR
    public ArticleAdapter(List<Result> listArticles) {
        this.listArticles = listArticles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new ArticleViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH ARTICLES
    @Override
    public void onBindViewHolder(ArticleViewHolder viewHolder, int position) {
        viewHolder.updateWithArticles(this.listArticles.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    public void setItemCount(int size ){
        int articleSize =listArticles.size();
        articleSize = size;
    }

    public Result getlistArticles(int position) {
        return this.listArticles.get(position);
    }
}

