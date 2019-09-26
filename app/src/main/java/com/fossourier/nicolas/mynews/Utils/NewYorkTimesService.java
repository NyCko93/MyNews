package com.fossourier.nicolas.mynews.Utils;


import com.fossourier.nicolas.mynews.Models.Article;

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


    // API Topstories
    @GET("svc/topstories/v2/{section}.json?api-key=8MIF8xOhLGGy6TRdjaDVBoFg8ptFsAyN")
    Observable<Article> getArticleTopStories(@Path("section") String section);

    // API MostPopular
    @GET("svc/mostpopular/v2/viewed/{period}.json?api-key=j44lROnlbMTmVOH5659jL2BvIlXCGbn0")
    Observable<Article> getArticleMostPopular(@Path("period") String period);

    // API Search
    @GET("svc/search/v2/articlesearch.json?q=election&api-key=SuXrAyB1QH1ZPdFledBMSLAiwYXSq8bS")
    Observable<Article> getArticleSearch(@Query("q") String search, @Query("fq") List<String> listSection, @Query("begin_date") String beginDate, @Query("end_date") String endDate);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient().newBuilder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build();

}