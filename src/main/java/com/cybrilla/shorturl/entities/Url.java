package com.cybrilla.shorturl.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Url {
    @Id
    @GeneratedValue
    private long Id;
    @Lob @Column
    private String longUrl;
    @Column
    private String shortUrl;
    @Column
    private LocalDateTime createdOn;


    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }


    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Url(long id, String longUrl, String shortUrl, LocalDateTime createdOn) {
        Id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdOn = createdOn;
    }

    public Url() {
    }

    @Override
    public String toString() {
        return "Url{" +
                "Id=" + Id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
