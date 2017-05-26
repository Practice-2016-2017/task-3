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
    <link href="<c:url value="/WEB-INF/views/css/style.css" />" rel="stylesheet" type="text/css">
    <link href="/WEB-INF/views/css/style.css" rel="stylesheet" type="text/css">
    <title>Tourist</title>

</head>

<body>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><p>User Page</p> ${pageContext.request.userPrincipal.name} | <a id="logout"
                onclick="document.forms['logoutForm'].submit()" style="color: green;
                cursor: pointer; text-decoration: underline">Logout</a>
        </h2>
    </c:if>
</div>

<c:if test="${!empty getUserBookings}">
    <h2><p>Your bookings</p></h2>
    <table style="border: 1px solid black">
        <tr>
            <th width="115" align="center">Date</th>
            <th width="170" align="center">Hotel</th>
            <th width="60" align="center">Room</th>
            <th width="60" align="center">Delete</th>
        </tr>
        <c:forEach items="${getUserBookings}" var="booking">
            <tr>
                <td width="115" align="center">${booking.date.toString().substring(0, 10)}</td>
                <td width="170" align="center">${booking.room.hotel.info}</td>
                <td width="60" align="center">${booking.room.roomNum}</td>
                <td width="60" align="center"><a href="<c:url value='/removeBooking/${booking.bookingId}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h2><p>Choose date</p></h2>
<c:url var="chooseAction" value="/tourist/choose"/>
<form:form action="${chooseAction}">

    <tr>
        <td><input type="date" name="date"/></td>
    </tr>
    <p><input type="submit" value="submit"></p>
</form:form>


<c:if test="${empty getAvailableRooms}">
    <c:url var="chooseAction1" value="/tourist/hotelchoose/${chosenDate}"/>
    <form:form action="${chooseAction1}">
        <c:if test="${!empty getHotelsByDate}">

            <div>
                Hotels, available for ${chosenDate}
            </div>

            <select name="choosehotel">
                <c:forEach items="${getHotelsByDate}" var="hotel">
                    <option value="${hotel.hotelId}">${hotel.info}</option>
                </c:forEach>
            </select>

            <p><input type="submit" value="submit"></p>

        </c:if>

    </form:form>
</c:if>


<c:if test="${!empty getAvailableRooms}">
    <c:url var="chooseAction2" value="/tourist/addBooking/${chosenDate}"/>
    <form:form action="${chooseAction2}">
        <div>
            Rooms, available for ${chosenDate}
        </div>

        <select name="chooseRoom">
            <c:forEach items="${getAvailableRooms}" var="room">
                <option value="${room.roomId}">${room.roomNum}</option>
            </c:forEach>
        </select>

        <p><input type="submit" value="submit"></p>

    </form:form>
</c:if>

</body>
</html>