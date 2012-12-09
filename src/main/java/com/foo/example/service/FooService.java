package com.foo.example.service;

import com.foo.common.base.service.FooGenericService;
import com.foo.example.model.Foo;

public interface FooService extends FooGenericService<Foo> {
	public void doJob();
}
