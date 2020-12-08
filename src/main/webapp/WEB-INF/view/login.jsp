<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="page.login.title"/></title>
    <jsp:include page="../template/metadata.jsp"/>
</head>
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">
            <div class="main">

                <form class="login-form-inline" action="<c:url value="/controller"/>" method="post">
                    <input type="hidden" name="command" value="login">

                    <label for="username">
                        <fmt:message key="login.form.label.username"/>
                    </label>
                    <input type="text" id="username"
                           placeholder="<fmt:message key="login.form.placeholder.username"/>"
                           name="username">

                    <label for="password">
                        <fmt:message key="login.form.label.password"/>
                    </label>
                    <input type="password" id="password"
                           placeholder="<fmt:message key="login.form.placeholder.password"/>" name="password">

                    <button type="submit"><fmt:message key="login.form.button.submit"/></button>
                </form>

                <c:if test="${requestScope.message != null}">
                    <div class="alert">
                        <span class="alert-closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                        <strong><fmt:message key="alert.header.error"/></strong>
                        <fmt:message key="${requestScope.message}"/>
                    </div>
                </c:if>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>

</div>
</body>
</html>
