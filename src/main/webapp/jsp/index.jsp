<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.7.1.jsp"%>
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "menuTree.action",
			autoParam : [ "id", "name=n" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			},
			dataFilter : filter
		},
		callback : {
			beforeAsync : beforeAsync
		}
	};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function beforeAsync(treeId, treeNode) {
		return treeNode ? treeNode.level < 5 : true;
	}
	$(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</script>
<div class="zTreeDemoBackground left">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<%@ include file="/jsp/foot-1.7.1.jsp"%>