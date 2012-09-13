package com.cqtd.common.ui.taglib.dao;

import org.springframework.stereotype.Repository;

import com.cqtd.common.base.dao.FooGenericDaoImpl;
import com.cqtd.common.ui.taglib.model.ZTree;

@Repository
public class ZTreeDaoImpl extends FooGenericDaoImpl<ZTree, String> implements
		ZTreeDao {

}
