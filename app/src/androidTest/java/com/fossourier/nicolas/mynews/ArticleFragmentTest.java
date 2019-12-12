package com.fossourier.nicolas.mynews;


import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.Models.MostPopularJson;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.Models.SearchArticle;
import com.fossourier.nicolas.mynews.Models.SearchArticleJson;
import com.fossourier.nicolas.mynews.Models.TopStoriesJson;

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

@RunWith(AndroidJUnit4.class)
public class ArticleFragmentTest {

    // Mockito mock the server
    @ClassRule
    public static MockWebServer server;

    @BeforeClass
    public static void setUpClass() {
        server = new MockWebServer();
    }

    // Test TopStories
    @Test
    public void fetchTopStoriesTest() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(TopStoriesJson.topStoriesJson));

        Observable<Article> observableArticles = NewYorkTimesStreamsTest.streamFetchTopStoriesTest();
        TestObserver<Article> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<Result> articles = testObserver.values().get(0).getResult();

        assertThat("The result list is not empty", !articles.isEmpty());
    }

    // Test MostPopular
    @Test
    public void fetchMostPopularTest() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MostPopularJson.mostPopularJson));

        Observable<Article> observableArticles = NewYorkTimesStreamsTest.streamFetchMostPopular();
        TestObserver<Article> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<Result> articles = testObserver.values().get(0).getResult();

        assertThat("The result list is not empty", !articles.isEmpty());

    }

    // Test SearchArticle
    @Test
    public void fetchSearchArticleTest() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(SearchArticleJson.searchArticleJson));

        Observable<SearchArticle> observableResult = NewYorkTimesStreamsTest.streamSearch();
        TestObserver<SearchArticle> testObserver = new TestObserver<>();

        observableResult.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        List<Doc> docs = testObserver.values().get(0).getResponse().getDocs();
        Log.e("test", "test");
        assertThat("The result list is not empty", !docs.isEmpty());
    }
}
