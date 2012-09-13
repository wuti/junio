package com.cqtd.common.ui.taglib.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Data model for JsTree
 * 
 * @see <b>http://www.jstree.com</b> for more info.
 * @author Steve
 * 
 */
@Entity
@Table(name = "JsTree")
public class JsTreeEntity {
	@Id
	@javax.persistence.GeneratedValue(generator = "system-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;


	private String /* JsTreeData field */title;
	private String /* JsTreeData field */icon;
	private String /* JsTreeAttr field */nodeId;
	private String /* JsTreeAttr field */href;

	/**
	 * 'state' and 'children' are only used for NON-leaf nodes
	 */
	private String state;

	/**
	 * 当isLeaf是True时state应为：closed，否则为open
	 */
	private String isLeaf;

	/**
	 * the `metadata` property will be saved using the jQuery `data` function on
	 * the `li` node
	 */
	private String metadata;
	/**
	 * any code you are using
	 */
	private String language;

	public void setState(String state) {
		String iState = Strings.nullToEmpty(state);
		// Only open and closed permitted.
		Preconditions.checkArgument(iState.equals("open")
				|| iState.equals("closed"));
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
