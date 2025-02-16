package com.meli.short_url_api.repository;

import com.meli.short_url_api.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
