package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.dao.UserHeaderImageDao;
import com.axis.entity.UserHeaderImageEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.UserHeaderImageModel;

@Component
public class UserHeaderImageConverter implements
		NecessaryConverter<UserHeaderImageEntity, UserHeaderImageModel> {

	private static final Logger logger = Logger
			.getLogger(UserHeaderImageConverter.class);
	
	@Autowired
	private MessageUtil messageUtil;

	@Override
	public UserHeaderImageEntity modelToEntity(UserHeaderImageModel m) {

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-Start");
		}
		
		UserHeaderImageEntity userHeaderImageEntity = new UserHeaderImageEntity();
		
		userHeaderImageEntity.setCreatedBy(m.getCreatedBy());
		userHeaderImageEntity.setCreateTimeStamp(new Date());
		userHeaderImageEntity.setImageName(m.getImageName());
		userHeaderImageEntity.setStatus(Status.ACTIVE);

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-End");
		}

		return userHeaderImageEntity;
	}

	@Override
	public UserHeaderImageEntity updateModelToEntity(UserHeaderImageModel m,
			UserHeaderImageEntity e) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity-Start");
		}
		
		e.setImageName(m.getImageName());
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		e.setStatus(Status.ACTIVE);

		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity-End");
		}

		return e;
	}

	@Override
	public UserHeaderImageModel entityToModel(UserHeaderImageEntity e)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel-Start");
		}
		
		UserHeaderImageModel userHeaderImageModel = new UserHeaderImageModel();
		
		if (e != null) {
		
		userHeaderImageModel.setCreatedBy(e.getCreatedBy());
		userHeaderImageModel.setCreateTimeStamp(e.getCreateTimeStamp());
		userHeaderImageModel.setImageName(e.getImageName());
		userHeaderImageModel.setLastUpdateTimeStamp(e.getLastUpdateTimeStamp());
		userHeaderImageModel.setUpdatedBy(e.getUpdatedBy());
		userHeaderImageModel.setUserHeaderImageId(e.getUserHeaderImageId());

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("UserHeaderImage.FileID.not.found"));
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel-End");
		}
		
		return userHeaderImageModel;
	}

	@SuppressWarnings("null")
	@Override
	public List<UserHeaderImageModel> entityListToModelList(
			List<UserHeaderImageEntity> es) throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}
		
		List<UserHeaderImageModel> userHeaderImageModels = null;
		
		if(es.size() > 0) {
			
			userHeaderImageModels = new ArrayList<UserHeaderImageModel>();
			
			for(UserHeaderImageEntity userHeaderImageEntity:es) {
				
				userHeaderImageModels.add(this.entityToModel(userHeaderImageEntity));
			}
		} else
			throw new DataNotFound(messageUtil.getBundle("UserHeaderImage.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-End");
		}

		return userHeaderImageModels;
	}

	@Override
	public List<UserHeaderImageEntity> modelListToEntityList(
			List<UserHeaderImageModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserHeaderImageEntity deleteModelToEntity(UserHeaderImageModel m,
			UserHeaderImageEntity e) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity-Start");
		}
		
		e.setStatus(Status.INACTIVE);

		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity-End");
		}

		return e;
	}

}
