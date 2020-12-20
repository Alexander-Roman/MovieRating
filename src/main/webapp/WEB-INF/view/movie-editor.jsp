<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<jsp:include page="../template/metadata.jsp"/>
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">
            <div class="main">
                <form class="movie-edit-form" enctype='multipart/form-data' action="<c:url value="/controller"/>"
                      method="post">
                    <input type="hidden" name="command" value="saveMovie">
                    <input type="hidden" name="id" value="${requestScope.movie.id}">
                    <input type="hidden" name="posterPath" value="${requestScope.movie.posterPath}">
                    <input type="hidden" name="rating" value="${requestScope.movie.rating}">

                    <h2><label for="edit-title"><fmt:message key="editor.form.label.title"/></label></h2>
                    <input type="text" id="edit-title" name="title"
                           placeholder="<fmt:message key="editor.form.input.title.placeholder"/>"
                           value="${requestScope.movie.title}" maxlength="255" required>
                    <div class="movie-info">
                        <div class="poster-container">
                            <c:choose>
                                <c:when test="${requestScope.movie == null || requestScope.movie.posterPath == null}">
                                    <img class="poster"
                                         src="<c:url value="/static/img/posters/NO_POSTER_AVAILABLE.jpg"/>" alt="">
                                </c:when>
                                <c:otherwise>
                                    <img class="poster" src="<c:url value="${requestScope.movie.posterPath}"/>" alt="">
                                </c:otherwise>
                            </c:choose>
                            <div>
                                <input type="file" id="edit-poster" name="poster" accept="image/jpeg,image/png">
                            </div>
                            <span class="poster-validation-alert"><fmt:message key="editor.form.poster.alert"/></span>
                        </div>
                        <div class="movie-description">
                            <div class="edit-description-container">
                                <div class="movie-edit-row">
                                    <div class="movie-edit-col-25">
                                        <label for="edit-director"><fmt:message
                                                key="editor.form.label.director"/></label>
                                    </div>
                                    <div class="movie-edit-col-75">
                                        <input type="text" id="edit-director" name="director"
                                               value="${requestScope.movie.director}"
                                               placeholder="<fmt:message key="editor.form.input.director.placeholder"/>"
                                               maxlength="45">
                                    </div>
                                </div>
                                <div class="movie-edit-row">
                                    <div class="movie-edit-col-25">
                                        <label for="edit-year"><fmt:message key="editor.form.label.year"/></label>
                                    </div>
                                    <div class="movie-edit-col-75">
                                        <input type="number" name="releaseYear" min="1895" max="2036" step="1"
                                               id="edit-year"
                                               value="${requestScope.movie.releaseYear}"
                                               placeholder="<fmt:message key="editor.form.input.year.placeholder"/>">
                                    </div>
                                </div>
                                <div class="movie-edit-row">
                                    <div class="movie-edit-col-25">
                                        <label for="edit-synopsis"><fmt:message
                                                key="editor.form.label.synopsis"/></label>
                                    </div>
                                    <div class="movie-edit-col-75">
                                        <textarea id="edit-synopsis" name="synopsis" maxlength="1000"
                                                  placeholder="<fmt:message key="editor.form.input.synopsis.placeholder"/>">${requestScope.movie.synopsis}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="cancel" onclick="history.back();">
                        <fmt:message key="editor.form.button.cancel"/>
                    </button>
                    <button id="save-movie-button" class="save" type="submit">
                        <fmt:message key="editor.form.button.submit"/>
                    </button>
                </form>
            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>

</div>
<script src="<c:url value="/static/js/poster-validator.js"/>"></script>
</body>
</html>