package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.dao.RoleCampaignDao;
import com.axis.dao.TargetFieldDao;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.TargetFieldEntity;
import com.axis.entity.TargetFieldValueEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.TargetFieldValueModel;

@Component
public class TargetFieldValueConverter implements NecessaryConverter<TargetFieldValueEntity, TargetFieldValueModel> {

	
	private static final Logger logger = Logger.getLogger(TargetFieldValueConverter.class);
	
	
	@Autowired
	private TargetFieldDao targetFieldDao;	
	
	@Autowired
	private RoleCampaignDao roleCampaignDao;
	
	@Override
	public TargetFieldValueEntity modelToEntity(TargetFieldValueModel m) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity(Target Field Value Conveter)-Start");
		}
		
		TargetFieldValueEntity targetFieldValue = new TargetFieldValueEntity();
		
		
		TargetFieldEntity targetField = targetFieldDao.find(m.getTargetId());		
		
		targetFieldValue.setFieldValue(m.getFieldValue());
		targetFieldValue.setCreatedBy(m.getCreatedBy());
		targetFieldValue.setCreateTimeStamp(new Date());
		targetFieldValue.setLastUpdateTimeStamp(new Date());
		targetFieldValue.setStatus(Status.ACTIVE);
		targetFieldValue.setUpdatedBy(m.getUpdatedBy());
		targetFieldValue.setTargetFieldEntity(targetField);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("modelToEntity(Target Field Value Conveter)-End");
		}
		
		return targetFieldValue;
	}

	@Override
	public TargetFieldValueEntity updateModelToEntity(TargetFieldValueModel m,
			TargetFieldValueEntity e) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity(Target Field Value Converter)-Start");
		}		
		
		e.setFieldValue(m.getFieldValue());
		//e.setTargetFieldEntity(targetField);		
		e.setLastUpdateTimeStamp(new Date());		
		if(e.getStatus() == Status.INACTIVE){
			e.setStatus(Status.INACTIVE);
		}
		else{
			e.setStatus(Status.ACTIVE);
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity(Target Field Value Converter)-End");
		}
		
		return e;
	}

	@Override
	public TargetFieldValueModel entityToModel(TargetFieldValueEntity e)
			throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Value Conveter)-Start");
		}
		
		TargetFieldValueModel targetFieldValueModel = new TargetFieldValueModel();
		
		targetFieldValueModel.setTargetFieldValueId(e.getTargetFieldValueId());
		targetFieldValueModel.setFieldValue(e.getFieldValue());
		targetFieldValueModel.setCampaignId(e.getTargetFieldEntity().getRoleCampaignEntity().getCampaignEntity().getCampaignId());
		targetFieldValueModel.setCampaignName(e.getTargetFieldEntity().getRoleCampaignEntity().getCampaignEntity().getCampaignName());
		targetFieldValueModel.setRoleId(e.getTargetFieldEntity().getRoleCampaignEntity().getRoleEntity().getRoleId());
		targetFieldValueModel.setRoleName(e.getTargetFieldEntity().getRoleCampaignEntity().getRoleEntity().getRoleName());
		targetFieldValueModel.setFieldName(e.getTargetFieldEntity().getFiledName());
		targetFieldValueModel.setStatus(e.getStatus());		
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Value Conveter)-End");
		}
		
		return targetFieldValueModel;
	}

	@Override
	public List<TargetFieldValueModel> entityListToModelList(
			List<TargetFieldValueEntity> es) throws DataNotFound,
			ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}
		
		List<TargetFieldValueModel> targetFieldModel = null;

		if (es != null) {
			for (TargetFieldValueEntity tf : es) {

				if (targetFieldModel == null) {
					targetFieldModel = new ArrayList<TargetFieldValueModel>();
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
	public List<TargetFieldValueEntity> modelListToEntityList(
			List<TargetFieldValueModel> ms) {
		
		return null;
	}

}