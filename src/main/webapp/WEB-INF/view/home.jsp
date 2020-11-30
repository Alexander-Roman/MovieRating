<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/localization"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="page.home.title"/></title>
    <jsp:include page="../template/metadata.jsp"/>
</head>

<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main flex-middle">
        <div class="row">
            <div class="main">
                <h2>MOVIES RATING</h2>
                <div class="flex flex-right">
                    <button class="add-button" type="submit">+ Add</button>
                </div>


                <table class="table" id="movies">
                    <tr>
                        <th>No.</th>
                        <th>Movie</th>
                        <th>Director</th>
                        <th>Year</th>
                        <th>Rating</th>
                    </tr>
                    <c:forEach var="movie" items="${requestScope.movies}" varStatus="counter">
                        <tr data-movie-page="<c:url value="controller?command=moviePage&movieId=${movie.id}"/>">
                            <td>${counter.count}</td>
                            <td>${movie.title}</td>
                            <td>${movie.director}</td>
                            <td>${movie.releaseYear}</td>
                            <td>${movie.rating}</td>
                        </tr>
                    </c:forEach>
                </table>


                <div class="flex-middle">
                    <div class="pagination">
                        <a href="#">&laquo;</a>
                        <a href="#">1</a>
                        <a class="active" href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#">6</a>
                        <a href="#">&raquo;</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>
    <script src="<c:url value="/static/js/movie-table-row-link.js"/>"></script>
</div>
</body>
</html>
