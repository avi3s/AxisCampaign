package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.entity.CampaignEntity;
import com.axis.entity.CampaignFileUserEntity;
import com.axis.entity.UserEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignFileUserModel;
import com.axis.model.CampaignModel;
import com.axis.model.UserModel;

@Component
public class CampaignFileUserConverter implements
		NecessaryConverter<CampaignFileUserEntity, CampaignFileUserModel> {

	@Autowired
	private MessageUtil messageUtil;

	@Override
	public CampaignFileUserEntity modelToEntity(CampaignFileUserModel m) {

		CampaignFileUserEntity campaignFileUserEntity = new CampaignFileUserEntity();
		UserEntity userEntity = new UserEntity();
		CampaignEntity campaignEntity = new CampaignEntity();

		userEntity.setUserId(m.getUserModel().getUserId());
		campaignEntity.setCampaignId(m.getCampaignModel().getCampaignId());

		campaignFileUserEntity.setCreatedBy(m.getCreatedBy());
		campaignFileUserEntity.setCreateTimeStamp(new Date());
		campaignFileUserEntity.setFileName(m.getFileName());
		campaignFileUserEntity.setStatus(Status.ACTIVE);
		campaignFileUserEntity.setUserEntity(userEntity);
		campaignFileUserEntity.setCampaignEntity(campaignEntity);
		campaignFileUserEntity.setView_status(Status.ACTIVE);
		if(Double.parseDouble(m.getFileSize()) < 1024.0D)
			campaignFileUserEntity.setFileSize(m.getFileSize()+" bytes");
			else if(Double.parseDouble(m.getFileSize()) >=1024.0D && Double.parseDouble(m.getFileSize()) < (1024.0D*1024.0D))
			campaignFileUserEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(m.getFileSize())/1024.0D))+" kb");
			else
			campaignFileUserEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(m.getFileSize())/(1024.0D*1024.0D)))+" mb");

		return campaignFileUserEntity;
	}

	@Override
	public CampaignFileUserEntity updateModelToEntity(CampaignFileUserModel m,
			CampaignFileUserEntity campaignFileUserEntity) {

		UserEntity userEntity = campaignFileUserEntity.getUserEntity();
		CampaignEntity campaignEntity = campaignFileUserEntity.getCampaignEntity();
		
		campaignFileUserEntity.setUpdatedBy(m.getUpdatedBy());
		campaignFileUserEntity.setLastUpdateTimeStamp(new Date());
		campaignFileUserEntity.setFileName(m.getFileName());
		campaignFileUserEntity.setStatus(Status.ACTIVE);
		campaignFileUserEntity.setUserEntity(userEntity);
		campaignFileUserEntity.setCampaignEntity(campaignEntity);
		
		return campaignFileUserEntity;
	}

	@Override
	public CampaignFileUserModel entityToModel(CampaignFileUserEntity e)
			throws ObjectNotFound {

		CampaignFileUserModel campaignFileUserModel = new CampaignFileUserModel();
		CampaignModel campaignModel = new CampaignModel();
		UserModel userModel = new UserModel();

		if (e != null) {

			campaignModel.setCampaignId(e.getCampaignEntity().getCampaignId());
			campaignModel.setCampaignName(e.getCampaignEntity()
					.getCampaignName());

			userModel.setUserId(e.getUserEntity().getUserId());
			userModel.setEmployeeName(e.getUserEntity().getEmployeeName());

			campaignFileUserModel.setCampaignFileUserId(e
					.getCampaignFileUserId());
			campaignFileUserModel.setCampaignModel(campaignModel);
			campaignFileUserModel.setUserModel(userModel);
			campaignFileUserModel.setCreatedBy(e.getCreatedBy());
			campaignFileUserModel.setCreateTimeStamp(e.getCreateTimeStamp());
			campaignFileUserModel.setFileName(e.getFileName());
			campaignFileUserModel.setLastUpdateTimeStamp(e
					.getLastUpdateTimeStamp());
			campaignFileUserModel.setStatus(e.getStatus());
			campaignFileUserModel.setUpdatedBy(e.getUpdatedBy());
			//campaignFileUserModel.setVersion(e.getVersion());

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("Campaign.FileID.not.found"));

		return campaignFileUserModel;
	}

	@Override
	public List<CampaignFileUserModel> entityListToModelList(
			List<CampaignFileUserEntity> es) throws DataNotFound,
			ObjectNotFound {

		List<CampaignFileUserModel> campaignFileUserModels = null;

		if (es.size() > 0) {

			campaignFileUserModels = new ArrayList<CampaignFileUserModel>();

			for (CampaignFileUserEntity campaignFileUserEntity : es) {

				campaignFileUserModels
						.add(entityToModel(campaignFileUserEntity));
			}
		} else
			throw new DataNotFound(messageUtil.getBundle("Campaign.File.not.found"));

		return campaignFileUserModels;
	}

	@Override
	public List<CampaignFileUserEntity> modelListToEntityList(
			List<CampaignFileUserModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CampaignFileUserEntity deleteModelToEntity(CampaignFileUserModel m,
			CampaignFileUserEntity campaignFileUserEntity) {

		UserEntity userEntity = campaignFileUserEntity.getUserEntity();
		CampaignEntity campaignEntity = campaignFileUserEntity.getCampaignEntity();
		
		campaignFileUserEntity.setCreatedBy(m.getCreatedBy());
		campaignFileUserEntity.setCreateTimeStamp(m.getCreateTimeStamp());
		campaignFileUserEntity.setFileName(m.getFileName());
		campaignFileUserEntity.setStatus(Status.INACTIVE);
		campaignFileUserEntity.setUserEntity(userEntity);
		campaignFileUserEntity.setCampaignEntity(campaignEntity);
		
		return campaignFileUserEntity;
	}

}