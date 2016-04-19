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
	<h3>Aplikacja spółdzielni mieszkaniowej SPRING</br>
	Dane kontaktowe</br>
	Jan kowalski</br>
	Telefon: +48 42 632 00 00</br>
	Email: biuro.spring@spring.com</h3>
    <form class="form" method="POST" action="">
      <p class="submit"><input type="submit" name="commit" value="Login"></p>			
    </form>
    
  </div>
</body>
</html>
