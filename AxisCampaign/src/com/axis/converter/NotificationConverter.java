package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.dao.NotificationDao;
import com.axis.dao.UserDao;
import com.axis.entity.NotificationEntity;
import com.axis.entity.UserEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.NotificationModel;
import com.axis.model.UserModel;
import com.axis.service.NotificationService;

@Component
public class NotificationConverter implements
		NecessaryConverter<NotificationEntity, NotificationModel> {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private UserDao userDao;

	private static final Logger logger = Logger
			.getLogger(NotificationConverter.class);

	/*@Override
	public NotificationEntity modelToEntity(NotificationModel m) {

		NotificationEntity notificationEntity = new NotificationEntity();

		if (m.getReceivedUserId() != 0) {

			UserEntity recievedUserEntity = userDao.find(m.getReceivedUserId());
			notificationEntity.setReceivedUserId(recievedUserEntity);
		}
		if (m.getSentUserId() != 0) {
			UserEntity sentUserEntity = userDao.find(m.getSentUserId());
			notificationEntity.setSentUserId(sentUserEntity);
		}

		notificationEntity.setCreateTimeStamp(new Date());
		notificationEntity.setCreatedBy(m.getCreatedBy());
		notificationEntity.setMessage(m.getMessage());
		notificationEntity.setParentId(m.getParentId());
		notificationEntity.setSubject(m.getSubject());
		notificationEntity.setLastUpdateTimeStamp(m.getLastUpdateTimeStamp());
		notificationEntity.setSentStatus(m.getSentStatus());
		notificationEntity.setViewStatus(m.getViewStatus());
		if (m.getRoleId() != 0) {
			notificationEntity.setRoleId(m.getRoleId());
		}
		notificationEntity.setStatus(m.getStatus());

		return notificationEntity;

	}

	@Override
	public NotificationEntity updateModelToEntity(NotificationModel m,
			NotificationEntity e) {

		UserEntity recievedUserEntity = new UserEntity();
		UserEntity sentUserEntity = new UserEntity();

		recievedUserEntity.setUserId(m.getReceivedUserId());
		sentUserEntity.setUserId(m.getSentUserId());

		e.setMessage(m.getMessage());
		e.setParentId(m.getParentId());
		e.setSubject(m.getSubject());
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		e.setSentStatus(m.getSentStatus());
		e.setReceiveStatus(m.getReceiveStatus());
		e.setReceivedUserId(recievedUserEntity);
		e.setViewStatus(m.getViewStatus());
		e.setStatus(m.getStatus());
		e.setSentUserId(sentUserEntity);
		e.setRoleId(m.getRoleId());

		return e;
	}

	@Override
	public NotificationModel entityToModel(NotificationEntity e)
			throws ObjectNotFound {

		NotificationModel notificationModel = new NotificationModel();

		notificationModel.setCreatedBy(e.getCreatedBy());
		notificationModel.setCreateTimeStamp(e.getCreateTimeStamp());
		notificationModel.setId(e.getNotificationId());
		notificationModel.setLastUpdateTimeStamp(e.getLastUpdateTimeStamp());
		notificationModel.setMessage(e.getMessage());
		notificationModel.setParentId(e.getParentId());
		notificationModel.setReceivedUserId(e.getReceivedUserId().getUserId());
		notificationModel.setReceiveStatus(e.getReceiveStatus());
		notificationModel.setSentStatus(e.getSentStatus());
		notificationModel.setSentUserId(e.getSentUserId().getUserId());
		notificationModel.setSubject(e.getSubject());
		notificationModel.setUpdatedBy(e.getUpdatedBy());
		// notificationModel.setVersion(e.getVersion());
		notificationModel.setViewStatus(e.getViewStatus());
		notificationModel.setStatus(e.getStatus());
		notificationModel.setRoleId(e.getRoleId());
		notificationModel.setReceivedUserName(e.getReceivedUserId()
				.getEmployeeName());
		notificationModel.setSentUserName(e.getSentUserId().getEmployeeName());

		return notificationModel;

	}

	@Override
	public List<NotificationModel> entityListToModelList(
			List<NotificationEntity> es) throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-Start");
		}

		List<NotificationModel> notificationModels = null;

		if (es.size() > 0) {
			for (NotificationEntity notificationEntity : es) {

				if (notificationModels == null) {
					notificationModels = new ArrayList<>();
				}

				notificationModels.add(entityToModel(notificationEntity));

			}
		} else {
			throw new DataNotFound(
					messageUtil.getBundle("Notification.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-End");
		}

		return notificationModels;
	}

	@Override
	public List<NotificationEntity> modelListToEntityList(
			List<NotificationModel> ms) {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-Start");
		}

		List<NotificationEntity> notificationEntities = null;

		if (ms != null) {
			for (NotificationModel notificationModel : ms) {

				if (notificationEntities == null) {
					notificationEntities = new ArrayList<>();
				}

				notificationEntities.add(modelToEntity(notificationModel));

			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-End");
		}

		return notificationEntities;
	}*/
	
	@Override
	public NotificationEntity modelToEntity(NotificationModel m) {

		NotificationEntity notificationEntity = new NotificationEntity();

		if (m.getReceivedUserId() != 0) {

			UserEntity recievedUserEntity = userDao.find(m.getReceivedUserId());
			notificationEntity.setReceivedUserId(recievedUserEntity);
		}
		if (m.getSentUserId() != 0) {
			UserEntity sentUserEntity = userDao.find(m.getSentUserId());
			notificationEntity.setSentUserId(sentUserEntity);
		}

		notificationEntity.setCreateTimeStamp(new Date());
		notificationEntity.setCreatedBy(m.getCreatedBy());
		notificationEntity.setMessage(m.getMessage());
		notificationEntity.setParentId(m.getParentId());
		notificationEntity.setSubject(m.getSubject());
		notificationEntity.setLastUpdateTimeStamp(m.getLastUpdateTimeStamp());
		notificationEntity.setSentStatus(m.getSentStatus());
		notificationEntity.setReceiveStatus(m.getReceiveStatus());
		notificationEntity.setViewStatus(m.getViewStatus());
		if (m.getRoleId() != 0) {
			notificationEntity.setRoleId(m.getRoleId());
		}
		notificationEntity.setStatus(m.getStatus());

		return notificationEntity;

	}

	@Override
	public NotificationEntity updateModelToEntity(NotificationModel m,
			NotificationEntity e) {

		UserEntity recievedUserEntity = new UserEntity();
		UserEntity sentUserEntity = new UserEntity();

		recievedUserEntity.setUserId(m.getReceivedUserId());
		sentUserEntity.setUserId(m.getSentUserId());

		e.setMessage(m.getMessage());
		e.setParentId(m.getParentId());
		e.setSubject(m.getSubject());
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		e.setSentStatus(m.getSentStatus());
		e.setReceiveStatus(m.getReceiveStatus());
		e.setReceivedUserId(recievedUserEntity);
		e.setViewStatus(m.getViewStatus());
		e.setStatus(m.getStatus());
		e.setSentUserId(sentUserEntity);
		e.setRoleId(m.getRoleId());

		return e;
	}

	@Override
	public NotificationModel entityToModel(NotificationEntity e)
			throws ObjectNotFound {

		NotificationModel notificationModel = new NotificationModel();

		notificationModel.setCreatedBy(e.getCreatedBy());
		notificationModel.setCreateTimeStamp(e.getCreateTimeStamp());
		notificationModel.setId(e.getNotificationId());
		notificationModel.setLastUpdateTimeStamp(e.getLastUpdateTimeStamp());
		notificationModel.setMessage(e.getMessage());
		notificationModel.setParentId(e.getParentId());
		notificationModel.setReceivedUserId(e.getReceivedUserId().getUserId());
		notificationModel.setReceiveStatus(e.getReceiveStatus());
		notificationModel.setSentStatus(e.getSentStatus());
		notificationModel.setSentUserId(e.getSentUserId().getUserId());
		notificationModel.setSubject(e.getSubject());
		notificationModel.setUpdatedBy(e.getUpdatedBy());
		// notificationModel.setVersion(e.getVersion());
		notificationModel.setViewStatus(e.getViewStatus());
		notificationModel.setStatus(e.getStatus());
		notificationModel.setRoleId(e.getRoleId());
		notificationModel.setReceivedUserName(e.getReceivedUserId()
				.getEmployeeName());
		notificationModel.setSentUserName(e.getSentUserId().getEmployeeName());

		return notificationModel;

	}

	@Override
	public List<NotificationModel> entityListToModelList(
			List<NotificationEntity> es) throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-Start");
		}

		List<NotificationModel> notificationModels = null;

		if (es.size() > 0) {
			for (NotificationEntity notificationEntity : es) {

				if (notificationModels == null) {
					notificationModels = new ArrayList<>();
				}

				notificationModels.add(entityToModel(notificationEntity));

			}
		} else {
			throw new DataNotFound(
					messageUtil.getBundle("Notification.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-End");
		}

		return notificationModels;
	}

	@Override
	public List<NotificationEntity> modelListToEntityList(
			List<NotificationModel> ms) {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-Start");
		}

		List<NotificationEntity> notificationEntities = null;

		if (ms != null) {
			for (NotificationModel notificationModel : ms) {

				if (notificationEntities == null) {
					notificationEntities = new ArrayList<>();
				}

				notificationEntities.add(modelToEntity(notificationModel));

			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModelListAdminNotification-End");
		}

		return notificationEntities;
	}
}
