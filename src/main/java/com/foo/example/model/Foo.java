package com.foo.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @Entity declares the class as an entity (i.e. a persistent POJO class), @Id declares the identifier property of this entity. 
 * 
 * Note: write filed such as private int to private Integer for OGNL
 * 
 */

@Entity
@Table(name = "FOO")
public class Foo {
	// @Id
	// @javax.persistence.GeneratedValue(generator = "system-uuid")
	// @org.hibernate.annotations.GenericGenerator(name = "system-uuid",
	// strategy = "assigned")
	// @Column(name = "ALARM_DEF_NO", nullable = false)
	// private String alarmDefNo;
	//
	// public void setAlarmDefNo(String alarmDefNo) {
	// this.alarmDefNo = alarmDefNo;
	// }
	//
	// public String getAlarmDefNo() {
	// return alarmDefNo;
	// }

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.GeneratedValue(generator = "system-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String parentId;
	private String text;
	private String href;
	private boolean leaf;
	private String cls;
	private Integer yearFlag; // 注意！！！ model里的属性不能为基本类型 如int
								// 而需是对象形式Integer，这样struts2才能用ognl自动转型
	private Integer monthFlag;

	/*
	 * This list refers to itself, it's not a one to many relationship.So create
	 * a sub-class named: AttachTree
	 */
	// private transient List<AttachTree> children;

	public Integer getYearFlag() {
		return yearFlag;
	}

	public void setYearFlag(Integer yearFlag) {
		this.yearFlag = yearFlag;
	}

	public Integer getMonthFlag() {
		return monthFlag;
	}

	public void setMonthFlag(Integer monthFlag) {
		this.monthFlag = monthFlag;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

}
