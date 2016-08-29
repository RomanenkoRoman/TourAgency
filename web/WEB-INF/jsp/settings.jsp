<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 04.08.2016

  To change this template use File | Settings | File Templates.
--%>
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
<%--<html>--%>
<c:set var="title" value="Settings" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">
                <%-- CONTENT --%>
                    <%--       <fmt:message key=""/>    --%>
                <form id="settings_form" action="controller" method="post">
                    <input type="hidden" name="command" value="updateSettings"/>
                    <label><h3><fmt:message key="settings.label.settings"/></h3></label>
                    <div class="form-actions">
                        <label for="language"><fmt:message key="settings.label.language"/> </label>
                        <select class="span2" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="settings.label.russian"/> </option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="settings.label.english"/></option>
                        </select>

                        <label><fmt:message key="settings.label.firstName"/> </label>
                        <input name="firstName">

                        <label><fmt:message key="settings.label.lastName"/> </label>
                        <input name="lastName">
                    </div>

                    <fmt:message key="settings.label.update" var="sub"/>
                    <input class="btn btn-primary" type="submit" value="${sub}">

                    <fmt:message key="settings.label.reset" var="res"/>
                    <input class="btn btn-info" type="reset" value="${res}">
                <%--</form>--%>
                    <%--<form method="post">--%>
                        <%--<label for="username"><fmt:message key="settings.label.username" />:</label>--%>
                        <%--<input type="text" id="username" name="username">--%>
                        <%--<br>--%>
                        <%--<label for="password"><fmt:message key="settings.label.password" />:</label>--%>
                        <%--<input type="password" id="password" name="password">--%>
                        <%--<br>--%>
                        <%--<fmt:message key="login.button.submit" var="buttonValue" />--%>
                        <%--<input type="submit" name="submit" value="${buttonValue}">--%>
                    <%--</form>--%>

                <%-- CONTENT --%>
            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>
</html>