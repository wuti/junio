package com.cqtd.system.action;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqtd.common.base.action.FooGenericAction;
import com.cqtd.system.model.SystemUser;
import com.cqtd.system.service.PasswordEncoder;
import com.cqtd.system.service.SystemUserService;

/**
 * Description: Copyright: Copyright (c)2012 Company: CQTD
 * 
 * @author: Dean
 * @version: 1.0 Create at: 2012-1-8 下午04:19:58
 */
public class LoginAction extends FooGenericAction {

	private static final long serialVersionUID = 2361351386293857192L;

	private SystemUser user = new SystemUser();
	private String tip; // 提示
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String login() {
		// if(!Strings.isNullOrEmpty(user.getUserNo().toString()) &&
		// !Strings.isNullOrEmpty(user.getPassword())){
		// //对口令进行加密
		// user.setPassword(passwordEncoder.encodePassword(user.getPassword(),user.getUserNo().toString()));
		// Search search = new Search().addFilterEqual("userNo",
		// user.getUserNo())
		// .addFilterEqual("password", user.getPassword());
		// SystemUser u = systemUserService.searchUnique(search);
		// if(u != null){
		// session.put(GlobalConstant.USER_SESSION_KEY, u);
		// }else{
		// user.setPassword(null);
		// this.setTip("用户名或者密码错误");
		// return INPUT;
		// }
		//
		// }
		return SUCCESS;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}
