package com.foo.example.util;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITMS_ORDER_SERVICE")
public class ItmsOrderServiceModel {

	@Embeddable
	public class MyPK implements Serializable {
		private static final long serialVersionUID = 1L;
		private int order_id;
		private String args_name;

		public int getOrder_id() {
			return order_id;
		}

		public void setOrder_id(int order_id) {
			this.order_id = order_id;

		}

		public String getArgs_name() {
			return args_name;
		}

		public void setArgs_name(String args_name) {
			this.args_name = args_name;
		}
	}

	@EmbeddedId
	private MyPK id;

	private String service;
	private int service_id;
	private String service_flag;

	private String args_value_new;
	private String args_value_old;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getService_flag() {
		return service_flag;
	}

	public void setService_flag(String service_flag) {
		this.service_flag = service_flag;
	}

	public String getArgs_value_new() {
		return args_value_new;
	}

	public void setArgs_value_new(String args_value_new) {
		this.args_value_new = args_value_new;
	}

	public String getArgs_value_old() {
		return args_value_old;
	}

	public void setArgs_value_old(String args_value_old) {
		this.args_value_old = args_value_old;
	}

	public MyPK getId() {
		return id;
	}

	public void setId(MyPK id) {
		this.id = id;
	}
}
