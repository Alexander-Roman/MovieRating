package com.epam.movierating.command.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequestWrapperImpl extends HttpServletRequestWrapper {

    private static final String AMPERSAND = "&";
    private static final String AMPERSAND_REPLACEMENT = "&amp;";
    private static final String LESS_THAN = "<";
    private static final String LESS_THAN_REPLACEMENT = "&lt;";
    private static final String GREATER_THAN = ">";
    private static final String GREATER_THAN_REPLACEMENT = "&gt;";

    public HttpServletRequestWrapperImpl(HttpServletRequest request) {
        super(request);
    }

    private String normalizeIncomingParameter(String value) {
        if (value == null) {
            return null;
        }

        return value
                .trim()
                .replace(AMPERSAND, AMPERSAND_REPLACEMENT)
                .replace(LESS_THAN, LESS_THAN_REPLACEMENT)
                .replace(GREATER_THAN, GREATER_THAN_REPLACEMENT);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return normalizeIncomingParameter(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        return Arrays.stream(values)
                .map(this::normalizeIncomingParameter)
                .toArray(String[]::new);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> originalParameters = super.getParameterMap();
        Map<String, String[]> normalizedParameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : originalParameters.entrySet()) {
            String key = entry.getKey();
            String[] values = this.getParameterValues(key);
            normalizedParameters.put(key, values);
        }
        return Collections.unmodifiableMap(normalizedParameters);
    }
}
