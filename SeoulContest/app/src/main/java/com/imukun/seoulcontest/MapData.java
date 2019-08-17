package com.imukun.seoulcontest;


import androidx.annotation.Keep;

import com.naver.maps.geometry.LatLng;

@Keep
public class MapData {

   String key;
    String complex;
    String name;
    String simple;
    String url;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    String star;

    String longitude;

    String latitude;

    public String getComplex() {
        return complex;
    }

    public void setComplex(String complex) {
        this.complex = complex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public MapData() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }






    public String getKey() { return key; }
    public void setKey(String key) {
        this.key = key;
    }



    public LatLng getLatLng(){
        LatLng latlng = new LatLng(Double.parseDouble(getLatitude()),Double.parseDouble(getLongitude()));
        return latlng;
    }

}


