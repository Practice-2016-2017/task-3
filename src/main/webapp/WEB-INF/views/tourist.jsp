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

<a href="${contextPath}/welcome">Back to main menu</a>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><p>User Page</p> ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>

<c:url var="addAction" value="/tourist/addBooking"/>
<form:form action="${addAction}" >
    <table>
        <tbody>
        <tr>
            <td>
                <select name="rooms">
                    <c:forEach items="${getAllRooms}" var="room">
                        <option  value="${room.roomId}" >${room.roomNum} </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="date" name="date" /></td>
        </tr>
        <tr>
            <td>
            <input type="submit" value="<spring:message text="Add Booking"/>"/>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>

</body>
</html>