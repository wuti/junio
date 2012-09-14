<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- Jstl and config start -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<title>watermelon test page1</title>
<!-- datatables css -->
<link rel="stylesheet" type="text/css" href="${app }/js/jquery/plugin/dataTables/css/demo_page.css" />
<link rel="stylesheet" type="text/css" href="${app }/js/jquery/plugin/dataTables/css/demo_table_jui.css" />
<link rel="stylesheet" type="text/css" href="${app }/js/jquery/plugin/dataTables/css/demo_table.css" />
<script type="text/javascript" language="javascript" src= "${app}/js/jquery/jquery-1.8.1.js" ></script>
<script type="text/javascript" language="javascript" src=" ${app}/js/jquery/plugin/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript">
var myJ=jQuery.noConflict();
myJ(function() {
		alert('ok');
		var oTable = myJ('#example').dataTable({
			"bProcessing" : true,
			"bServerSide": true,
			"sAjaxSource" : "example/getAjaxData.action",
			"aoColumns" : [ {
				"mData" : "id"
			}, {
				"mData" : "parentId"
			} ]
		});
});
</script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" border="0" class="display"
		id="example">
		<thead>
			<tr>
				<th width="20%">MyId</th>
				<th width="25%">ParentId</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>