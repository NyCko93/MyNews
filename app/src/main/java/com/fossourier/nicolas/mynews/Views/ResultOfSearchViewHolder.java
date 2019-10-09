package com.fossourier.nicolas.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultOfSearchViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.fragment_main_item_date)
    TextView textViewDate;
    @BindView(R.id.fragment_main_item_image)
    ImageView mImageView;
    @BindView(R.id.fragment_main_item_section)
    TextView textViewSection;
    @BindView(R.id.item_search)
    RelativeLayout mRelativeLayout;
    private static final String URL = "https://static01.nyt.com/";

    ResultOfSearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void updateWithResult(final Doc articles, RequestManager glide,
                          final ResultOfSearchAdapter.onSearchArticleAdapterListener callback) {
        if (articles.getSectionName() != null) {
            this.textViewSection.setText(articles.getSectionName());
        } else {
            textViewSection.setText("");
        }
        if (articles.getPubDate() != null) {
            String date = articles.getPubDate().substring(0, 10);
            this.textViewDate.setText(date);
        }
        if (!articles.getMultimedia().isEmpty()) {
            String mUrl = URL + articles.getMultimedia().get(0).getUrl();
            glide.load(mUrl).apply(RequestOptions.centerCropTransform()).into(mImageView);
        } else {
            mImageView.setImageResource(R.drawable.nytimes_default);
        }
        this.mRelativeLayout.setOnClickListener(v -> callback.onArticleClicked(articles));
    }
}
