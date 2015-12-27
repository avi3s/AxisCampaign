package com.axis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axis.enumeration.Status;

@Entity
@Table(name = "content_management_master")
public class ContentManagementDetailsEntity extends CommonEntity {

	private static final long serialVersionUID = -3409576651858897259L;

	@Id
	@SequenceGenerator(name = "con_mng_dt_sequence", sequenceName = "con_mng_dt_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="con_mng_dt_sequence")
	@Column(name = "id")
	private int contentManagementDetailsId;

	@Column(name="page_content", columnDefinition="CLOB")
	private String pageContent;

	@Column(name = "page_name")
	private String pageName;

	@Column(name = "path")
	private String path;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getContentManagementDetailsId() {
		return contentManagementDetailsId;
	}

	public void setContentManagementDetailsId(int contentManagementDetailsId) {
		this.contentManagementDetailsId = contentManagementDetailsId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + contentManagementDetailsId;
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
		ContentManagementDetailsEntity other = (ContentManagementDetailsEntity) obj;
		if (contentManagementDetailsId != other.contentManagementDetailsId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(contentManagementDetailsId);
	}
}
