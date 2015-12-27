package com.axis.converter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.entity.*;
import com.axis.enumeration.State;
import com.axis.enumeration.Status;
import com.axis.model.*;

@Component
public class UserConverter implements NecessaryConverter<UserEntity, UserModel> {

	@Override
	public UserEntity modelToEntity(UserModel m) {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmployeeName(m.getEmployeeName());
		userEntity.setEmployeeNumber(m.getEmployeeNumber());
		userEntity.setUserName(m.getUserName());
		userEntity.setPassword(m.getPassword());
		userEntity.setAbout(m.getAbout());
		userEntity.setOfficeAddress(m.getOfficeAddress());
		userEntity.setPrimaryTelephoneNumber(m.getPrimaryTelephoneNumber());
		userEntity.setSecondaryTelephoneNumber(m.getSecondaryTelephoneNumber());
		userEntity.setEmailAddress(m.getEmailAddress());
		userEntity.setStatus(Status.ACTIVE);
		userEntity.setCreateTimeStamp(new Date());
		userEntity.setCreatedBy(m.getCreatedBy());
		userEntity.setProfilePicture(m.getProfilePicture());

		UserEntity userEntity2 = new UserEntity();
		if(m.getUserParentId() == null)
			m.setUserParentId(1);
		userEntity2.setUserId(m.getUserParentId());
		userEntity.setUserEntity(userEntity2);

		SubRoleLevelEntity subRoleLevelEntity = new SubRoleLevelEntity();
		subRoleLevelEntity.setSubRoleLevelId(m.getSubRoleLevelId());
		userEntity.setSubRoleLevelEntity(subRoleLevelEntity);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(m.getRoleId());
		userEntity.setRoleEntity(roleEntity);

		return userEntity;
	}

	@Override
	public UserEntity updateModelToEntity(UserModel m, UserEntity userEntity) {

		userEntity.setEmployeeName(m.getEmployeeName());
		userEntity.setEmployeeNumber(m.getEmployeeNumber());
		userEntity.setUserName(m.getUserName());
		userEntity.setPassword(m.getPassword());
		userEntity.setAbout(m.getAbout());
		userEntity.setOfficeAddress(m.getOfficeAddress());
		userEntity.setPrimaryTelephoneNumber(m.getPrimaryTelephoneNumber());
		userEntity.setSecondaryTelephoneNumber(m.getSecondaryTelephoneNumber());
		userEntity.setEmailAddress(m.getEmailAddress());
		userEntity.setStatus(Status.ACTIVE);
		userEntity.setUpdatedBy(m.getUpdatedBy());
		userEntity.setLastUpdateTimeStamp(new Date());
		// userEntity.setCreateTimeStamp(new Date());
		// userEntity.setCreatedBy(m.getCreatedBy());
		userEntity.setProfilePicture(m.getProfilePicture());

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setUserId(m.getUserParentId());
		userEntity.setUserEntity(userEntity2);

		SubRoleLevelEntity subRoleLevelEntity = new SubRoleLevelEntity();
		subRoleLevelEntity.setSubRoleLevelId(m.getSubRoleLevelId());
		userEntity.setSubRoleLevelEntity(subRoleLevelEntity);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(m.getRoleId());
		userEntity.setRoleEntity(roleEntity);

		return userEntity;
	}

