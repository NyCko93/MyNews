package com.fossourier.nicolas.mynews;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.Views.ArticleAdapter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ArticleAdapterTest {

    private List<Result> listArticles = new ArrayList<>();
    private RequestManager glide;
    private ArticleAdapter.RecyclerViewOnClickListener listener;
    private ArticleAdapter mArticleAdapter = new ArticleAdapter(listArticles, glide, listener);

    @Test
    public void getItemCountTest(){
        assertEquals(0, mArticleAdapter.getItemCount());
    }
}
