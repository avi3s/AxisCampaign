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
@Table(name = "user_master")
public class UserEntity extends CommonEntity {

	private static final long serialVersionUID = 7806167903570991905L;

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")
	@Column(name = "id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "employee_number")
	private String employeeNumber;

	@Column(name = "profile_picture")
	private String profilePicture;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "about")
	private String about;

	@Column(name = "office_address")
	private String officeAddress;

	@Column(name = "primary_telephone_number")
	private String primaryTelephoneNumber;

	@Column(name = "secondary_telephone_number")
	private String secondaryTelephoneNumber;

	@Column(name = "email_address")
	private String emailAddress;

	@ManyToOne
	@JoinColumn(name = "sub_role_level_id")
	private SubRoleLevelEntity subRoleLevelEntity;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

	@ManyToOne
	@JoinColumn(name = "parent_user_id")
	private UserEntity userEntity;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;

	@OneToMany(mappedBy = "receivedUserId", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<NotificationEntity> receivedNotificationEntities;

	@OneToMany(mappedBy = "sentUserId", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<NotificationEntity> sentNotificationEntities;

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	List<CampaignFileUserEntity> campaignFileUserEntities;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getPrimaryTelephoneNumber() {
		return primaryTelephoneNumber;
	}

	public void setPrimaryTelephoneNumber(String primaryTelephoneNumber) {
		this.primaryTelephoneNumber = primaryTelephoneNumber;
	}

	public String getSecondaryTelephoneNumber() {
		return secondaryTelephoneNumber;
	}

	public void setSecondaryTelephoneNumber(String secondaryTelephoneNumber) {
		this.secondaryTelephoneNumber = secondaryTelephoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public SubRoleLevelEntity getSubRoleLevelEntity() {
		return subRoleLevelEntity;
	}

	public void setSubRoleLevelEntity(SubRoleLevelEntity subRoleLevelEntity) {
		this.subRoleLevelEntity = subRoleLevelEntity;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<NotificationEntity> getReceivedNotificationEntities() {
		return receivedNotificationEntities;
	}

	public void setReceivedNotificationEntities(
			List<NotificationEntity> receivedNotificationEntities) {
		this.receivedNotificationEntities = receivedNotificationEntities;
	}

	public List<NotificationEntity> getSentNotificationEntities() {
		return sentNotificationEntities;
	}

	public void setSentNotificationEntities(
			List<NotificationEntity> sentNotificationEntities) {
		this.sentNotificationEntities = sentNotificationEntities;
	}

	public List<CampaignFileUserEntity> getCampaignFileUserEntities() {
		return campaignFileUserEntities;
	}

	public void setCampaignFileUserEntities(
			List<CampaignFileUserEntity> campaignFileUserEntities) {
		this.campaignFileUserEntities = campaignFileUserEntities;
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
		result = prime * result + userId;
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
		UserEntity other = (UserEntity) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(userId);
	}
}
