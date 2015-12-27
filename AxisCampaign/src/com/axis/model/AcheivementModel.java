package com.axis.model;

import java.util.Arrays;

import com.axis.entity.RoleCampaignEntity;
import com.axis.enumeration.Status;

public class AcheivementModel {
	private int acheivementId;
	
	public int getAcheivementId() {
		return acheivementId;
	}
	public void setAcheivementId(int acheivementId) {
		this.acheivementId = acheivementId;
	}
	private String roleName;
	private String fieldType;
	private String fieldName;
	private String fieldValue;
	private String duplicates;
	private String blankspace;
	public String getBlankspace() {
		return blankspace;
	}
	public void setBlankspace(String blankspace) {
		this.blankspace = blankspace;
	}
	public String getDuplicates() {
		return duplicates;
	}
	public void setDuplicates(String duplicates) {
		this.duplicates = duplicates;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	private String updatefieldName;
	public String getUpdatefieldName() {
		return updatefieldName;
	}
	public void setUpdatefieldName(String updatefieldName) {
		this.updatefieldName = updatefieldName;
	}
	private String campaignName;
	private Long campaignId;
	private String type;
	private String message;
	private RoleCampaignEntity roleCampaignEntity;
	public RoleCampaignEntity getRoleCampaignEntity() {
		return roleCampaignEntity;
	}
	public void setRoleCampaignEntity(RoleCampaignEntity roleCampaignEntity) {
		this.roleCampaignEntity = roleCampaignEntity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public String getMessage2() {
		return message2;
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	private String message1;
	private String message2;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	private Long roleId;
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	private Status status;
	private String fieldName_array[];
	public String[] getFieldName_array() {
		return fieldName_array;
	}
	public void setFieldName_array(String[] fieldName_array) {
		this.fieldName_array = fieldName_array;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	@Override
	public String toString() {
		return "AcheivementModel [acheivementId=" + acheivementId
				+ ", roleName=" + roleName + ", fieldType=" + fieldType
				+ ", fieldName=" + fieldName + ", campaignName=" + campaignName
				+ ", campaignId=" + campaignId + ", type=" + type
				+ ", message=" + message + ", message1=" + message1
				+ ", message2=" + message2 + ", roleId=" + roleId + ", status="
				+ status + ", fieldName_array="
				+ Arrays.toString(fieldName_array) + "]";
	}
	
	
	

}
