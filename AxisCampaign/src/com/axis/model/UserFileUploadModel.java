package com.axis.model;

import com.axis.enumeration.Status;

public class UserFileUploadModel extends CommonModel {

	private int userFileUploadId;
	private String fileName;
	private String fileSize;
	private String fileType="UFUM";
	private String extension;
	private Status status;
	private RoleModel roleModel;

	public int getUserFileUploadId() {
		return userFileUploadId;
	}

	public void setUserFileUploadId(int userFileUploadId) {
		this.userFileUploadId = userFileUploadId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
