<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <title>${requestScope.movie.title}</title>
    <jsp:include page="../template/metadata.jsp"/>
</head>
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">
            <div class="main">
                <h2>${requestScope.movie.title}</h2>

                <div class="edit-button-container">
                    <ctg:access accessName="editMovie">
                        <a class="edit-button"
                           href="<c:url value="controller?command=editMovie&id=${requestScope.movie.id}"/>">
                            <fmt:message key="movie.button.edit"/>
                        </a>
                    </ctg:access>
                    <ctg:access accessName="deleteMovie">
                        <a class="edit-button"
                           href="<c:url value="controller?command=deleteMovie&id=${requestScope.movie.id}"/>">
                            <fmt:message key="movie.button.delete"/>
                        </a>
                    </ctg:access>
                </div>

                <div class="movie-info">
                    <div class="poster-container">
                        <c:choose>
                            <c:when test="${requestScope.movie.posterPath == null}">
                                <img class="poster" src="<c:url value="/static/img/posters/NO_POSTER_AVAILABLE.jpg"/>"
                                     alt="">
                            </c:when>
                            <c:otherwise>
                                <img class="poster" src="<c:url value="${requestScope.movie.posterPath}"/>" alt="">
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="movie-description">
                        <p><b><fmt:message key="movie.description.director"/></b> ${requestScope.movie.director}</p>
                        <p><b><fmt:message key="movie.description.year"/></b> ${requestScope.movie.releaseYear}</p>
                        <c:choose>
                            <c:when test="${requestScope.movie.rating == null}">
                                <p><b><fmt:message key="movie.description.rating"/></b> - / 10</p>
                            </c:when>
                            <c:otherwise>
                                <p><b><fmt:message key="movie.description.rating"/></b> ${requestScope.movie.rating} /
                                    10</p>
                            </c:otherwise>
                        </c:choose>
                        <p>${requestScope.movie.synopsis}</p>
                    </div>
                </div>

                <h3><fmt:message key="movie.account.rating.header"/></h3>
                <c:choose>
                    <c:when test="${sessionScope.account == null}">
                        <div class="alert alert-info">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
                            <strong><fmt:message key="movie.account.rating.alert.header"/></strong>
                            <fmt:message key="movie.account.rating.alert.text"/>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.personalRate != null}">
                        <p><b>${requestScope.personalRate}</b></p>
                    </c:when>
                    <c:otherwise>
                        <form class="custom-radio" action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="rateMovie">
                            <input type="hidden" name="id" value="${requestScope.movie.id}">
                            <label class="radio-container">1
                                <input type="radio" name="rating" value="1" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">2
                                <input type="radio" name="rating" value="2" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">3
                                <input type="radio" name="rating" value="3" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">4
                                <input type="radio" name="rating" value="4" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">5
                                <input type="radio" name="rating" value="5" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">6
                                <input type="radio" name="rating" value="6" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">7
                                <input type="radio" name="rating" value="7" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">8
                                <input type="radio" name="rating" value="8" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">9
                                <input type="radio" name="rating" value="9" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">10
                                <input type="radio" name="rating" value="10" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <input type="submit" value="<fmt:message key="movie.account.rating.form.submit"/>">
                        </form>
                    </c:otherwise>
                </c:choose>

                <h3><fmt:message key="movie.comments.header"/></h3>

                <c:forEach var="comment" items="${requestScope.comments}">
                    <div class="comment-container">
                        <ctg:access accessName="deleteComment">
                            <form class="comment-remove-form" action="<c:url value="/controller"/>">
                                <input type="hidden" name="command" value="deleteComment">
                                <button class="comment-remove-button" type="submit">
                                    <span class="comment-remove-button-img"></span>
                                </button>
                            </form>
                        </ctg:access>
                        <p><b>${comment.author.userName}</b></p>
                        <p>${comment.text}</p>
                        <fmt:parseDate value="${comment.dateTime}" pattern="y-M-dd'T'H:m" var="date"/>
                        <span class="comment-time"><fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH-mm"/></span>
                    </div>
                </c:forEach>


                <c:choose>
                    <c:when test="${sessionScope.account == null}">
                        <div class="alert alert-info">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
                            <strong><fmt:message key="movie.comments.alert.header"/></strong>
                            <fmt:message key="movie.comments.alert.text"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="comment-form-container">
                            <form action="<c:url value="/controller"/>" method="post">
                                <input type="hidden" name="command" value="leaveComment">
                                <div class="comment-form-row">
                                    <h3><label for="text"><fmt:message key="movie.comments.form.header"/></label></h3>
                                </div>
                                <div class="comment-form-row">
                            <textarea id="text" name="text"
                                      placeholder="<fmt:message key="movie.comments.form.placeholder"/>"></textarea>
                                </div>
                                <div class="comment-form-row">
                                    <input type="submit" value="<fmt:message key="movie.comments.form.button.submit"/>">
                                </div>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>

</div>
</body>
</html>