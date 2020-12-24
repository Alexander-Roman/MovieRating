<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<footer class="page-footer">
    <div class="footer">
        <h2><fmt:message key="footer.text"/></h2>
        <a class="download-link" href="https://github.com/Alexander-Roman/MovieRating/archive/review.zip"><fmt:message key="footer.download"/></a>
    </div>
</footer>