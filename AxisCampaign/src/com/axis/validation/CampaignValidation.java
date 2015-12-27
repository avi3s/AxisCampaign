package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.CampaignConverter;
import com.axis.dao.CampaignDao;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignModel;

@Component
public class CampaignValidation {

	private static final Logger logger = Logger
			.getLogger(CampaignValidation.class);

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private CampaignConverter campaignConverter;

	public void campaignCreateValidate(CampaignModel campaignModel)
			throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("campaignCreateValidate-Start");
		}
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		if (campaignModel.getRoleID_array() == null) {
			exceptions.put("Role_not_null", new DataNotFound(messageUtil.getBundle("Role.not.null")));
			//throw new DataNotFound(messageUtil.getBundle("Role.not.null"));
		}

		/*if (Util.isEmpty(campaignModel.getCampaignName())) {
			exceptions.put("Campaign_Name_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Name.not.null")));
			throw new DataNotFound(
					messageUtil.getBundle("Campaign.Name.not.null"));
		} else {
			campaignModel.setCampaignName(campaignModel.getCampaignName().trim());
		}*/

		if (Util.isEmpty(campaignModel.getFinancialYear())) {
			exceptions.put("Campaign_FinancialYear_not_null", new DataNotFound(messageUtil.getBundle("Campaign.FinancialYear.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.FinancialYear.not.null"));*/
		}

		if (Util.isEmpty(campaignModel.getQuarterId())) {
			exceptions.put("Campaign_QuarterId_not_null", new DataNotFound(messageUtil.getBundle("Campaign.QuarterId.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.QuarterId.not.null"));*/
		}

		if (Util.isEmpty(campaignModel.getCampaignDescription().trim())) {
			exceptions.put("Campaign_Description_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Description.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Description.not.null"));*/
		} else {
			campaignModel.setCampaignDescription(campaignModel
					.getCampaignDescription().trim());
		}

		if (Util.isEmpty(campaignModel.getCampaignLogo())) {
			exceptions.put("Campaign_Logo_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Logo.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Logo.not.null"));*/
		}
		
		if (Util.isEmpty(campaignModel.getCampaignName())) {
			exceptions.put("Campaign_Name_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Name.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Name.not.null"));*/
		} else {
			
				if(campaignDao.findAllActiveCampaignByCampaignName(campaignModel))
					exceptions.put("Campaign_Name_Already_Exists", new DataNotFound(messageUtil.getBundle("Campaign.Name.Already.Exists")));
				/*String campaignName = campaignDao.findAllActiveCampaignByCampaignName(campaignModel);
				
				campaignModel.setCampaignName(campaignModel.getCampaignName().trim());

				if (campaignModel.getCampaignName().equals(campaignName)) {
					exceptions.put("Campaign_Name_Already_Exists", new DataNotFound(messageUtil.getBundle("Campaign.Name.Already.Exists")));
					throw new RecordFound(
							messageUtil
									.getBundle("Campaign.Name.Already.Exists"));
				}*/
		}

		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("campaignCreateValidate-End");
		}
	}

	public void campaignUpdateValidate(CampaignModel campaignModel)
			throws FormExceptions  {

		if (logger.isDebugEnabled()) {
			logger.debug("campaignUpdateValidate-Start");
		}

		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		if (campaignModel.getRoleID_array() == null) {
			exceptions.put("Role_not_null", new DataNotFound(messageUtil.getBundle("Role.not.null")));
			//throw new DataNotFound(messageUtil.getBundle("Role.not.null"));
		}

		/*if (Util.isEmpty(campaignModel.getCampaignName())) {
			exceptions.put("Campaign_Name_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Name.not.null")));
			throw new DataNotFound(
					messageUtil.getBundle("Campaign.Name.not.null"));
		} else {
			campaignModel.setCampaignName(campaignModel.getCampaignName().trim());
		}*/

		if (Util.isEmpty(campaignModel.getFinancialYear())) {
			exceptions.put("Campaign_FinancialYear_not_null", new DataNotFound(messageUtil.getBundle("Campaign.FinancialYear.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.FinancialYear.not.null"));*/
		}

		if (Util.isEmpty(campaignModel.getQuarterId())) {
			exceptions.put("Campaign_QuarterId_not_null", new DataNotFound(messageUtil.getBundle("Campaign.QuarterId.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.QuarterId.not.null"));*/
		}

		if (Util.isEmpty(campaignModel.getCampaignDescription().trim())) {
			exceptions.put("Campaign_Description_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Description.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Description.not.null"));*/
		} else {
			campaignModel.setCampaignDescription(campaignModel
					.getCampaignDescription().trim());
		}

		if (Util.isEmpty(campaignModel.getCampaignLogo())) {
			exceptions.put("Campaign_Logo_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Logo.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Logo.not.null"));*/
		}
		
		if (Util.isEmpty(campaignModel.getCampaignName())) {
			exceptions.put("Campaign_Name_not_null", new DataNotFound(messageUtil.getBundle("Campaign.Name.not.null")));
			/*throw new DataNotFound(
					messageUtil.getBundle("Campaign.Name.not.null"));*/
		} else {
			
				if(campaignDao.findAllActiveCampaignByOtherCampaignName(campaignModel))
					exceptions.put("Campaign_Name_Already_Exists", new DataNotFound(messageUtil.getBundle("Campaign.Name.Already.Exists")));
				/*String campaignName = campaignDao.findAllActiveCampaignByCampaignName(campaignModel);
				
				campaignModel.setCampaignName(campaignModel.getCampaignName().trim());

				if (campaignModel.getCampaignName().equals(campaignName)) {
					exceptions.put("Campaign_Name_Already_Exists", new DataNotFound(messageUtil.getBundle("Campaign.Name.Already.Exists")));
					throw new RecordFound(
							messageUtil
									.getBundle("Campaign.Name.Already.Exists"));
				}*/
		}

		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);


		if (logger.isDebugEnabled()) {
			logger.debug("campaignUpdateValidate-End");
		}
	}
}
