<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="YourOrder" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <form id="your_order_form" action="controller" method="get">
                <input type="hidden" name="command" value="update_order"/>

                <table id="your_order_list">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Category</td>
                        <td>People</td>
                    </tr>
                    </thead>

                    <c:set var="bill" value="0"/>
                    <c:set var="k" value="0"/>
                    <%--<jsp:useBean id="results" scope="request" type="java.util.List"/>--%>
                    <c:forEach var="item" items="${results}">
                        <c:set var="k" value="${k+1}"/>
                        <c:set var="bill" value="${bill+item.price}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>${item.categoryId}</td>
                            <td>${item.peopleQuantity}</td>

                        </tr>
                    </c:forEach>

                </table>

            </form>
        </td>
    </tr>
    <tr>
        <td>
            Your bill is ${bill}
            <input type="submit" name="confirm1" value="Confirm">
        </td>
    </tr>
</table>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
