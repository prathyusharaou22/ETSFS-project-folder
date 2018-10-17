package com.sam.helper.bean;

import java.util.ArrayList;

public class EncryptedDataBean {
	
	
	private String name;
	private String encEmailId;
	private String department;
	private String encPassword;
	private String address;
	private String encSsn;
	private String key1;
	private String key2;
	
	private ArrayList<EncryptedDataBean> encarl;
	
	
	
	public ArrayList<EncryptedDataBean> getEncarl() {
		return encarl;
	}
	public void setArl(ArrayList<EncryptedDataBean> encarl) {
		this.encarl = encarl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEncEmailId() {
		return encEmailId;
	}
	public void setEncEmailId(String encEmailId) {
		this.encEmailId = encEmailId;
	}
	public String getEncPassword() {
		return encPassword;
	}
	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}
	public String getEncSsn() {
		return encSsn;
	}
	public void setEncSsn(String encSsn) {
		this.encSsn = encSsn;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}

}
