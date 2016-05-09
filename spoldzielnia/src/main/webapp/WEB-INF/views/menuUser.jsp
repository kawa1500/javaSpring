<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
<%@page session="true"%>

<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4 style="float: right; right:20%;">
            	Content for admin ${pageContext.request.authType}.
		</h4>
</c:if>			

<table>
<tr><td><a href="/app/user/"><spring:message code="menu.admin"/></a></td></tr>
	<tr><td><a href="/app/admin"><spring:message code="menu.user.admin"/></a></td></tr>
	<tr><td><a href="/app/user/changePassword"><spring:message code="user.change"/></a></td></tr>
</table>