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
@Table(name = "campaign_file_user_master")
public class CampaignFileUserEntity extends CommonEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "campaign_file_user_sequence", sequenceName = "campaign_file_user_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="campaign_file_user_sequence")
	@Column(name = "id")
	private int campaignFileUserId;

	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_size")
	private String fileSize;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "view_status")
	private Status view_status;

	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private CampaignEntity campaignEntity;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

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

	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}

	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	public Status getView_status() {
		return view_status;
	}

	public void setView_status(Status view_status) {
		this.view_status = view_status;
	}

	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + campaignFileUserId;
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
		CampaignFileUserEntity other = (CampaignFileUserEntity) obj;
		if (campaignFileUserId != other.campaignFileUserId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(campaignFileUserId);
	}
}
