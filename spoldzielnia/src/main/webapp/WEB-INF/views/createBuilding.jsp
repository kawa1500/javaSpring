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
<!-- commandName nazwa obiektu ktory znajduje sie w tym forumalrzu -->
<form:form method="post" action="" commandName="building">
    <table>
    <tr>
        <td><form:hidden path="idBuilding"/>
    </tr>
    <tr>
        <td><form:label path="buildingStreet"><spring:message code="building.buildingStreet"/></form:label></td>
        <td><form:input path="buildingStreet" /></td>
        <td><form:errors path="buildingStreet"/></td>
    </tr>
    
    <tr>
        <td><form:label path="buildingNumber"><spring:message code="building.buildingNumber"/></form:label></td>
        <td><form:input path="buildingNumber" /></td>
        <td><form:errors path="buildingNumber"/></td>
    </tr>
    
    <tr>
        <td><form:label path="buildingPostCode"><spring:message code="building.buildingPostCode"/></form:label></td>
        <td><form:input path="buildingPostCode" /></td>
        <td><form:errors path="buildingPostCode"/></td>
    </tr>
    <tr>
        <td><form:label path="buildingCity"><spring:message code="building.buildingCity"/></form:label></td>
        <td><form:input path="buildingCity" /></td>
        <td><form:errors path="buildingCity"/></td>
    </tr>
     	<td>
			<c:if test="${building.idBuilding<=0}">
            	<input type="submit" value="<spring:message code="building.add"/>"/>
        	</c:if>
        	<c:if test="${building.idBuilding>0}">
            	<input type="submit" value="<spring:message code="building.edit"/>"/>
        	</c:if>
        </td>
   
</table> 
</form:form>
</body>
</html>
 