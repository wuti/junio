package com.cqtd.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cqtd.common.base.service.FooGenericServiceImpl;
import com.cqtd.system.dao.SystemUserDao;
import com.cqtd.system.model.SystemUser;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-7 下午10:19:48 
 */
@Service
public class SystemUserServiceImpl extends FooGenericServiceImpl<SystemUser> implements
					SystemUserService {
	
	@Autowired
	@Qualifier("systemUserDaoImpl")
	private SystemUserDao systemUserDao;
	
	@Autowired
	public void setSystemUserDao(SystemUserDao systemUserDao) {
		setFooGenericDao(systemUserDao);
	}
	
	public long getMaxUserNo(){
		return systemUserDao.getMaxUserNo();
	}
}
