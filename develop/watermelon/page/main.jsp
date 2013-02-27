<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.7.1.jsp"%>

<script type="text/javascript">
var currentUser = '<s:property value="#session.USER_SESSION.userNo"/>';
</script>

<body id="index_layout" class="easyui-layout">

	<div id="index_div_pageLoading" class="pageloadingdiv">
		<span class="pageloadingspan">页面加载中，请稍候。。。。</span>
	</div>

	<div region="north" style="height: 50px;"></div>

	<div href="privilege/west.action" region="west" iconCls="icon-reload" split="true" title="West" 
		style="width:220px;padding1:1px;overflow:hidden;" border="false"></div>

	<div href="${app }/page/center.jsp" region="center" title="center title" border="false"></div>

	<div region="south" style="height: 50px;" ></div>

<%@ include file="/jsp/foot-1.7.1.jsp"%>