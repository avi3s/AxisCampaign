package com.axis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axis.enumeration.Status;

@Entity
@Table(name = "user_header_image_master")
public class UserHeaderImageEntity  extends CommonEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "UFUpload_sequence", sequenceName = "UFUpload_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UFUpload_sequence")
	@Column(name = "id")
	private int userHeaderImageId;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + userHeaderImageId;
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
		UserHeaderImageEntity other = (UserHeaderImageEntity) obj;
		if (userHeaderImageId != other.userHeaderImageId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Integer.toString(userHeaderImageId);
	}
	
}
