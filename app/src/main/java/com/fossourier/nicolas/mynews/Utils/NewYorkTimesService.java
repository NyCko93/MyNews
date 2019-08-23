package com.fossourier.nicolas.mynews.Utils;


import com.fossourier.nicolas.mynews.Models.Article;



import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface NewYorkTimesService {

    //api TopStories
    @GET("svc/topstories/v2/{section}.json?api-key=8MIF8xOhLGGy6TRdjaDVBoFg8ptFsAyN")
    Observable<Article> getArticleTopStories(@Path("section") String section);

//    //api MostPopular
//    @GET("svc/mostpopular/v2/viewed/1.json?api-key=j44lROnlbMTmVOH5659jL2BvIlXCGbn0")
//    Observable<Article> getArticleMostPopular();
//
//    //api MovieReviews
//    @GET("svc/movies/v2/reviews/search.json?query=godfather&api-key=biMS0gHdAmLXykM0YkXUq9eZRixfoUet")
//    Observable<Article> getArticleMovieReviews();




    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient().newBuilder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build();
}