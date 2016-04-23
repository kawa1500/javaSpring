<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <form class="form" method="post" action="createUser.html" align=center>
  	<p>First name:<br><input type="text" name="firstName" placeholder="First Name" required/></p>
	<p>Last Name:<br><input type="text" name="lastName" placeholder="Last Name" required/></p>
	<p>PESEL:<br><input type="text" name="PESEL" placeholder="PESEL" required/></p>
	<p>Date of Birth:<br><input type="text" name="birthDate" placeholder="Date of Birth" required/></p>
	<p>Email:<br><input type="text" name="email" placeholder="Email" required/></p>
	<p>Telephone:<br><input type="text" name="phone" placeholder="Telephone" required/></p>
	<p>Number of House:<br><input type="text" name="house" placeholder="Number of house" required/></p>
	<p>Number of Flat:<br><input type="text" name="flat" placeholder="Number of Flat" required/></p>
	<p>Password:<br><input type="text" name="password" placeholder="Password" required/></p>  
	<p class="submit"><input type="submit" value="Add User"></p>  <!-- Przycisk -->
	</form>
	
	
 