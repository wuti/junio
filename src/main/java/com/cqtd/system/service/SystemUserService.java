package com.cqtd.system.service;

import com.cqtd.common.base.service.FooGenericService;
import com.cqtd.system.model.SystemUser;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-7 下午10:19:36 
 */
public interface SystemUserService extends FooGenericService<SystemUser> {
	
	public long getMaxUserNo();
}
