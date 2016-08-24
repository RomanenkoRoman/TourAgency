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

<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <tr>

        <td class="content">
            <form id="private_office" action="controller" method="get">
                <input type="hidden" name="command" value="private_office"/>
        <td>
            Hello,${user.firstName} ${user.lastName}. This is your private office;<br>
            Your orders is :
            <br>
        </td>
        <c:choose>
            <c:when test="${fn:length(orders) == 0}">No such orders</c:when>
            <c:otherwise>
                <table id="your_order_list">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Name</td>
                        <td>Category</td>
                        <td>Price</td>
                            <%--<td>People</td>--%>
                        <td>Status</td>
                    </tr>
                    </thead>

                    <c:set var="bill" value="0"/>
                    <c:set var="k" value="0"/>

                    <c:forEach var="item" items="${orders}">
                        <c:set var="k" value="${k+1}"/>

                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.hotelName}</td>
                            <td>${item.categoryName}</td>
                            <td>${item.hotelPrice}</td>
                            <td>${item.status}</td>


                        </tr>
                    </c:forEach>

                </table>
            </c:otherwise>
        </c:choose>
        </form>


        </td>
    </tr>
</table>


<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
