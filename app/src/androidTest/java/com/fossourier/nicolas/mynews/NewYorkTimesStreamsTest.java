package com.fossourier.nicolas.mynews;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.SearchArticle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewYorkTimesStreamsTest {

    public static Observable<Article> streamFetchTopStoriesTest(){
        NewYorkTimesServiceTest newYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
        return newYorkTimesServiceTest.getTopStoriesArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamFetchMostPopular() {
        NewYorkTimesServiceTest newYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
        return newYorkTimesServiceTest.getMostPopularArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }

    public static Observable<SearchArticle> streamSearch() {
        NewYorkTimesServiceTest newYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
        return newYorkTimesServiceTest.getSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }

}
