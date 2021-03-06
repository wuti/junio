package com.foo.example.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.foo.common.base.action.FooGenericAction;
import com.foo.common.base.utils.FooUtils;
import com.foo.example.model.Foo;
import com.foo.example.service.FooService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class HelloAction extends FooGenericAction implements ModelDriven<Foo>,
		Preparable { // 模型驱动
	private static final long serialVersionUID = 1L;
	@Autowired
	private FooService fooService;
	private Map<String, Object> jsonList;

	private Foo foo = new Foo(); // 使用模型驱动 必须实例化对象 即new Foo();

	public String getWestPanel() {
		return Action.SUCCESS;
	}

	@Override
	public String execute() throws Exception {

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

		System.out.println("enter1");

		// Foo foo = new Foo();
		//
		// foo.setAlarmDefNo("4000");
		// fooService.save(foo);
		//
		// System.out.println(fooService.count(new Search()));
		//
		// foo = new Foo();
		// foo.setAlarmDefNo("4000");
		// fooService.remove(foo);

		Foo foo = new Foo();
//		fooService.save(foo);

		System.out.println("enter2");

		Map<String, Object> myMap = Maps.newHashMap();
		Search search = new Search();
		List<Foo> myList = null;

		if (Strings.nullToEmpty(request.getParameter("bNeedPaging")).equals(
				"false")) {
			myList = fooService.search(search);
			myMap.put("aaData", myList);
			int myCount = fooService.count(search);
			myMap.put("iTotalRecords", myCount);
			myMap.put("iTotalDisplayRecords", myCount);
		} else {
			String iDisplayLength = request.getParameter("iDisplayLength");
			String iDisplayStart = request.getParameter("iDisplayStart");
			search.setFirstResult(Integer.parseInt(iDisplayStart));
			search.setMaxResults(Integer.parseInt(iDisplayLength));
			SearchResult<Foo> searchResult = fooService.searchAndCount(search);
			myList = searchResult.getResult();
			myMap.put("aaData", myList);
			myMap.put("iTotalRecords", searchResult.getTotalCount());
			myMap.put("iTotalDisplayRecords", searchResult.getTotalCount());
		}
		FooUtils.printJsonObjectSerializeNulls(response, myMap);
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
	}

	/**
	 * 增加jqGrid插件分页列表
	 * 
	 * @throws Exception
	 */
	public void jqgridList() throws Exception {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		Search search = new Search();

		search.setFirstResult((page - 1) * rows);
		search.setMaxResults(rows);
		SearchResult<Foo> searchResult = fooService.searchAndCount(search);
		List<Foo> myList = searchResult.getResult();

		Map<String, Object> myMap = Maps.newHashMap();
		myMap.put("records", searchResult.getTotalCount()); // 总条数
		myMap.put("gridModel", myList);
		myMap.put("rows", rows);
		myMap.put("page", page); // 当前页
		myMap.put("totalPage", 13); // 需要计算 总页数

		FooUtils.printJsonObject(response, myMap);
	}

	public void easyuiAdd() throws Exception {
		fooService.save(foo);
		FooUtils.printJsonSuccessMsg(response);
	}

	public void easyuiModify() throws Exception {
		fooService.save(foo);
		FooUtils.printJsonSuccessMsg(response);
	}

	public void easyuiRemove() throws Exception {
		String id = request.getParameter("id");
		if (!Strings.isNullOrEmpty(id)) {
			fooService.removeById(id);
			FooUtils.printJsonSuccessMsg(response);
		}
	}

	public String goToList() {
		return Action.SUCCESS;
	}

	public String goToList2() {
		return Action.SUCCESS;
	}

	public String goToList3() {
		return Action.SUCCESS;
	}

	public Map<String, Object> getJsonList() {
		return jsonList;
	}

	public void setJsonList(Map<String, Object> jsonList) {
		this.jsonList = jsonList;
	}

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public void prepare() throws Exception {
	}

	public Foo getModel() {
		return foo;
	}
}
