package com.axis.model;

public class RoleCampaignModel  extends CommonModel {

	private int roleCampaignId;
	private RoleModel roleModel;
	private CampaignModel campaignModel;
	
	
	public int getRoleCampaignId() {
		return roleCampaignId;
	}
	public void setRoleCampaignId(int roleCampaignId) {
		this.roleCampaignId = roleCampaignId;
	}
	public RoleModel getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}
	public CampaignModel getCampaignModel() {
		return campaignModel;
	}
	public void setCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
	}
	
	
}
