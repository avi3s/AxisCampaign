package com.axis.model;

import com.axis.enumeration.Status;

public class CampaignFileModel extends CommonModel {

	private int campaignFileId;
	private String fileName;
	private Status status;
	private String extension;
	private String fileSize;
	private String fileType="CFM";
	private CampaignModel campaignModel;
	
	public int getCampaignFileId() {
		return campaignFileId;
	}
	public void setCampaignFileId(int campaignFileId) {
		this.campaignFileId = campaignFileId;
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
