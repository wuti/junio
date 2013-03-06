<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
	
<!-- Jstl and config start -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<!-- Jstl and config end -->

<!-- Import our tag here. -->
<%@ taglib uri="/WEB-INF/cqtd.tld" prefix="cqtd"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"></meta>
<title>generics</title>
<link href="${app }/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${app }/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="${app }/js/jquery/jquery-1.8.1.js"></script>
<script type="text/javascript" src="${app }/bootstrap/js/bootstrap.js"></script>
</head>