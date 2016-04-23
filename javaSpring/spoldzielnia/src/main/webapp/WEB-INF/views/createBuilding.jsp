<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <form class="form" method="post" action="addHouse.html" align=center>
 	<p>Address:<br><input type="text" name="address" placeholder="Address" required/></p>
  	<p>Number of House:<br><input type="text" name="house" placeholder="Number of house" required/></p>
		<p class="submit"><input type="submit" value="Add House"></p>  <!-- Przycisk -->
	</form>
	