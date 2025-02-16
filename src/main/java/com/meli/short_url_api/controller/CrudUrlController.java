package com.meli.short_url_api.controller;

import com.meli.short_url_api.dto.UrlDtoRequest;
import com.meli.short_url_api.dto.UrlDtoResponse;
import com.meli.short_url_api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/crud")
public class CrudUrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping(value = "/short-url")
    public UrlDtoResponse sent(@RequestBody UrlDtoRequest urlRequest) {
        return urlService.create(urlRequest);
    }

    @GetMapping(value = "/short-url")
    public UrlDtoResponse getUrlById (@RequestParam ("shortUrlId") String id) {
        return urlService.getUrlById(id);
    }

    @DeleteMapping(value = "/short-url")
    public void delete (@RequestParam ("shortUrlId") String id) {
        urlService.deleteUrlById(id);
    }
}
