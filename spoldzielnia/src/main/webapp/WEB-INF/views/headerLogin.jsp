<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
<%@page session="true"%>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4 style="float: right; right:20%;">
			<spring:message code="home.welcome"/>: ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
				
		</h4>
	</c:if>			
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<h4 style="float: right; right:20%;">
			<spring:message code="home.welcome"/> | <a href="/app/user/"><spring:message code="label.login"/></a>
				
		</h4>
	</c:if>
  