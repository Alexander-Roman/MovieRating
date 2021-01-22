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
                        <button class="edit-button movie-delete-button" type="button" onclick="showDeleteModal()">
                            <fmt:message key="movie.button.delete"/>
                        </button>
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
                                <p><b><fmt:message key="movie.description.rating"/></b>
                                    <fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                      value="${requestScope.movie.rating}"/> /
                                    10</p>
                            </c:otherwise>
                        </c:choose>
                        <p style="white-space: pre-wrap;">${requestScope.movie.synopsis}</p>
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
                                <input type="radio" name="rate" value="1" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">2
                                <input type="radio" name="rate" value="2" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">3
                                <input type="radio" name="rate" value="3" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">4
                                <input type="radio" name="rate" value="4" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">5
                                <input type="radio" name="rate" value="5" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">6
                                <input type="radio" name="rate" value="6" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">7
                                <input type="radio" name="rate" value="7" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">8
                                <input type="radio" name="rate" value="8" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">9
                                <input type="radio" name="rate" value="9" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <label class="radio-container">10
                                <input type="radio" name="rate" value="10" required>
                                <span class="radio-checkmark"></span>
                            </label>
                            <input type="submit" value="<fmt:message key="movie.account.rating.form.submit"/>">
                        </form>
                    </c:otherwise>
                </c:choose>

                <h3><fmt:message key="movie.comments.header"/></h3>

                <div id="comments-block">
                    <c:forEach var="comment" items="${requestScope.comments}">
                        <div data-id="${comment.id}" class="comment-container">
                            <ctg:access accessName="deleteComment">
                                <form class="comment-remove-form" action="<c:url value="/controller"/>" method="post">
                                    <input type="hidden" name="command" value="deleteComment">
                                    <input type="hidden" name="id" value="${comment.id}">
                                    <button class="comment-remove-button comment-remove-submit" type="button"
                                            onclick="deleteComment(this)">
                                        <span class="comment-remove-button-img"></span>
                                    </button>
                                    <button class="comment-remove-button comment-remove-cancel" type="button"
                                            onclick="cancelCommentRemove(this)">
                                        <span class="comment-remove-button-img"></span>
                                    </button>
                                    <button class="comment-remove-button comment-remove-options" type="button"
                                            onclick="showCommentRemoveOptions(this)">
                                        <span class="comment-remove-button-img"></span>
                                    </button>
                                </form>
                            </ctg:access>
                            <p><b>${comment.author.userName}</b></p>
                            <p style="white-space: pre-wrap;">${comment.text}</p>
                            <fmt:parseDate value="${comment.dateTime}" pattern="y-M-dd'T'H:m" var="date"/>
                            <span class="comment-time"><fmt:formatDate value="${date}"
                                                                       pattern="yyyy-MM-dd HH-mm"/></span>
                        </div>
                    </c:forEach>
                </div>

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
                            <form class="comment-form" action="<c:url value="/ajax"/>" method="post">
                                <input type="hidden" name="command" value="addComment">
                                <input type="hidden" name="id" value="${requestScope.movie.id}">
                                <div class="comment-form-row">
                                    <h3><label for="text"><fmt:message key="movie.comments.form.header"/></label></h3>
                                </div>
                                <div class="comment-form-row">
                                    <textarea id="text" name="text" oninput="validateCommentText()"
                                              placeholder="<fmt:message key="movie.comments.form.placeholder"/>"
                                              required></textarea>
                                </div>
                                <div class="comment-form-row">
                                    <button id="comment-form-submit" type="button" disabled
                                            onclick="addComment()">
                                        <fmt:message key="movie.comments.form.button.submit"/>
                                    </button>
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

<ctg:access accessName="deleteMovie">
    <div class="delete-movie-modal">
        <span class="delete-movie-close" title="<fmt:message key="movie.delete.modal.button.close.title"/>"
              onclick="hideDeleteModal()">×</span>
        <form class="delete-movie-modal-content" action="<c:url value="/controller"/>" method="post">
            <input type="hidden" name="command" value="deleteMovie">
            <input type="hidden" name="id" value="${requestScope.movie.id}">
            <div class="delete-movie-container">
                <h1><fmt:message key="movie.delete.modal.header"/> ${requestScope.movie.title}</h1>
                <p><fmt:message key="movie.delete.modal.message"/></p>

                <div class="delete-movie-clear-fix">
                    <button type="submit" class="delete-movie-confirm">
                        <fmt:message key="movie.delete.modal.button.delete"/>
                    </button>
                    <button type="button" class="delete-movie-cancel" onclick="hideDeleteModal()">
                        <fmt:message key="movie.delete.modal.button.cancel"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <script>
        let movieDeleteConfirmModal = document.querySelector('.delete-movie-modal');
    </script>
    <script src="<c:url value="/static/js/dynamic/movie-remove-confirm.js"/>"></script>
