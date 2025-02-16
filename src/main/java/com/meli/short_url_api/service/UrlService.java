package com.meli.short_url_api.service;

import com.meli.short_url_api.dto.UrlDtoRequest;
import com.meli.short_url_api.dto.UrlDtoResponse;
import com.meli.short_url_api.exception.ResourceAlreadyExistException;
import com.meli.short_url_api.exception.ResourceNotFoundException;
import com.meli.short_url_api.model.Url;
import com.meli.short_url_api.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private  UrlRepository urlRepository;

    public UrlDtoResponse create(UrlDtoRequest urlRequest) {
        String id = IdService.generateShortUrlId(urlRequest.url());
        if (urlRepository.existsById(id)) {
            throw new ResourceAlreadyExistException("Already exists");
        }
        Url urlRecord = new Url(id, urlRequest.url(), 0L);
        urlRepository.save(urlRecord);
        return new UrlDtoResponse(urlRequest.url(), id, urlRecord.getVisitCounter());
    }

    public UrlDtoResponse getUrlById(String id) {
        Url urlRecord = urlRepository.findById(id).orElse(null);
        if (urlRecord == null) {
            throw new ResourceNotFoundException("Recurso no encontrado");
        }
        return new UrlDtoResponse(urlRecord.getUrl(), id, urlRecord.getVisitCounter());
    }

    public void deleteUrlById(String id) {
        urlRepository.deleteById(id);
    }

    @Async
    public void asyncIncrementUrlCountById(String id) {
        urlRepository.findById(id).ifPresent(url -> {
            url.setVisitCounter(url.getVisitCounter()+1);
            urlRepository.save(url);
        });
    }
}
