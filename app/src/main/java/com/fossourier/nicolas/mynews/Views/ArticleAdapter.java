package com.fossourier.nicolas.mynews.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

// For RecyclerView
@SuppressWarnings("unused")
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    // FOR DATA
    private List<Result> listArticles;
    private ViewHolder viewHolder;
    private int position;
    public TextView textView;
    // 1 - Declaring a Glide object
    private RequestManager glide;

    // CONSTRUCTOR
    public ArticleAdapter(List<Result> listArticles, RequestManager glide) {
        this.listArticles = listArticles;
        this.glide = glide;
    }

    @NotNull
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
        viewHolder.updateWithArticles(this.listArticles.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return listArticles.size();
    }

}

