package com.fossourier.nicolas.mynews.Utils;


import androidx.annotation.Nullable;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.SearchArticle;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NewYorkTimesService {

    String API_KEY = "api-key=h45EgQX6GOTNO8bJjrewi1URXmPvi54O";

    // API Topstories
    @GET("svc/topstories/v2/{section}.json?" + API_KEY)
    Observable<Article> getArticleTopStories(@Path("section") String section);

    // API MostPopular
    @GET("svc/mostpopular/v2/viewed/{period}.json?" + API_KEY)
    Observable<Article> getArticleMostPopular(@Path("period") String period);

    // API Search
    @GET("svc/search/v2/articlesearch.json?sort=newest&" + API_KEY)
    Observable<SearchArticle> getArticleSearch(@Query("q") String search, @Nullable @Query("fq") List<String> listSections, @Nullable @Query("begin_date") String beginDate, @Nullable @Query("end_date") String endDate);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient().newBuilder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build();

}