	/*
	 * @Override public UserModel entityToModel(UserEntity userEntity) throws
	 * ObjectNotFound {
	 * 
	 * UserModel userModel = new UserModel();
	 * userModel.setUserId(userEntity.getUserId());
	 * userModel.setEmployeeName(userEntity.getEmployeeName());
	 * userModel.setEmployeeNumber(userEntity.getEmployeeNumber());
	 * userModel.setUserName(userEntity.getUserName());
	 * userModel.setPassword(userEntity.getPassword());
	 * userModel.setAbout(userEntity.getAbout());
	 * userModel.setOfficeAddress(userEntity.getOfficeAddress());
	 * userModel.setPrimaryTelephoneNumber(userEntity
	 * .getPrimaryTelephoneNumber());
	 * userModel.setSecondaryTelephoneNumber(userEntity
	 * .getSecondaryTelephoneNumber());
	 * userModel.setEmailAddress(userEntity.getEmailAddress());
	 * userModel.setRoleId(userEntity.getRoleEntity().getRoleId());
	 * userModel.setRoleName(userEntity.getRoleEntity().getRoleName());
	 * userModel.setSubRoleLevelId(userEntity.getSubRoleLevelEntity()
	 * .getSubRoleLevelId());
	 * userModel.setSubRoleLevelname(userEntity.getSubRoleLevelEntity()
	 * .getDescription());
	 * userModel.setUserParentId(userEntity.getUserEntity().getUserId());
	 * userModel.setParentUserName(userEntity.getUserEntity()
	 * .getEmployeeName());
	 * userModel.setProfilePicture(userEntity.getProfilePicture());
	 * 
	 * List<NotificationModel> notificationModels = new
	 * LinkedList<NotificationModel>(); Integer newNotificationsCount = 0;
	 * 
	 * for (NotificationEntity notificationEntity : userEntity
	 * .getReceivedNotificationEntities()) {
	 * 
	 * NotificationModel notificationModel = new NotificationModel();
	 * 
	 * if (notificationEntity.getStatus() == Status.ACTIVE &&
	 * notificationEntity.getViewStatus().equals("0")) {
	 * newNotificationsCount++; }
	 * 
	 * notificationModel.setId(notificationEntity.getNotificationId());
	 * notificationModel.setSubject(notificationEntity.getSubject());
	 * notificationModel.setMessage(notificationEntity.getMessage());
	 * notificationModel.setCreateTimeStamp(notificationEntity
	 * .getCreateTimeStamp());
	 * notificationModel.setSentUserName(notificationEntity
	 * .getSentUserId().getEmployeeName());
	 * notificationModel.setStatus(notificationEntity.getStatus());
	 * notificationModel.setViewStatus(notificationEntity.getViewStatus());
	 * 
	 * notificationModels.add(notificationModel); }
	 * 
	 * userModel.setReceivedNotificationsNumber(newNotificationsCount);
	 * userModel.setNotificationModels(notificationModels);
	 * 
	 * return userModel;
	 * 
	 * }
	 */

	@Override
	public List<UserModel> entityListToModelList(List<UserEntity> es)
			throws DataNotFound, ObjectNotFound {

		List<UserModel> userModels = new LinkedList<UserModel>();

		for (UserEntity userEntity : es) {
			UserModel userModel = new UserModel();

			if (userEntity.getStatus() == Status.ACTIVE) {

				userModel.setUserId(userEntity.getUserId());
				userModel.setEmployeeName(userEntity.getEmployeeName());
				userModel.setEmployeeNumber(userEntity.getEmployeeNumber());
				userModel.setUserName(userEntity.getUserName());
				userModel.setPassword(userEntity.getPassword());
				userModel.setAbout(userEntity.getAbout());
				userModel.setOfficeAddress(userEntity.getOfficeAddress());
				userModel.setPrimaryTelephoneNumber(userEntity
						.getPrimaryTelephoneNumber());
				userModel.setSecondaryTelephoneNumber(userEntity
						.getSecondaryTelephoneNumber());
				userModel.setEmailAddress(userEntity.getEmailAddress());
				userModel.setRoleId(userEntity.getRoleEntity().getRoleId());
				userModel.setRoleName(userEntity.getRoleEntity().getRoleName());
				userModel.setSubRoleLevelId(userEntity.getSubRoleLevelEntity()
						.getSubRoleLevelId());
				userModel.setSubRoleLevelname(userEntity
						.getSubRoleLevelEntity().getDescription());
				userModel.setUserParentId(userEntity.getUserEntity()
						.getUserId());
				userModel.setParentUserName(userEntity.getUserEntity()
						.getEmployeeName());
				userModel.setProfilePicture(userEntity.getProfilePicture());

				userModels.add(userModel);
			}
		}
		return userModels;

	}

