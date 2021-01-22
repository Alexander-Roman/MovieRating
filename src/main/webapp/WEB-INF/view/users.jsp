<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <title><fmt:message key="page.users.title"/></title>
    <jsp:include page="../template/metadata.jsp"/>
</head>
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main">
        <div class="row">
            <div class="main">
                <h2><fmt:message key="users.header"/></h2>


                <table class="table" id="users">
                    <tr>
                        <th><fmt:message key="users.table.header.name"/></th>
                        <th><fmt:message key="users.table.header.role"/></th>
                        <ctg:access accessName="blockUser">
                            <th><fmt:message key="users.table.header.blocking"/></th>
                        </ctg:access>
                        <ctg:access accessName="promoteUser">
                            <th><fmt:message key="users.table.header.access"/></th>
                        </ctg:access>
                    </tr>
                    <c:forEach var="user" items="${requestScope.users}">
                        <tr>
                            <td>${user.userName}</td>

                            <c:choose>
                                <c:when test="${user.role == 'USER'}">
                                    <td><fmt:message key="users.table.role.user"/></td>
                                </c:when>
                                <c:when test="${user.role == 'EDITOR'}">
                                    <td><fmt:message key="users.table.role.editor"/></td>
                                </c:when>
                                <c:when test="${user.role == 'ADMIN'}">
                                    <td><fmt:message key="users.table.role.admin"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>${user.role}</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${user.role == 'USER'}">

                                    <ctg:access accessName="blockUser">
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.blocked}">
                                                    <form action="<c:url value="/controller"/>">
                                                        <input type="hidden" name="command" value="unblockUser">
                                                        <input type="hidden" name="id" value="${user.id}">
                                                        <button class="table-button" type="submit">
                                                            <fmt:message key="users.table.button.unblock"/>
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="<c:url value="/controller"/>">
                                                        <input type="hidden" name="command" value="blockUser">
                                                        <input type="hidden" name="id" value="${user.id}">
                                                        <button class="table-button" type="submit">
                                                            <fmt:message key="users.table.button.block"/>
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </ctg:access>

                                </c:when>
                                <c:when test="${user.role == 'EDITOR'}">

                                    <ctg:access accessName="blockEditor">
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.blocked}">
                                                    <form action="<c:url value="/controller"/>">
                                                        <input type="hidden" name="command" value="unblockEditor">
                                                        <input type="hidden" name="id" value="${user.id}">
                                                        <button class="table-button" type="submit">
                                                            <fmt:message key="users.table.button.unblock"/>
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="<c:url value="/controller"/>">
                                                        <input type="hidden" name="command" value="blockEditor">
                                                        <input type="hidden" name="id" value="${user.id}">
                                                        <button class="table-button" type="submit">
                                                            <fmt:message key="users.table.button.block"/>
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </ctg:access>

                                </c:when>
                                <c:otherwise>
                                    <ctg:access accessName="blockUser">
                                        <td></td>
                                    </ctg:access>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${user.role == 'USER'}">
                                    <ctg:access accessName="promoteUser">
                                        <td>
                                            <form action="<c:url value="/controller"/>">
                                                <input type="hidden" name="command" value="promoteUser">
                                                <input type="hidden" name="id" value="${user.id}">
                                                <button class="table-button" type="submit">
                                                    <fmt:message key="users.table.button.promote"/>
                                                </button>
                                            </form>
                                        </td>
                                    </ctg:access>
                                </c:when>
                                <c:when test="${user.role == 'EDITOR'}">
                                    <ctg:access accessName="demoteEditor">
                                        <td>
                                            <form action="<c:url value="/controller"/>">
                                                <input type="hidden" name="command" value="demoteEditor">
                                                <input type="hidden" name="id" value="${user.id}">
                                                <button class="table-button" type="submit">
                                                    <fmt:message key="users.table.button.demote"/>
                                                </button>
                                            </form>
                                        </td>
                                    </ctg:access>
                                </c:when>
                                <c:otherwise>
                                    <ctg:access accessName="promoteUser">
                                        <td></td>
                                    </ctg:access>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>

                <div class="flex-middle">
                    <div class="pagination">
                        <c:choose>
                            <c:when test="${requestScope.page > 1}">
                                <a class="pagination-available"
                                   href="<c:url value="controller?command=users&page=${requestScope.page - 1}"/>">&laquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a class="pagination-disabled">&laquo;</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="i" begin="1" end="${requestScope.numberOfPages}">
                            <c:choose>
                                <c:when test="${i == requestScope.page}">
                                    <a class="pagination-active">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="pagination-available"
                                       href="<c:url value="controller?command=users&page=${i}"/>">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${requestScope.page < requestScope.numberOfPages}">
                                <a class="pagination-available"
                                   href="<c:url value="controller?command=users&page=${requestScope.page + 1}"/>">&raquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a class="pagination-disabled">&raquo;</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>
</div>
</body>
</html>
