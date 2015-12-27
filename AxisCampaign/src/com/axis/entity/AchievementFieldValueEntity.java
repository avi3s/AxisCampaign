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
@Table(name = "achievement_field_value_master")
public class AchievementFieldValueEntity extends CommonEntity {

	private static final long serialVersionUID = 79349535840336949L;

	@Id
	@SequenceGenerator(name = "ach_fld_val_sequence", sequenceName = "ach_fld_val_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ach_fld_val_sequence")
	@Column(name = "id")
	private int achievementFieldValueId;

	@Column(name = "field_value")
	private String fieldValue;

	@Column(name = "field_level")
	private String fieldLevel;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "achievement_id")
	private AchievementFieldEntity achievementFieldEntity;

	public int getAchievementFieldValueId() {
		return achievementFieldValueId;
	}

	public void setAchievementFieldValueId(int achievementFieldValueId) {
		this.achievementFieldValueId = achievementFieldValueId;
	}

	public AchievementFieldEntity getAchievementFieldEntity() {
		return achievementFieldEntity;
	}

	public void setAchievementFieldEntity(
			AchievementFieldEntity achievementFieldEntity) {
		this.achievementFieldEntity = achievementFieldEntity;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getFieldLevel() {
		return fieldLevel;
	}

	public void setFieldLevel(String fieldLevel) {
		this.fieldLevel = fieldLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + achievementFieldValueId;
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
		AchievementFieldValueEntity other = (AchievementFieldValueEntity) obj;
		if (achievementFieldValueId != other.achievementFieldValueId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(achievementFieldValueId);
	}
}
