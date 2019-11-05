package com.fossourier.nicolas.mynews;

import com.bumptech.glide.RequestManager;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.Views.ResultOfSearchAdapter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ResultOfSearchAdapterTest {

    private List<Doc> listSearchArticle = new ArrayList<>();
    private RequestManager mGlide;
    private ResultOfSearchAdapter.ResultOfSearchRVOnClickListener mResultOfSearchRVOnClickListener;
    private ResultOfSearchAdapter mResultOfSearchAdapter = new ResultOfSearchAdapter(listSearchArticle, mGlide, mResultOfSearchRVOnClickListener);

    @Test
    public void getItemCountTest(){
        assertEquals(0, mResultOfSearchAdapter.getItemCount());
    }
}
