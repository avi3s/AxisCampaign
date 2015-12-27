package com.axis.model;

import java.util.List;

import com.axis.enumeration.Status;

public class CampaignFileUserModel  extends CommonModel {

	private int campaignFileUserId;
	private String fileName;
	private Status status;
	private String extension;
	private CampaignModel campaignModel;
	private UserModel userModel;
	private List<String> promotionalFiles;
	private int requestType;
	private String fileSize;
	private String fileType="CFUM";
	
	public int getCampaignFileUserId() {
		return campaignFileUserId;
	}
	public void setCampaignFileUserId(int campaignFileUserId) {
		this.campaignFileUserId = campaignFileUserId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public CampaignModel getCampaignModel() {
		return campaignModel;
	}
	public void setCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public List<String> getPromotionalFiles() {
		return promotionalFiles;
	}
	public void setPromotionalFiles(List<String> promotionalFiles) {
		this.promotionalFiles = promotionalFiles;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