	@Override
	public List<UserEntity> modelListToEntityList(List<UserModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity deleteModelToEntity(UserModel userModel,
			UserEntity userEntity) {
		userEntity.setStatus(Status.INACTIVE);
		return userEntity;
	}

	public UserEntity updateModelToEntityForUser(UserModel m,
			UserEntity userEntity) {

		userEntity.setAbout(m.getAbout());
		userEntity.setSecondaryTelephoneNumber(m.getSecondaryTelephoneNumber());
		userEntity.setStatus(Status.ACTIVE);
		userEntity.setUpdatedBy(m.getUpdatedBy());
		userEntity.setLastUpdateTimeStamp(new Date());
		userEntity.setProfilePicture(m.getProfilePicture());

		return userEntity;
	}

	@Override
	public UserModel entityToModel(UserEntity userEntity) throws ObjectNotFound {

		UserModel userModel = new UserModel();
		userModel.setUserId(userEntity.getUserId());
		userModel.setEmployeeName(userEntity.getEmployeeName());
		userModel.setEmployeeNumber(userEntity.getEmployeeNumber());
		userModel.setUserName(userEntity.getUserName());
		userModel.setPassword(userEntity.getPassword());
		userModel.setAbout(userEntity.getAbout());
		userModel.setOfficeAddress(userEntity.getOfficeAddress());
		userModel.setPrimaryTelephoneNumber(userEntity
				.getPrimaryTelephoneNumber());
		userModel.setSecondaryTelephoneNumber(userEntity
				.getSecondaryTelephoneNumber());
		userModel.setEmailAddress(userEntity.getEmailAddress());
		userModel.setRoleId(userEntity.getRoleEntity().getRoleId());
		userModel.setRoleName(userEntity.getRoleEntity().getRoleName());
		userModel.setSubRoleLevelId(userEntity.getSubRoleLevelEntity()
				.getSubRoleLevelId());
		userModel.setSubRoleLevelname(userEntity.getSubRoleLevelEntity()
				.getDescription());
		userModel.setUserParentId(userEntity.getUserEntity().getUserId());
		userModel.setParentUserName(userEntity.getUserEntity()
				.getEmployeeName());
		userModel.setProfilePicture(userEntity.getProfilePicture());

		List<NotificationModel> notificationModels = new LinkedList<NotificationModel>();
		Integer newNotificationsCount = 0;

		for (NotificationEntity notificationEntity : userEntity
				.getReceivedNotificationEntities()) {

			NotificationModel notificationModel = new NotificationModel();

			if (notificationEntity.getStatus() == Status.ACTIVE
					&& notificationEntity.getViewStatus().equals("0")) {
				newNotificationsCount++;
			}

			if (notificationEntity.getStatus() == Status.ACTIVE) {
				notificationModel.setId(notificationEntity.getNotificationId());
				notificationModel.setSubject(notificationEntity.getSubject());
				notificationModel.setMessage(notificationEntity.getMessage());
				notificationModel.setCreateTimeStamp(notificationEntity
						.getCreateTimeStamp());
				notificationModel.setSentUserName(notificationEntity
						.getSentUserId().getEmployeeName());
				notificationModel.setStatus(notificationEntity.getStatus());
				notificationModel.setViewStatus(notificationEntity
						.getViewStatus());

				notificationModels.add(notificationModel);
			}
		}

		userModel.setReceivedNotificationsNumber(newNotificationsCount);
		userModel.setNotificationModels(notificationModels);

		Integer newUserUploadedFilesCount = 0;
        
        List<UserFileUploadModel> userFileUploadModels=new LinkedList<UserFileUploadModel>();
        for(UserFileUploadEntity userFileUploadEntity : userEntity.getRoleEntity().getUserFileUploadEntities()) {
            
            if (userFileUploadEntity.getStatus()== Status.ACTIVE) {
                newUserUploadedFilesCount++;
                UserFileUploadModel userFileUploadModel=new UserFileUploadModel();
                userFileUploadModel.setUserFileUploadId(userFileUploadEntity.getUserFileUploadId());
                userFileUploadModel.setFileName(userFileUploadEntity.getFileName());
                userFileUploadModel.setCreateTimeStamp(userFileUploadEntity.getCreateTimeStamp());
                userFileUploadModel.setFileSize(userFileUploadEntity.getFileSize());
                userFileUploadModel.setExtension(userFileUploadEntity.getFileName().substring(userFileUploadEntity.getFileName().lastIndexOf(".")+1, userFileUploadEntity.getFileName().length()));
                userFileUploadModels.add(userFileUploadModel);
            }
        }
        
        userModel.setUserFileUploadModels(userFileUploadModels);
        
		List<CampaignFileModel> campaignFileModels = new LinkedList<CampaignFileModel>();
		Integer newCampaignFilesCount = 0;


		for (RoleCampaignEntity roleCampaignEntity : userEntity.getRoleEntity()
				.getRoleCampaignEntities()) {

			for (CampaignFileEntity campaignFileEntity : roleCampaignEntity
					.getCampaignEntity().getCampaignFileEntities()) {
				
				if (campaignFileEntity.getView_status()== Status.ACTIVE
						&& campaignFileEntity.getStatus() == Status.ACTIVE) {
					newCampaignFilesCount++;
				}

				if (campaignFileEntity.getStatus() == Status.ACTIVE) {

					CampaignFileModel campaignFileModel = new CampaignFileModel();
					campaignFileModel.setCampaignFileId(campaignFileEntity
							.getCampaignFileId());
					campaignFileModel.setCreateTimeStamp(campaignFileEntity
							.getCreateTimeStamp());
					campaignFileModel.setFileName(campaignFileEntity
							.getFileName());
					campaignFileModel.setFileSize(campaignFileEntity.getFileSize());
					campaignFileModel.setExtension(campaignFileEntity.getFileName().substring(campaignFileEntity.getFileName().lastIndexOf(".")+1, campaignFileEntity.getFileName().length()));

					campaignFileModels.add(campaignFileModel);

				}

			}
		}

		userModel.setCampaignFileModels(campaignFileModels);

		List<CampaignFileUserModel> campaignFileUserModels = new LinkedList<CampaignFileUserModel>();
		Integer newCampaignUserFilesCount = 0;


		for (CampaignFileUserEntity campaignFileUserEntity : userEntity
				.getCampaignFileUserEntities()) {
			
			if (campaignFileUserEntity.getView_status()== Status.ACTIVE
					&& campaignFileUserEntity.getStatus() == Status.ACTIVE) {
				newCampaignUserFilesCount++;
			}

			if (campaignFileUserEntity.getStatus() == Status.ACTIVE) {

				CampaignFileUserModel campaignFileUserModel = new CampaignFileUserModel();
				campaignFileUserModel
						.setCampaignFileUserId(campaignFileUserEntity
								.getCampaignFileUserId());
				campaignFileUserModel.setCreateTimeStamp(campaignFileUserEntity
						.getCreateTimeStamp());
				
				campaignFileUserModel.setFileName(campaignFileUserEntity
						.getFileName());
				campaignFileUserModel.setFileSize(campaignFileUserEntity.getFileSize());
				campaignFileUserModel.setExtension(campaignFileUserEntity.getFileName().substring(campaignFileUserEntity.getFileName().lastIndexOf(".")+1, campaignFileUserEntity.getFileName().length()));


				campaignFileUserModels.add(campaignFileUserModel);

			}
		}

		userModel.setCampaignFileUserModels(campaignFileUserModels);
		userModel.setCampaignFilesCount(newCampaignFilesCount);
		userModel.setCampaignFilesUserCount(newCampaignUserFilesCount);
		userModel.setUserUploadedFilesCount(newUserUploadedFilesCount);

		List<CampaignModel> campaignModels = new LinkedList<CampaignModel>();

		for (RoleCampaignEntity roleCampaignEntity : userEntity.getRoleEntity()
				.getRoleCampaignEntities()) {

			CampaignEntity campaignEntity = roleCampaignEntity
					.getCampaignEntity();

			if (campaignEntity.getState() == State.ACTIVE) {

				CampaignModel campaignModel = new CampaignModel();
				campaignModel.setCampaignId(campaignEntity.getCampaignId());
				campaignModel.setCampaignName(campaignEntity.getCampaignName());
				campaignModel.setCampaignDescription(campaignEntity
						.getCampaignDescription());
				campaignModel.setCampaignLogo(campaignEntity.getCampaignLogo());

				campaignModels.add(campaignModel);

			}

		}

		userModel.setCampaignModels(campaignModels);

		return userModel;

	}
	
	/******************************** For Admin Profile Update ****************************************/
	
	public UserEntity updateAdminModelToEntity(UserModel m, UserEntity userEntity) {

		userEntity.setEmployeeName(m.getEmployeeName());
		userEntity.setEmployeeNumber(m.getEmployeeNumber());
		userEntity.setUserName(m.getUserName());
		userEntity.setPassword(m.getPassword());
		userEntity.setPrimaryTelephoneNumber(m.getPrimaryTelephoneNumber());
		userEntity.setEmailAddress(m.getEmailAddress());
		userEntity.setStatus(Status.ACTIVE);

		/*UserEntity userEntity2 = new UserEntity();
		userEntity2.setUserId(m.getUserParentId());
		userEntity.setUserEntity(userEntity2);

		SubRoleLevelEntity subRoleLevelEntity = new SubRoleLevelEntity();
		subRoleLevelEntity.setSubRoleLevelId(m.getSubRoleLevelId());
		userEntity.setSubRoleLevelEntity(subRoleLevelEntity);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(m.getRoleId());
		userEntity.setRoleEntity(roleEntity);*/

		return userEntity;
	}

}