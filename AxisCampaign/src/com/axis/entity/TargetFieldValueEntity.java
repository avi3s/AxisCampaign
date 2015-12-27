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
@Table(name = "target_field_value_master")
public class TargetFieldValueEntity extends CommonEntity {

	private static final long serialVersionUID = 79349535840336949L;

	@Id
	@SequenceGenerator(name = "TrgFldVal_sequence", sequenceName = "TrgFldVal_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TrgFldVal_sequence")
	@Column(name = "id")
	private int targetFieldValueId;

	@Column(name = "field_value")
	private String fieldValue;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "target_id")
	private TargetFieldEntity targetFieldEntity;

	public int getTargetFieldValueId() {
		return targetFieldValueId;
	}

	public void setTargetFieldValueId(int targetFieldValueId) {
		this.targetFieldValueId = targetFieldValueId;
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

	public TargetFieldEntity getTargetFieldEntity() {
		return targetFieldEntity;
	}

	public void setTargetFieldEntity(TargetFieldEntity targetFieldEntity) {
		this.targetFieldEntity = targetFieldEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + targetFieldValueId;
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
		TargetFieldValueEntity other = (TargetFieldValueEntity) obj;
		if (targetFieldValueId != other.targetFieldValueId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(targetFieldValueId);
	}
}
