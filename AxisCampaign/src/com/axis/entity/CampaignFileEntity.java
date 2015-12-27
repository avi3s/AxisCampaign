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
@Table(name = "campaign_file_master")
public class CampaignFileEntity extends CommonEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "campaign_file_sequence", sequenceName = "campaign_file_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="campaign_file_sequence")
	@Column(name = "id")
	private int campaignFileId;

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

	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}

	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
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
		result = prime * result + campaignFileId;
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
		CampaignFileEntity other = (CampaignFileEntity) obj;
		if (campaignFileId != other.campaignFileId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(campaignFileId);
	}
}
