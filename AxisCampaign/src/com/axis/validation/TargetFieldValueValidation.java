package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.model.TargetFieldValueModel;

@Component
public class TargetFieldValueValidation {

	private static final Logger logger = Logger
			.getLogger(TargetFieldValueValidation.class);

	@Autowired
	private MessageUtil messageUtil;

	public void targetFieldValueCreateValidate(
			TargetFieldValueModel targetFieldValueModel) throws DataNotFound,
			FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("targetFieldValueValidate-Start");
		}

		System.out.println("roleId validate: "
				+ targetFieldValueModel.getRoleId());
		System.out.println("camp id: " + targetFieldValueModel.getCampaignId());

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (targetFieldValueModel.getRoleId() == 0) {
			exceptions.put("targetValueRoleId",
					new DataNotFound(messageUtil.getBundle("RoleId.not.null")));
		}

		if (targetFieldValueModel.getCampaignId() == 0) {
			exceptions.put("targetValueCampaignId", new DataNotFound(
					messageUtil.getBundle("Campaign.not.null")));
			// throw new
			// DataNotFound(massageUtil.getBundle("CampaignId.not.null"));
		}

		if (targetFieldValueModel.getRoleId() != 0
				&& targetFieldValueModel.getCampaignId() != 0) {

			System.out.println("Length validate: "
					+ targetFieldValueModel.getFieldValue_array());

			/*
			 * for(int i=0; i <
			 * targetFieldValueModel.getFieldName_array().length; i++){
			 * if(Util.isEmpty(targetFieldValueModel.getFieldName_array()[i])){
			 * exceptions.put("targetValue", new
			 * DataNotFound(messageUtil.getBundle("TargetName.not.match"))); } }
			 */

			if (targetFieldValueModel.getFieldValue_array() == null) {
				exceptions.put("targetValueNotFound", new DataNotFound(
						messageUtil.getBundle("TargetValue.not.found")));
			} else {

				if (targetFieldValueModel.getFieldValue_array().length == 0) {
					exceptions.put(
							"targetValue",
							new DataNotFound(messageUtil
									.getBundle("TargetValue.not.null")));
				} else {
					for (int i = 0; i < targetFieldValueModel
							.getFieldValue_array().length; i++) {
						if (Util.isEmpty(targetFieldValueModel
								.getFieldValue_array()[i])) {
							exceptions
									.put("targetValue",
											new DataNotFound(
													messageUtil
															.getBundle("TargetValue.not.match")));
						}
					}
				}
			}
		}

		System.out.println("exception size: " + exceptions.size());

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

	}

	public void targetFieldValueUpdateValidate(
			TargetFieldValueModel targetFieldValueModel) throws DataNotFound,
			FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("targetFieldValue Update Validate-Start");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (Util.isEmpty(targetFieldValueModel.getFieldValue())) {
			exceptions.put(
					"targetValue",
					new DataNotFound(messageUtil
							.getBundle("TargetValue.not.null")));
		}

		System.out.println("exception size: " + exceptions.size());

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

	}

}
