package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.CampaignFileUserConverter;
import com.axis.dao.CampaignFileUserDao;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignFileUserModel;

@Component
public class CampaignFileUserValidation {

	private static final Logger logger = Logger
			.getLogger(CampaignFileUserValidation.class);

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignFileUserDao campaignFileUserDao;

	@Autowired
	private CampaignFileUserConverter campaignFileUserConverter;

	public void promotionalFileCreateValidate(
			CampaignFileUserModel campaignFileUserModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("promotionalFileCreateValidate-Start");
		}

		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if(campaignFileUserModel.getCampaignModel() == null){
			//System.out.println("hi");
		//if (campaignFileUserModel.getCampaignModel().getCampaignId() == 0) {
			exceptions.put("Role_not_null", new DataNotFound(messageUtil.getBundle("Role.not.null")));
			//throw new DataNotFound(messageUtil.getBundle("Campaign.not.null"));
		}
		if (campaignFileUserModel.getUserModel() == null){
		//if (campaignFileUserModel.getUserModel().getUserId() == 0) {
			exceptions.put("User_not_null", new DataNotFound(messageUtil.getBundle("User.not.null")));
			//throw new DataNotFound(messageUtil.getBundle("User.not.null"));
		}

		if (Util.isEmpty(campaignFileUserModel.getFileName())) {
			exceptions.put("File_name_not_null", new DataNotFound(messageUtil.getBundle("File.Name.not.null")));
			//throw new DataNotFound(messageUtil.getBundle("File.Name.not.null"));
		} /*else {

			CampaignFileUserModel campaignFileUserModel2 = null;
			try {
				 campaignFileUserModel2 = campaignFileUserConverter
						.entityToModel(campaignFileUserDao
								.findAllActiveCampaignFileUserByFileName(campaignFileUserModel
										.getFileName()));

			} catch (ObjectNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				if (campaignFileUserModel2 != null) {
					throw new RecordFound(
							messageUtil.getBundle("File.Name.Already.Exists"));
				}
			}
		}*/

		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("promotionalFileCreateValidate-End");
		}
	}

	public void promotionalFileUpdateValidate(
			CampaignFileUserModel campaignFileUserModel) throws DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("promotionalFileUpdateValidate-Start");
		}

		if (campaignFileUserModel.getCampaignModel().getCampaignId() == 0) {
			throw new DataNotFound(messageUtil.getBundle("Campaign.not.null"));
		}

		if (campaignFileUserModel.getUserModel().getUserId() == 0) {
			throw new DataNotFound(messageUtil.getBundle("User.not.null"));
		}

		if (Util.isEmpty(campaignFileUserModel.getFileName())) {
			throw new DataNotFound(messageUtil.getBundle("File.Name.not.null"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("promotionalFileUpdateValidate-End");
		}
	}
}
