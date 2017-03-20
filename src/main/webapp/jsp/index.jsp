<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>hotel</title></head>
<body>
<h2>Hello World!</h2>
<%
    String user = "Drozd";
    if (1 > 0) out.println("Hello, " + user);
%>

<p> Вы ${visitorCount} посетитель!</p>

</body>
</html>
