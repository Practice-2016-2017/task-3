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

<h1>Hotel List</h1>

<c:if test="${!empty getAllHotels}">
    <table>
        <tr>
            <th width="80">ID</th>
            <th width="120">Info</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllHotels}" var="hotel">
            <tr>
                <td>${hotel.hotelId}</td>
                <td>${hotel.info}</td>
                <td><a href="<c:url value='/remove/${hotel.hotelId}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Hotel</h1>

<c:url var="addAction" value="/hotels/add"/>

<form:form action="${addAction}" commandName="hotel">
    <table>
        <c:if test="${!empty hotel.info}">
            <tr>
                <td>
                    <form:label path="hotelId">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="hotelId" readonly="true" size="8" disabled="true"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td>
                <form:label path="info">
                    <spring:message text="Info"/>
                </form:label>
            </td>
            <td>
                <form:input path="info"/>
            </td>
        </tr>

        <tr>
        <td colspan="2">
                <c:if test="${empty hotel.info}">
                    <input type="submit"
                           value="<spring:message text="Add Hotel"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>
</body>
</html>
