<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Add item to Catalog"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<center>

    <table id="main-container">


        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content center">


                <form id="add_catalog_item" action="controller" method="post">


                    <input type="hidden" name="command" value="addCatalogItem"/>


                    <fieldset>
                        <legend>Add Catalog Item</legend>
                        <br>

                        <div>
                            <legend>Name</legend>
                            <input name="name"/>
                        </div>
                        <br/>

                        <div>
                            <legend>Category type</legend>
                            <select name="categoryType">
                                <option selected disabled>Choose category</option>
                                <option name="rest" value="rest">REST</option>
                                <option name="excursion" value="excursion">EXCURSION</option>
                                <option name="shopping" value="shopping">SHOPPING</option>
                            </select>
                        </div>
                        <br/>

                        <div>
                            <legend>Hotel type</legend>
                            <select  name="hotelType">
                                <option selected disabled>Choose hotel</option>
                                <option name="hotel" value="hotel">HOTEL</option>
                                <option name="motel" value="motel">MOTEL</option>
                                <option name="boutique" value="boutique">BOUTIQUE</option>
                                <option name="apartments" value="apartments">APARTMENTS</option>
                            </select>
                        </div>
                        <br/>

                        <div>
                            <legend>People quantity</legend>
                            <select name="quantity">
                                <option selected disabled>Quantity</option>
                                <option name="1" value="1">1</option>
                                <option name="2" value="2">2</option>
                                <option name="3" value="3">3</option>
                                <option name="4" value="4">4</option>
                            </select>
                        </div>
                        <br/>
                        <div>
                            <legend>Price</legend>
                            <input type="number" min="500" max="10000" name="price"/>
                        </div>
                    </fieldset>


                   <h3>${message}</h3>

                    <input type="submit" value="Add to catalog">
                    <input type="reset" value="Reset">
                </form>

                <br>


            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    </table>
</center>
</body>
</html>