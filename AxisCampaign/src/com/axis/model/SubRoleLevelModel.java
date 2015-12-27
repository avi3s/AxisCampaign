package com.axis.model;

import java.util.Date;

public class SubRoleLevelModel {
	
	private int subRoleLevelId;
	private int subRoleLevelParentId;
	private String description;
	private int roleLevelId;
	private Date createTimeStamp;
	private Date lastUpdateTimeStamp;
	private Integer createdBy;
	private Integer updatedBy;
	private String uniqueId;
	private String category;
	private String parent;


	
	
	public int getSubRoleLevelId() {
		return subRoleLevelId;
	}
	public void setSubRoleLevelId(int subRoleLevelId) {
		this.subRoleLevelId = subRoleLevelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	public int getSubRoleLevelParentId() {
		return subRoleLevelParentId;
	}
	public void setSubRoleLevelParentId(int subRoleLevelParentId) {
		this.subRoleLevelParentId = subRoleLevelParentId;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "<option value="+subRoleLevelId+">"+description+"</option>";
	}
	
	

}
