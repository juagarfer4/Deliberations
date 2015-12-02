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


</br>
</br>
</br>


<div class="container">    
        
    <div id="loginbox" class="mainbox col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3"> 

        
        <div class="panel panel-default" >
            <div class="panel-heading">
                <div class="panel-title text-center"><spring:message code="login.login" /></div>
            </div>     

            <div class="panel-body" >

                <form action="user/loginMake.do" id="form" class="form-horizontal"  method="post" modelAttribute="account">
                   
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input code="customer.user" path="username" id="user" type="text" class="form-control"  placeholder="<spring:message code="login.user" />">                                        
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input code="customer.password" path="password" id="password" type="password" class="form-control" placeholder="<spring:message code="login.password" />">
                    </div>                                                                  

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-sm-12 controls">
                            <button type="submit" href="#" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i> <spring:message code="login.login" /></button>                          
                        </div>
                    </div>

                </form>     

            </div>                     
        </div>  
    </div>
</div>
</br>
</br>
</br>

