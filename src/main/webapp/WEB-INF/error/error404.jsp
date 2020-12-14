<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <title>404</title>
    <jsp:include page="../template/metadata.jsp"/>
</head>

<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">

            <div class="main">

                <h2>404: Page not found!</h2>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>
</div>
</body>
</html>

