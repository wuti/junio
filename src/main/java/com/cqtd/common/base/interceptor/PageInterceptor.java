package com.cqtd.common.base.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cqtd.common.base.global.SystemContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/** 
 * Description: 分页拦截器
 * Copyright:   Copyright (c)2011
 * Company:     CQTD
 * @author:     Dean
 * @version:    1.0
 * Create at:   2011-12-10 下午 21:53:12
 */
public class PageInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5514058717808051225L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageStartStr = request.getParameter("page");//第几页
		String pageSizeStr = request.getParameter("rows");//分页单页条数
		
		//如果是分页查询
		if(pageStartStr != null && pageSizeStr != null){
			@SuppressWarnings("unchecked")
			Map<String,Object> parameterMap = request.getParameterMap();
			int start = Integer.parseInt(pageStartStr);
			int size = Integer.parseInt(pageSizeStr);
			
			//将查询条件以及分页参数放入系统上下文
			SystemContext.setPageStart(start);
			SystemContext.setPageSize(size);
			SystemContext.setPageParams(parameterMap);
			
			try{
				return invocation.invoke();
			}finally{
				//清空系统上下文
				SystemContext.removePageStart();
				SystemContext.removePageSize();
				SystemContext.removePageParams();
			}
		}
		return invocation.invoke();
	}

}
