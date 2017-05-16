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
<a href="../../welcome">Back to main menu</a>

<br/>
<br/>

<h1>Hotel List</h1>

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

<c:url var="addAction1" value="/users/addH"/>
<p></p>
<form:form action="${addAction1}" commandName="hotel">
    <table>
        <tr>
            <td>
                <form:label path="info">
                    <spring:message text="Info"/>
                </form:label>
            </td>
            <td>
                <form:input path="info"/>
            </td>

            <td colspan="2">
                <c:if test="${empty hotel.info}">
                    <input type="submit" value="<spring:message text="Add Hotel"/>"/>
                </c:if>
            </td>
        </tr>

    </table>

</form:form>

<h1>Manager and tourist List</h1>

<c:if test="${!empty getAllUsers}">
    <table>
        <tr>
            <th width="80">ID</th>
            <th width="120">Info</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllUsers}" var="user">
            <%--<c:if test="${user.biggestRole.name != ROLE_ADMIN}">--%>

                <tr>
                    <td><INPUT TYPE="radio" name="userId" value=${user.id}/>${user.id}</td>
                    <td>${user.username}</td>
                    <td><a href="<c:url value='/removeUser/${user.id}'/>">Delete</a></td>

                </tr>

            <%--</c:if>--%>
        </c:forEach>
    </table>
</c:if>
<p>

    <c:url var="addAction2" value="/users/addManager"/>
<p></p>
<form:form action="${addAction2}" commandName="hotel">    <label>
        <select name="HotelInfo">
            <c:forEach items="${getAllHotelId}" var="hotel">
                <option value="${hotel.info}">${hotel.info}</option>
            </c:forEach>
        </select>
    </label>

    <label>
        <select name="Username">
            <c:forEach items="${getAllUsers}" var="user">
                <option value="${user.username}">${user.username}</option>
            </c:forEach>
        </select>
    </label>

    <input type="submit" value="<spring:message text="Add manager"/>"/>
    </form:form>
</body>
</html>
