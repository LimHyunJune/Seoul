package com.imukun.seoulcontest;

import androidx.annotation.Keep;

@Keep
public class OSSData {
    String title = "";
    String url = "";
    String copyright = "";
    String license = "";

    public OSSData() {
    }



    public OSSData(String title, String url, String copyright, String license) {
        this.title = title;
        this.url = url;
        this.copyright = copyright;
        this.license = license;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}