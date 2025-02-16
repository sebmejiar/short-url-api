package com.meli.short_url_api.dto;

public record UrlDtoResponse(String longUrl, String shortUrl, Long visitCounter) {
}
