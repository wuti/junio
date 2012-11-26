package com.foo.example.dao;

import org.springframework.stereotype.Repository;

import com.foo.common.base.dao.FooGenericDaoImpl;
import com.foo.example.model.Foo;

@Repository
public class FooDaoImpl extends FooGenericDaoImpl<Foo, String> implements
		FooDao {

}
