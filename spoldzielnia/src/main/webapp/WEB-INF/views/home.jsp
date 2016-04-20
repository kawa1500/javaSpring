<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

<body>
  <div class="logo">
  	<span style="float: right">
  		<a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
  	</span>
  </div>
  <div class="login"> 
    <h1><spring:message code="home.title"/></h1>
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

