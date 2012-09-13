package com.cqtd.common.ui.taglib.model;


/**
 * Data model for JsTree
 * 
 * @see <b>http://www.jstree.com</b> for more info.
 * @author Steve
 * 
 */
public class JsTreeAttr {

	private String nodeId;
	private String href;

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHref() {
		return href;
	}
}
