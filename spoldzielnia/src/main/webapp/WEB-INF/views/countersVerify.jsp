<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
</head>
<body>
<form:form method="post" action="" commandName="counter">
    <table class="tabelka">
    <tr>
        <td><form:hidden path="idCounter"/>
    </tr>
    <tr>
        <td><form:label path="user"><spring:message code="flat.info"/></form:label></td>
        <td>
			<form:select path="user" multiple="false">
				<c:forEach var="user" items="${userList}" >
        			<option value="${user.idUser}" label="${user.flat.building.buildingCity} ${user.flat.building.buildingStreet} ${user.flat.building.buildingNumber} ${user.flat.flatNumber}"/>
    			</c:forEach>
			</form:select>
		</td>
        <td><form:errors path="user"/></td>
    </tr>
    <tr>
        <td><form:label path="water"><spring:message code="price.water"/> m3</form:label></td>
        <td><form:input path="water" /></td>
        <td><form:errors path="water"/></td>
    </tr>
    <tr>
        <td><form:label path="hotWater"><spring:message code="price.hotwater"/> m3</form:label></td>
        <td><form:input path="hotWater" /></td>
        <td><form:errors path="hotWater"/></td>
    </tr>
    <tr>
        <td><form:label path="gas"><spring:message code="price.gas"/> m3</form:label></td>
        <td><form:input path="gas" /></td>
        <td><form:errors path="gas"/></td>
    </tr>
    <tr>
        <td><form:label path="current"><spring:message code="price.current"/> kWh</form:label></td>
        <td><form:input path="current" /></td>
        <td><form:errors path="current"/></td>
    </tr>
    <tr>
        <td><form:label path="energy"><spring:message code="price.energy"/> kWh</form:label></td>
        <td><form:input path="energy" /></td>
        <td><form:errors path="energy"/></td>
    </tr>
    <tr>
        <td>
        	<c:if test="${update==true}">
            	<input class="myButton" type="submit" value="<spring:message code="price.submit"/>"/>
        	</c:if>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>