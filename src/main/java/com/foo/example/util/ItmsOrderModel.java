package com.foo.example.util;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITMS_ORDER_INFO")
public class ItmsOrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@javax.persistence.SequenceGenerator(name = "SEQ_GEN", sequenceName = "ITMS_SEQ_ORDER", allocationSize = 1)
	private int order_id;
	private String received_order_id;
	private String received_order_lhs;
	private Date order_date;
	private String order_service_type;
	private String order_remark;
	private Date order_deadline;
	private String customer_name_new;
	private String customer_name_old;
	private String order_customer_kind;
	private int system_domain;
	private int corporation_domain;
	private String ad_no;
	private String pppoe_account;
	private String contact_person_new;
	private String contact_person_old;
	private Date received_date;
	private String order_status;
	private Date order_deal_date;
	private String order_done_flag;
	private Date order_done_date;
	private String dummy_flag;
	private String remark;
	private String dev_sno_oui;
	private String pppoe_password;
	private String customer_addr_new;
	private String customer_addr_old;
	private String flow_type;
	private String user_sn_no;
	private String user_sn_key;
	private String unique_user_id;
	private String oper_remark;
	private String remark1;
	private String remark2;
	private String remark3;
	private int lifetime;
	private String mac;
	private String product_type;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getReceived_order_id() {
		return received_order_id;
	}

	public void setReceived_order_id(String received_order_id) {
		this.received_order_id = received_order_id;
	}

	public String getReceived_order_lhs() {
		return received_order_lhs;
	}

	public void setReceived_order_lhs(String received_order_lhs) {
		this.received_order_lhs = received_order_lhs;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getOrder_service_type() {
		return order_service_type;
	}

	public void setOrder_service_type(String order_service_type) {
		this.order_service_type = order_service_type;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public Date getOrder_deadline() {
		return order_deadline;
	}

	public void setOrder_deadline(Date order_deadline) {
		this.order_deadline = order_deadline;
	}

	public String getCustomer_name_new() {
		return customer_name_new;
	}

	public void setCustomer_name_new(String customer_name_new) {
		this.customer_name_new = customer_name_new;
	}

	public String getCustomer_name_old() {
		return customer_name_old;
	}

	public void setCustomer_name_old(String customer_name_old) {
		this.customer_name_old = customer_name_old;
	}

	public String getOrder_customer_kind() {
		return order_customer_kind;
	}

	public void setOrder_customer_kind(String order_customer_kind) {
		this.order_customer_kind = order_customer_kind;
	}

	public int getSystem_domain() {
		return system_domain;
	}

	public void setSystem_domain(int system_domain) {
		this.system_domain = system_domain;
	}

	public int getCorporation_domain() {
		return corporation_domain;
	}

	public void setCorporation_domain(int corporation_domain) {
		this.corporation_domain = corporation_domain;
	}

	public String getAd_no() {
		return ad_no;
	}

	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}

	public String getPppoe_account() {
		return pppoe_account;
	}

	public void setPppoe_account(String pppoe_account) {
		this.pppoe_account = pppoe_account;
	}

	public String getContact_person_new() {
		return contact_person_new;
	}

	public void setContact_person_new(String contact_person_new) {
		this.contact_person_new = contact_person_new;
	}

	public String getContact_person_old() {
		return contact_person_old;
	}

	public void setContact_person_old(String contact_person_old) {
		this.contact_person_old = contact_person_old;
	}

	public Date getReceived_date() {
		return received_date;
	}

	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public Date getOrder_deal_date() {
		return order_deal_date;
	}

	public void setOrder_deal_date(Date order_deal_date) {
		this.order_deal_date = order_deal_date;
	}

	public String getOrder_done_flag() {
		return order_done_flag;
	}

	public void setOrder_done_flag(String order_done_flag) {
		this.order_done_flag = order_done_flag;
	}

	public Date getOrder_done_date() {
		return order_done_date;
	}

	public void setOrder_done_date(Date order_done_date) {
		this.order_done_date = order_done_date;
	}

	public String getDummy_flag() {
		return dummy_flag;
	}

	public void setDummy_flag(String dummy_flag) {
		this.dummy_flag = dummy_flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDev_sno_oui() {
		return dev_sno_oui;
	}

	public void setDev_sno_oui(String dev_sno_oui) {
		this.dev_sno_oui = dev_sno_oui;
	}

	public String getPppoe_password() {
		return pppoe_password;
	}

	public void setPppoe_password(String pppoe_password) {
		this.pppoe_password = pppoe_password;
	}

	public String getCustomer_addr_new() {
		return customer_addr_new;
	}

	public void setCustomer_addr_new(String customer_addr_new) {
		this.customer_addr_new = customer_addr_new;
	}

	public String getCustomer_addr_old() {
		return customer_addr_old;
	}

	public void setCustomer_addr_old(String customer_addr_old) {
		this.customer_addr_old = customer_addr_old;
	}

	public String getFlow_type() {
		return flow_type;
	}

	public void setFlow_type(String flow_type) {
		this.flow_type = flow_type;
	}

	public String getUser_sn_no() {
		return user_sn_no;
	}

	public void setUser_sn_no(String user_sn_no) {
		this.user_sn_no = user_sn_no;
	}

	public String getUser_sn_key() {
		return user_sn_key;
	}

	public void setUser_sn_key(String user_sn_key) {
		this.user_sn_key = user_sn_key;
	}

	public String getUnique_user_id() {
		return unique_user_id;
	}

	public void setUnique_user_id(String unique_user_id) {
		this.unique_user_id = unique_user_id;
	}

	public String getOper_remark() {
		return oper_remark;
	}

	public void setOper_remark(String oper_remark) {
		this.oper_remark = oper_remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
}
