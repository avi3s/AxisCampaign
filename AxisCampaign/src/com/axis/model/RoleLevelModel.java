package com.axis.model;

import java.util.Date;

public class RoleLevelModel {
	
	private int rowLevelId;
	private int parentRoleLevelId;
	private String levelName;
	private Date createTimeStamp;
	private Date lastUpdateTimeStamp;
	private int parentRoleLevelIdExtra;


	private Integer createdBy;
	private Integer updatedBy;

	private String parentRoleLevelName;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + parentRoleLevelId;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleLevelModel other = (RoleLevelModel) obj;
		if (parentRoleLevelId != other.parentRoleLevelId)
			return false;
		return true;
	}
	
	public int getRowLevelId() {
		return rowLevelId;
	}
	public void setRowLevelId(int rowLevelId) {
		this.rowLevelId = rowLevelId;
	}
	public int getParentRoleLevelId() {
		return parentRoleLevelId;
	}
	public void setParentRoleLevelId(int parentRoleLevelId) {
		this.parentRoleLevelId = parentRoleLevelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getParentRoleLevelName() {
		return parentRoleLevelName;
	}
	public void setParentRoleLevelName(String parentRoleLevelName) {
		this.parentRoleLevelName = parentRoleLevelName;
	}
	public Date getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}
	public void setLastUpdateTimeStamp(Date lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getParentRoleLevelIdExtra() {
		return parentRoleLevelIdExtra;
	}
	public void setParentRoleLevelIdExtra(int parentRoleLevelIdExtra) {
		this.parentRoleLevelIdExtra = parentRoleLevelIdExtra;
	}
	
}
