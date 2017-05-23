<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Error occurred</title>

</head>

<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<div>
 ${errorText}
</div>

<a href="<c:url value='/${linkBack}'/>">Get Back</a>

<div></div>






</body>
</html>