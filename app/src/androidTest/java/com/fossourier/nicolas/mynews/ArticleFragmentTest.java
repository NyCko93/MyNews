package com.fossourier.nicolas.mynews;



import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.MostPopularJson;
import com.fossourier.nicolas.mynews.Models.Result;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.MatcherAssert.assertThat;

//import com.fossourier.nicolas.mynews.Models.SearchArticleJson;
import com.fossourier.nicolas.mynews.Models.TopStoriesJson;

@RunWith(AndroidJUnit4.class)
public class ArticleFragmentTest  {

    //Mockito mock the server
    @ClassRule
    public static MockWebServer server;

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = new MockWebServer();
        server.start();
    }

//    @Before
//    public void setUp() throws Exception{
//        super.setUp();
//        injectInstrumentation(InstrumentationRegistry.getInstrumentation().getTargetContext());
//    }

    //Test MostPopular
    @Test
    public void fetchMostPopularTest() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MostPopularJson.mostPopularJson));

        Observable<Article> mObservable = NewYorkTimesStreamsTest.streamFetchMostPopularTest();
        TestObserver<Article> mTestObserver = new TestObserver<>();

        mObservable.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<Result> articles = mTestObserver.values().get(0).getResult();
        assertThat("The result list is not empty", !articles.isEmpty());

    }

    //Test TopStories
    @Test
    public void fetchTopStoriesTest() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(TopStoriesJson.topStoriesJson));

        Observable<Article> mObservable = NewYorkTimesStreamsTest.streamFetchTopStoriesTest();
        TestObserver<Article> mTestObserver = new TestObserver<>();

        mObservable.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<Result> articles = mTestObserver.values().get(0).getResult();
        assertThat("The result list is not empty", !articles.isEmpty());
    }

//    @Test
//    public void fetchSearchTest() {
//        server.enqueue(new MockResponse()
//                .setResponseCode(200)
//                .setBody(SearchArticleJson.searchArticleJson));
//
//        Observable<SearchArticle> mObservable = NewYorkTimesStreamsTest.streamArticleSearch();
//        TestObserver<SearchArticle> mTestObserver = new TestObserver<>();
//
//        mObservable.subscribeWith(mTestObserver)
//                .assertNoErrors()
//                .assertNoTimeout()
//                .awaitTerminalEvent();
//
//        List<Doc> docs = (List<Doc>) mTestObserver.values().get(0).getResponse().getDocs();
//        assertThat("The result list is not empty", !docs.isEmpty());
//    }
}
