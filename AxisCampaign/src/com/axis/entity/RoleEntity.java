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
@Table(name = "role_master")
public class RoleEntity extends CommonEntity {

	private static final long serialVersionUID = 929630397397534078L;

	@Id
	@SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="role_sequence")
	@Column(name = "id")
	private int roleId;

	@Column(name = "role_name")
	private String roleName;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "role_level_id")
	private RoleLevelEntity roleLevelEntity;

	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<RoleCampaignEntity> roleCampaignEntities;

	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<UserEntity> userEntities;
	
	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<UserFileUploadEntity> userFileUploadEntities;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public RoleLevelEntity getRoleLevelEntity() {
		return roleLevelEntity;
	}

	public void setRoleLevelEntity(RoleLevelEntity roleLevelEntity) {
		this.roleLevelEntity = roleLevelEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<RoleCampaignEntity> getRoleCampaignEntities() {
		return roleCampaignEntities;
	}

	public void setRoleCampaignEntities(
			List<RoleCampaignEntity> roleCampaignEntities) {
		this.roleCampaignEntities = roleCampaignEntities;
	}

	public List<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<UserFileUploadEntity> getUserFileUploadEntities() {
		return userFileUploadEntities;
	}

	public void setUserFileUploadEntities(
			List<UserFileUploadEntity> userFileUploadEntities) {
		this.userFileUploadEntities = userFileUploadEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + roleId;
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
		RoleEntity other = (RoleEntity) obj;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(roleId);
	}
}
