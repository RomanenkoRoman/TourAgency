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

<c:set var="title" value="Catalog" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<center>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">
                <%-- CONTENT --%>

                <form id="filter" action="controller">

                    <input type="hidden" name="command" value="filterCatalog">
                    <center>
                        <legend><h3><fmt:message key="listCatalog.catalogOfTours"/></h3></legend>
                    </center>
                    <table class="table table-striped" id="list_catalog_table">
                        <thead>

                        <tr>
                            <td></td>
                            <td></td>
                            <td><select class="span2" name="price" id="price">
                                <option selected disabled><fmt:message key="addCatalogItem.label.price"/></option>
                                <option value="1" name="1">0-700</option>
                                <option value="2" name="2">700-2100</option>
                                <option value="3" name="3">2000></option>
                            </select>
                            </td>
                            <td>
                                <select class="span2" name="category">
                                    <option selected disabled><fmt:message key="addCatalogItem.button.chooseCategory"/></option>
                                    <option value="REST" name="REST"><fmt:message key="addCatalogItem.option.rest"/></option>
                                    <option value="EXCURSION" name="EXCURSION"><fmt:message key="addCatalogItem.option.excursion"/></option>
                                    <option value="SHOPPING" name="SHOPPING"><fmt:message key="addCatalogItem.option.shopping"/></option>
                                </select>
                            </td>
                            <td>
                                <select class="span2" name="hotel_type">
                                    <option selected disabled><fmt:message key="addCatalogItem.option.chooseHotel"/></option>
                                    <option value="APARTMENTS" name="APARTMENTS"><fmt:message key="addCatalogItem.option.apartments"/></option>
                                    <option value="BOUTIQUE" name="BOUTIQUE"><fmt:message key="addCatalogItem.option.boutique"/></option>
                                    <option value="HOTEL" name="HOTEL"><fmt:message key="listOrders.hotel"/></option>
                                    <option value="MOTEL" name="MOTEL"><fmt:message key="addCatalogItem.option.motel"/></option>
                                </select>
                            </td>
                            <td>
                                <select class="span1" name="people">
                                    <option selected disabled><fmt:message key="listCatalog.all"/></option>
                                    <option value="1" name="1">1</option>
                                    <option value="2" name="2">2</option>
                                    <option value="3" name="3">3</option>
                                    <option value="4" name="4">4</option>
                                </select>

                            </td>
                            <td>

                                <input class="btn btn-primary" type="submit" value="<fmt:message key="listCatalog.button.filter"/>" name="filterCatalog">
                            </td>
                            <td>
                                <input class="btn btn-info" type="reset" value="<fmt:message key="addCatalogItem.button.reset"/>">

                            </td>

                        </tr>
                        </thead>
                </form>


                <form id="make_order" action="controller">
                    <input type="hidden" name="command" value="makeAnOrder"/>
        <tr>
            <td><b>№</b></td>
            <td><b><fmt:message key="addCatalogItem.label.name"/></b></td>
            <td><b><fmt:message key="addCatalogItem.label.price"/></b></td>
            <td><b><fmt:message key="deleteItems.table.thead.category"/></b></td>
            <td><b><fmt:message key="addCatalogItem.label.hotelType"/></b></td>
            <td><b><fmt:message key="deleteItems.table.thead.people"/></b></td>
            <td><b><fmt:message key="listCatalog.button.order"/></b></td>

        </tr>

        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${catalogItems}">

            <c:set var="k" value="${k+1}"/>

            <tr>
                <td><c:out value="${k}"/></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.categoryId}</td>
                <td>${item.typeHotel}</td>
                <td>${item.peopleQuantity}</td>
                <td><input type="checkbox" name="itemId" value="${item.id}"/></td>
                <c:if test="${item.hot == true}">
                    <td><span class="label label-important"><fmt:message key="deleteItems.table.hot"/></span></td>
                </c:if>
            </tr>

        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><input class="btn btn-success" value="<fmt:message key="listCatalog.button.toOrder"/>" type="submit"/></td>
            <td><input class="btn btn-info" value="<fmt:message key="addCatalogItem.button.reset"/>" type="reset"/></td>

        </tr>
    </table>


</center>

</body>