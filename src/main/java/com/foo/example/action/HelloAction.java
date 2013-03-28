package com.foo.example.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.common.base.action.FooGenericAction;
import com.foo.common.base.pojo.FooGenericSearch;
import com.foo.common.base.pojo.FooGenericSearchResult;
import com.foo.common.base.pojo.FooGenericTransactionModel;
import com.foo.common.base.service.FooGenericService;
import com.foo.common.base.utils.FooUtils;
import com.foo.example.model.Foo;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.opensymphony.xwork2.Action;

public class HelloAction extends FooGenericAction { // 模型驱动
	private static final long serialVersionUID = 1L;
	@Autowired
	private FooGenericService fooGenericService;

	@Override
	public String execute() throws Exception {
		System.out.println("test");
		// Foo attachTree1 = new Foo();
		// Foo attachTree2 = new Foo();
		// fooService.save(attachTree1, attachTree2);
		// logger.info("You get last size of" + fooService.findAll().size());
		// System.out.println(fooService.findAll().size());
		// logger.info(fooSpringJdbcService
		// .queryForInt("select count(0) from attachTree where text='123' ")
		// + "");
		return Action.SUCCESS;
	}

	/**
	 * 分页查询1：普通分页查询，不使用拦截器
	 */
	public void getAjaxData() throws IOException {

		System.out.println("return list");

		Map<String, Object> myMap = Maps.newHashMap();

		FooGenericSearch search = new FooGenericSearch();
		search.setQueryHql(" from Foo");
		search.setCountHql(" select count(*) from Foo");
		List<?> myList = null;

		if (Strings.nullToEmpty(request.getParameter("bNeedPaging")).equals(
				"false")) {
			myList = fooGenericService.search(search);
			myMap.put("aaData", myList);
			int myCount = fooGenericService.count(search);
			myMap.put("iTotalRecords", myCount);
			myMap.put("iTotalDisplayRecords", myCount);
		} else {
			String iDisplayLength = request.getParameter("iDisplayLength");
			String iDisplayStart = request.getParameter("iDisplayStart");
			search.setFirstResult(Integer.parseInt(iDisplayStart));
			search.setMaxResults(Integer.parseInt(iDisplayLength));
			FooGenericSearchResult searchResult = fooGenericService
					.searchAndCount(search);
			myList = searchResult.getResult();
			myMap.put("aaData", myList);
			myMap.put("iTotalRecords", searchResult.getTotalCount());
			myMap.put("iTotalDisplayRecords", searchResult.getTotalCount());
		}
		FooUtils.printJsonObjectSerializeNulls(response, myMap);
		// System.out.println("Add test");
		// Foo myFoo = new Foo();
		// Search mySearch = new Search();
		// mySearch.setSearchClass(Foo.class);
		// mySearch.addFilterEqual("id", "2c9e9d86343ae29801343ae2b30c0001");
		// myFoo = fooService.searchUnique(mySearch);
		// myFoo.setText("123456");
		// fooService.save(myFoo);
		// System.out.println("Remove test");
		// Foo myFoo = new Foo();
		// myFoo.setId("2c9e9d86343ae29801343ae2b30c0001");
		// fooService.remove(myFoo);
	}

	/**
	 * 新增
	 */
	public void add() throws IOException {
		String text = request.getParameter("text");
		String href = request.getParameter("href");
		System.out.println(text);
		System.out.println(href);
		FooUtils.printJsonSuccessMsg(response);
		System.out.println("fuck");
	}

	public void testSave() throws IOException {
		fooGenericService.doInTransaction(new FooGenericTransactionModel() {
			@Override
			public void execute() {
				fooGenericService.save(new Foo());
				throw new RuntimeException();
			}
		});
		FooUtils.printJsonSuccessMsg(response);
	}

}
