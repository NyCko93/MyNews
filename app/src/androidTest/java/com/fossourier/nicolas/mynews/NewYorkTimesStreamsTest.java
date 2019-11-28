package com.fossourier.nicolas.mynews;

import androidx.test.runner.AndroidJUnit4;
import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.SearchArticle;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NewYorkTimesStreamsTest {


    @Test
    public void streamTopStoriesTest() {

        // Stream
        Observable<Article> observableTopStoriesTest = NewYorkTimesStreams.streamFetchTopStories("home");
        // Create Observer
        TestObserver<Article> mTestObserver = new TestObserver<>();
        // Launch Observable
        observableTopStoriesTest.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        // Get List
        Article mTopStories = mTestObserver.values().get(0);
        // Check if connection status is OK
        assertEquals("OK", mTopStories.getStatus());
        // Check if results exist
        assertNotNull(mTopStories.getNumResults());
    }


    @Test
    public void streamMostPopularTest() {
        // Stream
        Observable<Article> mMostPopularObservable = NewYorkTimesStreams.streamFetchMostPopular("7");

        // Create Observer
        TestObserver<Article> mTestObserver = new TestObserver<>();

        // Launch Observable
        mMostPopularObservable.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        // Get List
        Article mMostPopular = mTestObserver.values().get(0);
        // Check if connection status is OK
        assertEquals("OK", mMostPopular.getStatus());
        // Check if results exist
        assertNotNull(mMostPopular.getNumResults());
    }

//    @Test
//    public static Observable<SearchArticle> streamArticleSearchTest() {
//
//        // Query terms
//        String mQuery = "Netflix";
//        // Stream
//        Observable<SearchArticle> mArticleSearchObservable = NewYorkTimesStreams.streamFetchArticleSearch
//                (mQuery, Collections.singletonList("arts"), "20190501", "20190514" );
//        // Create Observer
//        TestObserver<SearchArticle> mTestObserver = new TestObserver<>();
//        // Launch Observable
//        mArticleSearchObservable.subscribeWith(mTestObserver)
//                .assertNoErrors()
//                .assertNoTimeout()
//                .awaitTerminalEvent();
//        // Get List
//        SearchArticle mSearchArticle = mTestObserver.values().get(0);
//        // Check if connection status is OK
//        assertEquals("OK", mSearchArticle.getStatus());
//        // Check if results exist
//        assertNotNull(mSearchArticle.getResponse());
//        return mArticleSearchObservable;
//    }

    public static Observable<Article> streamFetchTopStoriesTest(){
        NewYorkTimesServiceTest mNewYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
        return mNewYorkTimesServiceTest.getArticleTopStoriesTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(1000, TimeUnit.SECONDS);
    }

    public static Observable<Article> streamFetchMostPopularTest(){
        NewYorkTimesServiceTest mNewYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
        return mNewYorkTimesServiceTest.getArticleMostPopularTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(1000, TimeUnit.SECONDS);
    }
//
//    public static Observable<SearchArticle>  streamFetchSearchArticleTest(){
//        NewYorkTimesServiceTest mNewYorkTimesServiceTest = NewYorkTimesServiceTest.retrofit.create(NewYorkTimesServiceTest.class);
//        return mNewYorkTimesServiceTest.getArticleSearchTest()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(1000, TimeUnit.SECONDS);
//    }
}
