package com.cqtd.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Description:  
 * Copyright:   Copyright (c)2012 
 * Company:     CQTD 
 * @author:     Dean 
 * @version:    1.0 
 * Create at:   2012-1-4 下午10:27:22 
 */

@Entity
@Table(name = "cq_sys_user")
public class SystemUser {
	
	@Id
	@Column(name = "id",length = 32)
	@javax.persistence.GeneratedValue(generator = "system-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	
	@Column(nullable = false)
	private Long userNo;     //用户编号
	
	@Column(length = 100,nullable = false)
	private String username; //用户名称
	
	@Column(length = 100,nullable = false)
	private String password; 
	
	@Column(length = 100,nullable = true)
	private String name;     //姓名
	
	@Column(length = 30,nullable = true)
	private String qq;
	
	@Column(length = 100,nullable = true)
	private String email;
	
	@Column(length = 200,nullable = true)
	private String address;
	
	@Column(length = 30,nullable = true)
	private String telephone;
	
	@Column(length = 30,nullable = true)
	private String mobilephone;
	
	@Column(length = 200,nullable = true)
	private String specialtyStore; //专卖店
	
	@Column(length = 10,nullable = true)
	private String postcode;
	
	private Long dealerUserNo; //所属经销商用户号 （一对多）
	
	private Date addDate;  //录入数据日期
	
	@Column(length = 10,nullable = true)
	private String deleted;//会员状态，0:有效，1:无效
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getSpecialtyStore() {
		return specialtyStore;
	}
	public void setSpecialtyStore(String specialtyStore) {
		this.specialtyStore = specialtyStore;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Long getDealerUserNo() {
		return dealerUserNo;
	}
	public void setDealerUserNo(Long dealerUserNo) {
		this.dealerUserNo = dealerUserNo;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
