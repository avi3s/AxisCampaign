package com.axis.model;

import java.util.Date;
import java.util.List;

import com.axis.enumeration.State;
import com.axis.enumeration.Status;

public class CampaignModel extends CommonModel {

	private int campaignId;
	private String campaignName;
	private String campaignDescription;
	private String campaignLogo;
	private String financialYear;
	private Date quarterEndDate;
	private String quarterId;
	private Date quarterStartDate;
	private Status status;
	private State state;
	private List<CampaignFileModel> campaignFileModels;
	private List<RoleCampaignModel> roleCampaignModels;
	private String roleID_array[];
	private List<Integer> roleID_array_to_show;
	private int roleId;
	private int requestType;

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignDescription() {
		return campaignDescription;
	}

	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}

	public String getCampaignLogo() {
		return campaignLogo;
	}

	public void setCampaignLogo(String campaignLogo) {
		this.campaignLogo = campaignLogo;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Date getQuarterEndDate() {
		return quarterEndDate;
	}

	public void setQuarterEndDate(Date quarterEndDate) {
		this.quarterEndDate = quarterEndDate;
	}

	public String getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(String quarterId) {
		this.quarterId = quarterId;
	}

	public Date getQuarterStartDate() {
		return quarterStartDate;
	}

	public void setQuarterStartDate(Date quarterStartDate) {
		this.quarterStartDate = quarterStartDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<CampaignFileModel> getCampaignFileModels() {
		return campaignFileModels;
	}

	public void setCampaignFileModels(List<CampaignFileModel> campaignFileModels) {
		this.campaignFileModels = campaignFileModels;
	}

	public List<RoleCampaignModel> getRoleCampaignModels() {
		return roleCampaignModels;
	}

	public void setRoleCampaignModels(List<RoleCampaignModel> roleCampaignModels) {
		this.roleCampaignModels = roleCampaignModels;
	}

	public String[] getRoleID_array() {
		return roleID_array;
	}

	public void setRoleID_array(String[] roleID_array) {
		this.roleID_array = roleID_array;
	}

	public List<Integer> getRoleID_array_to_show() {
		return roleID_array_to_show;
	}

	public void setRoleID_array_to_show(List<Integer> roleID_array_to_show) {
		this.roleID_array_to_show = roleID_array_to_show;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

}
