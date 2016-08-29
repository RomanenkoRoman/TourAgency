<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 26.08.2016
  Time: 12:33
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
<c:set var="title" value="Discount Settings" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<center>
    <form name="discount settings" action="discountController">
        <input type="hidden" name="command" value="discount">


        <table id="main-container">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
            <tr>
                <td>

                    <label><h3><fmt:message key="discount.discountSettings"/></h3></label>
                    <fieldset>


                        <div class="form-actions">

                            <p><fmt:message key="discount.maxBorder"/></p>
                            <p><input class="span2" type="number" min="0" max="100" step="1" name="max" width="3"></p>
                            <p><fmt:message key="discount.step"/></p>
                            <p><input class="span2" type="number" min="0" max="5" name="step" width="4"></p>


                            <br>
                            <p><input class="btn btn-primary" type="submit" name="Update" value="<fmt:message key="settings.label.update"/>"></p>
                        </div>
                    </fieldset>
                </td>

            </tr>
        </table>

    </form>
</center>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
