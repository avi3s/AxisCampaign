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
@Table(name = "sub_role_level_master")
public class SubRoleLevelEntity extends CommonEntity {

	private static final long serialVersionUID = -3571888559906519938L;

	@Id
	@SequenceGenerator(name = "sub_role_level_sequence", sequenceName = "sub_role_level_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="sub_role_level_sequence")
	@Column(name = "id")
	private int subRoleLevelId;

	@Column(name = "unique_id")
	private String uniqueId;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "role_level_id")
	private RoleLevelEntity roleLevelEntity;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SubRoleLevelEntity subRoleLevelEntity;


	@OneToMany(mappedBy = "subRoleLevelEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<UserEntity> userEntities;

	public int getSubRoleLevelId() {
		return subRoleLevelId;
	}

	public void setSubRoleLevelId(int subRoleLevelId) {
		this.subRoleLevelId = subRoleLevelId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleLevelEntity getRoleLevelEntity() {
		return roleLevelEntity;
	}

	public void setRoleLevelEntity(RoleLevelEntity roleLevelEntity) {
		this.roleLevelEntity = roleLevelEntity;
	}

	public List<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public SubRoleLevelEntity getSubRoleLevelEntity() {
		return subRoleLevelEntity;
	}

	public void setSubRoleLevelEntity(SubRoleLevelEntity subRoleLevelEntity) {
		this.subRoleLevelEntity = subRoleLevelEntity;
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
		result = prime * result + subRoleLevelId;
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
		SubRoleLevelEntity other = (SubRoleLevelEntity) obj;
		if (subRoleLevelId != other.subRoleLevelId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(subRoleLevelId);
	}
}
