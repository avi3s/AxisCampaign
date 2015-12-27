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
import com.axis.model.TargetFieldModel;

@Component
public class TargetFieldValidation {

	private static final Logger logger = Logger
			.getLogger(TargetFieldValidation.class);

	@Autowired
	private MessageUtil messageUtil;
	
	
	public void targetFieldCreateValidate(TargetFieldModel targetFieldModel) throws DataNotFound, FormExceptions{
		
		if (logger.isDebugEnabled()) {
			logger.debug("targetFieldValidate-Start");
		}
		
		System.out.println("Length: "+targetFieldModel.getFieldName_array().length);
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if (targetFieldModel.getRoleId() == 0 ) {
			exceptions.put("roleId", new DataNotFound(messageUtil.getBundle("RoleId.not.null")));			
		}
		
		if (targetFieldModel.getCampaignId() == 0 ) {
			exceptions.put("campaignId", new DataNotFound(messageUtil.getBundle("Campaign.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("CampaignId.not.null"));
		}
		
		if(targetFieldModel.getFieldName_array().length == 0){
			exceptions.put("targetName", new DataNotFound(messageUtil.getBundle("TargetName.not.null")));
		}
		else{
			for(int i=0; i < targetFieldModel.getFieldName_array().length; i++){				
				if(Util.isEmpty(targetFieldModel.getFieldName_array()[i])){			
						exceptions.put("targetName", new DataNotFound(messageUtil.getBundle("TargetName.not.match")));			
				}
			}
		}
		
		/*for(int i=0; i < targetFieldModel.getFieldName_array().length; i++){				
			if(Util.isEmpty(targetFieldModel.getFieldName_array()[i])){			
					exceptions.put("targetName"+i, new DataNotFound(messageUtil.getBundle("TargetName.not.null")));			
			}
		}*/
		
		if (targetFieldModel.getFieldType().equals("0")) {
			exceptions.put("fieldType", new DataNotFound(messageUtil.getBundle("FieldType.not.null")));			
		}
		
		System.out.println("exception size: "+ exceptions.size());
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		
	}
	
	
	public void targetFieldUpdateValidate(TargetFieldModel targetFieldModel) throws DataNotFound, FormExceptions{
		
		if (logger.isDebugEnabled()) {
			logger.debug("targetFieldUpdateValidate-Start");
		}
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if (targetFieldModel.getRoleId() == 0 ) {
			exceptions.put("updateRoleId", new DataNotFound(messageUtil.getBundle("RoleId.not.null")));			
		}
		
		if (targetFieldModel.getCampaignId() == 0 ) {
			exceptions.put("updateCampaignId", new DataNotFound(messageUtil.getBundle("Campaign.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("CampaignId.not.null"));
		}
		
		if (targetFieldModel.getFieldType().equals("0")) {
			exceptions.put("updateFieldType", new DataNotFound(messageUtil.getBundle("FieldType.not.null")));			
		}
		
		if (Util.isEmpty(targetFieldModel.getFieldName())){
			exceptions.put("updateTargetName", new DataNotFound(messageUtil.getBundle("TargetName.not.null")));
		}
		
		System.out.println("exception size: "+ exceptions.size());
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
	}
}
