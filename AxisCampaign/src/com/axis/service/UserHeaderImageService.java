package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.UserHeaderImageConverter;
import com.axis.dao.UserHeaderImageDao;
import com.axis.entity.UserHeaderImageEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.UserHeaderImageModel;

@Service
@Transactional
public class UserHeaderImageService {

	public static final Logger logger = Logger
			.getLogger(UserHeaderImageService.class);

	@Autowired
	private UserHeaderImageConverter userHeaderImageConverter;

	@Autowired
	private UserHeaderImageDao userHeaderImageDao;

	@Autowired
	private MessageUtil messageUtil;

	public void insertUserHeaderImage(UserHeaderImageModel userHeaderImageModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("insertUserHeaderImage-Start");
		}

		userHeaderImageDao.create(userHeaderImageConverter
				.modelToEntity(userHeaderImageModel));

		if (logger.isDebugEnabled()) {
			logger.debug("insertUserHeaderImage-End");
		}

	}

	public void updateUserHeaderImage(UserHeaderImageModel userHeaderImageModel)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("updateUserHeaderImage-Start");
		}

		UserHeaderImageEntity userHeaderImageEntity = userHeaderImageDao
				.find(userHeaderImageModel.getUserHeaderImageId());

		if (userHeaderImageEntity != null) {

			userHeaderImageDao.update(userHeaderImageConverter
					.updateModelToEntity(userHeaderImageModel,
							userHeaderImageEntity));

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("UserHeaderImage.FileID.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("updateUserHeaderImage-End");
		}

	}

	public List<UserHeaderImageModel> fetchAllUserHeaderImage()
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchAllUserHeaderImage-Start");
		}

		List<UserHeaderImageModel> userHeaderImageModels = userHeaderImageConverter
				.entityListToModelList(userHeaderImageDao
						.findAllActiveUserHeaderImage());

		if (logger.isDebugEnabled()) {
			logger.debug("fetchAllUserHeaderImage-End");
		}

		return userHeaderImageModels;

	}

	public UserHeaderImageModel fetchUserHeaderImageById(int id)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserHeaderImageById-Start");
		}

		UserHeaderImageModel userHeaderImageModel = userHeaderImageConverter
				.entityToModel(userHeaderImageDao.find(id));

		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserHeaderImageById-End");
		}

		return userHeaderImageModel;

	}

	public void deleteUserHeaderImage(UserHeaderImageModel userHeaderImageModel) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteUserHeaderImage-Start");
		}
		
		UserHeaderImageEntity userHeaderImageEntity = userHeaderImageDao
				.find(userHeaderImageModel.getUserHeaderImageId());

		if (userHeaderImageEntity != null) {

			userHeaderImageDao.update(userHeaderImageConverter
					.deleteModelToEntity(userHeaderImageModel,
							userHeaderImageEntity));

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("UserHeaderImage.FileID.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("deleteUserHeaderImage-End");
		}

	}
}
