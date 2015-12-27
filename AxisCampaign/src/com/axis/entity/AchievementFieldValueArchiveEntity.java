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
@Table(name = "achievmnt_fieldval_archmaster")
public class AchievementFieldValueArchiveEntity extends CommonEntity {

	private static final long serialVersionUID = 79349535840336949L;

	@Id
	@SequenceGenerator(name = "ach_fld_val_arc_seq", sequenceName = "ach_fld_val_arc_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ach_fld_val_arc_seq")
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
	private AchievementFieldArchiveEntity achievementFieldArchiveEntity;

	public int getAchievementFieldValueId() {
		return achievementFieldValueId;
	}

	public void setAchievementFieldValueId(int achievementFieldValueId) {
		this.achievementFieldValueId = achievementFieldValueId;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldLevel() {
		return fieldLevel;
	}

	public void setFieldLevel(String fieldLevel) {
		this.fieldLevel = fieldLevel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AchievementFieldArchiveEntity getAchievementFieldArchiveEntity() {
		return achievementFieldArchiveEntity;
	}

	public void setAchievementFieldArchiveEntity(
			AchievementFieldArchiveEntity achievementFieldArchiveEntity) {
		this.achievementFieldArchiveEntity = achievementFieldArchiveEntity;
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
		AchievementFieldValueArchiveEntity other = (AchievementFieldValueArchiveEntity) obj;
		if (achievementFieldValueId != other.achievementFieldValueId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(achievementFieldValueId);
	}
}
