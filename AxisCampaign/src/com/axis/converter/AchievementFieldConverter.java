package com.axis.converter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.dao.CampaignDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.AcheivementModel;


@Component
public class AchievementFieldConverter implements NecessaryConverter<AchievementFieldEntity, AcheivementModel> {
	@Autowired
	RoleCampaignDao roleCampaignDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	CampaignDao campaignDao;
   
	private static final Logger logger = Logger.getLogger(AchievementFieldConverter.class);
	@Override
	public AchievementFieldEntity modelToEntity(AcheivementModel acheivementModel) {
		// TODO Auto-generated method stub
		AchievementFieldEntity achievementFieldEntity=new AchievementFieldEntity();
		
		achievementFieldEntity.setFieldType(acheivementModel.getFieldType());
		achievementFieldEntity.setFiledName(acheivementModel.getFieldName());
		achievementFieldEntity.setStatus(acheivementModel.getStatus());
		achievementFieldEntity.setCreatedBy(1);
		achievementFieldEntity.setCreateTimeStamp(new Date());
		achievementFieldEntity.setLastUpdateTimeStamp(new Date());
		//achievementFieldEntity.setVersion(1L);
        RoleEntity roleEntity=roleDao.find(acheivementModel.getRoleId().intValue());
		
		CampaignEntity campaignEntity=campaignDao.find(acheivementModel.getCampaignId().intValue());
		
		RoleCampaignEntity roleCampaignEntity=roleCampaignDao.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);
		
		
		//RoleCampaignEntity roleCampaignEntity=roleCampaignDao.find(acheivementModel.getCampaignId().intValue());
		achievementFieldEntity.setRoleCampaignEntity(roleCampaignEntity);

		
		return achievementFieldEntity;
	}

	@Override
	public AchievementFieldEntity updateModelToEntity(AcheivementModel m,
			AchievementFieldEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcheivementModel entityToModel(AchievementFieldEntity achievementFieldEntity)
			throws ObjectNotFound {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Model)-Start");
		}

		AcheivementModel acheivementModel = new AcheivementModel();
		acheivementModel.setAcheivementId(achievementFieldEntity.getAchievementFieldId());
		acheivementModel.setCampaignName(achievementFieldEntity.getRoleCampaignEntity().getCampaignEntity().getCampaignName());
		acheivementModel.setFieldName(achievementFieldEntity.getFiledName());
		acheivementModel.setUpdatefieldName(achievementFieldEntity.getFiledName());
		acheivementModel.setFieldType(achievementFieldEntity.getFieldType());
		acheivementModel.setRoleName(achievementFieldEntity.getRoleCampaignEntity().getRoleEntity().getRoleName());	
		acheivementModel.setStatus(achievementFieldEntity.getStatus());
		if (logger.isDebugEnabled()) {
			logger.debug("entityToModel(Target Field Model)-End");
		}
		
		return acheivementModel;
	}

	@Override
	public List<AcheivementModel> entityListToModelList(
			List<AchievementFieldEntity> achievementFieldEntities) throws DataNotFound,
			ObjectNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-Start");
		}
		
		List<AcheivementModel> acheivementModel = null;

		if (achievementFieldEntities != null) {
			for (AchievementFieldEntity achievementFieldEntity : achievementFieldEntities) {

				if (acheivementModel == null) {
					acheivementModel = new ArrayList<AcheivementModel>();
				}

				acheivementModel.add(entityToModel(achievementFieldEntity));
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("entityListToModelList-End");
		}
		
		return acheivementModel;
	}

	@Override
	public List<AchievementFieldEntity> modelListToEntityList(
			List<AcheivementModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}
	

}