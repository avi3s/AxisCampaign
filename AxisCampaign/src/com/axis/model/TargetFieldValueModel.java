package com.axis.model;

import java.util.List;

import com.axis.enumeration.Status;

public class TargetFieldValueModel extends CommonModel{

	private int targetFieldValueId;
	private String fieldName;
	private String fieldValue;
	private Status status;
	private int targetId;
	private int campaignId;
	private int length;
	private String campaignName;
	private int roleId;
	private String roleName;
	private String fieldName_array[];
	private String fieldValue_array[];
	private String campId;
	private int successNo;
	
	private List<Integer> UnmatchedLists;
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getTargetFieldValueId() {
		return targetFieldValueId;
	}
	
	public void setTargetFieldValueId(int targetFieldValueId) {
		this.targetFieldValueId = targetFieldValueId;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldValue() {
		return fieldValue;
	}
	
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getTargetId() {
		return targetId;
	}
	
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	public int getCampaignId() {
		return campaignId;
	}
	
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	public String getCampaignName() {
		return campaignName;
	}
	
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
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
	
	public String[] getFieldName_array() {
		return fieldName_array;
	}
	
	public void setFieldName_array(String[] fieldName_array) {
		this.fieldName_array = fieldName_array;
	}
	
	
	
	public String[] getFieldValue_array() {
		return fieldValue_array;
	}

	public void setFieldValue_array(String[] fieldValue_array) {
		this.fieldValue_array = fieldValue_array;
	}

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public List<Integer> getUnmatchedLists() {
		return UnmatchedLists;
	}

	public void setUnmatchedLists(List<Integer> unmatchedLists) {
		UnmatchedLists = unmatchedLists;
	}

	public int getSuccessNo() {
		return successNo;
	}

	public void setSuccessNo(int successNo) {
		this.successNo = successNo;
	}
	
	
}
