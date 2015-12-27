package com.axis.model;

import com.axis.entity.AchievementFieldEntity;

public class AcheivementFieldValueModel {
	private Integer acheivementFieldValueId;
	private AchievementFieldEntity achievementFieldEntity;
	public AchievementFieldEntity getAchievementFieldEntity() {
		return achievementFieldEntity;
	}
	public void setAchievementFieldEntity(
			AchievementFieldEntity achievementFieldEntity) {
		this.achievementFieldEntity = achievementFieldEntity;
	}
	public Integer getAcheivementFieldValueId() {
		return acheivementFieldValueId;
	}
	public void setAcheivementFieldValueId(Integer acheivementFieldValueId) {
		this.acheivementFieldValueId = acheivementFieldValueId;
	}
	private Long roleId;
	private String roleName;
	private String fieldValue;
	private String fieldName;
	private String fieldLevel;
	public String getFieldLevel() {
		return fieldLevel;
	}
	public void setFieldLevel(String fieldLevel) {
		this.fieldLevel = fieldLevel;
	}
	private String campaignName;
	private Long campaignId;
	private String fieldValue_array[];
	private String duplicates;
	public String getDuplicates() {
		return duplicates;
	}
	public void setDuplicates(String duplicates) {
		this.duplicates = duplicates;
	}
	public String getBlankspace() {
		return blankspace;
	}
	public void setBlankspace(String blankspace) {
		this.blankspace = blankspace;
	}
	private String blankspace;
	private Integer acheivement_id[];
public Integer[] getAcheivement_id() {
		return acheivement_id;
	}
	public void setAcheivement_id(Integer[] acheivement_id) {
		this.acheivement_id = acheivement_id;
	}
public String[] getFieldValue_array() {
		return fieldValue_array;
	}
	public void setFieldValue_array(String[] fieldValue_array) {
		this.fieldValue_array = fieldValue_array;
	}
	public Integer getAcheivementId() {
		return acheivementId;
	}
	public void setAcheivementId(Integer acheivementId) {
		this.acheivementId = acheivementId;
	}
	//	Integer acheivement_id[];
	private Integer acheivementId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	
}
