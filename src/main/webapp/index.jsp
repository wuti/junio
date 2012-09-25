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
		var oTable = myJ('#example').dataTable({
			"bProcessing" : true,
			"bServerSide": true,
			"bDeferRender": true,
			"bFilter": true,
			"bSort": false,
			"bSortClasses": false,
			"oLanguage": {"sUrl": "${app}/js/jquery/plugin/dataTables/dataTables.cn.txt" },
			"sAjaxSource" : "example/getAjaxData.action",
			"aoColumns" : [ {
				"mData" : "id"
			}, {
				"mData" : "yearFlag"
			}, {
				"mData" : "monthFlag"
			} ],
			"fnServerData": function(sSource, aoData, fnCallback, oSettings) {
			    oSettings.jqXHR = myJ.ajax({
			        "dataType": 'json',
			        "type": "POST",
			        "url": sSource,
			        "data": aoData,
			        "success": fnCallback
			    });
			},
			"sPaginationType": "full_numbers",
			"fnServerParams": function ( aoData ) {
			      aoData.push( { "name": "bNeedPaging", "value": "true" } );
			}
		});
});
</script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" border="0" class="display"
		id="example">
		<thead>
			<tr>
				<th >id</th>
				<th >yearFlag</th>
				<th >mothFlag</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
<input id="button"' type="submit" class="smallButton" value="click" />
</html>