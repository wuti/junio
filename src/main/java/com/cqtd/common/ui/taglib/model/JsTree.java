package com.cqtd.common.ui.taglib.model;

import javax.persistence.Embedded;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Data model for JsTree
 * 
 * @see <b>http://www.jstree.com</b> for more info.
 * @author Steve
 * 
 */
public class JsTree {

	/**
	 * A JSON object (or false if not used). Default is false. Specifies the
	 * content to load into the container and convert to a tree. You can also
	 * set this to a function - it will be executed in the tree's scope for
	 * every node that needs to be loaded, the function will receive two
	 * arguments - the node being loaded & a function to call with the data once
	 * your processing is done.
	 * 
	 * Set it as @Embedded to divide various fields
	 */
	@Embedded
	private JsTreeData /* Json Object field */data;
	/**
	 * Omit 'attr' if not needed; the 'attr' object gets passed to the jQuery
	 * 'attr' function
	 * 
	 * Set it as @Embedded to divide various fields
	 */
	private JsTreeAttr /* Json Object field */attr;

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
		// String iState = Strings.nullToEmpty(state);
		// Only open and closed permitted.
		// Preconditions.checkArgument(iState.equals("open")
		// || iState.equals("closed"));
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

	public JsTreeData getData() {
		return data;
	}

	public void setData(JsTreeData data) {
		this.data = data;
	}

	public JsTreeAttr getAttr() {
		return attr;
	}

	public void setAttr(JsTreeAttr attr) {
		this.attr = attr;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsLeaf() {
		return isLeaf;
	}
}
