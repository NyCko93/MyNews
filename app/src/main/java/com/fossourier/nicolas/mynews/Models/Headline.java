package com.fossourier.nicolas.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Headline {

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("content_kicker")
    @Expose
    private String contentKicker;
    @SerializedName("print_headline")
    @Expose
    private String printHeadline;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("seo")
    @Expose
    private Object seo;
    @SerializedName("sub")
    @Expose
    private Object sub;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getContentKicker() {
        return contentKicker;
    }

    public void setContentKicker(String contentKicker) {
        this.contentKicker = contentKicker;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getSeo() {
        return seo;
    }

    public void setSeo(Object seo) {
        this.seo = seo;
    }

    public Object getSub() {
        return sub;
    }

    public void setSub(Object sub) {
        this.sub = sub;
    }

}
