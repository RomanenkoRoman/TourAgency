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

<c:set var="title" value="List Users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<center>


    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="banUnBan">
            <tr>
                <td class="content">
                    <%-- CONTENT --%>

                    <c:set var="k" value="0"/>
                    <c:choose>

                        <c:when test="${fn:length(userList) == 0}">No found users</c:when>
                        <c:otherwise>
                            <fieldset>
                                <legend><h3>All users</h3></legend>
                                <table id="list_order_table">
                                    <thead>
                                    <tr>
                                        <td>№</td>
                                        <td>Login</td>
                                        <td>Password</td>
                                        <td>First name</td>
                                        <td>Last name</td>
                                        <td>Role</td>
                                        <td>Ban</td>
                                        <td>Unban</td>

                                    </tr>
                                    </thead>


                                    <c:forEach var="bean" items="${userList}">
                                        <c:set var="k" value="${k+1}"/>

                                        <tr>
                                            <td>${k}</td>
                                            <td>${bean.login}</td>
                                            <td>${bean.password}</td>
                                            <td>${bean.firstName} </td>
                                            <td>${bean.lastName}</td>
                                            <td>${bean.role}</td>

                                            <c:choose>
                                                <c:when test="${bean.ban == true}">
                                                    <td>BANNED</td>
                                                    <td><input type="checkbox" name="unBan" value="${bean.id}"></td>
                                                </c:when>
                                                <c:when test="${bean.ban == false}">
                                                    <td><input type="checkbox" name="ban" value="${bean.id}"></td>
                                                    <td></td>
                                                </c:when>
                                            </c:choose>

                                            </td>

                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <br/>
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
                            </fieldset>

                        </c:otherwise>
                    </c:choose>

                    <%-- CONTENT --%>
                </td>
            </tr>
        </form>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>

</html>