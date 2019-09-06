package com.fossourier.nicolas.mynews.Utils;

import com.fossourier.nicolas.mynews.Models.Article;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewYorkTimesStreams {

    public static Observable<Article> streamTopStories(String section) {
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(1000, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamMostPopular(String period) {
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(1000, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamBusiness(String section) {
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleBusiness(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(1000, TimeUnit.SECONDS);
    }
}
