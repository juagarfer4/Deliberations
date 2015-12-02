<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="decription/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="${actionURI}" method="post" modelAttribute="thread">

<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="user"/>	
<form:hidden path="creationMoment"/>
<form:hidden path="comments"/>

<acme:decriptionbox code="thread.title" path="title"/>
<acme:decriptionbox code="thread.decription" path="decription"/>
<acme:submit name="save" code="thread.save"/>





</form:form>