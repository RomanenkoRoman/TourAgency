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

<c:set var="title" value="Admin Catalog" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_order" action="controller">
                <input type="hidden" name="command" value="doHotUnHot"/>


                <table id="list_catalog_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Category</td>
                        <td>Hotel Type</td>
                        <td>People</td>
                        <td>Do Hot</td>
                        <td>Cancel</td>
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
                                    <td>HOT</td>
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
                        <td><input type="submit" value="Update" name="Update"></td>
                        <td><input type="reset" value="Reset" name="Reset"/></td>
                    </tr>


                </table>


            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>