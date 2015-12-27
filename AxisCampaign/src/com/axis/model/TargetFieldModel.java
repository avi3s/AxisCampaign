package com.axis.model;

import java.util.Date;

import com.axis.enumeration.Status;

public class TargetFieldModel extends CommonModel{
	
	private int targetFieldId;
	private int campaignId;
	private int roleId;
	private String roleName;
	private String fieldName;
	private String fieldType;
	private int id_array[];
	private String value_array[];
	private String fieldName_array[];
	private String campaignName;
	private Status status;
	private Date createDate;
	private int length;
	
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getTargetFieldId() {
		return targetFieldId;
	}
	
	public void setTargetFieldId(int targetFieldId) {
		this.targetFieldId = targetFieldId;
	}
	
	public int getCampaignId() {
		return campaignId;
	}
	
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}	
		
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}
	
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public int[] getId_array() {
		return id_array;
	}

	public void setId_array(int[] id_array) {
		this.id_array = id_array;
	}

	public String[] getValue_array() {
		return value_array;
	}

	public void setValue_array(String[] value_array) {
		this.value_array = value_array;
	}

	public String[] getFieldName_array() {
		return fieldName_array;
	}

	public void setFieldName_array(String[] fieldName_array) {
		this.fieldName_array = fieldName_array;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
