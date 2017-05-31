<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Create an account</title>


</head>

<body>

<div class="container" align="center">

    <form:form method="POST" modelAttribute="userForm" >
        <h2 align="center" > Create your account </h2>
        <p></p>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username"  placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>


        <p></p>
        <spring:bind path="password">
            <div   class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <p></p>

        <spring:bind path="confirmPassword">
            <div  class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit" align=" center">Submit</button>
    </form:form>

</div>
<!-- /container -->
</body>
</html>