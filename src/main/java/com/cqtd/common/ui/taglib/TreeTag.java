package com.cqtd.common.ui.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tree taglib
 * 
 * @author Steve
 */
public class TreeTag extends TagSupport {

	private String treeType;

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("your type is" + treeType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getTreeType() {
		return treeType;
	}

}
