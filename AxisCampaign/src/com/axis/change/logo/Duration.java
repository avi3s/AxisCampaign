package com.axis.change.logo;

import javax.xml.bind.annotation.XmlAttribute;


public class Duration {
	
	
	private String endMonth;

	private String startMonth;

	/**
	 * @return the endMonth
	 */
	public String getEndMonth() {
		return endMonth;
	}

	/**
	 * @param endMonth
	 *            the endMonth to set
	 */
	
	@XmlAttribute(name="end-month")
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	/**
	 * @return the startMonth
	 */
	public String getStartMonth() {
		return startMonth;
	}

	/**
	 * @param startMonth
	 *            the startMonth to set
	 */
	@XmlAttribute(name="start-month")
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

}
