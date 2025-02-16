package com.meli.short_url_api.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UrlFilter implements Filter {
    Logger log = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getRequestURI();

        if (path.contains("/crud")) { //contenga la palabra crud en la URL
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(path);
            dispatcher.forward(httpRequest, httpResponse);
        } else if (!"/crud".contains(path)) {
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/redirect" + path);
            dispatcher.forward(httpRequest, httpResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
