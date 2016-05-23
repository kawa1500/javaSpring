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
    <table>
    <tr>
        <td><form:hidden path="idUser"/>
    </tr>
    <tr>
        <td><form:label path="firstName"><spring:message code="user.firstName"/></form:label></td>
        <td><form:input path="firstName" /></td>
        <td><form:errors path="firstName"/></td>
    </tr>
    <tr>
        <td><form:label path="lastName"><spring:message code="user.lastName"/></form:label></td>
        <td><form:input path="lastName" /></td>
        <td><form:errors path="lastName"/></td>
    </tr>
    <tr>
        <td><form:label path="email"><spring:message code="user.email"/></form:label></td>
        <td><form:input path="email" /></td>
        <td><form:errors path="email"/></td>
    </tr>
    <tr>
        <td><form:label path="login"><spring:message code="user.login"/></form:label></td>
        <td><form:input path="login" /></td>
        <td><form:errors path="login"/></td>
    </tr>
    <tr>
        <td><form:label path="password" ><spring:message code="user.password"/></form:label></td>
        <td><form:input type="password" path="password" /></td>
        <td><form:errors path="password"/></td>
    </tr>
    <tr>
        <td><form:label path="PESEL">PESEL</form:label></td>
        <td><form:input path="PESEL" /></td>
        <td><form:errors path="PESEL"/></td>
    </tr>
    <tr>
        <td><form:label path="phone"><spring:message code="user.phone"/></form:label></td>
        <td><form:input path="phone" /></td>
        <td><form:errors path="phone"/></td>
    </tr>
    <tr>
		<td><form:label path="flat"></form:label></td>
		<td><form:select path="flat" multiple="false">
				<form:options items="${flatList}" itemValue="idFlat" itemLabel="flatNumber"/>
		</form:select></td>
		<td><form:errors path="flat"/></td>
	
    <tr>
    <tr>
		<td><form:label path="userRole"></form:label></td>
		<td><form:select path="userRole" multiple="true">
				<form:options items="${userRoleList}" itemValue="id" itemLabel="role"/>
		</form:select></td>
		<td><form:errors path="userRole"/></td>
	</tr>
    <tr>
        <td>
			<c:if test="${user.idUser<=0}">
            	<input type="submit" value="<spring:message code="user.add"/>"/>
        	</c:if>
        	<c:if test="${user.idUser>0}">
            	<input type="submit" value="<spring:message code="user.edit"/>"/>
        	</c:if>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>