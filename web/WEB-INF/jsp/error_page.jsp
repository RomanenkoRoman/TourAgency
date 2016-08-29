<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 04.08.2016

  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
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

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>
<table id="main-container">

    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%-- HEADER --%>

    <tr >
        <td class="content">
            <%-- CONTENT --%>

            <h2 class="error">
                <fmt:message key="errorPage.folowingErrorOccured"/>
            </h2>

            <%-- this way we obtain an information about an exception (if it has been occurred) --%>
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>

            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>

            <c:if test="${not empty exception}">
                <% exception.printStackTrace(new PrintWriter(out)); %>
            </c:if>

            <%-- if we get this page using forward --%>
            <c:if test="${not empty requestScope.errorMessage}">
                <h3>${requestScope.errorMessage}</h3>
            </c:if>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</center>
</body>
</html>