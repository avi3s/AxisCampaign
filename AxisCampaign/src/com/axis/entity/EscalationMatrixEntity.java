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
@Table(name = "escalation_matrix_master")
public class EscalationMatrixEntity extends CommonEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EscMat_sequence", sequenceName = "EscMat_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="EscMat_sequence")
	@Column(name = "id")
	private int escalationMatrixId;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@Column(name = "type")
	private String type;

	//(cascade={CascadeType.ALL})
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private CampaignEntity campaignEntity;

	public int getEscalationMatrixId() {
		return escalationMatrixId;
	}

	public void setEscalationMatrixId(int escalationMatrixId) {
		this.escalationMatrixId = escalationMatrixId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}

	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + escalationMatrixId;
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
		EscalationMatrixEntity other = (EscalationMatrixEntity) obj;
		if (escalationMatrixId != other.escalationMatrixId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(escalationMatrixId);
	}

}
