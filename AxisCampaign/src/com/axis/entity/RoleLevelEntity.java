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
@Table(name = "role_level_master")
public class RoleLevelEntity extends CommonEntity {

	private static final long serialVersionUID = 8385038117911900998L;

	@Id
	@SequenceGenerator(name = "role_level_sequence", sequenceName = "role_level_sequence", allocationSize=10, initialValue=5)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="role_level_sequence")
	@Column(name = "id")
	private int rowLevelId;

	@Column(name = "level_name")
	private String levelName;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private RoleLevelEntity roleLevelEntity;
	
	@OneToMany(mappedBy = "roleLevelEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	private List<RoleEntity> roleEntities;
	
//	@OneToMany(mappedBy="roleLevelEntity")
//    private List<RoleLevelEntity> roleLevelEntities;

	public List<RoleEntity> getRoleEntities() {
		return roleEntities;
	}

	public void setRoleEntities(List<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

	@OneToMany(mappedBy = "roleLevelEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<SubRoleLevelEntity> SubRoleLevelEntities;

	public int getRowLevelId() {
		return rowLevelId;
	}

	public void setRowLevelId(int rowLevelId) {
		this.rowLevelId = rowLevelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public RoleLevelEntity getRoleLevelEntity() {
		return roleLevelEntity;
	}

	public void setRoleLevelEntity(RoleLevelEntity roleLevelEntity) {
		this.roleLevelEntity = roleLevelEntity;
	}

	public List<SubRoleLevelEntity> getSubRoleLevelEntities() {
		return SubRoleLevelEntities;
	}

	public void setSubRoleLevelEntities(
			List<SubRoleLevelEntity> subRoleLevelEntities) {
		SubRoleLevelEntities = subRoleLevelEntities;
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
		result = prime * result + rowLevelId;
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
		RoleLevelEntity other = (RoleLevelEntity) obj;
		if (rowLevelId != other.rowLevelId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(rowLevelId);
	}
}
