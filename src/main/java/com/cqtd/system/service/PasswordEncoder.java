package com.cqtd.system.service;

/** 
 * Description: 口令加密接口  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-8 下午04:47:23 
 */
public interface PasswordEncoder {
	/**
	 * 对口令进行加密
	 * @param rawPass 未加密的原始口令
	 * @param salt 干扰串
	 * @return 加密后的口令
	 */
	public String encodePassword(String rawPass,String salt);
	
	/**
	 * 验证口令是否一致
	 * @param encPass 之前加密后的口令
	 * @param rawPass 未加密的原始口令
	 * @param salt 干扰串
	 * @return
	 */
	public boolean isPasswordValid(String encPass, String rawPass,String salt);
}
