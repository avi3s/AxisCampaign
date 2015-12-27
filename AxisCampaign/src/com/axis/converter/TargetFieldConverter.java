package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.dao.RoleCampaignDao;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.TargetFieldEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.TargetFieldModel;

@Component
public class TargetFieldConverter implements NecessaryConverter<TargetFieldEntity, TargetFieldModel> {

	private static final Logger logger = Logger.getLogger(TargetFieldConverter.class);
	
	@Autowired
	private RoleCampaignDao roleCampaignDao;
	
	@Override
	public TargetFieldEntity modelToEntity(TargetFieldModel m) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity(Target Field Model)-Start");
		}
		
		TargetFieldEntity targetField = new TargetFieldEntity();
		
		//RoleCampaignEntity roleCampaign = roleCampaignDao.find(m.getCampaignId());	
		//To fetch RoleCampaign id from DB
	    RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(m.getRoleId(), m.getCampaignId());
			
		
		targetField.setFieldType(m.getFieldType());
		targetField.setFiledName(m.getFieldName());
		targetField.setStatus(Status.ACTIVE);
		targetField.setCreateTimeStamp(new Date());
		targetField.setCreatedBy(m.getCreatedBy());
		targetField.setUpdatedBy(m.getUpdatedBy());
		targetField.setLastUpdateTimeStamp(new Date());
		targetField.setRoleCampaignEntity(roleCampaign);	
		
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity(Target Field Model)-End");
		}
		
		return targetField;
	}

	@Override
	public TargetFieldEntity updateModelToEntity(TargetFieldModel m,
			TargetFieldEntity e) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity(Target Field Model)-Start");
		}
		
		//RoleCampaignEntity roleCampaign = roleCampaignDao.find(m.getCampaignId());			
		
		e.setFieldType(m.getFieldType());
		e.setFiledName(m.getFieldName());
		e.setLastUpdateTimeStamp(new Date());
		//e.setRoleCampaignEntity(roleCampaign);
		
		if(e.getStatus() == Status.INACTIVE){
			e.setStatus(Status.INACTIVE);
			
		}
		else{
			e.setStatus(Status.ACTIVE);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity(Target Field Model)-End");
		}
		
		return e;
	}

	@Override
	public TargetFieldModel entityToModel(TargetFieldEntity e)
			throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Model)-Start");
		}

		TargetFieldModel targetFieldModel = new TargetFieldModel();
		
		targetFieldModel.setTargetFieldId(e.getTargetFieldId());
		targetFieldModel.setRoleId(e.getRoleCampaignEntity().getRoleEntity().getRoleId());
		targetFieldModel.setCampaignId(e.getRoleCampaignEntity().getCampaignEntity().getCampaignId());
		targetFieldModel.setRoleName(e.getRoleCampaignEntity().getRoleEntity().getRoleName());
		targetFieldModel.setCampaignName(e.getRoleCampaignEntity().getCampaignEntity().getCampaignName());
		targetFieldModel.setFieldName(e.getFiledName());
		targetFieldModel.setFieldType(e.getFieldType());
		targetFieldModel.setStatus(e.getStatus());		
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Model)-End");
		}
		
		return targetFieldModel;
	}

	@Override
	public List<TargetFieldModel> entityListToModelList(
			List<TargetFieldEntity> targetField) throws DataNotFound, ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}
		
		List<TargetFieldModel> targetFieldModel = null;

		if (targetField != null) {
			for (TargetFieldEntity tf : targetField) {

				if (targetFieldModel == null) {
					targetFieldModel = new ArrayList<TargetFieldModel>();
				}

				targetFieldModel.add(entityToModel(tf));
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-End");
		}
		
		return targetFieldModel;
	}

	@Override
	public List<TargetFieldEntity> modelListToEntityList(
			List<TargetFieldModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}



}