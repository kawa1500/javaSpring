<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <form class="form" method="post" action="addFlat.html" align=center>
 	<p>Renter:<br><input type="text" name="renter" placeholder="Name of renter" required/></p>
  	<p>Number of Flat:<br><input type="text" name="flat" placeholder="Number of flat" required/></p>
	<p class="submit"><input type="submit" value="Add Flat"></p>  <!-- Przycisk -->
	</form>
	
	
 