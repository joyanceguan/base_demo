package com.joyance.basedemo.mybatis.persistence;

import java.io.Serializable;
import java.util.Date;

public class Config implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String c_desc;
	private String c_key;
	private String c_value;
	private Integer index_key;
	private int status;
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getC_desc() {
		return c_desc;
	}
	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}
	public String getC_key() {
		return c_key;
	}
	public void setC_key(String c_key) {
		this.c_key = c_key;
	}
	public String getC_value() {
		return c_value;
	}
	public void setC_value(String c_value) {
		this.c_value = c_value;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getIndex_key() {
		return index_key;
	}
	public void setIndex_key(Integer index_key) {
		this.index_key = index_key;
	}
	
}
