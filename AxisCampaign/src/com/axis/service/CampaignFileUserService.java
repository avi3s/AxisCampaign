package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.CampaignFileUserConverter;
import com.axis.dao.CampaignFileUserDao;
import com.axis.entity.CampaignFileUserEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignFileUserModel;
import com.axis.validation.CampaignFileUserValidation;

@Service
@Transactional
public class CampaignFileUserService {

	private static final Logger logger = Logger
			.getLogger(CampaignFileUserService.class);
	
	@Autowired
	private CampaignFileUserConverter campaignFileUserConverter;
	
	@Autowired
	private CampaignFileUserDao campaignFileUserDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private CampaignFileUserValidation campaignFileUserValidation;
	
	public void insertCampaignFileUser (CampaignFileUserModel campaignFileUserModel) throws FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("insertCampaignFileUser-Start");
		}

		for(int i=0 ; i<campaignFileUserModel.getPromotionalFiles().size(); i=i+2) {
			
			campaignFileUserModel.setFileName(campaignFileUserModel.getPromotionalFiles().get(i));
			campaignFileUserModel.setFileSize(campaignFileUserModel.getPromotionalFiles().get(i+1));
			
			campaignFileUserValidation.promotionalFileCreateValidate(campaignFileUserModel);
			
			campaignFileUserDao.create(campaignFileUserConverter.modelToEntity(campaignFileUserModel));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("insertCampaignFileUser-End");
		}

	}
	
	public void updateCampaignFileUser (CampaignFileUserModel campaignFileUserModel) throws ObjectNotFound, RecordFound, DataNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFileUser-Start");
		}
		
		campaignFileUserValidation.promotionalFileUpdateValidate(campaignFileUserModel);
	
		CampaignFileUserEntity campaignFileUserEntity = campaignFileUserDao.find(campaignFileUserModel.getCampaignFileUserId());
		
		if(campaignFileUserEntity != null) {

		campaignFileUserDao.update(campaignFileUserConverter.updateModelToEntity(campaignFileUserModel, campaignFileUserEntity));
		
		} else
			throw new ObjectNotFound(messageUtil.getBundle("Campaign.File.not.found"));
			
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFileUser-End");
		}
	}
	
	public void deleteCampaignFileUser (CampaignFileUserModel campaignFileUserModel) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFileUser-Start");
		}
		
		CampaignFileUserEntity campaignFileUserEntity = campaignFileUserDao.find(campaignFileUserModel.getCampaignFileUserId());
		
		if(campaignFileUserEntity != null) {

		campaignFileUserDao.update(campaignFileUserConverter.deleteModelToEntity(campaignFileUserModel, campaignFileUserEntity));
		
		} else
			throw new ObjectNotFound(messageUtil.getBundle("Campaign.File.not.found"));
			
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFileUser-End");
		}
	}
	
	public List<CampaignFileUserModel> fetchAllCampaignFileUser () throws DataNotFound, ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchAllCampaignFileUser-Start");
		}
		
		List<CampaignFileUserModel> campaignFileUserModels = campaignFileUserConverter.entityListToModelList(campaignFileUserDao.findAllActiveCampaignFileUserByStatus());
				
		if (logger.isDebugEnabled()) {
			logger.debug("fetchAllCampaignFileUser-End");
		}
		
		return campaignFileUserModels;
	}
	
	public CampaignFileUserModel fetchCampaignFileUserId (int campaignFileUserId) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignFileUserId-Start");
		}
		
		CampaignFileUserModel campaignFileUserModel = campaignFileUserConverter.entityToModel(campaignFileUserDao.find(campaignFileUserId));
		
		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignFileUserId-End");
		}
		
		return campaignFileUserModel;
	}
}
