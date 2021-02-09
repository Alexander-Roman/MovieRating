<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<div class="side">
    <h2><fmt:message key="movies.sidebar.header"/></h2>
    <div class="search-container flex-box">
        <input type="text" placeholder="<fmt:message key="movies.sidebar.search.placeholder"/>" name="search">
        <button class="button" type="submit"><fmt:message key="movies.sidebar.search.button.submit"/></button>
    </div>
    <h3><fmt:message key="movies.sidebar.filters.header"/></h3>
    <p><fmt:message key="movies.sidebar.filter.header.year"/></p>
    <div class="multi-range-container">
        <div class="flex-box">
            <label>from <input type="number" class="lower-value" min="1994" max="2020" step="1" value="1994"></label>
            <label>to <input type="number" class="upper-value" min="1994" max="2020" step="1" value="2020"></label>
        </div>
        <div class="slider-container">
            <input type="range" class="slider upper-slider" min="1994" max="2020" step="1" value="2020">
            <input type="range" class="slider lower-slider" min="1994" max="2020" step="1" value="1994">
        </div>
    </div>
    <p><fmt:message key="movies.sidebar.filter.header.rating"/></p>
    <div class="multi-range-container">
        <div class="flex-box">
            <label>from <input type="number" class="lower-value" min="0" max="10" step="1" value="0"></label>
            <label>to <input type="number" class="upper-value" min="0" max="10" step="1" value="10"></label>
        </div>
        <div class="slider-container">
            <input type="range" class="slider upper-slider" min="0" max="10" step="1" value="10">
            <input type="range" class="slider lower-slider" min="0" max="10" step="1" value="0">
        </div>
    </div>
    <label class="checkbox-container"><fmt:message key="movies.sidebar.filter.checkbox"/>
        <input type="checkbox" checked="checked" name="unrated">
        <span class="checkmark"></span>
    </label>
    <div class="search-container flex-box">
        <button class="button"><fmt:message key="movies.sidebar.filter.button.reset"/></button>
        <button class="button" type="submit"><fmt:message key="movies.sidebar.filter.button.submit"/></button>
    </div>
</div>