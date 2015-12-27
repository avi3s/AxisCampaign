package com.axis.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axis.enumeration.State;
import com.axis.enumeration.Status;

@Entity
@Table(name = "campaign_master")
public class CampaignEntity extends CommonEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "campaign_sequence", sequenceName = "campaign_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="campaign_sequence")	
	@Column(name = "id")
	private int campaignId;

	@Column(name = "campaign_description")
	private String campaignDescription;

	@Column(name = "campaign_logo")
	private String campaignLogo;

	@Column(name = "campaign_name")
	private String campaignName;

	@Column(name = "financial_year")
	private String financialYear;

	@Column(name = "quarter_end_date")
	private Date quarterEndDate;

	@Column(name = "quarter_id")
	private String quarterId;

	@Column(name = "quarter_start_date")
	private Date quarterStartDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private State state;

	@OneToMany(mappedBy = "campaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<EscalationMatrixEntity> escalationMatrixEntities;

	@OneToMany(mappedBy = "campaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<FaqEntity> faqEntities;

	@OneToMany(mappedBy = "campaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<CampaignFileEntity> campaignFileEntities;

	@OneToMany(mappedBy = "campaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<CampaignFileUserEntity> campaignFileUserEntities;

	@OneToMany(mappedBy = "campaignEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<RoleCampaignEntity> roleCampaignEntities;

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
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

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
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

	public List<EscalationMatrixEntity> getEscalationMatrixEntities() {
		return escalationMatrixEntities;
	}

	public void setEscalationMatrixEntities(
			List<EscalationMatrixEntity> escalationMatrixEntities) {
		this.escalationMatrixEntities = escalationMatrixEntities;
	}

	public List<FaqEntity> getFaqEntities() {
		return faqEntities;
	}

	public void setFaqEntities(List<FaqEntity> faqEntities) {
		this.faqEntities = faqEntities;
	}

	public List<CampaignFileEntity> getCampaignFileEntities() {
		return campaignFileEntities;
	}

	public void setCampaignFileEntities(
			List<CampaignFileEntity> campaignFileEntities) {
		this.campaignFileEntities = campaignFileEntities;
	}

	public List<CampaignFileUserEntity> getCampaignFileUserEntities() {
		return campaignFileUserEntities;
	}

	public void setCampaignFileUserEntities(
			List<CampaignFileUserEntity> campaignFileUserEntities) {
		this.campaignFileUserEntities = campaignFileUserEntities;
	}

	public List<RoleCampaignEntity> getRoleCampaignEntities() {
		return roleCampaignEntities;
	}

	public void setRoleCampaignEntities(
			List<RoleCampaignEntity> roleCampaignEntities) {
		this.roleCampaignEntities = roleCampaignEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + campaignId;
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
		CampaignEntity other = (CampaignEntity) obj;
		if (campaignId != other.campaignId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(campaignId);
	}
}
