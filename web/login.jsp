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

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>

<center>
    <%--===========================================================================
    Here we use a table layout.
    Class page corresponds to the '.page' element in included CSS document.
    ===========================================================================--%>
    <table id="main-container">
        <div>
            <%--===========================================================================
            This is the HEADER, containing a top menu.
            header.jspf contains all necessary functionality for it.
            Just included it in this JSP document.
            ===========================================================================--%>

            <%-- HEADER --%>
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
            <%-- HEADER --%>

            <%--===========================================================================
            This is the CONTENT, containing the main part of the page.
            ===========================================================================--%>
            <tr>
                <td class="content center">
                    <%-- CONTENT --%>

                    <%--===========================================================================
                    Defines the web form.
                    ===========================================================================--%>
                    <form id="login_form" action="controller" method="post">

                        <%--===========================================================================
                        Hidden field. In the query it will act as command=login.
                        The purpose of this to define the command name, which have to be executed
                        after you submit current form.
                        ===========================================================================--%>
                        <input type="hidden" name="command" value="login"/>

                        <%--<fieldset>--%>
                        <center>
                            <legend><fmt:message key="header.login"/></legend>
                        </center>


                        <input class="input-block-level" type="text" name="login" placeholder="<fmt:message key="login.login"/>"/><br/>
                        <br/>
                        <%--<legend>Password</legend>--%>
                        <input class="input-block-level" type="password" name="password" placeholder="<fmt:message key="settings.label.password"/>"/>
                        <%--</fieldset>--%>
                        <br/>
                        <center>
                            <input class="btn btn-primary" type="submit" value="<fmt:message key="header.login"/>">

                        </center>
                    </form>

                    <%-- CONTENT --%>

                </td>

            </tr>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>
</html>