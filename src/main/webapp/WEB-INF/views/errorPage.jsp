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


<h3>
    <div>
        We're sorry to tell you that, but the following error occurred:
    </div>
    <p></p>
<div>
 ${errorText}
</div>
    <p></p>

    <a href="<c:url value='/${linkBack}'/>">Get Back</a>

</h3>







</body>
</html>