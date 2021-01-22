package com.epam.movierating.filter;

import com.epam.movierating.command.request.HttpServletRequestWrapperImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestOriginal = (HttpServletRequest) request;
        HttpServletRequest requestWrapper = new HttpServletRequestWrapperImpl(requestOriginal);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
