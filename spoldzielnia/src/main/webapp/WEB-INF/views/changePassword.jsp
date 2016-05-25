<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
</head>
<body>
<form:form method="post" action="" commandName="user">
    <table class="tabelka">
    <tr>
        <td><form:hidden path="oldPasswordHash"/>
    </tr>
    <tr>
        <td><form:label path="login"><spring:message code="user.login"/></form:label></td>
        <td><form:input path="login" /></td>
        <td><form:errors path="login"/></td>
    </tr>
    <tr>
        <td><form:label path="oldPassword"><spring:message code="user.password"/></form:label></td>
        <td><form:input type="password" path="oldPassword" /></td>
        <td><form:errors path="oldPassword"/></td>
    </tr>
    <tr>
        <td><form:label path="newPassword"><spring:message code="user.password.new"/></form:label></td>
        <td><form:input type="password" path="newPassword" /></td>
        <td><form:errors path="newPassword"/></td>
    </tr>
    <tr>
        <td>
			<input class="myButton" type="submit" value="<spring:message code="user.change"/>"/>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>