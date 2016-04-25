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
<form:form method="post" action="" commandName="userRole">
    <table>
    <tr>
        <td><form:hidden path="id"/>
    </tr>
    <tr>
        <td><form:label path="role"><spring:message code="user.role"/></form:label></td>
        <td><form:input path="role" /></td>
        <td><form:errors path="role"/></td>
    </tr>
    <tr>
        <td>
            <input type="submit" value="<spring:message code="user.addRole"/>"/>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>