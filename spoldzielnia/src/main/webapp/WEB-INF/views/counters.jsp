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
<form:form method="post" action="" commandName="counter">
    <table>
    <tr>
        <td><form:hidden path="idCounter"/>
    </tr>
    <tr>
        <td><form:label path="water"><spring:message code="price.water"/> m3</form:label></td>
        <td><form:input path="water" /></td>
        <td><form:errors path="water"/></td>
    </tr>
    <tr>
        <td><form:label path="gas"><spring:message code="price.gas"/> m3</form:label></td>
        <td><form:input path="gas" /></td>
        <td><form:errors path="gas"/></td>
    </tr>
    <tr>
        <td><form:label path="current"><spring:message code="price.current"/> kWh</form:label></td>
        <td><form:input path="current" /></td>
        <td><form:errors path="current"/></td>
    </tr>
    <tr>
        <td><form:label path="energy"><spring:message code="price.energy"/> kJ</form:label></td>
        <td><form:input path="energy" /></td>
        <td><form:errors path="energy"/></td>
    </tr>
    <tr>
        <td>
        	<c:if test="${update==true}">
            	<input type="submit" value="<spring:message code="price.submit"/>"/>
        	</c:if>
        </td>
    </tr>
</table> 
</form:form>
<table style="width: 100%">
<tr>
    <th><spring:message code="price.water"/></th>
    <th><spring:message code="price.gas"/></th>
    <th><spring:message code="price.current"/></th>
    <th><spring:message code="price.energy"/></th>
    <th><spring:message code="price.date"/></th>
</tr>
<c:forEach items="${counterList}" var="counters">
    <tr>
        <td align="center">${counters.water} </td>
        <td align="center">${counters.gas} </td>
        <td align="center">${counters.current} </td>
        <td align="center">${counters.energy} </td>
        <td align="center">${counters.modDate} </td>
    </tr>
</c:forEach>
</table>
</body>
</html>