package com.axis.entity;

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
@Table(name = "user_file_upload_master")
public class UserFileUploadEntity  extends CommonEntity {

	private static final long serialVersionUID = 5929241494960821386L;

	@Id
	@SequenceGenerator(name = "UFUpload_sequence", sequenceName = "UFUpload_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UFUpload_sequence")
	@Column(name = "id")
	private int userFileUploadId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_size")
	private String fileSize;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

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

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
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
		result = prime * result + userFileUploadId;
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
		UserFileUploadEntity other = (UserFileUploadEntity) obj;
		if (userFileUploadId != other.userFileUploadId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Integer.toString(userFileUploadId);
	}
	
}
