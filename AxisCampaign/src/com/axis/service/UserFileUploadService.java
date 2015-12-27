package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.controller.UserFileUploadController;
import com.axis.converter.UserFileUploadConverter;
import com.axis.dao.UserFileUploadDao;
import com.axis.entity.UserFileUploadEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.UserFileUploadModel;

@Service
@Transactional
public class UserFileUploadService {

	public static final Logger logger = Logger.getLogger(UserFileUploadService.class);
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private UserFileUploadConverter userFileUploadConverter;
	
	@Autowired
	private UserFileUploadDao userFileUploadDao;
	
	public void insertUserFileUpload (UserFileUploadModel userFileUploadModel) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("insertUserFileUpload-Start");
		}
		
		userFileUploadDao.create(userFileUploadConverter.modelToEntity(userFileUploadModel));
		
		if (logger.isDebugEnabled()) {
			logger.debug("insertUserFileUpload-End");
		}
		
	}
	
	public void updateUserFileUpload (int userFileUploadId) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateUserFileUpload-Start");
		}
		
		UserFileUploadModel userFileUploadModel = userFileUploadConverter.entityToModel(userFileUploadDao.find(userFileUploadId));
		
		UserFileUploadEntity userFileUploadEntity = userFileUploadDao.find(userFileUploadModel.getUserFileUploadId());
		
		if (userFileUploadEntity != null) {
			
		userFileUploadDao.update(userFileUploadConverter.updateModelToEntity(userFileUploadModel, userFileUploadEntity));
		
		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("UserFileID.not.found"));
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateUserFileUpload-End");
		}
	}
	
	public List<UserFileUploadModel> fetchUserFileByUserId (int userId) throws DataNotFound, ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserFileByUserId-Start");
		}

		List<UserFileUploadModel> userFileUploadModels = userFileUploadConverter.entityListToModelList(userFileUploadDao.findAllActiveUserFile(userId));
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserFileByUserId-End");
		}

		return userFileUploadModels;
	}
	
	public UserFileUploadModel fetchUserFileByFileId(int campaign_file_id) throws ObjectNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserFileByUserId-Start");
		}

		UserFileUploadModel userFileUploadModel = userFileUploadConverter.entityToModel(userFileUploadDao.find(campaign_file_id));
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchUserFileByUserId-End");
		}

		return userFileUploadModel;
	}
}
