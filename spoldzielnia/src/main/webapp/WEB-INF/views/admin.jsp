<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
<%@page session="true"%>
<html>
<body>

  <div class="login"> 
    <h1><spring:message code="home.title"/>
    	<span style="float: right">
  		<a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
  	</span>
    </h1>
	<h3>Aplikacja spółdzielni mieszkaniowej SPRING</br>
	Dane kontaktowe</br>
	Jan kowalski</br>
	Telefon: +48 42 632 00 00</br>
	Email: biuro.spring@spring.com</h3>

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
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
				
		</h2>
	</c:if>			
    
  </div>
</body>
</html>
