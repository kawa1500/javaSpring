<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
</head>
<body>
<form:form method="post" action="" commandName="flat">
    <table>
    <tr>
        <td><form:hidden path="idFlat"/>
    </tr>
    <tr>
        <td><form:label path="flatNumber"><spring:message code="flat.flatNumber"/></form:label></td>
        <td><form:input path="flatNumber" /></td>
    </tr>
    <tr>
       
    </tr>
</table> 
</form:form>
</body>
</html>
 