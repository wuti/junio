package com.cqtd.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cqtd.common.base.service.FooGenericServiceImpl;
import com.cqtd.example.dao.FooDao;
import com.cqtd.example.model.Foo;

@Service
public class FooServiceImpl extends FooGenericServiceImpl<Foo> implements
		FooService {
	@Autowired
	public void setFooDao(FooDao fooDao) {
		setFooGenericDao(fooDao);
	}

	@Scheduled(fixedDelay = 60000)
	@Override
	public void doJob() {
		System.out.println("fuck");
	}
}
