package com.monit.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public class RequestValidationBeforefilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (null != header) {
            header = header.trim();
            if (StringUtils.startsWithIgnoreCase(header, "Basic")) {
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try {
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, StandardCharsets.UTF_8);
                    if (token.contains(":")) {
                        String[] parts = token.split(":");  //username:password
                        if (parts[0].toLowerCase().contains("test")) {
                            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            return;
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid basic authentication token");
                    }
                } catch (IllegalArgumentException e) {
                    throw new ServletException("Failed to decode basic authentication token");
                }
            }
        }
        chain.doFilter(request, response);
    }
}
