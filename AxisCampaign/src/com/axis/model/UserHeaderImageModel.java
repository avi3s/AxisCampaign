package com.axis.model;

import com.axis.enumeration.Status;

public class UserHeaderImageModel extends CommonModel {

	private int userHeaderImageId;
	private String imageName;
	private Status status;

	public int getUserHeaderImageId() {
		return userHeaderImageId;
	}

	public void setUserHeaderImageId(int userHeaderImageId) {
		this.userHeaderImageId = userHeaderImageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
