package com.cqtd.system.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqtd.common.base.action.FooGenericAction;
import com.cqtd.common.base.utils.FooUtils;
import com.cqtd.system.model.SystemUser;
import com.cqtd.system.service.PasswordEncoder;
import com.cqtd.system.service.SystemUserService;
import com.cqtd.system.util.SystemUserConstant;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-8 上午01:25:10 
 */
public class SystemUserAction extends FooGenericAction{
	private SystemUser user = new SystemUser();
	private static final long serialVersionUID = 1L;
	@Autowired
	private SystemUserService systemUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void addUserBaseInfo() throws Exception {
		user.setUserNo(systemUserService.getMaxUserNo() + SystemUserConstant.USER_NO_DIST);
		user.setPassword(passwordEncoder.encodePassword(
				SystemUserConstant.USER_DEFAULT_PWD,user.getUserNo().toString()));
		user.setAddDate(new Date());
		user.setDeleted("0");
		systemUserService.save(user);
		FooUtils.printJsonSuccessMsg(response);
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public SystemUserService getSystemUserService() {
		return systemUserService;
	}

	public void setSystemUserService(SystemUserService systemUserService) {
		this.systemUserService = systemUserService;
	}

}
