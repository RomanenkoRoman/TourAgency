<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 04.08.2016

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

<c:set var="title" value="List Orders" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>


    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <form action="statusController" method="post">
            <tr>
                <td class="content">
                    <%-- CONTENT --%>

                    <c:set var="k" value="0"/>
                    <c:choose>


                    <c:when test="${fn:length(extendedUserOrderBeanList) == 0}">No such orders</c:when>

                    <c:otherwise>
                    <fieldset>
                        <legend><h3><fmt:message key="listOrders.changeStatusOrGive"/></h3></legend>
                        <table class="table table-striped" id="list_order_table">
                            <thead>
                            <tr>
                                <td><b>№</b></td>
                                <td><b><fmt:message key="settings.label.firstName"/></b></td>
                                <td><b><fmt:message key="settings.label.lastName"/></b></td>
                                <td><b><fmt:message key="listOrders.hotel"/></b></td>
                                <td><b><fmt:message key="deleteItems.table.thead.category"/></b></td>
                                <td><b><fmt:message key="listOrders.type"/></b></td>
                                <td><b><fmt:message key="addCatalogItem.label.price"/></b></td>
                                <td><b><fmt:message key="listOrders.status"/></b></td>
                                <td><b><fmt:message key="listOrders.changeStatus"/></b></td>
                                <td><b><fmt:message key="listOrders.discount"/></b></td>

                            </tr>
                            </thead>

                            <c:forEach var="bean" items="${extendedUserOrderBeanList}">
                                <c:set var="k" value="${k+1}"/>

                            <tr>
                                <td>${k}</td>
                                <td>${bean.userFirstName} </td>
                                <td>${bean.userLastName}</td>
                                <td>${bean.hotelName}</td>
                                <td>${bean.hotelType}</td>
                                <td>${bean.categoryName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${bean.discount>0}">
                                            ${bean.hotelPrice - (bean.hotelPrice / 100 * bean.discount)}
                                        </c:when>
                                        <c:when test="${bean.discount == 0}">
                                            ${bean.hotelPrice}
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${bean.status}</td>
                                <td>
                                    <input type="radio" name="${k}" value="paid"><fmt:message key="listOrders.paid"/>
                                    <input type="radio" name="${k}" value="canceled"><fmt:message key="listOrders.canceled"/>
                                </td>
                                <td><input class="span1" type="number" step="${step}" max="${max}" min="0"
                                           name="${k+100}"></td>
                            </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>
                                    <input class="btn btn-primary" type="submit" value="<fmt:message key="settings.label.update"/>" name="Update">
                                    <input class="btn btn-info" type="reset" value="<fmt:message key="addCatalogItem.button.reset"/>" name="Reset">

                                </td>
                            </tr>
        </form>
    </table>


    </c:otherwise>
    </c:choose>

    <%-- CONTENT --%>
    </td>
    </tr>
    </form>

    <%--<%@ include file="/WEB-INF/jspf/footer.jspf" %>--%>

    </table>
</center>
</body>

</html>