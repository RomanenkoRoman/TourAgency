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

<c:set var="title" value="Add item to Catalog"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<center>

    <table id="main-container">


        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content center">


                <form id="add_catalog_item" action="controller" method="post">


                    <input type="hidden" name="command" value="addCatalogItem"/>


                    <fieldset>
                        <legend><fmt:message key="addCatalogItem.label.addCatalogItem"/></legend>
                        <div class="form-actions">
                            <legend><fmt:message key="addCatalogItem.label.name"/></legend>
                            <input name="name"/>

                            <legend><fmt:message key="addCatalogItem.label.categoryType"/></legend>
                            <select name="categoryType">
                                <option selected disabled><fmt:message key="addCatalogItem.option.chooseCategory"/></option>
                                <option name="rest" value="rest"><fmt:message key="addCatalogItem.option.rest"/></option>
                                <option name="excursion" value="excursion"><fmt:message key="addCatalogItem.option.excursion"/></option>
                                <option name="shopping" value="shopping"><fmt:message key="addCatalogItem.option.shopping"/></option>
                            </select>

                            <legend><fmt:message key="addCatalogItem.label.hotelType"/></legend>
                            <select  name="hotelType">
                                <option selected disabled><fmt:message key="addCatalogItem.option.chooseHotel"/></option>
                                <option name="hotel" value="hotel"><fmt:message key="addCatalogItem.option.hotel"/></option>
                                <option name="motel" value="motel"><fmt:message key="addCatalogItem.option.motel"/></option>
                                <option name="boutique" value="boutique"><fmt:message key="addCatalogItem.option.boutique"/></option>
                                <option name="apartments" value="apartments"><fmt:message key="addCatalogItem.option.apartments"/></option>
                            </select>

                            <legend><fmt:message key="addCatalogItem.label.peopleQuantity"/></legend>
                            <select name="quantity">
                                <option selected disabled><fmt:message key="addCatalogItem.option.quantity"/></option>
                                <option name="1" value="1">1</option>
                                <option name="2" value="2">2</option>
                                <option name="3" value="3">3</option>
                                <option name="4" value="4">4</option>
                            </select>
                            <legend><fmt:message key="addCatalogItem.label.price"/></legend>
                            <input type="number" min="500" max="10000" name="price"/>
                        </div>
                    </fieldset>


                   <h3>${message}</h3>

                    <input class="btn btn-primary" type="submit" value="<fmt:message key="addCatalogItem.button.addToCatalog"/>">
                    <input class="btn btn-info" type="reset" value="<fmt:message key="addCatalogItem.button.reset"/>">
                </form>

                <br>


            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>
</html>