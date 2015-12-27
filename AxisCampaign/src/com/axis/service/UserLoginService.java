package com.axis.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.UserConverter;
import com.axis.dao.UserDao;
import com.axis.entity.UserEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignFileModel;
import com.axis.model.CampaignFileUserModel;
import com.axis.model.CampaignModel;
import com.axis.model.CommonModel;
import com.axis.model.NotificationModel;
import com.axis.model.UserFileUploadModel;
import com.axis.model.UserModel;
import com.axis.validation.AdminValidation;
import com.axis.validation.UserValidation;

@Service
@Transactional
public class UserLoginService {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private UserValidation userValidation;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserConverter userConverter;

	public UserModel userLoginCheck(UserModel userModel) throws DataNotFound,
			ObjectNotFound, FormExceptions {

		userValidation.validateForm(userModel);

		List<UserEntity> userEntities = userDao.userLoginCheck(userModel);

		if (userEntities.isEmpty() || userEntities == null
				|| userEntities.size() == 0) {

			System.out.println("noooo user found");
			throw new DataNotFound(
					messageUtil.getBundle("invalid.login.credentials"));
		} else {

			for (UserEntity userEntity : userEntities) {
				System.out.println("check notifications :: "
						+ userEntity.getReceivedNotificationEntities().size());

				userModel = userConverter.entityToModel(userEntity);
			}
		}

		return userModel;

	}

	public List<NotificationModel> getTopFiveNotifications(UserModel userModel) {

		List<NotificationModel> notificationModels = userModel
				.getNotificationModels();

		Collections.sort(notificationModels,
				new Comparator<NotificationModel>() {

					@Override
					public int compare(NotificationModel obj1,
							NotificationModel obj2) {
						if (obj1 != null && obj2 != null) {
							if (obj1.getCreateTimeStamp() != null
									&& obj2.getCreateTimeStamp() != null) {
								return obj1.getCreateTimeStamp().compareTo(
										obj2.getCreateTimeStamp());
							}
						}
						return 0;
					}

				});
		Collections.reverse(notificationModels);

		if (notificationModels.size() > 5)
			return notificationModels.subList(0, 5);
		else
			return notificationModels;

	}

	@SuppressWarnings("unchecked")
	public List<CommonModel> getTopFiveFiles(UserModel userModel) {

		List commonModels = new LinkedList();

		for (CampaignFileUserModel campaignFileUserModel : userModel
				.getCampaignFileUserModels()) {
			commonModels.add(campaignFileUserModel);
		}

		for (CampaignFileModel campaignFileModel : userModel
				.getCampaignFileModels()) {
			commonModels.add(campaignFileModel);
		}
		
		for (UserFileUploadModel  userFileUploadModel : userModel
                .getUserFileUploadModels()) {
            commonModels.add(userFileUploadModel);
        }

		Collections.sort(commonModels, new Comparator<CommonModel>() {

			@Override
			public int compare(CommonModel obj1, CommonModel obj2) {

				if (obj1 != null && obj2 != null) {
					if (obj1.getCreateTimeStamp() != null
							&& obj2.getCreateTimeStamp() != null) {
						return obj1.getCreateTimeStamp().compareTo(
								obj2.getCreateTimeStamp());
					}
				}
				return 0;
			}

		});

		Collections.reverse(commonModels);

		if (commonModels.size() > 5)
			return commonModels.subList(0, 5);
		else
			return commonModels;

	}

	public List<CommonModel> getAllFiles(UserModel userModel) {
		List commonModels = new LinkedList();

		for (CampaignFileUserModel campaignFileUserModel : userModel
				.getCampaignFileUserModels()) {
			commonModels.add(campaignFileUserModel);
		}

		System.out.println(" 1) Campaign User Files :: " + commonModels.size());

		for (CampaignFileModel campaignFileModel : userModel
				.getCampaignFileModels()) {
			commonModels.add(campaignFileModel);
		}
		
		for (UserFileUploadModel userFileUploadModel : userModel
                .getUserFileUploadModels()) {
            commonModels.add(userFileUploadModel);
        }

		System.out.println(" 2) Campaign Files :: " + commonModels.size());

		Collections.sort(commonModels, new Comparator<CommonModel>() {

			@Override
			public int compare(CommonModel obj1, CommonModel obj2) {

				if (obj1 != null && obj2 != null) {
					if (obj1.getCreateTimeStamp() != null
							&& obj2.getCreateTimeStamp() != null) {
						return obj1.getCreateTimeStamp().compareTo(
								obj2.getCreateTimeStamp());
					}
				}
				return 0;
			}

		});

		Collections.reverse(commonModels);
		return commonModels;

	}

	public List<CampaignModel> getTopThreeCampaigns(UserModel userModel) {

		List<CampaignModel> campaignModels = userModel.getCampaignModels();

		Collections.sort(campaignModels, new Comparator<CampaignModel>() {

			@Override
			public int compare(CampaignModel obj1, CampaignModel obj2) {

				if (obj1 != null && obj2 != null) {
					if (obj1.getCreateTimeStamp() != null
							&& obj2.getCreateTimeStamp() != null) {
						return obj1.getCreateTimeStamp().compareTo(
								obj2.getCreateTimeStamp());
					}

				}
				return 0;

			}

		});

		Collections.reverse(campaignModels);

		if (campaignModels.size() > 3)
			return campaignModels.subList(0, 3);
		else
			return campaignModels;
	}

	public List<CampaignModel> getAllCampaigns(UserModel userModel) {

		List<CampaignModel> campaignModels = userModel.getCampaignModels();

		Collections.sort(campaignModels, new Comparator<CampaignModel>() {

			@Override
			public int compare(CampaignModel obj1, CampaignModel obj2) {

				if (obj1 != null && obj2 != null) {
					if (obj1.getCampaignName() != null
							&& obj2.getCampaignName() != null) {
						return obj1.getCampaignName().compareTo(
								obj2.getCampaignName());
					}

				}
				return 0;

			}

		});

		return campaignModels;
	}
}
