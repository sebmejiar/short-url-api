package com.meli.short_url_api.controller;

import com.meli.short_url_api.dto.UrlDtoResponse;
import com.meli.short_url_api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {
    @Autowired
    private UrlService urlService;

    @GetMapping(value = "/{id}")
    public String redirect(@PathVariable("id") String id) {
        UrlDtoResponse urlDtoResponse = urlService.getUrlById(id);
        urlService.asyncIncrementUrlCountById(id);
        return "redirect:" + urlDtoResponse.longUrl();
    }
}
