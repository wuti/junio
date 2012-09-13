package com.cqtd.common.ui.taglib.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqtd.common.base.action.FooGenericAction;
import com.cqtd.common.base.utils.FooUtils;
import com.cqtd.common.ui.taglib.model.ZMenuTree;
import com.cqtd.common.ui.taglib.model.ZTree;
import com.cqtd.common.ui.taglib.service.ZTreeService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Search;
import com.opensymphony.xwork2.Action;

public class ZTreeAction extends FooGenericAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	ZTreeService zTreeService;

	public void privilegeTree() throws IllegalAccessException,
			InvocationTargetException, IOException {
		String nodeId = Strings.nullToEmpty(request.getParameter("nodeId"));

		/* 默认加载的菜单值，在正式环境应该修改默认值 */
		List<ZTree> menuTreeList = Lists.newArrayList();

		if (nodeId.equals("")) {
			ZMenuTree zMenuTree = new ZMenuTree();
			zMenuTree.setName("所有模块");
			zMenuTree.setNodeId("0");
			zMenuTree.setOpen("true");
			List<ZTree> childsList = Lists.newArrayList();
			childsList = zTreeService.search(new Search().addFilterEqual(
					"parentNodeId", "0"));
			zMenuTree.setChilds(childsList);
			FooUtils.printJsonObject(response, zMenuTree);
			return;
		} else {
			menuTreeList = zTreeService.search(new Search().addFilterEqual(
					"parentNodeId", nodeId));
			FooUtils.printJsonObject(response, menuTreeList);
			return;
		}
	}

	/**
	 * 默认显示系统设置菜单。方便测试
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */

	public void getMenuTree() throws IllegalAccessException,
			InvocationTargetException, IOException {

		String nodeId = Strings.nullToEmpty(request.getParameter("nodeId"));

		/* 默认加载的菜单值，在正式环境应该修改默认值 */
		List<ZTree> menuTreeList = Lists.newArrayList();

		if (nodeId.equals("")) {
			menuTreeList = zTreeService.search(new Search().addFilterEqual(
					"nodeId", "1"));
			ZMenuTree zMenuTree = new ZMenuTree();
			BeanUtils.copyProperties(zMenuTree, menuTreeList.get(0));
			List<ZTree> childsList = Lists.newArrayList();
			childsList = zTreeService.search(new Search().addFilterEqual(
					"parentNodeId", "1"));
			zMenuTree.setChilds(childsList);
			FooUtils.printJsonObject(response, zMenuTree);
			return;
		} else {
			menuTreeList = zTreeService.search(new Search().addFilterEqual(
					"parentNodeId", nodeId));
			FooUtils.printJsonObject(response, menuTreeList);
			return;
		}
	}

	public String privilege() {
		return Action.SUCCESS;
	}

	public void getMenuItem() throws IllegalAccessException,
			InvocationTargetException, IOException {

		List<ZTree> menuTreeList = Lists.newArrayList();
		menuTreeList = zTreeService.search(new Search().addFilterEqual(
				"nodeLevel", "1"));
		FooUtils.printJsonObject(response, menuTreeList);
	}

	public void renderWestPanel() throws IOException {
		List<ZTree> menuTreeList = Lists.newArrayList();
		menuTreeList = zTreeService.search(new Search().addFilterEqual(
				"nodeLevel", "1"));
		String htmlForWestPanel = "";
		for (ZTree zTree : menuTreeList) {
			if (zTree.getName().equals("系统设置")) {
				htmlForWestPanel += "<div id='systemConfig' title = '"
						+ zTree.getName() + "' selected='true' ></div>";
			} else {
				htmlForWestPanel += "<div title = '" + zTree.getName()
						+ ("' ></div>");
			}
		}
		FooUtils.printJsonObject(response, "htmlForWestPanel", htmlForWestPanel);
	}

	public void initSystemMenuTree() throws IOException {
		if (zTreeService.count(new Search().addFilterEqual("nodeId", "1")) > 0) {
			FooUtils.printJsonSuccessMsg(response);
		} else {
			ZTree systemConfig = new ZTree();
			systemConfig.setName("系统设置");
			systemConfig.setNodeId("1");
			systemConfig.setNodeLevel(1);
			ZTree menuConfig = new ZTree();
			menuConfig.setName("菜单管理");
			menuConfig.setNodeId("101");
			menuConfig.setNodeLevel(2);
			ZTree otherModual = new ZTree();
			otherModual.setName("其他模块");
			otherModual.setNodeId("2");
			otherModual.setNodeLevel(1);
			zTreeService.save(systemConfig);
			zTreeService.save(menuConfig);
			zTreeService.save(otherModual);
		}
	}

	public void saveItem() throws IOException {
		String nodePage = Strings.nullToEmpty(request
				.getParameter("myNodePage"));
		String nodeName = Strings.nullToEmpty(request
				.getParameter("myNodeName"));
		String nodeId = Strings.nullToEmpty(request.getParameter("myNodeId"));

		// Fetch first node.
		List<ZTree> myList = zTreeService.search(new Search().addFilterEqual(
				"parentNodeId", nodeId).addSortDesc("nodeId"));

		ZTree newNode = new ZTree();
		// New node is always the child.
		newNode.setIsParent("false");
		newNode.setName(nodeName);
		newNode.setNodeId(getMaxTreeNodeId(myList, nodeId));
		newNode.setNodeLevel(getMyNodeLevel(newNode.getNodeId()));
		newNode.setOpen("false");
		newNode.setPage(nodePage);
		newNode.setParentNodeId(nodeId);
		zTreeService.save(newNode);

		// When insert child, update its parent.
		ZTree parentZTree = zTreeService.search(
				new Search().addFilterEqual("nodeId", nodeId)).get(0);
		parentZTree.setIsParent("true");
		zTreeService.save(parentZTree);

		FooUtils.printJsonSuccessMsg(response);
	}

	public void editItem() throws IOException {

		String nodeId = Strings.nullToEmpty(request
				.getParameter("myNodeIdForEdit"));
		ZTree ztree = zTreeService.search(
				new Search().addFilterEqual("nodeId", nodeId)).get(0);
		ztree.setName(request.getParameter("myNodeNameForEdit"));
		ztree.setPage(request.getParameter("myNodePageForEdit"));
		zTreeService.save(ztree);
		FooUtils.printJsonSuccessMsg(response);
	}

	public void removeItem() throws IOException {
		String nodeId = Strings.nullToEmpty(request
				.getParameter("myNodeIdForRemove"));
		String parentNodeId = Strings.nullToEmpty(request
				.getParameter("myParentNodeIdForRemove"));
		// If there are no childs ,set isParent to false.

		List<ZTree> myRemoveList = zTreeService.search(new Search()
				.addFilterLike("nodeId", nodeId + "%"));
		zTreeService.remove(myRemoveList);
		FooUtils.printJsonSuccessMsg(response);
	}

	public String west() {
		return Action.SUCCESS;
	}

	private String getMaxTreeNodeId(List<ZTree> list, String parentNodeId) {
		// 如果是第一级节点，那么情况比较特殊，单独处理
		// if (Integer.valueOf(parentNodeId) == 0) {
		// ZTree ztree = zTreeService.search(
		// new Search().addFilterEqual("parentNodeId", "0")
		// .addSortDesc("nodeId")).get(0);
		// return String.valueOf(Integer.valueOf(ztree.getNodeId()) + 1);
		// }

		if (list == null || list.size() < 1) {
			return parentNodeId + "01";
		} else {
			String currentMaxNodeId = list.get(0).getNodeId();
			int size = currentMaxNodeId.length();
			int newMax = Integer.valueOf(list.get(0).getNodeId()
					.substring(size - 1, size)) + 1;
			if (newMax < 2 || newMax > 99) {
				// throw new RuntimeException();
			}
			String myNodeId = currentMaxNodeId.substring(0, size - 2)
					+ (newMax < 10 ? "0" + String.valueOf(newMax) : String
							.valueOf(newMax));
			logger.info("Last nodeId is " + myNodeId);
			return myNodeId;
		}
	}

	public Integer getMyNodeLevel(String nodeId) {
		return ((nodeId.length() - 1) / 2) + 1;
	}
}
