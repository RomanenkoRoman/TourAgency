<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 26.08.2016
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
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
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<head>
    <title><fmt:message key="catalogItemAdded"/></title>
</head>
<body>
<center>
    <table>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <tr>
            <td class="content">
                <%-- CONTENT --%>

                <h2 class="success">
                    <fmt:message key="catalogItemAddedSucc"/>
                </h2>

            </td>
        </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </table>
</center>
</body>
</html>
