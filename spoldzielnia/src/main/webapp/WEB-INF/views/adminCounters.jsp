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
<c:if  test="${!empty confirmList}">
<table style="width: 90%">
<tr>
	<td><h2><spring:message code="counter.confirmation"/></h2></td>
	<td align="right"><a href="/app/admin/confirmAll"><spring:message code="counter.confirmAll"/></a></td>
</tr>
</table>
<table style="width: 100%">
<tr>
	<th>ID USER</th>
    <th><spring:message code="price.water"/></th>
    <th><spring:message code="price.gas"/></th>
    <th><spring:message code="price.current"/></th>
    <th><spring:message code="price.energy"/></th>
    <th><spring:message code="counter.ryczalt"/></th>
    <th><spring:message code="price.date"/></th>
</tr>
<c:forEach items="${confirmList}" var="counters">
    <tr>
    	<td align="center">${counters.idFlat} </td>
        <td align="center">${counters.water} </td>
        <td align="center">${counters.gas} </td>
        <td align="center">${counters.current} </td>
        <td align="center">${counters.energy} </td>
        <td align="center">${counters.ryczalt} </td>
        <td align="center">${counters.modDate} </td>
        <td><a href="/app/admin/confirm?idCounter=${counters.idCounter}"><spring:message code="counter.confirm"/></a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</br></br>
<c:if  test="${!empty ryczaltList}">
<table style="width: 90%">
<tr>
	<td><h2><spring:message code="counter.ryczaltion"/></h2></td>
	<c:if test="${update==true}">
            	<td align="right"><a href="/app/admin/ryczaltAll"><spring:message code="counter.ryczaltAll"/></a></td>
    </c:if>
</tr>
</table>
<table style="width: 100%">
<tr>
	<th>ID USER</th>
    <th><spring:message code="price.water"/></th>
    <th><spring:message code="price.gas"/></th>
    <th><spring:message code="price.current"/></th>
    <th><spring:message code="price.energy"/></th>
    <th><spring:message code="counter.ryczalt"/></th>
    <th><spring:message code="price.date"/></th>
</tr>
<c:forEach items="${ryczaltList}" var="counters">
    <tr>
    	<td align="center">${counters.idFlat} </td>
        <td align="center">${counters.water} </td>
        <td align="center">${counters.gas} </td>
        <td align="center">${counters.current} </td>
        <td align="center">${counters.energy} </td>
        <td align="center">${counters.ryczalt} </td>
        <td align="center">${counters.modDate} </td>
        <c:if test="${update==true}">
            	<td><a href="/app/admin/ryczalt?idUser=${counters.idFlat}&idCounter=${counters.idCounter}"><spring:message code="counter.ryczalt"/></a></td>
        </c:if>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>