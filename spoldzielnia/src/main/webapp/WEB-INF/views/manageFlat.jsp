<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
<tr>
    <th><spring:message code="flat.flatNumber"/></th>
    <th><spring:message code="flat.flatSurface"/></th>
    <th><spring:message code="flat.tenantNumber"/></th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${flat}" var="flat">
    <tr>
        <td>${flat.flatNumber} </td>
  		<td>${flat.flatSurface} </td>
  		<td>${flat.tenantNumber} </td>
  
        <td><a href="/app/admin/deleteFlat?idFlat=${flat.idFlat}"><spring:message code="flat.delete"/></a></td>
         <td><a href="/app/admin/createFlat?idFlat=${flat.idFlat}"><spring:message code="flat.edit"/></a></td>
    </tr>
</c:forEach>
</table>


 