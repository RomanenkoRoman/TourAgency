<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<center>

    <table id="main-container">


        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content center">


                <form id="sign_in_form" action="controller" method="post">


                    <input type="hidden" name="command" value="signIn"/>


                    <fieldset>
                        <legend>Sign In</legend>

                        <legend>Login</legend>
                        <input name="login"/><br/>

                        <br/>

                        <legend>Password</legend>
                        <input name="password"/>

                        <br/>

                        <legend>First name</legend>
                        <input name="firstName"/><br/>

                        <br/>

                        <legend>Last name</legend>
                        <input name="lastName"/>
                    </fieldset>


                    <input type="submit" value="Submit">
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