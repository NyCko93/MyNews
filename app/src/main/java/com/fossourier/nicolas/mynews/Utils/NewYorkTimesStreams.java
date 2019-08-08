//package com.fossourier.nicolas.mynews.Utils;
//
//import com.fossourier.nicolas.mynews.Models.TopStories.TopStories;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//
//public class NewYorkTimesStreams {
//    public  Observable<List<TopStories>> streamFetchUserFollowing(String section){
//        NewYorkTimesService newYorkTimesService = NewYorkTimesService.retrofit.create(NewYorkTimesService.class);
//        return NewYorkTimesService.getTopStories(section)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(10, TimeUnit.SECONDS);
//    }
//}
