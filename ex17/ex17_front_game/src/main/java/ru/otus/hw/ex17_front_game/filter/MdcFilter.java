package ru.otus.hw.ex17_front_game.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class MdcFilter extends OncePerRequestFilter {
    public static final String HEADER_X_REQUEST_ID = "X-Request-Id";

    public static final String MDC_REQUEST_ID = "requestId";

    public static final String MDC_AUTHORIZATION = "Authorization";

    private final Logger log = LoggerFactory.getLogger(MdcFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var xRequestId = request.getHeader(HEADER_X_REQUEST_ID);
        log.debug("xRequestId:{}", xRequestId);
        if (xRequestId != null) {
            MDC.put(MDC_REQUEST_ID, xRequestId);
        }
        var xAuthorization = request.getHeader(MDC_AUTHORIZATION);
        log.debug("xAuthorization:{}", xAuthorization);
        if (xAuthorization != null) {
            MDC.put(MDC_AUTHORIZATION, xAuthorization);
        }
        var headerIterator = request.getHeaderNames().asIterator();
        var headers = new ArrayList<String>();
        while (headerIterator.hasNext()) {
            var head = headerIterator.next();
            headers.add(head);
            log.debug("headers :{}, {}", head, request.getHeader(head));
        }
        log.debug("request headers:{}", headers);
        response.addHeader(HEADER_X_REQUEST_ID, xRequestId);
        filterChain.doFilter(request, response);
        MDC.remove(MDC_REQUEST_ID);
        MDC.remove(MDC_REQUEST_ID);
        log.debug("response headers:{}", response.getHeaderNames());
    }
}
