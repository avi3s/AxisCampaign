package com.axis.model;

import java.util.Date;

import com.axis.enumeration.Status;

public class RoleModel {

	private int roleId;
	private String role_name;
	private int roleLevelId;
	private Date createTimeStamp;
	private Date lastUpdateTimeStamp;
	private int parentRoleLevelIdExtra;
	private Integer createdBy;
	private Integer updatedBy;
	private String role_level_name;
	private Status status;


	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getRoleLevelId() {
		return roleLevelId;
	}
	public void setRoleLevelId(int roleLevelId) {
		this.roleLevelId = roleLevelId;
	}
	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	public Date getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}
	public void setLastUpdateTimeStamp(Date lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}
	public int getParentRoleLevelIdExtra() {
		return parentRoleLevelIdExtra;
	}
	public void setParentRoleLevelIdExtra(int parentRoleLevelIdExtra) {
		this.parentRoleLevelIdExtra = parentRoleLevelIdExtra;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getRole_level_name() {
		return role_level_name;
	}
	public void setRole_level_name(String role_level_name) {
		this.role_level_name = role_level_name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
		
}
