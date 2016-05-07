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
<form:form method="post" action="" commandName="flat">
    <table>
    <tr>
        <td><form:hidden path="idFlat"/>
    </tr>
    <tr>
        <td><form:label path="flatNumber"><spring:message code="flat.flatNumber"/></form:label></td>
        <td><form:input path="flatNumber" /></td>
        <td><form:errors path="flatNumber"/></td>
    </tr>
    
    <tr>
        <td><form:label path="flatSurface"><spring:message code="flat.flatSurface"/></form:label></td>
        <td><form:input path="flatSurface" /></td>
        <td><form:errors path="flatSurface"/></td>
    </tr>
    
    <tr>
        <td><form:label path="tenantNumber"><spring:message code="flat.tenantNumber"/></form:label></td>
        <td><form:input path="tenantNumber" /></td>
        <td><form:errors path="tenantNumber"/></td>
    </tr>
      
     	<td>
			<c:if test="${flat.idFlat<=0}">
            	<input type="submit" value="<spring:message code="flat.add"/>"/>
        	</c:if>
        	<c:if test="${flat.idFlat>0}">
            	<input type="submit" value="<spring:message code="flat.edit"/>"/>
        	</c:if>
        </td>
   
</table> 
</form:form>
</body>
</html>
 