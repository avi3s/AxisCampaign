package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.entity.RoleEntity;
import com.axis.entity.UserFileUploadEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.UserFileUploadModel;

@Component
public class UserFileUploadConverter implements
		NecessaryConverter<UserFileUploadEntity, UserFileUploadModel> {

	private static final Logger logger = Logger
			.getLogger(UserFileUploadConverter.class);

	@Autowired
	private MessageUtil messageUtil;

	@Override
	public UserFileUploadEntity modelToEntity(UserFileUploadModel m) {

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-Start");
		}

		UserFileUploadEntity userFileUploadEntity = new UserFileUploadEntity();
		RoleEntity roleEntity = new RoleEntity();

		userFileUploadEntity.setCreatedBy(m.getCreatedBy());
		userFileUploadEntity.setCreateTimeStamp(new Date());
		userFileUploadEntity.setFileName(m.getFileName());
		if(Double.parseDouble(m.getFileSize()) < 1024.0D)
			userFileUploadEntity.setFileSize(m.getFileSize()+" bytes");
			else if(Double.parseDouble(m.getFileSize()) >=1024.0D && Double.parseDouble(m.getFileSize()) < (1024.0D*1024.0D))
				userFileUploadEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(m.getFileSize())/1024.0D))+" kb");
			else
				userFileUploadEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(m.getFileSize())/(1024.0D*1024.0D)))+" mb");		
		userFileUploadEntity.setStatus(Status.ACTIVE);

		roleEntity.setRoleId(m.getRoleModel().getRoleId());
		userFileUploadEntity.setRoleEntity(roleEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-End");
		}

		return userFileUploadEntity;
	}

	@Override
	public UserFileUploadEntity updateModelToEntity(UserFileUploadModel m,
			UserFileUploadEntity e) {

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-Start");
		}

		e.setStatus(Status.INACTIVE);

		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-Start");
		}

		return e;
	}

	@Override
	public UserFileUploadModel entityToModel(UserFileUploadEntity e)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel-Start");
		}

		UserFileUploadModel userFileUploadModel = new UserFileUploadModel();
		RoleModel roleModel = new RoleModel();

		if (e != null) {

			userFileUploadModel.setCreatedBy(e.getCreatedBy());
			userFileUploadModel.setCreateTimeStamp(e.getCreateTimeStamp());
			userFileUploadModel.setFileName(e.getFileName());
			userFileUploadModel.setFileSize(e.getFileSize());
			userFileUploadModel.setLastUpdateTimeStamp(e
					.getLastUpdateTimeStamp());
			userFileUploadModel.setUpdatedBy(e.getUpdatedBy());
			userFileUploadModel.setUserFileUploadId(e.getUserFileUploadId());

			roleModel.setRoleId(e.getRoleEntity().getRoleId());
			roleModel.setRole_name(e.getRoleEntity().getRoleName());

			userFileUploadModel.setRoleModel(roleModel);
			
		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("UserFileID.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel-End");
		}
		return userFileUploadModel;
	}

	@Override
	public List<UserFileUploadModel> entityListToModelList(
			List<UserFileUploadEntity> es) throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}

		List<UserFileUploadModel> userFileUploadModels = null;

		if (es.size() > 0) {

			userFileUploadModels = new ArrayList<UserFileUploadModel>();

			for (UserFileUploadEntity userFileUploadEntity : es) {

				userFileUploadModels.add(this
						.entityToModel(userFileUploadEntity));
			}

		} else
			throw new DataNotFound(
					messageUtil.getBundle("UserFile.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-End");
		}

		return userFileUploadModels;
	}

	@Override
	public List<UserFileUploadEntity> modelListToEntityList(
			List<UserFileUploadModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

}
