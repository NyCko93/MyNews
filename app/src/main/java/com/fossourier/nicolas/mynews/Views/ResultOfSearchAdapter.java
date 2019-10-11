package com.fossourier.nicolas.mynews.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.R;

import java.util.List;

public class ResultOfSearchAdapter extends RecyclerView.Adapter<ResultOfSearchViewHolder> {

    private final ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener;
    private List<Doc> listSearchArticle;
    private RequestManager mGlide;


    public ResultOfSearchAdapter(List<Doc> listSearchArticle, RequestManager glide, ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener) {
        this.mResultOfSearchRVOnClickListener = mResultOfSearchRVOnClickListener;
        this.listSearchArticle = listSearchArticle;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public ResultOfSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_result_of_search_item, parent, false);
        return new ResultOfSearchViewHolder(view, mResultOfSearchRVOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultOfSearchViewHolder holder, int position) {
        if (listSearchArticle != null && listSearchArticle.size() >= position + 1 && listSearchArticle.get(position) != null) {
            holder.updateWithResult(this.listSearchArticle.get(position), this.mGlide);
        }
    }

    @Override
    public int getItemCount() {
        return this.listSearchArticle.size();
    }

    // Interface to configure a listener on RecyclerView items
    public interface ResultOfSearchRVOnClickListener {
        void onSearchArticleClick(int position);
    }

}
