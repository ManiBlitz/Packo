<%-- 
    Document   : _header.jsp
    Created on : Mar 25, 2018, 9:30:44 AM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>

<div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
    <!--left-fixed -navigation-->
    <aside class="sidebar-left">
        <nav class="navbar navbar-inverse">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                </button>
                <h1><a class="navbar-brand" href="${pageContext.request.contextPath}/home"><span class="fa fa-cube"></span> Packo<span class="dashboard_text">Notification Service v0.1</span></a></h1>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="sidebar-menu">

                    <li class="header"><i class="fa fa-building"></i> ${sessionScope.residence.residence_name}</li>

                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/home">
                            <i class="fa fa-home"></i> <span>Home</span>
                        </a>
                    </li>

                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/profile">
                            <i class="fa fa-user"></i>
                            <span>Profile</span>
                            <span class="label label-primary pull-right">soon</span>
                        </a>
                    </li>
                    
                    <c:if test="${sessionScope.loginedAdmin.admin_priority == 1}"> 
                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/parameters">
                            <i class="fa fa-gear"></i>
                            <span>Parameters</span>
                        </a>
                    </li>
                    </c:if>
                    
                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/about">
                            <i class="fa fa-info-circle"></i>
                            <span>About</span>
                        </a>
                    </li>

                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/help">
                            <i class="fa fa-question-circle"></i>
                            <span>Help</span>
                        </a>
                    </li>
                    
                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/contact">
                            <i class="fa fa-phone"></i>
                            <span>Contact Us</span>
                        </a>
                    </li>


                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/logout">
                            <i class="fa fa-sign-out"></i>
                            <span>Logout</span>
                        </a>
                    </li>
                    
                    <li class="header">Administrator Actions</li>
                    
                    <li class="treeview">
                        <a href="${pageContext.request.contextPath}/newpackage">
                            <i class="fa fa-cube"></i>
                            <span>Add new package</span>
                        </a>
                    </li>
                    <c:if test="${sessionScope.loginedAdmin.admin_priority == 1}">
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/newresident">
                                <i class="fa fa-user-plus"></i>
                                <span>Add new resident</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/newadmin">
                                <i class="fa fa-plus-circle"></i>
                                <span>Add new administrator</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </div>
  <!-- /.navbar-collapse -->
        </nav>
    </aside>
</div>
