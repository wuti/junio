<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	var setting = {
		treeId : "privilegeTree",
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : false
		},
		edit : {
			enable : true
		},
		async : {
			enable : true,
			url : "privilegeTree.action",
			autoParam : [ "nodeId" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			}
		},
		callback : {
			beforeDrag : beforeDrag,
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
			onRemove : onRemove,
			onRename : onRename
		}
	};

	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		$('input#myNodeIdForRemove').val(treeNode.nodeId);
		//className = (className === "dark" ? "" : "dark");
		//showLog("[ " + getTime() + " beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; "
			//	+ treeNode.name);
		//var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
		//zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗?下面的所有子节点都会被删除");
	}
	function onRemove(e, treeId, treeNode) {
		console.log('aaa');
		$('#privilegeFormForRemove').form('submit',{
			url:'privilege/removeItem.action',
			onSubmit:function(){return $(this).form('validate');},
			success:function(){
				$.fn.zTree.init($("#privilegeTree"), setting);
			}
		});
	}
	function resetTree() {
		$.fn.zTree.init($("#privilegeTree"), setting);
	}
	function beforeRename(treeId, treeNode, newName) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime() + " beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.name);
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode) {
		showLog("[ " + getTime() + " onRename ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.name);
	}
	function showLog(str) {
		if (!log)
			log = $("#log");
		log.append("<li class='"+className+"'>" + str + "</li>");
		if (log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
				.getSeconds(), ms = now.getMilliseconds();
		return (h + ":" + m + ":" + s + " " + ms);
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if ($("#addBtn_" + treeNode.id).length > 0)
			return;
		var addStr = "<button type='button' class='icon_add'  id='addBtn_"
				+ treeNode.id
				+ "' title='添加子节点' onfocus='this.blur();'></button>";
		sObj.append(addStr);
		var btn = $("#addBtn_" + treeNode.id);
		if (btn)
			btn.bind("click", function() {
				$('input#myParentNodeId').val(treeNode.parentNodeId);
				$('input#myNodeId').val(treeNode.nodeId);
				$('#privilegeDialog').dialog('setTitle', '新增节点').dialog('open');
				//var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
				//zTree.addNodes(treeNode, {
					//id : (100 + newCount),
					//pId : treeNode.id,
					//name : "new node" + (newCount++)
				//});
			});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.id).unbind().remove();
	};
	
	saveItem_privilege = function(){
		$('#privilegeForm').form('submit',{
			url:'privilege/saveItem.action',
			onSubmit:function(){return $(this).form('validate');},
			success:function(){
				$('#privilegeDialog').dialog('close');
				$.fn.zTree.init($("#privilegeTree"), setting);
			}
		});
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#privilegeTree"), setting);
	});
</script>

<form id="privilegeFormForRemove" method="post">
	<input type="hidden" id="myNodeIdForRemove" name="myNodeIdForRemove" value="" >
</form>

<!-- EasyUi dialog for Add operation -->
<div id="privilegeDialog" class="easyui-dialog"
	style="width: 550px; height: 220px;" closed="true" modal="true"
	buttons="#dlg-buttons">
	<form id="privilegeForm" method="post">
		<div style="padding: 20px">
			<table cellpadding="0" cellspacing="0" class="form-table">
				<tr>
					<td>节点名称</td>
					<td><input name="myNodeName" style="width: 150px"></input>
					</td>
					<td>节点链接(Action,Jsp,Html均支持)</td>
					<td><input name="myNodePage" style="width: 150px"></input>
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="myParentNodeId" name="myParentNodeId" value="" >
		<input type="hidden" id="myNodeId" name="myNodeId" value="" >
	</form>
	<div id="dlg-buttons" style="text-align: center">
		<a href="#" class="easyui-linkbutton" iconCls="icon-save"
			onclick="saveItem_privilege()">保存</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#privilegeDialog').dialog('close')">取消</a>
	</div>
</div>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="privilegeTree" class="ztree"></ul>
	</div>
</div>
<a href="#" class="easyui-linkbutton" iconCls="icon-save" >保存</a>
<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetTree();">重置</a>