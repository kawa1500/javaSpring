<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table>
	<tr><td><a href="/app/admin"><spring:message code="menu.admin"/></a></td></tr>
	<tr><td><a href="/app/user/"><spring:message code="menu.user.user"/></a></td></tr>
	<tr><td><a href="/app/admin/createUser"><spring:message code="menu.createUser"/></a></td></tr>
	<tr><td><a href="/app/admin/createBuilding"><spring:message code="menu.createBuilding"/></a></td></tr>
	<tr><td><a href="/app/admin/createFlat"><spring:message code="menu.createFlat"/></a></td></tr>
	<tr><td><a href="/app/admin/manageUsers"><spring:message code="menu.manageUsers"/></a></td></tr>
	<tr><td><a href="/app/admin/createRole"><spring:message code="menu.createRole"/></a></td></tr>
</table>