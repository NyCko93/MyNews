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

    // Interface to configure a listener on RecyclerView items
    public interface RecyclerViewOnClickListener {

        void recyclerViewOnClick(int position);
    }

    private final RecyclerViewOnClickListener listener;
    private List<Result> listArticles;
    private ViewHolder viewHolder;
    private int position;
    public TextView textView;
    private RequestManager glide;

    public ArticleAdapter(List<Result> listArticles, RequestManager glide, RecyclerViewOnClickListener listener) {
        this.listArticles = listArticles;
        this.glide = glide;
        this.listener = listener;
    }

    @NotNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);
        return new ArticleViewHolder(view, listener);
    }

      // ---------------------------------//
     // UPDATE VIEW HOLDER WITH ARTICLES //
    // ---------------------------------//
    @Override
    public void onBindViewHolder(ArticleViewHolder viewHolder, int position) {
        viewHolder.updateWithArticles(this.listArticles.get(position), this.glide);
    }

      // --------------------------------------------//
     // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST //
    // --------------------------------------------//
    @Override
    public int getItemCount() {
        return listArticles.size();
    }
}

