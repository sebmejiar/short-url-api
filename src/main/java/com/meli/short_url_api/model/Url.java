package com.meli.short_url_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "short_url_collection")
public class Url {
   @Id
   private String id;
   private String url;
   private Long visitCounter;

    public Url(String id, String url, long visitCounter) {
        this.id = id;
        this.url = url;
        this.visitCounter = visitCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(Long visitCounter) {
        this.visitCounter = visitCounter;
    }
}
