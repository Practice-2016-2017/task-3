<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Hotels</title>
</head>
<body>
<a href="../../hotel">Back to main menu</a>

<br/>
<br/>

<h1>User List</h1>

<form action = "addManager" >

<c:if test="${!empty getAllHotelId}">
    <table>
        <tr>
            <th width="80">ID</th>
            <th width="120">Info</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllHotelId}" var="hotel">
            <tr>
                <td><INPUT TYPE="radio" name="hotelId" value=${user.id}/>${hotel.hotelId}</td>
                <td>${hotel.info}</td>
                <td><a href="<c:url value='/remove/${hotel.hotelId}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>



<c:if test="${!empty getAllUsers}">
    <table>
        <tr>
            <th width="80">ID</th>
            <th width="120">Info</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllUsers}" var="user">
            <tr>
                <td><INPUT TYPE="radio" name="userId" value=${user.id}/>${user.id}</td>
                <td>${user.username}</td>
                <td><a href="<c:url value='/removeUser/${user.id}'/>">Delete</a></td>

            </tr>

        </c:forEach>
    </table>
</c:if>


    <input type = "submit" value = "Submit" />

</form>>


</body>
</html>
