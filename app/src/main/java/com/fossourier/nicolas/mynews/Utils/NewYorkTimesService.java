//package com.fossourier.nicolas.mynews.Utils;
//
//
//import android.database.Observable;
//
//import com.fossourier.nicolas.mynews.Models.TopStories.TopStories;
//
//
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.GET;
//import retrofit2.http.Path;
//
//public interface NewYorkTimesService {
//    //api Topstories
//    @GET("svc/topstories/v2/{section}.json?api-key=8MIF8xOhLGGy6TRdjaDVBoFg8ptFsAyN")
//     Observable<TopStories> getTopStories(@Path("section") String section);
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://api.nytimes.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build();
//}
