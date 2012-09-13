package com.cqtd.common.ui.taglib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqtd.common.base.service.FooGenericServiceImpl;
import com.cqtd.common.ui.taglib.dao.JsTreeDao;
import com.cqtd.common.ui.taglib.model.JsTreeEntity;

@Service
public class JsTreeServiceImpl extends FooGenericServiceImpl<JsTreeEntity>
		implements JsTreeService {
	@Autowired
	public void setFooDao(JsTreeDao jsTreeDao) {
		setFooGenericDao(jsTreeDao);
	}
}
