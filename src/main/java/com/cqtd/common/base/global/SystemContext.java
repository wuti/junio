package com.cqtd.common.base.global;

import java.util.Map;

/** 
 * Description: 系统上下文 
 * Copyright:   Copyright (c)2011
 * Company:     CQTD
 * @author:     Dean
 * @version:    1.0
 * Create at:   2011-12-10 下午 21:53:12
 */
public class SystemContext {
	
	//线程变量
	private static ThreadLocal<Integer> pageStart = new ThreadLocal<Integer>(); //分页起始条数
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();  //分页单页条数
	private static ThreadLocal<Map<String,Object>> pageParams 
						= new ThreadLocal<Map<String,Object>>();                //分页查询条件
	
	/**
	 * 获取分页起始条数
	 * @return
	 */
	public static Integer getPageStart() {
		Integer pageStartValue = pageStart.get();
		return pageStartValue == null ? 0 : pageStartValue;
	}
	
	/**
	 * 设置分页起始条数
	 * @param pageStartValue
	 */
	public static void setPageStart(Integer pageStartValue) {
		pageStart.set(pageStartValue);
	}
	
	/**
	 * 清空分页起始条数
	 *
	 */
	public static void removePageStart() {
		pageStart.remove();
	}
	
	/**
	 * 获取分页偏移量
	 * @return
	 */
	public static Integer getPageSize() {
		Integer pageSizeValue = pageSize.get();
		return pageSizeValue == null ? 20 : pageSizeValue;
	}
	/**
	 * 设置分页偏移量
	 * @param pageSizeValue
	 */
	public static void setPageSize(Integer pageSizeValue) {
		pageSize.set(pageSizeValue);
	}

	/**
	 * 清空分页偏移量
	 *
	 */
	public static void removePageSize() {
		pageSize.remove();
	}
	
	/**
	 * 获取查询条件
	 * @return
	 */
	public static Map<String,Object> getPageParams() {
		return pageParams.get();
	}
	
	/**
	 * 设置查询条件
	 * @param pageParamsValue
	 */
	public static void setPageParams(Map<String,Object> pageParamsValue) {
		pageParams.set(pageParamsValue);
	}
	
	/**
	 * 清空查询条件
	 *
	 */
	public static void removePageParams() {
		pageParams.remove();
	}
	
}
