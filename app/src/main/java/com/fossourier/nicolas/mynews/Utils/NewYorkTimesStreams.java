package com.fossourier.nicolas.mynews.Utils;

import androidx.annotation.Nullable;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.SearchArticle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.fossourier.nicolas.mynews.Utils.NewYorkTimesService.API_KEY;


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

//    public static Observable<SearchArticle> streamArticleSearch
//            (String search, String listSection, String beginDate, String endDate) {
//        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
//        return newYorkTimesService.getArticleSearch(search, listSection, beginDate, endDate)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(1000, TimeUnit.SECONDS);
//    }

//    // getting searched articles
//    public static Observable<SearchArticle> streamArticleSearch
//    (String search, List<String> category, String beginDate, String endDate) {
//        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
//        return newYorkTimesService.getArticleSearch(search, category, beginDate, endDate, "relevance")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(10, TimeUnit.SECONDS);
//    }

    public static Observable<SearchArticle> streamArticleSearch(String search, @Nullable List<String> listSections, @Nullable String beginDate, @Nullable String endDate){
        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
        return newYorkTimesService.getArticleSearch(search, listSections, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
