package com.fossourier.nicolas.mynews;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.Views.ArticleAdapter;
import com.fossourier.nicolas.mynews.Views.ResultOfSearchAdapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdapterTest {

    // For TopStories
    private List<Result> listArticles = new ArrayList<>();
    private RequestManager glide;
    private ArticleAdapter.RecyclerViewOnClickListener listener;
    private ArticleAdapter mArticleAdapter = new ArticleAdapter(listArticles, glide, listener);

    @Test
    public void getItemCountTest(){
        assertEquals(0, mArticleAdapter.getItemCount());
    }

    // For Search
    private List<Doc> listSearchArticle = new ArrayList<>();
    private RequestManager mGlide;
    private ResultOfSearchAdapter.ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener;
    private ResultOfSearchAdapter mResultOfSearchAdapter = new ResultOfSearchAdapter(listSearchArticle, mGlide, mResultOfSearchRVOnClickListener);

    @Test
    public void getSearchItemCountTest(){
        assertEquals(0, mResultOfSearchAdapter.getItemCount());
    }

}
