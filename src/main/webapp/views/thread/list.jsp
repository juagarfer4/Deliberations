<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="threads" id="row" requestURI="thread/list.do" pagesize="5" class="displaytag" >


	<spring:message var="titleHeader" code="thread.title"/>
	<display:column title="${titleHeader}" >
		<jstl:out value="${row.title }"></jstl:out>
	</display:column>

	
	<spring:message var="authorHeader" code="thread.author"/>
	<display:column title="${authorHeader}" >
		<jstl:out value="${row.user.name }"></jstl:out>
	</display:column>


	<spring:message var="dateHeader" code="thread.date"/>
	<display:column title="${dateHeader}" property="creationMoment" format="{0,date,dd/MM/yyyy HH:mm}">
		<jstl:out value="${row.creationMoment}"></jstl:out>
	</display:column>

 	<spring:message var="displayHeader" code="thread.display"/>
	<display:column title="${displayHeader}" >
		<a href="thread/display.do?id=${row.id}"> <spring:message code="thread.display"/> </a>
	</display:column>

</display:table>