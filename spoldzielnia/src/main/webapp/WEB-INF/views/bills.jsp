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
<table style="width: 100%">
<tr>
    <th><spring:message code="bills.cost"/></th>
    <th><spring:message code="price.date"/></th>
</tr>
<c:forEach items="${billsList}" var="bills">
    <tr>
        <td align="center">${bills.cost} </td>
        <td align="center">${bills.modDate} </td>
        <td align="center"><a class="myButton" href="/app/user/billsView?idBill=${bills.idBills}"><spring:message code="bills.details"/></a></td>
        <c:if test="${bills.status==1}">
            	<td align="center"><a class="myButton" href="/app/user/pay?idBill=${bills.idBills}"><spring:message code="bills.pay"/></a></td>
        </c:if>
    </tr>
</c:forEach>
</table>
</body>
</html>