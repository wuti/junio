
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
	
<!-- Jstl and config start -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<!-- Jstl and config end -->

<!-- Import our tag here. -->
<%@ taglib uri="/WEB-INF/cqtd.tld" prefix="cqtd"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Cqtd Demo Sites.</title>
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/layout/css/layout-default-latest.css" />

<!-- The cupertino ui style.  -->
<link rel="stylesheet" type="text/css" 
href="${app }/js/jquery-1.7.1/plugin/jquery-ui-1.8.16.custom/css/cupertino/jquery-ui-1.8.16.custom.css">
<!-- datatables css -->
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/dataTables-1.8.2/css/demo_page.css" />
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/dataTables-1.8.2/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/dataTables-1.8.2/css/demo_table_jui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${app }/js/jquery-1.7.1/plugin/jquery.jqGrid-4.3.0/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${app }/js/jquery-1.7.1/plugin/jquery-easyui-1.2.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${app }/js/jquery-1.7.1/plugin/jquery-easyui-1.2.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${app }/css/main.css" />

<!-- 加上和easyui的css会冲突
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/JQuery-zTree-v3.0/css/demo.css" />
 -->
<link rel="stylesheet" type="text/css" href="${app }/js/jquery-1.7.1/plugin/JQuery-zTree-v3.0/css/zTreeStyle/zTreeStyle.css" />

<!-- CUSTOMIZE/OVERRIDE THE DEFAULT CSS -->
<style type="text/css">
/* remove padding and scrolling from elements that contain an Accordion OR a content-div */
.ui-layout-center, /* has content-div */ .ui-layout-west,
	/* has Accordion */ .ui-layout-east, /* has content-div ... */
	.ui-layout-east .ui-layout-content { /* content-div has Accordion */
	padding: 0;
	overflow: hidden;
}

.ui-layout-center P.ui-layout-content {
	line-height: 1.4em;
	margin: 0; /* remove top/bottom margins from <P> used as content-div */
}

h3,h4 { /* Headers & Footer in Center & East panes */
	font-size: 1.1em;
	background: #EEF;
	border: 1px solid #BBB;
	border-width: 0 0 1px;
	padding: 7px 10px;
	margin: 0;
}
</style>

<!-- REQUIRED scripts for layout widget -->

<!-- Jquery main version,this one must at the top. -->
<script type="text/javascript" src="${app }/js/jquery-1.7.1/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" 
src="${app }/js/jquery-1.7.1/plugin/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js"></script>

<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/layout/jquery.layout-latest.js"></script>

<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/layout/debug.js"></script>

<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/jstree_pre1-1.0_fix_1/jquery.jstree.js"></script>

<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/dataTables-1.8.2/jquery.dataTables.js"></script>
<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/jquery.jqGrid-4.3.0/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/jquery.jqGrid-4.3.0/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${app }/js/jquery-1.7.1/plugin/jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="UTF-8" src="${app }/js/jquery-1.7.1/plugin/JQuery-zTree-v3.0/js/jquery.ztree.core-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${app }/js/jquery-1.7.1/plugin/JQuery-zTree-v3.0/js/jquery.ztree.excheck-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${app }/js/jquery-1.7.1/plugin/JQuery-zTree-v3.0/js/jquery.ztree.exedit-3.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${app }/js/syUtil.js"></script>
</head>