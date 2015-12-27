package com.axis.model;

import com.axis.enumeration.Status;

public class NotificationModel extends CommonModel {

	private int id;
	private String subject;
	private String message;
	private int sentUserId;
	private int receivedUserId;
	private Status status;
	private Status sentStatus;
	private Status receiveStatus;
	private String parentId;
	private String viewStatus;
	private int roleId;
	private int[] userId_array;
	private String sentUserName;
	private String receivedUserName;
	private String type;
	private int campaignid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSentUserId() {
		return sentUserId;
	}

	public void setSentUserId(int sentUserId) {
		this.sentUserId = sentUserId;
	}

	public int getReceivedUserId() {
		return receivedUserId;
	}

	public void setReceivedUserId(int receivedUserId) {
		this.receivedUserId = receivedUserId;
	}

	public Status getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(Status sentStatus) {
		this.sentStatus = sentStatus;
	}

	public Status getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Status receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int[] getUserId_array() {
		return userId_array;
	}

	public void setUserId_array(int[] userId_array) {
		this.userId_array = userId_array;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSentUserName() {
		return sentUserName;
	}

	public void setSentUserName(String sentUserName) {
		this.sentUserName = sentUserName;
	}

	public String getReceivedUserName() {
		return receivedUserName;
	}

	public void setReceivedUserName(String receivedUserName) {
		this.receivedUserName = receivedUserName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCampaignid() {
		return campaignid;
	}

	public void setCampaignid(int campaignid) {
		this.campaignid = campaignid;
	}

}
