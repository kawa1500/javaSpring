<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
<tr>
    <th><spring:message code="building.buildingStreet"/></th>
     <th><spring:message code="building.buildingNumber"/></th>
    <th><spring:message code="building.buildingPostCode"/></th>
    <th><spring:message code="building.buildingCity"/></th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${building}" var="building">
    <tr>
   
        <td>${building.buildingStreet} </td>
        <td>${building.buildingNumber} </td>
        <td>${building.buildingPostCode}</td>
        <td>${building.buildingCity}</td>
        <td><a href="/app/admin/deleteBuilding?idBuilding=${building.idBuilding}"><spring:message code="building.delete"/></a></td>
         <td><a href="/app/admin/createBuilding?idBuilding=${building.idBuilding}"><spring:message code="building.edit"/></a></td>
    </tr>
</c:forEach>
</table>


 