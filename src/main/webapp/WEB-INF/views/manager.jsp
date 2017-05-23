<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Manager</title>

</head>

<body>


<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Manager Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()" style="color: green">Logout</a>
        </h2>

    </c:if>
</div>

<div>
You work at ${attachedHotel}
</div>
<div></div>
<a href="<c:url value='/manager/addRoom'/>">Add new room</a>


<c:if test="${!empty Rooms}">
    <table>
        <tr>
            <th width="60" align="center">ID</th>
            <th width="60" align="center">Room Number</th>
        </tr>
        <c:forEach items="${Rooms}" var="room">
            <tr>
                <td width="60" align="center">${room.roomId}</td>
                <td width="60" align="center">${room.roomNum}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>




</body>
</html>