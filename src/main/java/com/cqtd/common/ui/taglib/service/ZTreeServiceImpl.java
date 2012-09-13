package com.cqtd.common.ui.taglib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqtd.common.base.service.FooGenericServiceImpl;
import com.cqtd.common.ui.taglib.dao.ZTreeDao;
import com.cqtd.common.ui.taglib.model.ZTree;

@Service
public class ZTreeServiceImpl extends FooGenericServiceImpl<ZTree> implements
		ZTreeService {
	@Autowired
	public void setFooDao(ZTreeDao zTreeDao) {
		setFooGenericDao(zTreeDao);
	}
}
