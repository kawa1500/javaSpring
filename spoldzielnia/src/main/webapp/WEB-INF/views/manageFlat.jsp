<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="tabelka">
<tr>
	<th><spring:message code="building.buildingStreet"/></th>
    <th><spring:message code="building.buildingNumber"/></th>
    <th><spring:message code="building.buildingPostCode"/></th>
    <th><spring:message code="building.buildingCity"/></th>
    <th><spring:message code="flat.flatNumber"/></th>
    <th><spring:message code="flat.flatSurface"/></th>
    <th><spring:message code="flat.tenantNumber"/></th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${flat}" var="flat">
    <tr>
    	<td>${flat.building.buildingStreet} </td>
        <td>${flat.building.buildingNumber} </td>
        <td>${flat.building.buildingPostCode}</td>
        <td>${flat.building.buildingCity}</td>
        <td>${flat.flatNumber} </td>
  		<td>${flat.flatSurface} </td>
  		<td>${flat.tenantNumber} </td>
  
        <td><a class="myButton" href="/app/admin/deleteFlat?idFlat=${flat.idFlat}"><spring:message code="flat.delete"/></a></td>
         <td><a class="myButton" href="/app/admin/createFlat?idFlat=${flat.idFlat}"><spring:message code="flat.edit"/></a></td>
    </tr>
</c:forEach>
</table>


 