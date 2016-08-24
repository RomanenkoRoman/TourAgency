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

<c:set var="title" value="List Orders" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


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
                        <table id="list_order_table">
                            <thead>
                            <tr>
                                <td>№</td>
                                <td>First name</td>
                                <td>Last name</td>
                                <td>Hotel</td>
                                <td>Category</td>
                                <td>Type</td>
                                <td>Price</td>
                                <td>Status</td>
                                <td>Change Status</td>
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
                                    <td>${bean.hotelPrice}</td>
                                    <td>${bean.status}</td>
                                    <td>
                                            <%--<input type="checkbox" name="paid" value="${k}">paid--%>
                                            <%--<input type="checkbox" name="canceled" value="${k}">canceled--%>

                                        <input type="radio" name="${k}" value="paid">paid
                                        <input type="radio" name="${k}" value="canceled">canceled

                                            <%--<c:set var="radio">${k}</c:set>--%>
                                            <%--<c:set var="list">${bean.orderId} ${bean.catalogId}</c:set>--%>

                                            <%--<input type="hidden" name="list" value="${list}"/>--%>
                                            <%--<input type="hidden" name="catalogId" value="${bean.catalogId}"/>--%>

                                    </td>
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
                                    <input type="submit" value="Update" name="Update">
                                    <input type="reset" value="Reset" name="Reset">
                                </td>
                            </tr>
                        </table>


                    </c:otherwise>
                </c:choose>

                <%-- CONTENT --%>
            </td>
        </tr>
    </form>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>