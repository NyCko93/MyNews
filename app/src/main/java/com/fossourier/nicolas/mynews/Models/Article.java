//package com.fossourier.nicolas.mynews.Models;
//
//// Model of article for RV
//
//import com.fossourier.nicolas.mynews.Models.TopStories.ResultTopStories;
//import com.fossourier.nicolas.mynews.Models.TopStories.TopStories;
//
//import java.util.List;
//
//public class Article {
//
//    // URL of article
//    private String url;
//    // date
//    private String date;
//    // Title of article
//    private String title;
//    // a short course
//    private String resume;
//
//    public Article() {
//    }
//
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setResume(String resume) {
//        this.resume = resume;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getResume() {
//        return resume;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public static void listTopStories(List<Article> listArticle, TopStories topStories) {
//        for (ResultTopStories resultTopStories : topStories.getResults()) {
//            Article article = new Article();
//
//            article.setUrl(resultTopStories.getUrl());
//
//            article.setDate(resultTopStories.getCreatedDate());
//
//            article.setResume(resultTopStories.getTitle());
//
//            listArticle.add(article);
//        }
//
//    }
//
//}