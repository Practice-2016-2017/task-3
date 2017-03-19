<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<h2>Hello World!</h2>
<%
    String user="alex";
    if(1>0) out.println(user);
%>

<c:out value="${1 > 0}" />
<c:if test="${1 > 0}">
    <p> Hello Again!</p>>
</c:if>

</body>
</html>
