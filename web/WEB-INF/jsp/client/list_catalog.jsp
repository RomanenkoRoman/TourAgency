<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 04.08.2016

  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Catalog" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>


            <table id="list_catalog_table">
                <thead>
                <form id="filter" action="controller">
                    <input type="hidden" name="command" value="filterCatalog">
                    <tr>
                        <td></td>
                        <td></td>
                        <td><select name="price" id="price">
                            <option selected disabled>Price</option>
                            <option value="1" name="1">0-700</option>
                            <option value="2" name="2">700-2100</option>
                            <option value="3" name="3">2000></option>
                        </select>
                        </td>
                        <td>
                            <select name="category">
                                <option selected disabled>Choose Category</option>
                                <option value="REST" name="REST">REST</option>
                                <option value="EXCURSION" name="EXCURSION">EXCURSION</option>
                                <option value="SHOPPING" name="SHOPPING">SHOPPING</option>
                            </select>
                        </td>
                        <td>
                            <select name="hotel_type">
                                <option selected disabled>Choose Hotel Type</option>
                                <option value="APARTMENTS" name="APARTMENTS">APARTMENTS</option>
                                <option value="BOUTIQUE" name="BOUTIQUE">BOUTIQUE</option>
                                <option value="HOTEL" name="HOTEL">HOTEL</option>
                                <option value="MOTEL" name="MOTEL">MOTEL</option>
                            </select>
                        </td>
                        <td>
                            <select name="people">
                                <option selected disabled>All</option>
                                <option value="1" name="1">1</option>
                                <option value="2" name="2">2</option>
                                <option value="3" name="3">3</option>
                                <option value="4" name="4">4</option>
                            </select>

                        </td>
                        <td>
                            <input type="submit" value="Filter" name="filterCatalog">
                            <input type="reset" value="Reset">
                        </td>

                    </tr>

                </form>
                <form id="make_order" action="controller">
                    <input type="hidden" name="command" value="makeAnOrder"/>
                    <tr>
                        <td>№</td>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Category</td>
                        <td>Hotel Type</td>
                        <td>People</td>
                        <td>Order</td>

                    </tr>
                </thead>

                <c:set var="k" value="0"/>

                <%--<jsp:useBean id="catalogItems" scope="request" type="java.util.List"/>--%>

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
                            <td>HOT</td>
                        </c:if>
                    </tr>

                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><input value="To order" type="submit"/></td>
                    <td><input value="Reset" type="reset"/></td>
                </tr>
            </table>


            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>