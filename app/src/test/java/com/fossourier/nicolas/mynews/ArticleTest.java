package com.fossourier.nicolas.mynews;

import com.fossourier.nicolas.mynews.Models.Article;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class ArticleTest {

    // Test returns of Article
    @Test
    public void articleTest(){

        Article article = new Article();

        // Response expected on set
        article.setStatus("OK");
        article.setCopyright("copyright");

        // Test on get with response expected
        assertEquals("OK", article.getStatus());
        assertEquals("copyright", article.getCopyright());
    }
}

