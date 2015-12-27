package com.axis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class UserModel {

	private int userId;
	private int roleId;
	private int subRoleLevelId;
	private Integer userParentId;

	private String roleName;
	private String subRoleLevelname;
	private String parentUserName;

	private String employeeName;
	private String userName;
	private String password;
	private String employeeNumber;
	private String profilePicture;
	private String about;
	private String officeAddress;
	private String primaryTelephoneNumber;
	private String secondaryTelephoneNumber;
	private String emailAddress;

	private Date createTimeStamp;
	private Date lastUpdateTimeStamp;
	private Integer createdBy;
	private Integer updatedBy;

	private List<NotificationModel> notificationModels;
	private List<CampaignFileUserModel> campaignFileUserModels;
	private List<CampaignFileModel> campaignFileModels;
	private Integer receivedNotificationsNumber;
	private Integer campaignFilesUserCount;
	private Integer campaignFilesCount;
	private List<Integer> UnmatchedLists;
	private List<String> error_message_list;
	private List<CampaignModel> campaignModels;
	private List<UserFileUploadModel> userFileUploadModels;
	
	private Integer userUploadedFilesCount;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getPrimaryTelephoneNumber() {
		return primaryTelephoneNumber;
	}

	public void setPrimaryTelephoneNumber(String primaryTelephoneNumber) {
		this.primaryTelephoneNumber = primaryTelephoneNumber;
	}

	public String getSecondaryTelephoneNumber() {
		return secondaryTelephoneNumber;
	}

	public void setSecondaryTelephoneNumber(String secondaryTelephoneNumber) {
		this.secondaryTelephoneNumber = secondaryTelephoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getSubRoleLevelId() {
		return subRoleLevelId;
	}

	public void setSubRoleLevelId(int subRoleLevelId) {
		this.subRoleLevelId = subRoleLevelId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSubRoleLevelname() {
		return subRoleLevelname;
	}

	public void setSubRoleLevelname(String subRoleLevelname) {
		this.subRoleLevelname = subRoleLevelname;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	public List<NotificationModel> getNotificationModels() {
		return notificationModels;
	}

	public void setNotificationModels(List<NotificationModel> notificationModels) {
		this.notificationModels = notificationModels;
	}

	public Integer getReceivedNotificationsNumber() {
		return receivedNotificationsNumber;
	}

	public void setReceivedNotificationsNumber(
			Integer receivedNotificationsNumber) {
		this.receivedNotificationsNumber = receivedNotificationsNumber;
	}

	public List<CampaignFileUserModel> getCampaignFileUserModels() {
		return campaignFileUserModels;
	}

	public void setCampaignFileUserModels(
			List<CampaignFileUserModel> campaignFileUserModels) {
		this.campaignFileUserModels = campaignFileUserModels;
	}

	public Integer getCampaignFilesUserCount() {
		return campaignFilesUserCount;
	}

	public void setCampaignFilesUserCount(Integer campaignFilesUserCount) {
		this.campaignFilesUserCount = campaignFilesUserCount;
	}

	public Integer getCampaignFilesCount() {
		return campaignFilesCount;
	}

	public void setCampaignFilesCount(Integer campaignFilesCount) {
		this.campaignFilesCount = campaignFilesCount;
	}

	public List<CampaignFileModel> getCampaignFileModels() {
		return campaignFileModels;
	}

	public void setCampaignFileModels(List<CampaignFileModel> campaignFileModels) {
		this.campaignFileModels = campaignFileModels;
	}

	public List<Integer> getUnmatchedLists() {
		return UnmatchedLists;
	}

	public void setUnmatchedLists(List<Integer> unmatchedLists) {
		UnmatchedLists = unmatchedLists;
	}

	public List<String> getError_message_list() {
		return error_message_list;
	}

	public void setError_message_list(List<String> error_message_list) {
		this.error_message_list = error_message_list;
	}

	public Integer getUserParentId() {
		return userParentId;
	}

	public void setUserParentId(Integer userParentId) {
		this.userParentId = userParentId;
	}

	public List<CampaignModel> getCampaignModels() {
		return campaignModels;
	}

	public void setCampaignModels(List<CampaignModel> campaignModels) {
		this.campaignModels = campaignModels;
	}

	public List<UserFileUploadModel> getUserFileUploadModels() {
		return userFileUploadModels;
	}

	public void setUserFileUploadModels(
			List<UserFileUploadModel> userFileUploadModels) {
		this.userFileUploadModels = userFileUploadModels;
	}

	public Integer getUserUploadedFilesCount() {
		return userUploadedFilesCount;
	}

	public void setUserUploadedFilesCount(Integer userUploadedFilesCount) {
		this.userUploadedFilesCount = userUploadedFilesCount;
	}

	@Override
	public String toString() {
		return "<option value=" + userId + ">" + employeeName + "</option>";
	}

}
