package com.axis.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axis.enumeration.Status;

@Entity
@Table(name = "notification_master")
public class NotificationEntity extends CommonEntity {

	private static final long serialVersionUID = -8696374447600695150L;

	@Id
	@SequenceGenerator(name = "NotificationEntity_sequence", sequenceName = "NotificationEntity_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="NotificationEntity_sequence")
	@Column(name = "id")
	private int notificationId;
	
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "message")
	private String message;

	@Column(name = "parent_id")
	private String parentId;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "receive_status")
	private Status receiveStatus;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "sent_status")
	private Status sentStatus;

	@Column(name = "subject")
	private String subject;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "view_status")
	private String viewStatus;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "receiver_user_id")
	private UserEntity receivedUserId;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "sent_user_id")
	private UserEntity sentUserId;

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Status getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Status receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Status getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(Status sentStatus) {
		this.sentStatus = sentStatus;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public UserEntity getReceivedUserId() {
		return receivedUserId;
	}

	public void setReceivedUserId(UserEntity receivedUserId) {
		this.receivedUserId = receivedUserId;
	}

	public UserEntity getSentUserId() {
		return sentUserId;
	}

	public void setSentUserId(UserEntity sentUserId) {
		this.sentUserId = sentUserId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + notificationId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationEntity other = (NotificationEntity) obj;
		if (notificationId != other.notificationId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(notificationId);
	}

}
