<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
  <title>Flat Login Form</title>
	<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
</head>
<body>
  <div class="logo"></div>
  <div class="login"> <!-- Login -->
    <h1>Login to your account</h1>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
		
    <form class="form" method="POST" action="<c:url value='j_spring_security_check' />">

      <p class="field">
        <input type="text" name="login" placeholder="Email address" required/>
        
      </p>

      <p class="field">
        <input type="password" name="password" placeholder="Password" required/>
        
      </p>

      <p class="submit"><input type="submit" name="commit" value="Login"></p>

	<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			
    </form>
  </div> <!--/ Login-->
</body>
</html>
