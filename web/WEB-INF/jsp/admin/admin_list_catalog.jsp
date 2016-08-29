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

<c:set var="title" value="Admin Catalog" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<center>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">
                <%-- CONTENT --%>

                <form id="make_order" action="controller">
                    <input type="hidden" name="command" value="doHotUnHot"/>
                    <center>
                        <legend><h3><fmt:message key="listCatalog.changeHotStatus"/></h3></legend>
                    </center>
                    <table class="table table-striped" id="list_catalog_table">

                        <thead>
                        <tr>
                            <td><b>№</b></td>
                            <td><b><fmt:message key="addCatalogItem.label.name"/></b></td>
                            <td><b><fmt:message key="addCatalogItem.label.price"/></b></td>
                            <td><b><fmt:message key="deleteItems.table.thead.category"/></b></td>
                            <td><b><fmt:message key="addCatalogItem.label.hotelType"/></b></td>
                            <td><b><fmt:message key="deleteItems.table.thead.people"/></b></td>
                            <td><b><fmt:message key="listCatalog.doHot"/></b></td>
                            <td><b><fmt:message key="listCatalog.cancel"/></b></td>
                        </tr>
                        </thead>

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
                                <c:choose>
                                    <c:when test="${item.hot == false}">
                                        <td><input type="checkbox" name="doHot" value="${item.id}"></td>
                                        <td></td>
                                    </c:when>
                                    <c:when test="${item.hot == true}">
                                        <%--<td><b><font color="red">HOT</font> </b></td>--%>
                                        <td><span class="label label-important"><fmt:message key="deleteItems.table.hot"/></span></td>
                                        <td><input type="checkbox" name="doUnHot" value="${item.id}"></td>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        <tr>

                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input class="btn btn-primary" type="submit" value="<fmt:message key="settings.label.update"/>" name="Update"></td>
                            <td><input class="btn btn-info" type="reset" value="<fmt:message key="addCatalogItem.button.reset"/>" name="Reset"/></td>
                        </tr>


                    </table>
                    </form>
</center>


</form>

<%-- CONTENT --%>
</td>
</tr>

<%--<%@ include file="/WEB-INF/jspf/footer.jspf" %>--%>

</table>
</body>