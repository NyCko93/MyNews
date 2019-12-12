package com.fossourier.nicolas.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultOfSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.fragment_result_search_item_section)
    TextView textSectionSearch;
    @BindView(R.id.fragment_result_search_item_date)
    TextView textDateSearch;
    @BindView(R.id.fragment_result_search_item_content)
    TextView textContentSearch;
    @BindView(R.id.fragment_result_search_item_image)
    ImageView imageSearch;

    final ResultOfSearchAdapter.ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener;

    ResultOfSearchViewHolder(View itemView, ResultOfSearchAdapter.ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mResultOfSearchRVOnClickListener = mResultOfSearchRVOnClickListener;
        ButterKnife.bind(this, itemView);
    }

    void updateWithResult(Doc listSearchArticle, RequestManager glide) {

        //---------------//
        //    ABSTRACT   //
        //---------------//
        if (listSearchArticle.getHeadline() != null) {
            this.textContentSearch.setText(listSearchArticle.getHeadline().getMain());
        }

        //-----------------//
        // PUBLISHED DATE  //
        //-----------------//
        if (listSearchArticle.getPubDate() != null) {
            this.textDateSearch.setText(listSearchArticle.getPubDate().substring(0, 10));
        }

        //--------------//
        // SECTION NAME //
        //--------------//
        if (listSearchArticle.getSectionName() != null) {
            this.textSectionSearch.setText(listSearchArticle.getSectionName());
        } else {
            textSectionSearch.setText("");
        }

        //-----------------//
        //      IMAGE      //
        //-----------------//
        if (listSearchArticle.getMultimedia() != null && listSearchArticle.getMultimedia().size() >= 1) {
            glide.load("https://static01.nyt.com/" + listSearchArticle.getMultimedia().get(0).getUrl())
                    .error(R.drawable.nytimes_default)
                    .into(imageSearch);
        } else {
            imageSearch.setImageResource(R.drawable.nytimes_default);
        }
}


    @Override
    public void onClick(View v) {
        mResultOfSearchRVOnClickListener.onSearchArticleClick(getAdapterPosition());
    }
}
