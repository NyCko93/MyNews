//package com.fossourier.nicolas.mynews.Models.MostPopular;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//// API-KEY = https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=j44lROnlbMTmVOH5659jL2BvIlXCGbn0
//
//public class MostPopular {
//
//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("copyright")
//    @Expose
//    private String copyright;
//    @SerializedName("num_results")
//    @Expose
//    private Integer numResults;
//    @SerializedName("results")
//    @Expose
//    private List<ResultMostPopular> results = null;
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
//    public Integer getNumResults() {
//        return numResults;
//    }
//
//    public void setNumResults(Integer numResults) {
//        this.numResults = numResults;
//    }
//
//    public List<ResultMostPopular> getResults() {
//        return results;
//    }
//
//    public void setResults(List<ResultMostPopular> results) {
//        this.results = results;
//    }
//
//}