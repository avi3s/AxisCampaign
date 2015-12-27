package com.axis.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role_campaign_mapping")
public class RoleCampaignEntity extends CommonEntity {

	private static final long serialVersionUID = -6157058685580820627L;

	@Id
	@SequenceGenerator(name = "RoleCampaignEntity_sequence", sequenceName = "RoleCampaignEntity_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="RoleCampaignEntity_sequence")
	@Column(name = "id")
	private int roleCampaignId;

	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private CampaignEntity campaignEntity;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

	@OneToMany(mappedBy = "roleCampaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<TargetFieldEntity> targetFieldEntities;

	@OneToMany(mappedBy = "roleCampaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<AchievementFieldEntity> achievementFieldEntities;

	public int getRoleCampaignId() {
		return roleCampaignId;
	}

	public void setRoleCampaignId(int roleCampaignId) {
		this.roleCampaignId = roleCampaignId;
	}

	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}

	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<TargetFieldEntity> getTargetFieldEntities() {
		return targetFieldEntities;
	}

	public void setTargetFieldEntities(
			List<TargetFieldEntity> targetFieldEntities) {
		this.targetFieldEntities = targetFieldEntities;
	}

	public List<AchievementFieldEntity> getAchievementFieldEntities() {
		return achievementFieldEntities;
	}

	public void setAchievementFieldEntities(
			List<AchievementFieldEntity> achievementFieldEntities) {
		this.achievementFieldEntities = achievementFieldEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + roleCampaignId;
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
		RoleCampaignEntity other = (RoleCampaignEntity) obj;
		if (roleCampaignId != other.roleCampaignId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(roleCampaignId);
	}
}
