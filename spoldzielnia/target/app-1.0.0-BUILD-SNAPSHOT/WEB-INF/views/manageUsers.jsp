<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
<tr>
    <th><spring:message code="user.firstName"/></th>
     <th><spring:message code="user.lastName"/></th>
    <th><spring:message code="user.email"/></th>
    <th><spring:message code="user.phone"/></th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.firstName} </td>
        <td>${user.lastName} </td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td><a href="/app/admin/deleteUser?idUser=${user.idUser}"><spring:message code="user.delete"/></a></td>
         <td><a href="/app/admin/createUser?idUser=${user.idUser}"><spring:message code="user.edit"/></a></td>
    </tr>
</c:forEach>
</table>


 