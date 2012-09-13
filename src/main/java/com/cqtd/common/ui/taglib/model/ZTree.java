package com.cqtd.common.ui.taglib.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cq_sys_ztree")
public class ZTree {
	@Id
	@javax.persistence.GeneratedValue(generator = "system-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String name;
	private String isParent;
	private String page;
	private String nodeId;
	private String parentNodeId;
	private Integer nodeLevel;
	private String open;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

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

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

}
