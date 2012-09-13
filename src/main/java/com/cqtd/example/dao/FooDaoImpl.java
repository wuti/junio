package com.cqtd.example.dao;

import org.springframework.stereotype.Repository;

import com.cqtd.common.base.dao.FooGenericDaoImpl;
import com.cqtd.example.model.Foo;

@Repository
public class FooDaoImpl extends FooGenericDaoImpl<Foo, String> implements
		FooDao {

}
