<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="loc" value="${language}"/>
<c:choose>
    <c:when test="${loc == 'ru_RU'}">
        <fmt:setLocale value="ru" />
    </c:when>
    <c:when test="${loc == 'ru'}">
        <fmt:setLocale value="ru" />
    </c:when>
    <c:when test="${loc == 'en'}">
        <fmt:setLocale value="en" />
    </c:when>

</c:choose>
<fmt:setBundle basename="ua.nure.romanenko.SummaryTask4.text" />
<html lang="${language}">

<c:set var="title" value="List Users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>


    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <form action="controller" method="post">
            <input type="hidden" name="command" value="banUnBan">
            <tr>
                <td class="content">
                    <%-- CONTENT --%>

                    <c:set var="k" value="0"/>
                    <c:choose>

                    <c:when test="${fn:length(userList) == 0}">No found users</c:when>
                    <c:otherwise>
                    <legend><h3><fmt:message key="banUsers.label.allUsers"/></h3></legend>
                    <table class="table table-striped" id="list_order_table">
                        <thead>
                        <tr>
                            <td><b>№</b></td>
                            <td><b><fmt:message key="banUsers.table.thead.login"/></b></td>
                            <td><b><fmt:message key="banUsers.table.thead.password"/></b></td>
                            <td><b><fmt:message key="settings.label.firstName"/></b></td>
                            <td><b><fmt:message key="settings.label.lastName"/></b></td>
                            <td><b><fmt:message key="banUsers.table.thead.role"/></b></td>
                            <td><b><fmt:message key="banUsers.table.thead.ban"/></b></td>
                            <td><b><fmt:message key="banUsers.table.thead.UnBan"/></b></td>

                        </tr>
                        </thead>


                        <c:forEach var="bean" items="${userList}">
                            <c:set var="k" value="${k+1}"/>

                            <tr>
                                <td>${k}</td>
                                <td>${bean.login}</td>
                                <td>${bean.password}</td>
                                <td>${bean.firstName} </td>
                                <td>${bean.lastName}</td>
                                <td>${bean.role}</td>

                                <c:choose>
                                    <c:when test="${bean.ban == true}">
                                        <td><span class="label label-important"><fmt:message key="banUsers.table.banned"/></span></td>
                                        <td><input type="checkbox" name="unBan" value="${bean.id}"></td>
                                    </c:when>
                                    <c:when test="${bean.ban == false}">
                                        <td><input type="checkbox" name="ban" value="${bean.id}"></td>
                                        <td></td>
                                    </c:when>
                                </c:choose>

                                </td>

                            </tr>
                        </c:forEach>
                        <tr>
                            <br/>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <input class="btn btn-warning" type="submit" value="<fmt:message key="banUsers.table.babUnBun"/>" name="Update">
                            </td>
                            <td>
                                <input class="btn btn-info" type="reset" value="<fmt:message key="addCatalogItem.button.reset"/>" name="Reset">
                            </td>
                        </tr>
                    </table>
        </form>
    </table>


    </c:otherwise>
    </c:choose>

    <%-- CONTENT --%>
    </td>
    </tr>
    </form>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>

</html>