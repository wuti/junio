package com.cqtd.system.dao;

import com.cqtd.common.base.dao.FooGenericDao;
import com.cqtd.system.model.SystemUser;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-7 下午10:19:00 
 */
public interface SystemUserDao extends FooGenericDao<SystemUser, String> {
	public long getMaxUserNo();
}
