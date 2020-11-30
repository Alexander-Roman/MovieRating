<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/localization"/>

<header class="page-header">
    <div class="header">
        <h1><fmt:message key="project.name"/></h1>
        <p><fmt:message key="project.description"/></p>
    </div>

    <div class="navbar">
        <a class="navbar-item" href="<c:url value="/controller?command=homePage"/>">
            <fmt:message key="navbar.link.home"/>
        </a>
        <!--<a class="navbar-item">Users</a>-->
        <div class="navbar-dropdown right">
            <a class="navbar-item navbar-drop-item localization"></a>
            <div class="navbar-dropdown-content">
                <a href="<c:url value="/controller?command=locale&language=en&country="/>">
                    <fmt:message key="navbar.link.locale.en"/>
                </a>
                <a href="<c:url value="/controller?command=locale&language=be&country=BY"/>">
                    <fmt:message key="navbar.link.locale.be.by"/>
                </a>
                <a href="<c:url value="/controller?command=locale&language=ru&country=BY"/>">
                    <fmt:message key="navbar.link.locale.ru.by"/>
                </a>
            </div>
        </div>
        <a class="navbar-item" href="<c:url value="/controller?command=loginPage"/>">
            <fmt:message key="navbar.link.login"/>
        </a>
    </div>
</header>