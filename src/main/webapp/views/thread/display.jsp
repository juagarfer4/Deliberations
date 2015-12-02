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


<display:table name="threads" id="row" requestURI="thread/display.do" pagesize="5" class="displaytag" >

	<spring:message var="dateHeader" code="thread.date"/>
	<display:column title="${dateHeader}" >
		<jstl:out value="${row.creationMoment}"></jstl:out>
	</display:column>

	<spring:message var="titleHeader" code="thread.title"/>
	<display:column title="${titleHeader}" >
		<jstl:out value="${row.title}"></jstl:out>
	</display:column>
	
	<spring:message var="decriptionHeader" code="thread.decription"/>
	<display:column title="${decriptionHeader}" >
		<jstl:out value="${row.decription}"></jstl:out>
	</display:column>
	
	<spring:message var="editHeader" code="thread.edit"/>
	<display:column title="${editHeader}" >
		<a href="thread/edit.do?id=${row.id }"> <spring:message code="thread.edit"/> </a>
	</display:column>



</display:table>


<display:table name="comments" id="row" requestURI="thread/display.do" pagesize="5" class="displaytag" >


	<spring:message var="dateHeader" code="thread.date"/>
	<display:column title="${dateHeader}" >
		<jstl:out value="${row.creationMoment}"></jstl:out>
	</display:column>
	
	<spring:message var="authorHeader" code="thread.author"/>
	<display:column title="${authorHeader}" >
		<jstl:out value="${row.user.name }"></jstl:out>
	</display:column>
	
	<spring:message var="decriptionHeader" code="thread.decription"/>
	<display:column title="${decriptionHeader}" >
		<jstl:out value="${row.decription }"></jstl:out>
	</display:column>





</display:table>


<form:form action="comment/save.do" method="post" modelAttribute="comment">

<form:hidden path="id"/>
<form:hidden path="version"/>	
<form:hidden path="user"/>
<form:hidden path="creationMoment" />
<form:hidden path="thread"/>


<acme:decriptionbox code="thread.comment" path="decription"/>
<acme:submit name="save" code="comment.save"/>





</form:form>