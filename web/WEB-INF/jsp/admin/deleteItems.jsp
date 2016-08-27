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

<c:set var="title" value="Delete Items" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<center>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">
                <%-- CONTENT --%>

                <form id="make_order" action="controller">
                    <input type="hidden" name="command" value="deleteSelected"/>


                    <fieldset>
                        <legend><h3>Delete item</h3></legend>
                        <table id="list_catalog_table">
                            <thead>
                            <tr>
                                <td>№</td>
                                <td>Name</td>
                                <td>Price</td>
                                <td>Category</td>
                                <td>Hotel Type</td>
                                <td>People</td>
                                <td>Delete</td>
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
                                    <td><input type="checkbox" name="delete" value="${item.id}"></td>
                                    <c:choose>
                                        <c:when test="${item.hot == true}">
                                            <td>HOT</td>
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
                                <td><input type="submit" value="Delete" name="Update"></td>
                                <td><input type="reset" value="Reset" name="Reset"/></td>
                            </tr>


                        </table>
                    </fieldset>

                </form>

                <%-- CONTENT --%>
            </td>
        </tr>

        <%--<%@ include file="/WEB-INF/jspf/footer.jspf" %>--%>

    </table>
</center>
</body>