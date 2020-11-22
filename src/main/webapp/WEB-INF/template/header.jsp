<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="page-header">
    <div class="header">
        <h1>MovieRating</h1>
        <p>Java Web Development. Final Task. Web Project</p>
    </div>

    <div class="navbar">
        <a class="navbar-item active">Home</a>
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