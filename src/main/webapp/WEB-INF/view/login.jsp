<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../template/metadata.jsp" />
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp" />

    <main class="page-main flex-middle">
        <div class="row">
            <div class="main">

                <form class="login-form-inline" action="<c:url value="/controller"/>" method="post">
                    <input type="hidden" name="command" value="login">
                    <label for="username">Username:</label>
                    <input type="text" id="username" placeholder="Enter Username" name="username">
                    <label for="password">Password:</label>
                    <input type="password" id="password" placeholder="Enter password" name="password">
                    <button type="submit">Submit</button>
                </form>

                <c:if test="${requestScope.message != null}">
                    <div class="alert">
                        <span class="alert-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                        <strong>Error!</strong> ${requestScope.message}
                    </div>
                </c:if>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp" />

</div>
</body>
</html>
