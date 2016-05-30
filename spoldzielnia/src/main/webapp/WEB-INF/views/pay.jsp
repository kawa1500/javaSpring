<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ include file="/WEB-INF/views/recaptcha_options.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
</head>
<body>
<form:form method="post" action="">
    <div id="captcha_paragraph">
		    <%
		        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LcW3OASAAAAAKEJTHMmp_bo5kny4lZXeDtgcMqC", 
		        					"6LcW3OASAAAAAKVX2duVsSy2uMMHL105-jPDrHMD", false);
		        out.print(c.createRecaptchaHtml(null, null));
		    %>			    
	</div> 	
    <input class="myButton" type="submit" value="<spring:message code="bills.pay"/>"/>
</form:form>
					
</body>
</html>