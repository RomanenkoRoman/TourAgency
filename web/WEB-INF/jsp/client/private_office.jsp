<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 20.08.2016
  Time: 17:49
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
<c:set var="title" value="Private office" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <tr>

        <td class="content">
            <form id="private_office" action="controller" method="get">
                <input type="hidden" name="command" value="private_office"/>
        <td>

            <br>
        </td>
        <c:choose>

        <c:when test="${fn:length(orders) == 0}">

    <tr>
        <td class="content">
            <h3 class="error">
                <fmt:message key="privateOffice.hello"/>${user.firstName} ${user.lastName}<fmt:message key="privateOffice.thisIsYour"/><br>
                <fmt:message key="privateOffice.noSuchOrders"/>
            </h3>
        </td>
    </tr>

    </c:when>
    <c:otherwise>
        <table class="table table-striped" id="your_order_list">
            <thead>
            <tr>
                <td><b>№</b></td>
                <td><b><fmt:message key="addCatalogItem.label.name"/></b></td>
                <td><b><fmt:message key="addCatalogItem.label.price"/></b></td>
                <td><b><fmt:message key="deleteItems.table.thead.category"/></b></td>
                <td><b><fmt:message key="addCatalogItem.label.hotelType"/></b></td>
                <td><b><fmt:message key="deleteItems.table.thead.people"/></b></td>
                <td><b><fmt:message key="listOrders.status"/></b></td>
                <td><b><fmt:message key="listOrders.discount"/></b></td>
            </tr>
            </thead>

            <c:set var="bill" value="0"/>
            <c:set var="k" value="0"/>

            <h3><fmt:message key="privateOffice.hello"/>${user.firstName} ${user.lastName}<fmt:message key="privateOffice.thisIsYour"/><br>
                <fmt:message key="privateOffice.yourOrderIs"/>
            </h3>
            <br>
            <br>
            <c:forEach var="item" items="${orders}">
                <c:set var="k" value="${k+1}"/>

                <tr>
                    <td><c:out value="${k}"/></td>
                    <td>${item.hotelName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.discount>0}">
                                ${item.hotelPrice - (item.hotelPrice / 100 * item.discount)}

                            </c:when>
                            <c:when test="${item.discount == 0}">
                                ${item.hotelPrice}
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${item.categoryName}</td>
                    <td>${item.hotelType}</td>
                    <td>${item.people}</td>
                    <td>${item.status}</td>

                    <td>
                        <c:choose>
                            <c:when test="${item.discount>0}">
                                ${item.discount}%
                            </c:when>
                        </c:choose>

                    </td>


                </tr>
            </c:forEach>

        </table>
    </c:otherwise>
    </c:choose>
    </form>


    </td>
    </tr>
</table>
</center>


<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>

</html>
