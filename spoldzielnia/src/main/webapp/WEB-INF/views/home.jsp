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
  <div class="login"> 
    <h1>Login to your account</h1>
	<h3>Dane kontaktowe jakieś trzeba dopisać ${serverTime}</h3>
    <form class="form" method="POST" action="">
      <p class="submit"><input type="submit" name="commit" value="Login"></p>			
    </form>
    
  </div>
</body>
</html>
