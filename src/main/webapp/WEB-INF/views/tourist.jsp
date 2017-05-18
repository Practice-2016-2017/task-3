<%@ page import="com.roi.model.Hotel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Tourist</title>

</head>

<body>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><p>User Page</p> ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>

<c:url var="chooseAction" value="/tourist/choose"/>
<form:form action="${chooseAction}">
    <select name = "choosehotel">
        <c:forEach items = "${getAllHotels}" var = "hotel">
            <option value="${hotel.info}">${hotel.info}</option>
        </c:forEach>
    </select>
    <p><input type ="submit" value="submit"></p>
</form:form>


<c:url var="addAction" value="/tourist/addBooking"/>
<form:form action="${addAction}" >
    <table>
        <tr>
            <td><input type="date" name="date" /></td>
        </tr>
        <tr>
            <td>
                <select name="rooms">
                    <c:forEach items="${getRoomsInHotel}" var="room">
                        <option  value="${room.roomId}" >${room.roomNum} </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
            <input type="submit" value="<spring:message text="Add Booking"/>"/>
            </td>
        </tr>
    </table>
</form:form>



</body>
</html>