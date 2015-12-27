package com.axis.model;

import java.util.List;

public class UserTargetModel {

	private List<String> headerList;	
	private List<List<String>> bodycontains;
	private String roleName;
	
	public List<String> getHeaderList() {
		return headerList;
	}
	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList;
	}
	public List<List<String>> getBodycontains() {
		return bodycontains;
	}
	public void setBodycontains(List<List<String>> bodycontains) {
		this.bodycontains = bodycontains;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
