<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.7.1.jsp"%>

<script type="text/javascript">
	$(function() {

		$("#demo2").jstree({
			"json_data" : {
				"ajax" : {
					url : "menuTree.action",
					data : function(n) {
						console.log(n);
						return {
							nodeId : n.attr ? n.attr("nodeId") : 0
						};
					}
				}
			},
			"contextmenu" : {
				"items" : {
					// Some key
					"iCreate" : {
						// The item label
						"label" : "新增",
						// The function to execute upon a click
						"action" : function(obj) {
							this.create(obj);
						},
						// All below are optional 
						"_disabled" : false, // clicking the item won't do a thing
						"_class" : "class", // class is applied to the item LI node
						"separator_before" : false, // Insert a separator before the item
						"separator_after" : true, // Insert a separator after the item
						// false or string - if does not contain `/` - used as classname
						"icon" : false
					}
				/* MORE ENTRIES ... */
				}
			},
			"plugins" : [ "themes", "json_data", "ui", "crrm", "contextmenu" ]
		}).bind("select_node.jstree", function(e, data) {
			console.log(data.rslt.obj.attr('href'));
		}).bind("rename_node.jstree", function(e, data) {
		}).bind("create_node.jstree", function(e, data) {
			console.log(data.rslt.parent.attr('nodeid'));
		});
		;
	});
</script>
<div id="demo2" class="demo"></div>
<div id="demo3" class="demo"></div>
Success And Fuck
<cqtd:tree treeType="fuckHer"></cqtd:tree>
<%@ include file="/jsp/foot-1.7.1.jsp"%>