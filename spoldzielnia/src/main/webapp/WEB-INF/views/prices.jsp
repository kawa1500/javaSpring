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
<form:form method="post" action="" commandName="price">
    <table>
    <tr>
        <td><form:hidden path="idPrices"/>
    </tr>
    <tr>
        <td><form:label path="water"><spring:message code="price.water"/> zł/m2</form:label></td>
        <td><form:input path="water" /></td>
        <td><form:errors path="water"/></td>
    </tr>
    <tr>
        <td><form:label path="gas"><spring:message code="price.gas"/> zł/m3</form:label></td>
        <td><form:input path="gas" /></td>
        <td><form:errors path="gas"/></td>
    </tr>
    <tr>
        <td><form:label path="current"><spring:message code="price.current"/> zł/kWh</form:label></td>
        <td><form:input path="current" /></td>
        <td><form:errors path="current"/></td>
    </tr>
    <tr>
        <td><form:label path="energy"><spring:message code="price.energy"/> zł/kJ</form:label></td>
        <td><form:input path="energy" /></td>
        <td><form:errors path="energy"/></td>
    </tr>
    <tr>
        <td><form:label path="intercom" ><spring:message code="price.intercom"/> zł/<spring:message code="price.person"/></form:label></td>
        <td><form:input path="intercom" /></td>
        <td><form:errors path="intercom"/></td>
    </tr>
    <tr>
        <td><form:label path="trash"><spring:message code="price.trash"/> zł/<spring:message code="price.person"/></form:label></td>
        <td><form:input path="trash" /></td>
        <td><form:errors path="trash"/></td>
    </tr>
    <tr>
        <td><form:label path="sewage"><spring:message code="price.sewage"/> zł/m3</form:label></td>
        <td><form:input path="sewage" /></td>
        <td><form:errors path="sewage"/></td>
    </tr>
    <tr>
		<td><form:label path="other"><spring:message code="price.trash"/> zł/<spring:message code="price.person"/></form:label></td>
		<td><form:input path="other" /></td>
		<td><form:errors path="other"/></td>
	</tr>
	<tr>
		<td><form:label path="rWater"><spring:message code="price.ryczalt.water"/></form:label></td>
		<td><form:input path="rWater" /></td>
		<td><form:errors path="rWater"/></td>
	</tr>
	<tr>
		<td><form:label path="rGas"><spring:message code="price.ryczalt.gas"/></form:label></td>
		<td><form:input path="rGas" /></td>
		<td><form:errors path="rGas"/></td>
	</tr>
	<tr>
		<td><form:label path="rCurrent"><spring:message code="price.ryczalt.current"/></form:label></td>
		<td><form:input path="rCurrent" /></td>
		<td><form:errors path="rCurrent"/></td>
	</tr>
	<tr>
		<td><form:label path="rEnergy"><spring:message code="price.ryczalt.energy"/></form:label></td>
		<td><form:input path="rEnergy" /></td>
		<td><form:errors path="rEnergy"/></td>
	</tr>
    <tr>
        <td>
            <input type="submit" value="<spring:message code="price.submit"/>"/>
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
    <th><spring:message code="price.intercom"/></th>
    <th><spring:message code="price.trash"/></th>
    <th><spring:message code="price.sewage"/></th>
    <th><spring:message code="price.other"/></th>
    <th><spring:message code="price.ryczalt.water"/></th>
    <th><spring:message code="price.ryczalt.gas"/></th>
    <th><spring:message code="price.ryczalt.current"/></th>
    <th><spring:message code="price.ryczalt.energy"/></th>
    <th><spring:message code="price.date"/></th>
</tr>
<c:forEach items="${priceList}" var="prices">
    <tr>
        <td align="center">${prices.water} </td>
        <td align="center">${prices.gas} </td>
        <td align="center">${prices.current} </td>
        <td align="center">${prices.energy} </td>
        <td align="center">${prices.intercom} </td>
        <td align="center">${prices.trash} </td>
        <td align="center">${prices.sewage} </td>
        <td align="center">${prices.other} </td>
        <td align="center">${prices.rWater} </td>
        <td align="center">${prices.rGas} </td>
        <td align="center">${prices.rCurrent} </td>
        <td align="center">${prices.rEnergy} </td>
        <td align="center">${prices.createDate} </td>
    </tr>
</c:forEach>
</table>
</body>
</html>