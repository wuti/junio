package com.cqtd.common.ui.taglib.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqtd.common.base.action.FooGenericAction;
import com.cqtd.common.base.utils.FooUtils;
import com.cqtd.common.ui.taglib.model.JsTree;
import com.cqtd.common.ui.taglib.model.JsTreeAttr;
import com.cqtd.common.ui.taglib.model.JsTreeData;
import com.cqtd.common.ui.taglib.model.JsTreeEntity;
import com.cqtd.common.ui.taglib.service.JsTreeService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Search;

public class JsTreeAction extends FooGenericAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	JsTreeService jsTreeService;

	public void getMenuTree() throws IllegalAccessException,
			InvocationTargetException {

		List<JsTreeEntity> tmpList = Lists.newArrayList();
		List<JsTree> menuTreeList = Lists.newArrayList();
		String nodeId = Strings.nullToEmpty(request.getParameter("nodeId"));
		if (nodeId.equals("0")) {
			tmpList = jsTreeService.findAll();
			// tmpList = jsTreeService.search(new Search().addFilterEqual(
			// "nodeId", "1"));
			JsTree jsTree = new JsTree();
			JsTreeData data = new JsTreeData();
			JsTreeAttr attr = new JsTreeAttr();
			for (JsTreeEntity jsTreeEntity : tmpList) {
				BeanUtils.copyProperties(jsTree, jsTreeEntity);
				BeanUtils.copyProperties(data, jsTreeEntity);
				BeanUtils.copyProperties(attr, jsTreeEntity);
				jsTree.setData(data);
				jsTree.setAttr(attr);
				menuTreeList.add(jsTree);
				jsTree = new JsTree();
				data = new JsTreeData();
				attr = new JsTreeAttr();
			}
		} else if (nodeId.equals("1")) {
			tmpList = jsTreeService.search(new Search().addFilterLike("nodeId",
					"1%"));
		}
		try {
			FooUtils.printJsonObject(response, menuTreeList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
