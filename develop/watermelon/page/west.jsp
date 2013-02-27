<script type="text/javascript">
	sy.ns('sy.west');
	$(function() {
		$.ajax({
			type : "POST",
			async : false,
			url : "privilge/renderWestPanel.action",
			success : function(jsonMsg) {
				var htmlForWestPanel = $.parseJSON(jsonMsg).htmlForWestPanel;
				$('div#west_accordion').html(htmlForWestPanel);
				$('div#systemConfig').html(
						'<ul id="westTree" class="ztree"></ul>');
			}
		});

		/* 添加tab方法 */
		sy.west.addTab = function(tabsId, title, href) {
			$('#index_div_pageLoading').show();
			var id = '#' + tabsId;
			if ($(id).tabs('exists', title)) {
				$(id).tabs('select', title);
				$('#index_div_pageLoading').hide();
			} else {
				$(id).tabs('add', {
					title : title,
					href : href,
					closable : true
				});
			}
		};

		/* Init tree */
		var setting = {
			view : {
				selectedMulti : false
			},
			async : {
				enable : true,
				url : "privilge/menuTree.action",
				autoParam : [ "nodeId" ],
				otherParam : {
					"otherParam" : "zTreeAsyncTest"
				}
			},
			callback : {
				onClick : function(event, treeId, treeNode, clickFlag) {
					sy.west.addTab('center_tabs', treeNode.name, treeNode.page);
				}
			}
		};
		$.fn.zTree.init($("#westTree"), setting);
		$('a').bind('click',function(event,data){
		});
	});
</script>
<div id="west_accordion" class="easyui-accordion" fit="true"
	border="false"></div>
