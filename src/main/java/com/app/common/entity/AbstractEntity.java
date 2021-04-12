package com.app.common.entity;

import com.app.common.util.Util;

import java.util.Date;

/**
 * 所有表的公共字段，用于其他实体类继承
 */
public abstract class AbstractEntity {
	
	/** 序列号 */
	protected Long id;

	/** 创建者*/
	private String createdBy;

	/** 创建时间 */
	private Date createdDt = new Date();

	private String createdDtView;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
		this.createdDtView = Util.formatDate(createdDt);
	}

	public String getCreatedDtView() {
		return createdDtView;
	}
}
