<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
<%@page session="true"%>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>

  <div class="login"> 
    <h1>
    	<tiles:insertAttribute name="header"/>
    	<tiles:insertAttribute name="headerLogin"/>
    </h1>
    <table align=center	border=1 width=100% >
    	<tr>
    		<td class="glow"><tiles:insertAttribute name="body"/></td>
    		<td class="menu"><tiles:insertAttribute name="menu"/></td>
    	</tr>
    </table>
  </div>
</body>
</html>
