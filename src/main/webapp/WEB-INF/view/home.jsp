<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../template/head.jsp"/>
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
                <ctg:movie-list movies="${requestScope.movies}"/>
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
