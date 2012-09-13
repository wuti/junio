package com.cqtd.common.ui.taglib.dao;

import org.springframework.stereotype.Repository;

import com.cqtd.common.base.dao.FooGenericDaoImpl;
import com.cqtd.common.ui.taglib.model.JsTreeEntity;

@Repository
public class JsTreeDaoImpl extends FooGenericDaoImpl<JsTreeEntity, String> implements
		JsTreeDao {

}
