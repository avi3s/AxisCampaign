package com.axis.model;

import java.util.Date;

import com.axis.enumeration.Status;

public class ContentManagementDetailsModel extends CommonModel{

	private int id;
	private String pageName;
	private String path;
	private String pageContent;
	private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ContentManagementDetailsModel [id=" + id + "]";
	}



}
