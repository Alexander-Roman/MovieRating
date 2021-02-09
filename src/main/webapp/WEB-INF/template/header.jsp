<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<header class="page-header">
    <div class="header">
        <h1><fmt:message key="project.name"/></h1>
        <p><fmt:message key="project.description"/></p>
    </div>

    <div class="navbar">
        <a class="navbar-item" href="<c:url value="/controller?command=home"/>">
            <fmt:message key="navbar.link.home"/>
        </a>
        <ctg:access accessName="users">
            <a class="navbar-item" href="<c:url value="/controller?command=users"/>">
                <fmt:message key="navbar.link.users"/>
            </a>
        </ctg:access>
        <div class="navbar-dropdown right">
            <a class="navbar-item navbar-drop-item localization"></a>
            <div class="navbar-dropdown-content">
                <a href="<c:url value="/controller?command=locale&lang=EN"/>">
                    <fmt:message key="navbar.link.locale.en"/>
                </a>
                <a href="<c:url value="/controller?command=locale&lang=BE"/>">
                    <fmt:message key="navbar.link.locale.be.by"/>
                </a>
                <a href="<c:url value="/controller?command=locale&lang=RU"/>">
                    <fmt:message key="navbar.link.locale.ru.by"/>
                </a>
            </div>
        </div>
        <c:choose>
            <c:when test="${sessionScope.account == null}">
                <a class="navbar-item" href="<c:url value="/controller?command=loginPage"/>">
                    <fmt:message key="navbar.link.login"/>
                </a>
            </c:when>
            <c:otherwise>
                <a class="navbar-item" href="<c:url value="/controller?command=logout"/>">
                    <fmt:message key="navbar.link.logout"/>
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</header>