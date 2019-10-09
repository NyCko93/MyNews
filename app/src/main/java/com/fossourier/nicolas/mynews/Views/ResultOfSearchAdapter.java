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

    private final onSearchArticleAdapterListener mListener;
    private List<Doc> mSearch;
    private RequestManager mGlide;

    public ResultOfSearchAdapter(List<Doc> search, RequestManager glide,
                                 onSearchArticleAdapterListener searchArticleAdapterListener) {
        this.mListener = searchArticleAdapterListener;
        this.mSearch = search;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public ResultOfSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_result_of_search_item, parent, false);
        return new ResultOfSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultOfSearchViewHolder holder, int position) {
        if (mSearch != null && mSearch.size() >= position + 1 && mSearch.get(position) != null) {
            holder.updateWithResult(this.mSearch.get(position), this.mGlide, mListener);
        }
    }

    @Override
    public int getItemCount() {
        return this.mSearch.size();
    }

    public interface onSearchArticleAdapterListener {
        void onArticleClicked(Doc resultTopStories);
    }
}
