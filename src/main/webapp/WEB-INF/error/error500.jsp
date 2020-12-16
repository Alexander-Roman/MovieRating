<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <title>500</title>
    <jsp:include page="../template/metadata.jsp"/>
</head>

<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">

            <div class="main">

                <h2>500: Internal Server Error!</h2>
                <p>Request from ${pageContext.errorData.requestURI} is failed!</p>
                <p>Servlet name: ${pageContext.errorData.servletName}</p>
                <p>Status code: ${pageContext.errorData.statusCode}</p>
                <p>Exception: ${pageContext.exception}</p>
                <p>Exception message: ${pageContext.exception.message}</p>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>
</div>
</body>
</html>