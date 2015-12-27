package com.axis.model;

import java.util.Date;
import java.util.List;

import com.axis.enumeration.Status;

public class EscalationMatrixModel {

	private int id;
	private String contactNumber;
	private String email;
	private String name;
	private Status status;
	private String type;
    private int campaignId;
	private Date createDate;
	private String campaignName;
	private List<Integer> UnmatchedLists;
	private String issue;
	private String totalRows;
	private String errorRowCount;
	private int rowMissMatchCount;
	
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getErrorRowCount() {
		return errorRowCount;
	}
	public void setErrorRowCount(String errorRowCount) {
		this.errorRowCount = errorRowCount;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	private String about;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public List<Integer> getUnmatchedLists() {
		return UnmatchedLists;
	}
	public void setUnmatchedLists(List<Integer> unmatchedLists) {
		UnmatchedLists = unmatchedLists;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public int getRowMissMatchCount() {
		return rowMissMatchCount;
	}
	public void setRowMissMatchCount(int rowMissMatchCount) {
		this.rowMissMatchCount = rowMissMatchCount;
	}	
}
