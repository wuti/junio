package com.cqtd.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqtd.common.base.dao.FooGenericDaoImpl;
import com.cqtd.system.model.SystemUser;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-7 下午10:19:11 
 */

@Repository
public class SystemUserDaoImpl extends FooGenericDaoImpl<SystemUser, String> implements
					SystemUserDao {
	@SuppressWarnings("unchecked")
	public long getMaxUserNo(){
		String hql = "select max(userNo) from SystemUser";
		List<Long> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return 0;
	}
}
