package com.fossourier.nicolas.mynews.Utils;

import com.fossourier.nicolas.mynews.Models.Article;


import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewYorkTimesStreams {
    public static Observable<Article> streamFetchUserFollowing(String section){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleBySection(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(100, TimeUnit.SECONDS);
    }
}
