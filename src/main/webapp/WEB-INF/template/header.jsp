<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_BY"/>
<fmt:setBundle basename="property/localization"/>

<header class="page-header">
    <div class="header">
        <h1><fmt:message key="project.name" /></h1>
        <p>Java Web Development. Final Task. Web Project</p>
    </div>

    <div class="navbar">
        <a class="navbar-item active" href="<c:url value="/controller?command=homePage"/>">Home</a>
        <a class="navbar-item">Users</a>
        <div class="navbar-dropdown right">
            <a class="navbar-item navbar-drop-item localization"></a>
            <div class="navbar-dropdown-content">
                <a href="#">English (EN)</a>
                <a href="#">Russian (RU)</a>
            </div>
        </div>
        <a class="navbar-item" href="<c:url value="/controller?command=loginPage"/>">Login</a>
    </div>
</header>