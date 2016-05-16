<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
<%@page session="true"%>




<table>
	<tr><td><a href="/app/user/"><spring:message code="menu.admin"/></a></td></tr> 
	<sec:authentication property="principal.authorities" var="authorities" />
	<sec:authorize ifAllGranted="ROLE_ADMIN">
		<tr><td><a href="/app/admin/createUser"><spring:message code="menu.createUser"/></a></td></tr>
		<tr><td><a href="/app/admin/createBuilding"><spring:message code="menu.createBuilding"/></a></td></tr>
		<tr><td><a href="/app/admin/createFlat"><spring:message code="menu.createFlat"/></a></td></tr>
		<tr><td><a href="/app/admin/manageUsers"><spring:message code="menu.manageUsers"/></a></td></tr>
		<tr><td><a href="/app/admin/createRole"><spring:message code="menu.createRole"/></a></td></tr>
		<tr><td><a href="/app/admin/manageBuilding"><spring:message code="menu.manageBuilding"/></a></td></tr>
		<tr><td><a href="/app/admin/manageFlat"><spring:message code="menu.manageFlat"/></a></td></tr>
		<tr><td><a href="/app/admin/prices"><spring:message code="menu.admin.price"/></a></td></tr>
		<tr><td><a href="/app/admin/counters"><spring:message code="menu.admin.counters"/></a></td></tr>
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_USER">
  		<tr><td><a href="/app/user/changePassword"><spring:message code="user.change"/></a></td></tr>
  		<tr><td><a href="/app/user/counters"><spring:message code="menu.user.counters"/></a></td></tr>
  		<tr><td><a href="/app/user/bills"><spring:message code="menu.user.bills"/></a></td></tr>
	</sec:authorize>
	
</table>