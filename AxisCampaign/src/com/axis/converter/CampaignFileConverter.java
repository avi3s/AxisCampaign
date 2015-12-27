package com.axis.converter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.dao.CampaignDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.CampaignFileEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignFileModel;

@Component
public class CampaignFileConverter implements NecessaryConverter <CampaignFileEntity, CampaignFileModel> {

	
	@Autowired
	private CampaignDao campaignDao;
	
	@Override
	public CampaignFileEntity modelToEntity(CampaignFileModel m) {
		
		CampaignFileEntity campaignFileEntity = new CampaignFileEntity();

		campaignFileEntity.setCreatedBy(m.getCreatedBy());
		campaignFileEntity.setCreateTimeStamp(new Date());
		campaignFileEntity.setFileName(m.getFileName());
		campaignFileEntity.setStatus(Status.ACTIVE);
		
		return campaignFileEntity;
	}

	@Override
	public CampaignFileEntity updateModelToEntity(CampaignFileModel m,
			CampaignFileEntity e) {

		CampaignFileEntity campaignFileEntity = new CampaignFileEntity();
		
		campaignFileEntity.setUpdatedBy(m.getUpdatedBy());
		campaignFileEntity.setLastUpdateTimeStamp(new Date());
		campaignFileEntity.setFileName(m.getFileName());
		campaignFileEntity.setStatus(Status.ACTIVE);
		
		return campaignFileEntity;
	}
	
	public CampaignFileEntity updateModelToEntity1(CampaignFileModel m, int campaignId, int updatedBy) {

		CampaignFileEntity campaignFileEntity = new CampaignFileEntity();
		CampaignEntity campaignEntity = campaignDao.find(campaignId);
		
		campaignFileEntity.setUpdatedBy(updatedBy);
		campaignFileEntity.setLastUpdateTimeStamp(new Date());
		campaignFileEntity.setFileName(m.getFileName());
		campaignFileEntity.setStatus(Status.ACTIVE);
		campaignFileEntity.setCampaignEntity(campaignEntity);
		
		return campaignFileEntity;
	}

	@Override
	public CampaignFileModel entityToModel(CampaignFileEntity e)
			throws ObjectNotFound {

		CampaignFileModel campaignFileModel = new CampaignFileModel();
		
		campaignFileModel.setCampaignFileId(e.getCampaignFileId());
		campaignFileModel.setFileName(e.getFileName());

		return campaignFileModel;
	}

	@Override
	public List<CampaignFileModel> entityListToModelList(
			List<CampaignFileEntity> es) throws DataNotFound, ObjectNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CampaignFileEntity> modelListToEntityList(
			List<CampaignFileModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}


}