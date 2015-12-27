package com.axis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.model.ContentManagementDetailsModel;
import com.axis.model.NotificationModel;
import com.axis.service.NotificationService;
import com.axis.validation.NotificationValidation;
import com.axis.common.MessageUtil;
import com.axis.converter.NotificationConverter;
import com.axis.dao.NotificationDao;
import com.axis.dao.UserDao;
import com.axis.entity.ContentManagementDetailsEntity;
import com.axis.entity.NotificationEntity;
import com.axis.entity.UserEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;

@Service
@Transactional
public class NotificationService {

	private static final Logger logger = Logger
			.getLogger(NotificationService.class);
	@Autowired
	private NotificationDao notificationDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private NotificationConverter notificationConverter;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private NotificationValidation notificationValidation;

	public void insertnotificationService(NotificationModel notificationModel)
			throws DataNotFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("insertnotificationService-Start");
		}

		notificationValidation.userNotificationValidate(notificationModel);

		NotificationEntity notification = notificationConverter
				.modelToEntity(notificationModel);
		notificationDao.create(notification);

		if (logger.isDebugEnabled()) {
			logger.debug("insertnotificationService-End");
		}
	}

	/*
	 * public void updateNotificationService(NotificationModel
	 * notificationModel) throws ObjectNotFound {
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("updateNotificationService-Start"); }
	 * 
	 * NotificationEntity notification =
	 * notificationDao.find(notificationModel.getId());// session open
	 * 
	 * if (notification == null) { throw new
	 * ObjectNotFound(messageUtil.getBundle("Notification.object.not.found")); }
	 * 
	 * notificationDao.update(notificationConverter.updateModelToEntity(
	 * notificationModel, notification ));
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("updateNotificationService-End"); }
	 * 
	 * }
	 */

	/**
	 * Get All the recieved Notifications
	 * 
	 * @return Notification List
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * */
	public List<NotificationModel> getReceivedNotificationService(int id)
			throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-Start");
		}
		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		List<NotificationEntity> notifications = notificationDao
				.findAllRecievedNotificationById(id);

		if (notifications == null || notifications.isEmpty()
				|| notifications.size() == 0) {
			throw new ObjectNotFound(
					messageUtil.getBundle("Notification.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-End");
		}

		return notificationConverter.entityListToModelList(notifications);

	}

	/**
	 * Get All the recieved Notifications
	 * 
	 * @return Notification List
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * */
	public List<NotificationModel> getNotificationListbyCreateDate(Date date)
			throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-Start");
		}

		List<NotificationEntity> notifications = notificationDao
				.findAllNotificationBydate(date);

		if (notifications == null || notifications.isEmpty()
				|| notifications.size() == 0) {
			return null;
			// throw new
			// ObjectNotFound(messageUtil.getBundle("Notification.object.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-End");
		}

		return notificationConverter.entityListToModelList(notifications);

	}

	/**
	 * Get All the sent Notifications
	 * 
	 * @return Notification List
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * */
	public List<NotificationModel> getSentNotificationService(int id)
			throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-Start");
		}

		List<NotificationEntity> notifications = notificationDao
				.findAllSentNotificationById(id);

		if (notifications == null || notifications.isEmpty()
				|| notifications.size() == 0) {
			throw new ObjectNotFound(
					messageUtil.getBundle("Notification.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationService-End");
		}

		return notificationConverter.entityListToModelList(notifications);

	}

	/**
	 * 
	 * This method is use to view notification details by Id(ADMIN or OTHERS)
	 * 
	 * @param contentId
	 *            ContentManagement
	 * 
	 * @return cmsModel ContentManagement class object
	 * @throws ObjectNotFound
	 * */
	public NotificationModel viewNotification(int notificationId)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("viewNotification(int viewNotification)-Start");
		}

		NotificationEntity notificationEntity = notificationDao
				.find(notificationId);

		if (notificationEntity == null) {
			throw new ObjectNotFound(
					messageUtil.getBundle("cms.object.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewCMSDetails(int viewNotification)-End");
		}

		return notificationConverter.entityToModel(notificationEntity);

	}

	/**
	 * 
	 * This method is use to update content details (ADMIN or OTHERS)
	 * 
	 * @return cmsModel ContentManagementModel class list of object
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws RecordFound
	 * 
	 * */
	/*
	 * public void updateNotification(NotificationModel notificationModel)
	 * throws ObjectNotFound, DataNotFound, RecordFound {
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("updateContent(ContentManagementModel cmsModel)-Start"); }
	 * notificationValidation.notificationValidate(notificationModel);
	 * 
	 * UserEntity recievedUserEntity =
	 * userDao.find(notificationModel.getUserId());
	 * 
	 * NotificationEntity notificationEntity =
	 * notificationDao.find(notificationModel.getId());
	 * 
	 * notificationEntity.setLastUpdateTimeStamp(new Date());
	 * notificationEntity.setViewStatus("0");
	 * notificationEntity.setReceivedUserId(recievedUserEntity);
	 * notificationEntity.setStatus(Status.ACTIVE);
	 * notificationEntity.setSentStatus(Status.ACTIVE);
	 * notificationEntity.setMessage(notificationModel.getMessage());
	 * notificationEntity.setSubject(notificationModel.getSubject());
	 * 
	 * notificationDao.update(notificationEntity);
	 * 
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("updateContent(User userModel)-End"); }
	 * 
	 * }
	 */

	/**
	 * 
	 * This method is use to update content details (ADMIN or OTHERS)
	 * 
	 * @return cmsModel ContentManagementModel class list of object
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws RecordFound
	 * 
	 * */
	public void deleteNotification(NotificationModel notificationModel)
			throws ObjectNotFound, DataNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("updateContent(ContentManagementModel cmsModel)-Start");
		}
		NotificationEntity notificationEntity = notificationDao
				.find(notificationModel.getId());

		notificationDao.delete(notificationEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("updateContent(User userModel)-End");
		}

	}

	public Date getCreatedDatebyId(NotificationModel notificationModel)
			throws ObjectNotFound {

		NotificationEntity notificationEntity = notificationDao
				.find(notificationModel.getId());

		return notificationConverter.entityToModel(notificationEntity)
				.getCreateTimeStamp();

	}

	public void inactiveNotification(int id) {

		notificationDao.makeInactive(id);
	}

	/*	*//**
	 * Get the Notifications w.r.t ADMIN
	 * 
	 * @return Notification model
	 * @throws ObjectNotFound
	 * */
	/*
	 * 
	 * public NotificationModel getAdminNotificationServiceById(int id) throws
	 * ObjectNotFound {
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("getNotificationServiceByID-Start"); }
	 * 
	 * NotificationEntity notification = notificationDao
	 * .getAdminNotificationServiceById(id);
	 * 
	 * if(notification == null){ throw new
	 * ObjectNotFound(messageUtil.getBundle("Notification.id.not.found")); }
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("getNotificationServiceByID-End"); }
	 * 
	 * return notificationConverter.entityToModel(notification);
	 * 
	 * }
	 */

	public void setNotificationViewed(int userId) {

		List<NotificationEntity> notificationEntities = notificationDao
				.getUnseenNotification(userId);
		System.out.println("unseen notifications :: "
				+ notificationEntities.size());

		for (NotificationEntity notificationEntity : notificationEntities) {

			notificationEntity.setViewStatus("1");
			notificationDao.update(notificationEntity);

		}
	}

	public void setSingleNotificationViewed(int id) {

		NotificationEntity notificationEntity = notificationDao.find(id);

		notificationEntity.setViewStatus("1");
		notificationDao.update(notificationEntity);

	}

}
