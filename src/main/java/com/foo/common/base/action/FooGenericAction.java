package com.foo.common.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.foo.common.base.service.FooSpringJdbcService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>
 * Parent action for all your actions.
 * 
 * The following fields are protected:
 * <li>HttpServletRequest request
 * <li>HttpServletResponse response
 * <p>
 * When you want your filed to be used by sub-actions, declare it as "protected"
 * <br>
 * Add your realization when it's necessary.
 * 
 * @author Steve
 * 
 */
public class FooGenericAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;

	@Autowired
	protected FooSpringJdbcService fooSpringJdbcService;
	protected Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