</ctg:access>

<!-- Prepared layout of dynamic elements -->
<div style="display: none">

    <!-- Global variables -->
    <script>
        let movieId = ${requestScope.movie.id};
        let controllerActionPath = "<c:url value="/controller"/>";
        let ajaxActionPath = "<c:url value="/ajax"/>";
    </script>

    <ctg:access accessName="deleteComment">

        <!-- 200 alert for delete comment command -->
        <div class="alert alert-success delete-command-alert-success-sample">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
            <strong><fmt:message key="movie.comment.delete.alert.success.header"/></strong>
            <fmt:message key="movie.comment.delete.alert.success.text"/>
        </div>
        <script>
            let deleteCommentAlertSuccess = document.querySelector(".delete-command-alert-success-sample");
        </script>

        <!-- 404 alert for delete comment command -->
        <div class="alert alert-warning delete-command-alert-404-sample">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
            <strong><fmt:message key="movie.comment.delete.error.404.header"/></strong>
            <fmt:message key="movie.comment.delete.error.404.text"/>
        </div>
        <script>
            let deleteCommentAlertNotFound = document.querySelector(".delete-command-alert-404-sample");
        </script>

        <!-- 500 alert for delete comment command -->
        <div class="alert alert-error delete-command-alert-500-sample">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
            <strong><fmt:message key="movie.comment.delete.error.500.header"/></strong>
            <fmt:message key="movie.comment.delete.error.500.text"/>
        </div>
        <script>
            let deleteCommentAlertError = document.querySelector(".delete-command-alert-500-sample");
        </script>

        <script src="<c:url value="/static/js/ajax/delete-comment.js"/>"></script>
    </ctg:access>

    <ctg:access accessName="addComment">
        <!-- Comment block sample -->
        <div data-id="" class="comment-container-sample comment-container">
            <ctg:access accessName="deleteComment">
                <form class="comment-remove-form" action="<c:url value="/controller"/>" method="post">
                    <input type="hidden" name="command" value="deleteComment">
                    <input type="hidden" name="id" value="">
                    <button class="comment-remove-button comment-remove-submit" type="button"
                            onclick="deleteComment(this)">
                        <span class="comment-remove-button-img"></span>
                    </button>
                    <button class="comment-remove-button comment-remove-cancel" type="button"
                            onclick="cancelCommentRemove(this)">
                        <span class="comment-remove-button-img"></span>
                    </button>
                    <button class="comment-remove-button comment-remove-options" type="button"
                            onclick="showCommentRemoveOptions(this)">
                        <span class="comment-remove-button-img"></span>
                    </button>
                </form>
            </ctg:access>
            <p class="author-name" style="font-weight: bold;"></p>
            <p class="comment-text" style="white-space: pre-wrap;"></p>
            <span class="comment-time"></span>
        </div>
        <script>
            let newCommentContainerSample = document.querySelector(".comment-container-sample");
        </script>

        <!-- Error alert for add comment command -->
        <div class="alert alert-error add-comment-error-sample">
                            <span class="alert-closebtn"
                                  onclick="this.parentElement.style.display='none';">&times;</span>
            <strong><fmt:message key="movie.comment.add.error.header"/></strong>
            <fmt:message key="movie.comment.add.error.text"/>
        </div>
        <script>
            let addCommentAlertError = document.querySelector(".delete-command-alert-500-sample");
        </script>

        <script src="<c:url value="/static/js/ajax/add-comment.js"/>"></script>
    </ctg:access>
</div>
<ctg:access accessName="addComment">
    <script src="<c:url value="/static/js/validator/comment-form-validator.js"/>"></script>
</ctg:access>
<ctg:access accessName="deleteComment">
    <script src="<c:url value="/static/js/dynamic/comment-remove-confirm.js"/>"></script>
</ctg:access>
</body>
</html>