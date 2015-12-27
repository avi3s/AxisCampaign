package com.axis.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axis.enumeration.Status;

@Entity
@Table(name = "target_field_master")
public class TargetFieldEntity extends CommonEntity {

	private static final long serialVersionUID = 7074150482692697807L;

	@Id
	@SequenceGenerator(name = "TargetFieldEntity_sequence", sequenceName = "TargetFieldEntity_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TargetFieldEntity_sequence")
	@Column(name = "id")
	private int targetFieldId;

	@Column(name = "field_name")
	private String filedName;

	@Column(name = "field_type")
	private String fieldType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "role_campaign_id")
	private RoleCampaignEntity roleCampaignEntity;

	@OneToMany(mappedBy = "targetFieldEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<TargetFieldValueEntity> targetFieldValueEntities;

	public int getTargetFieldId() {
		return targetFieldId;
	}

	public void setTargetFieldId(int targetFieldId) {
		this.targetFieldId = targetFieldId;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public RoleCampaignEntity getRoleCampaignEntity() {
		return roleCampaignEntity;
	}

	public void setRoleCampaignEntity(RoleCampaignEntity roleCampaignEntity) {
		this.roleCampaignEntity = roleCampaignEntity;
	}

	public List<TargetFieldValueEntity> getTargetFieldValueEntities() {
		return targetFieldValueEntities;
	}

	public void setTargetFieldValueEntities(
			List<TargetFieldValueEntity> targetFieldValueEntities) {
		this.targetFieldValueEntities = targetFieldValueEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + targetFieldId;
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
		TargetFieldEntity other = (TargetFieldEntity) obj;
		if (targetFieldId != other.targetFieldId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(targetFieldId);
	}
}
