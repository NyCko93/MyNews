//package com.fossourier.nicolas.mynews.Models.MovieReviews;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import javax.xml.transform.Result;
//
//// API-KEY = https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=godfather&api-key=biMS0gHdAmLXykM0YkXUq9eZRixfoUet
//
//public class MovieReviews {
//
//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("copyright")
//    @Expose
//    private String copyright;
//    @SerializedName("has_more")
//    @Expose
//    private Boolean hasMore;
//    @SerializedName("num_results")
//    @Expose
//    private Integer numResults;
//    @SerializedName("results")
//    @Expose
//    private List<ResultMovieReviews> results = null;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getCopyright() {
//        return copyright;
//    }
//
//    public void setCopyright(String copyright) {
//        this.copyright = copyright;
//    }
//
//    public Boolean getHasMore() {
//        return hasMore;
//    }
//
//    public void setHasMore(Boolean hasMore) {
//        this.hasMore = hasMore;
//    }
//
//    public Integer getNumResults() {
//        return numResults;
//    }
//
//    public void setNumResults(Integer numResults) {
//        this.numResults = numResults;
//    }
//
//    public List<ResultMovieReviews> getResults() {
//        return results;
//    }
//
//    public void setResults(List<ResultMovieReviews> results) {
//        this.results = results;
//    }
//
//}