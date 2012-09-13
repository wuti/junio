package com.cqtd.common.ui.taglib.model;

import java.util.List;

public class ZMenuTree {
	private String name;
	private String isParent;
	private String page;
	private List<ZTree> childs;
	private String nodeId;
	private Integer nodeLevel;
	private Integer parentNodeId;
	private String open;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setChilds(List<ZTree> childs) {
		this.childs = childs;
	}

	public List<ZTree> getChilds() {
		return childs;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setParentNodeId(Integer parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public Integer getParentNodeId() {
		return parentNodeId;
	}

}
