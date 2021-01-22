package com.epam.movierating.command.request;

import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpServletRequestWrapperImpl extends HttpServletRequestWrapper {

    public HttpServletRequestWrapperImpl(HttpServletRequest request) {
        super(request);
    }

    private String normalizeIncomingParameter(String value) {
        if (value == null) {
            return null;
        }

        return value
                .trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return normalizeIncomingParameter(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        return Arrays.stream(values)
                .map(this::normalizeIncomingParameter)
                .toArray(String[]::new);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Enumeration<String> enumeration = super.getParameterNames();
        Map<String, String[]> parameters = Collections.list(enumeration).stream().collect(Collectors.toMap(
                key -> key,
                this::getParameterValues
        ));
        return Collections.unmodifiableMap(parameters);
    }
}
