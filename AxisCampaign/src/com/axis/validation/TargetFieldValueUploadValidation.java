package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.axis.common.MessageUtil;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;

@Component
public class TargetFieldValueUploadValidation {

private static final Logger logger = Logger.getLogger(TargetFieldValueUploadValidation.class);
	
	@Autowired
	private MessageUtil messageUtil;
	
	
	public void targetFieldValueUploadCreateValidate(int roleId, int campaignId, MultipartFile file) throws DataNotFound, FormExceptions{
		
		if (logger.isDebugEnabled()) {
			logger.debug("targetFieldValueUploadValidate-Start");
		}
		
		System.out.println("roleId: "+roleId+" Campaign Id: "+campaignId+" fileName: "+file);
		System.out.println("fileName: "+ file.getOriginalFilename() );
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if (roleId == 0 ) {
			exceptions.put("uploadRoleId", new DataNotFound(messageUtil.getBundle("RoleId.not.null")));
		}
		
		if (campaignId == 0 ) {
			exceptions.put("uploadCampaignId", new DataNotFound(messageUtil.getBundle("Campaign.not.null")));
		}
		
		if(file.getOriginalFilename().equals("")){
			exceptions.put("uploadFile", new DataNotFound(messageUtil.getBundle("UploadFile.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("UploadFile.not.null"));
		}		
		
		System.out.println("exception size: "+ exceptions.size());
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
	}	
	
}
