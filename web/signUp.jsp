

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign Up"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<center>

    <table id="main-container">


        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content center">


                <form id="sign_up_form" action="controller" method="post">


                    <input type="hidden" name="command" value="signUp"/>


                    <fieldset>
                        <legend><h3>Sign Up</h3></legend>

                        <legend>Login</legend>
                        <input name="login"/><br/>

                        <br/>
                        <div>
                            <legend>Password</legend>
                            <input name="password"/>
                        </div>
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