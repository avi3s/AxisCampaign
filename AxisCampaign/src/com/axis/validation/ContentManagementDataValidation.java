package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.dao.ContentManagementDao;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.RecordFound;
import com.axis.model.ContentManagementDetailsModel;



@Component
public class ContentManagementDataValidation {
	
	private static final Logger logger = Logger.getLogger(ContentManagementDataValidation.class);
	
	@Autowired
	private MessageUtil massageUtil;
	
	@Autowired
	private ContentManagementDao cmsDao; 
	
	/**
	 * This method is use for ContentManagementModel data validation purpose
	 * @param ContentManagement model data 
	 * @throws RecordFound 
	 * @throws FormExceptions 
	 * */
	public void CmsCreateValidate(ContentManagementDetailsModel contentManagementModel) throws FormExceptions,DataNotFound, RecordFound{
		
		if(logger.isDebugEnabled()){
			logger.debug("userValidate-End");
		}
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if(Util.isEmpty(contentManagementModel.getPageName())){
			exceptions.put("cmsPageNotNull", new DataNotFound(massageUtil.getBundle("cms.page_name.not.null")));
		}
		
		
		if(Util.isEmpty(contentManagementModel.getPath())){
			exceptions.put("cmsPathNotNull",new DataNotFound(massageUtil.getBundle("cms.path.not.null")));
		}
		
		if(Util.isEmpty(contentManagementModel.getPageContent())){
			exceptions.put("cmsContentNotNull", new DataNotFound(massageUtil.getBundle("cms.content.not.null")));
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
	
		if(logger.isDebugEnabled()){
			logger.debug("cmsValidation-Ends");
		}
		
	}

	public void cmsUpdateValidate(ContentManagementDetailsModel cmsModel) {
		// TODO Auto-generated method stub
		
	}

}
