<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 20.08.2016
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<html>
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
                Hello,${user.firstName} ${user.lastName}. This is your private office;<br>
                No such orders
            </h3>
        </td>
    </tr>

    </c:when>
    <c:otherwise>
        <table id="your_order_list">
            <thead>
            <tr>
                <td>№</td>
                <td>Name</td>
                <td>Price</td>
                <td>Category</td>
                <td>Hotel Type</td>
                <td>People</td>
                <td>Status</td>
                <td>Discount</td>
            </tr>
            </thead>

            <c:set var="bill" value="0"/>
            <c:set var="k" value="0"/>

            <h3>Hello,${user.firstName} ${user.lastName}. This is your private office;<br>
                Your orders is :
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
