package com.fossourier.nicolas.mynews.Utils;

import com.fossourier.nicolas.mynews.Models.Article;


import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewYorkTimesStreams {

    public static Observable<Article> streamTopStories(){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(100, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamBusiness(){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleBusiness()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(100, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamMostPopular(){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(100, TimeUnit.SECONDS);
    }

//    public static Observable<Article> streamMovieReviews(){
//        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
//        return newYorkTimesService.getArticleMovieReviews()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(100, TimeUnit.SECONDS);
//    }


}
