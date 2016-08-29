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

<c:set var="title" value="Sign Up"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<body>
<center>

    <table id="main-container">


        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content center">


                <form id="sign_up_form" action="controller" method="post">


                    <input type="hidden" name="command" value="signUp"/>


                    <fieldset>

                        <center>
                            <legend><h3><fmt:message key="header.signUp"/></h3></legend>
                        </center>
                        <%--<legend>Login</legend>--%>
                        <input class="input-block-level" type="text" placeholder="<fmt:message key="login.login"/>" name="login"/><br/>

                        <br/>
                        <div>
                            <%--<legend>Password</legend>--%>
                            <input class="input-block-level" type="text" placeholder="<fmt:message key="settings.label.password"/>" name="password"/>
                        </div>
                        <br/>

                        <%--<legend>First name</legend>--%>
                        <input class="input-block-level" type="text" placeholder="<fmt:message key="settings.label.firstName"/>" name="firstName"/><br/>

                        <br/>

                        <%--<legend>Last name</legend>--%>
                        <input class="input-block-level" type="text" placeholder="<fmt:message key="settings.label.lastName"/>" name="lastName"/>
                    </fieldset>

                    <center><input class="btn btn-primary" type="submit" value="<fmt:message key="login.submit"/>">
                    </center>
                </form>

                <br>

                <h3>${message}</h3>

            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>
</html>