package com.cybrilla.shorturl.dto;

public class DtoUrl {
    private String url;

    public DtoUrl(String url) {
        this.url = url;
    }

    public DtoUrl() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DtoUrl{" +
                "url='" + url + '\'' +
                '}';
    }
}
