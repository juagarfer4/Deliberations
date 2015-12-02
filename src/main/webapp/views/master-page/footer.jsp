<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <jsp:useBean id="date" class="java.util.Date" /> --%>

<!-- <hr /> -->

<%-- <b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Sample Co., Inc.</b> --%>
<div class="footer">
		<div class="container">
				<div class="col-md-4 contact-left">
					
					<h4><spring:message code="footer.address" /></h4>
					<address>
						  Escuela Técnica Superior de Ingeniería Informática - Universidad de Sevilla<br>
						  Av. Reina Mercedes s/n, 41012 Sevilla<br>
						 
						</address>
				</div>
				<div class="col-md-4 contact-left">
					
					<h4><spring:message code="footer.phone.fax" /></h4>
					<p><spring:message code="footer.phone" />: +(34) 954556817</p>
					
				</div>
				<div class="col-md-4 contact-left">
					
					<h4><spring:message code="footer.email" /></h4>
					<p><spring:message code="footer.email" />: info-eii@listas@us.es</p>
					
				</div>
				<div class="clearfix"></div>
			
			<div class="copyright">
			   <p>© 2015 Chattle . All rights reserved | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a></p>
		
		</div>
		</div>
	</div>