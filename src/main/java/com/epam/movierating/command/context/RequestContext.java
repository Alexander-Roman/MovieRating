package com.epam.movierating.command.context;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestContext {

    private final Map<String, String> parameters;
    private final Map<String, Object> attributes;

    public RequestContext(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        parameters = Collections.list(paramNames).stream().collect(Collectors.toMap(
                String::toString,
                request::getParameter
        ));
        Enumeration<String> attrNames = request.getParameterNames();
        attributes = Collections.list(attrNames).stream().collect(Collectors.toMap(
                String::toString,
                request::getAttribute
        ));
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }
}
