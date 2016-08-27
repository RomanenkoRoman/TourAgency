<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 26.08.2016
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <title>Discount Settings</title>
</head>
<body>
<center>
    <form name="discount settings" action="discountController">
        <input type="hidden" name="command" value="discount">


        <table id="main-container">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
            <tr>
                <td>

                        <label><h3>Discount settings</h3></label>


                        <div>

                            <p>Max border</p>
                            <p><input type="number" min="0" max="100" step="1" name="max" width="3"></p>
                        </div>
                        <div>
                            <p>Step</p>
                            <p><input type="number" min="0" max="5" name="step" width="4"></p>
                        </div>


                    <div>
                        <br>
                        <p><input type="submit" name="Update" value="Update"></p>
                    </div>
                </td>

            </tr>
        </table>

    </form>
</center>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
