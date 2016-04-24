<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
</head>
<body>
<form:form method="post" action="" commandName="build">
    <table>
    <tr>
        <td><form:hidden path="idBuild"/>
    </tr>
    <tr>
        <td><form:label path="buildNumber"><spring:message code="build.buildNumber"/></form:label></td>
        <td><form:input path="buildNumber" /></td>
    </tr>
    <tr>
        <td><form:label path="buildAddress"><spring:message code="build.buildAddress"/></form:label></td>
        <td><form:input path="buildAddress" /></td>
    </tr>
       
    
</table> 
</form:form>
</body>
</html>
 
 