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

    <tr>
        <td><input type="date" name="date" /></td>
    </tr>
    <p><input type ="submit" value="submit"></p>
</form:form>



<c:if test="${empty getAvailableRooms}">
<c:url var="chooseAction1" value="/tourist/hotelchoose/${chosenDate}"/>
<form:form action="${chooseAction1}">





    <c:if test="${!empty getHotelsByDate}">

    <div>
        Hotels, available for ${chosenDate}
    </div>

    <select name = "choosehotel">
        <c:forEach items = "${getHotelsByDate}" var = "hotel">
            <option value="${hotel.hotelId}">${hotel.info}</option>
        </c:forEach>
    </select>

    <p><input type ="submit" value="submit"></p>

    </c:if>

</form:form>
</c:if>


<c:if test="${!empty getAvailableRooms}">
    <c:url var="chooseAction2" value="/tourist/addBooking/${chosenDate}"/>
    <form:form action="${chooseAction2}">
    <div>
        Rooms, available for ${chosenDate}
    </div>

    <select name = "chooseRoom">
        <c:forEach items = "${getAvailableRooms}" var = "room">
            <option value="${room.roomId}">${room.roomNum}</option>
        </c:forEach>
    </select>

    <p><input type ="submit" value="submit"></p>

</form:form>
</c:if>

</body>
</html>