package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;

@Component
public class AchievementFieldValueConverter implements NecessaryConverter<AchievementFieldValueEntity, AcheivementFieldValueModel> {

	private static final Logger logger = Logger.getLogger(AchievementFieldConverter.class);
	
	@Override
	public AchievementFieldValueEntity modelToEntity(
			AcheivementFieldValueModel acheivementFieldValueModel) {
		// TODO Auto-generated method stub
		AchievementFieldValueEntity achievementFieldValueEntity=new AchievementFieldValueEntity();
		AchievementFieldEntity achievementFieldEntity=new AchievementFieldEntity();
		achievementFieldValueEntity.setAchievementFieldEntity(acheivementFieldValueModel.getAchievementFieldEntity());
		achievementFieldValueEntity.setCreatedBy(1);
		achievementFieldValueEntity.setCreateTimeStamp(new Date());
		achievementFieldValueEntity.setFieldValue(acheivementFieldValueModel.getFieldValue());
		achievementFieldValueEntity.setLastUpdateTimeStamp(new Date());
		achievementFieldValueEntity.setFieldLevel(acheivementFieldValueModel.getFieldLevel());
		achievementFieldValueEntity.setStatus(Status.ACTIVE);
		achievementFieldValueEntity.setUpdatedBy(1);
		//achievementFieldValueEntity.setVersion(3L);
		return achievementFieldValueEntity;
	}

	@Override
	public AchievementFieldValueEntity updateModelToEntity(
			AcheivementFieldValueModel m, AchievementFieldValueEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcheivementFieldValueModel entityToModel(
			AchievementFieldValueEntity e) throws ObjectNotFound {
		AcheivementFieldValueModel acheivementFieldValueModel=new AcheivementFieldValueModel();
		acheivementFieldValueModel.setCampaignId(Long.valueOf((e.getAchievementFieldEntity().getRoleCampaignEntity().getCampaignEntity().getCampaignId())));
		acheivementFieldValueModel.setCampaignName(e.getAchievementFieldEntity().getRoleCampaignEntity().getCampaignEntity().getCampaignName());
		acheivementFieldValueModel.setFieldName(e.getAchievementFieldEntity().getFiledName());
		acheivementFieldValueModel.setFieldValue(e.getFieldValue());
		acheivementFieldValueModel.setRoleId(Long.valueOf((e.getAchievementFieldEntity().getRoleCampaignEntity().getRoleEntity().getRoleId())));
		acheivementFieldValueModel.setRoleName(e.getAchievementFieldEntity().getRoleCampaignEntity().getRoleEntity().getRoleName());
		acheivementFieldValueModel.setAcheivementFieldValueId(Integer.valueOf((e.getAchievementFieldValueId())));
		
		// TODO Auto-generated method stub
		return acheivementFieldValueModel;
	}

	@Override
	public List<AcheivementFieldValueModel> entityListToModelList(
			List<AchievementFieldValueEntity> achievementFieldValueEntities) throws DataNotFound,
			ObjectNotFound {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}
		
		List<AcheivementFieldValueModel> acheivementFieldValueModels = null;

		if (achievementFieldValueEntities != null) {
		for (AchievementFieldValueEntity achievementFieldValueEntitieses : achievementFieldValueEntities) {

				if (acheivementFieldValueModels == null) {
					acheivementFieldValueModels = new ArrayList<AcheivementFieldValueModel>();
				}

				acheivementFieldValueModels.add(entityToModel(achievementFieldValueEntitieses));
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-End");
		}
		return acheivementFieldValueModels;
	}

	@Override
	public List<AchievementFieldValueEntity> modelListToEntityList(
			List<AcheivementFieldValueModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}


}