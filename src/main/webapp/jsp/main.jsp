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

		//Initialize the west panel tree UI here.

	//	$("#demo1").jstree({
	//		"themeroller" : {},
	//		"plugins" : [ "html_data", "themeroller" ]
	//	}).bind("rename_node.jstree", function(e, data) {
	//	}).bind("create_node.jstree", function(e, data) {
	//		//console.log(data.rslt.parent.attr('nodeid'));
	//	});
	//	;

		myLayout = $('body').layout({
			west__size : 250
		// RESIZE Accordion widget when panes resize
		});

		// ACCORDION - in the West pane
		$("#accordion1").accordion({
			fillSpace : true
		});

		// ACCORDION - in the East pane - in a 'content-div'
		$("#accordion2").accordion({
			fillSpace : true,
			active : 1
		});

		// THEME SWITCHER
		// addThemeSwitcher('.ui-layout-north',{ top: '12px', right: '5px' });
		// if a new theme is applied, it could change the height of some content,
		// so call resizeAll to 'correct' any header/footer heights affected
		// NOTE: this is only necessary because we are changing CSS *AFTER LOADING* using themeSwitcher
		setTimeout(myLayout.resizeAll, 1000); /* allow time for browser to re-render with new theme */
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</script>

<div class="ui-layout-north ui-widget-content" style="display: none;">
	North Pane</div>

<div class="ui-layout-south ui-widget-content " style="display: none;">
	South Pane</div>

<div class="ui-layout-center" style="display: none;">
	<h3 class="ui-widget-header">Center Pane</h3>
	<p class="ui-layout-content ui-widget-content">
		<iframe id="mainFrame" name="mainFrame" class="ui-layout-center"
			width="100%" height="100%" frameborder="0" scrolling="auto"
			src="${app }/jsp/welcome.jsp"></iframe>
	</p>
</div>

<div class="ui-layout-west" style="display: none;">
	<div id="accordion1" class="basic">

		<h3>
			<a href="#">Section 1</a>
		</h3>
		<div id="demo1">
			<ul>
				<li id="phtml_1">
					<ul>
						<li id="phtml_2"><a href="goToList.action" target="mainFrame">Child
								node 1</a></li>
						<li id="phtml_3"><a href="goToList2.action"
							target="mainFrame"">Child node 2</a></li>
						<li id="phtml_3"><a href="goToList3.action"
							target="mainFrame"">Child node 3</a></li>
					</ul></li>
			</ul>
		</div>

		<h3>
			<a href="#">Section 2</a>
		</h3>
		<div id="demo2">
			<ul id="treeDemo" class="ztree"></ul>
		</div>

		<h3>
			<a href="#">Section 3</a>
		</h3>
		<div></div>

		<h3>
			<a href="#">Section 4</a>
		</h3>
		<div></div>

	</div>
</div>
<%@ include file="/jsp/foot-1.7.1.jsp"%>