package com.fossourier.nicolas.mynews;

import androidx.test.runner.AndroidJUnit4;
import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class NewYorkTimesStreamsTest {

    @Test
    public void streamTopStoriesTest() {
        Observable<Article> observable = NewYorkTimesStreams.streamTopStories("books");
        TestObserver<Article> mTestObserver = new TestObserver<>();
        observable.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        Article articlesTopStories = mTestObserver.values().get(0);
        assertTrue(articlesTopStories.getResult().size() > 0);
    }

    @Test
    public void checkHttpRequest() {
        Observable<Article> observableArticles = NewYorkTimesStreams.streamMostPopular("30");
        TestObserver<Article> mTestObserver = new TestObserver<>();
        observableArticles.subscribeWith(mTestObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();
        Article articlesMostPopular = mTestObserver.values().get(0);
        assertTrue(articlesMostPopular.getResult().size() > 0);
    }

}
