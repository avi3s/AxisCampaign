package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.entity.ContentManagementDetailsEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.ContentManagementDetailsModel;

@Component
public class ContentManagementConverter implements NecessaryConverter<ContentManagementDetailsEntity, ContentManagementDetailsModel> {
	
	private static final Logger logger = Logger.getLogger(ContentManagementConverter.class);
	
	@Autowired
	private MessageUtil messageUtil;

	@Override
	public ContentManagementDetailsEntity modelToEntity(
			ContentManagementDetailsModel m) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-Start");
		}

		ContentManagementDetailsEntity cmsEntity = new ContentManagementDetailsEntity();

		cmsEntity.setPageName(m.getPageName());
		cmsEntity.setPath(m.getPath());
		cmsEntity.setPageContent(m.getPageContent());
		Date date = new Date();
		cmsEntity.setCreateTimeStamp(date);
		cmsEntity.setStatus(Status.ACTIVE);
		cmsEntity.setCreatedBy(m.getCreatedBy());
		cmsEntity.setUpdatedBy(m.getUpdatedBy());
		cmsEntity.setLastUpdateTimeStamp(m.getCreateTimeStamp());
//		cmsEntity.setStatus(cmsModel.getStatus());
//		cmsEntity.setCreateDate(cmsModel.getCreateDate());


		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity-End");
		}

		return cmsEntity;
	}

	@Override
	public ContentManagementDetailsEntity updateModelToEntity(
			ContentManagementDetailsModel m, ContentManagementDetailsEntity e) {
		// TODO Auto-generated method stub
		return null;
		

	}

	@Override
	public ContentManagementDetailsModel entityToModel(
			ContentManagementDetailsEntity e) throws ObjectNotFound {
		
		ContentManagementDetailsModel cmsModel = new ContentManagementDetailsModel();
		
		
		cmsModel.setId(e.getContentManagementDetailsId());	
		cmsModel.setPageName(e.getPageName());
		cmsModel.setPath(e.getPath());
		cmsModel.setPageContent(e.getPageContent());
		cmsModel.setStatus(e.getStatus());
		cmsModel.setCreateTimeStamp(e.getCreateTimeStamp());
		cmsModel.setCreatedBy(e.getCreatedBy());
		cmsModel.setLastUpdateTimeStamp(e.getCreateTimeStamp());
		cmsModel.setUpdatedBy(e.getUpdatedBy());
		//cmsModel.setVersion(e.getVersion());

		return cmsModel;
	}

	@Override
	public List<ContentManagementDetailsModel> entityListToModelList(
			List<ContentManagementDetailsEntity> es) throws DataNotFound,
			ObjectNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList(List<User> usersEntity)-Start");
		}

		List<ContentManagementDetailsModel> cmsModel = null;

		if (es.size() > 0) {
			for (ContentManagementDetailsEntity u : es) {

				if (cmsModel == null) {
					cmsModel = new ArrayList<>();
				}

				cmsModel.add(entityToModel(u));
			}
		}
		else{
			throw new DataNotFound(messageUtil.getBundle("content.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList(List<User> usersEntity)-End");
		}

		return cmsModel;
	}

	@Override
	public List<ContentManagementDetailsEntity> modelListToEntityList(
			List<ContentManagementDetailsModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

	


}