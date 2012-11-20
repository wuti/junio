package com.cqtd.example.service;

import com.cqtd.common.base.service.FooGenericService;
import com.cqtd.example.model.Foo;

public interface FooService extends FooGenericService<Foo> {
	public void doJob();
}
