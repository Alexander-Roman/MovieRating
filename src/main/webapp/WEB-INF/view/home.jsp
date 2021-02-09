<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <title><fmt:message key="page.home.title"/></title>
    <jsp:include page="../template/metadata.jsp"/>
</head>

<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">

            <!-- Sidebar here -->

            <div class="main">
                <h2><fmt:message key="movies.header"/></h2>

                <ctg:access accessName="newMovie">
                    <div class="add-button-container">
                        <a class="add-button" href="<c:url value="controller?command=newMovie"/>">
                            <fmt:message key="movies.button.add"/>
                        </a>
                    </div>
                </ctg:access>

                <table class="table" id="movies">
                    <tr>
                        <th class="wide"><fmt:message key="movies.table.header.number"/></th>
                        <th><fmt:message key="movies.table.header.title"/></th>
                        <th class="wide"><fmt:message key="movies.table.header.director"/></th>
                        <th class="wide"><fmt:message key="movies.table.header.year"/></th>
                        <th><fmt:message key="movies.table.header.rating"/></th>
                    </tr>
                    <c:forEach var="movie" items="${requestScope.movies}" varStatus="counter">
                        <tr data-movie-page="<c:url value="controller?command=movie&id=${movie.id}"/>">
                            <td class="wide">${counter.count + requestScope.itemsPerPage * (requestScope.page - 1)}</td>

                            <td>
                                <div class="title-cell">
                                    <div class="title-cell-img">
                                        <c:choose>
                                            <c:when test="${movie.posterPath == null}">
                                                <img src="<c:url value="/static/img/posters/NO_POSTER_AVAILABLE.jpg"/>"
                                                     alt="">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="<c:url value="${movie.posterPath}"/>" alt="">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="title-cell-text">${movie.title}</div>
                                </div>
                            </td>

                            <td class="wide">${movie.director}</td>
                            <td class="wide">${movie.releaseYear}</td>
                            <td><fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                  value="${movie.rating}"/></td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="flex-middle">
                    <div class="pagination">
                        <c:choose>
                            <c:when test="${requestScope.page > 1}">
                                <a class="pagination-available"
                                   href="<c:url value="controller?command=home&page=${requestScope.page - 1}"/>">&laquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a class="pagination-disabled">&laquo;</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="i" begin="1" end="${requestScope.numberOfPages}">
                            <c:choose>
                                <c:when test="${i == requestScope.page}">
                                    <a class="pagination-active">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="pagination-available"
                                       href="<c:url value="controller?command=home&page=${i}"/>">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${requestScope.page < requestScope.numberOfPages}">
                                <a class="pagination-available"
                                   href="<c:url value="controller?command=home&page=${requestScope.page + 1}"/>">&raquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a class="pagination-disabled">&raquo;</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>
</div>
<script src="<c:url value="/static/js/movie-table-row-link.js"/>"></script>
<!-- Sidebar script -->
</body>
</html>
