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
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;

@Component
public class AcheivementDataValueValidation {

	private static final Logger logger = Logger.getLogger(AcheivementDataValueValidation.class);

	@Autowired
	private MessageUtil massageUtil;

	public void validateFormValueAcheivement(AcheivementFieldValueModel acheivementFieldValueModel) throws FormExceptions, DataNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		//System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
		
		if(acheivementFieldValueModel.getRoleId()==null){
			exceptions.put("ddlselectroloeId", new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null")));
			
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null"));
		}
		
		
		if(acheivementFieldValueModel.getCampaignId()==null){
			exceptions.put("ddlselectcampaignId", new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		}
		
//		if(Util.isEmpty(acheivementModel.getFieldType())){
//			exceptions.put("ddlselectfieldType", new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null")));
//			//throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null"));
//		}
		
//		if(Util.isEmpty(acheivementModel.getFieldName())){
//			throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
//		}
		if(!acheivementFieldValueModel.getFieldValue().equals("NOT")){
		if(acheivementFieldValueModel.getFieldValue()==null || acheivementFieldValueModel.getFieldValue()==""){
			exceptions.put("ddlselectvalidFieldValue", new DataNotFound(massageUtil.getBundle("Acheivement.fieldValue.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		}
		}
		
		if(Util.isEmptyValue(acheivementFieldValueModel.getFieldValue_array())){
			
			System.out.println("IS EMPTY CHECK IN THE ........ ACHEIVEMENT FIELD VALUES");
			exceptions.put("ddlselectfieldnamearray", new DataNotFound(massageUtil.getBundle("AcheivementValue.acheivementFieldValue.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
		}
		
		
		if(acheivementFieldValueModel.getDuplicates().equals("true")){
			//if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
				exceptions.put("ddlselectfieldnamearray8", new DataNotFound(massageUtil.getBundle("AcheivementValue.duplicatefieldValue.not.null")));
				//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
			//}
			}
		if(acheivementFieldValueModel.getBlankspace().equals("true")){
			//if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
				exceptions.put("ddlselectfieldnamearray9", new DataNotFound(massageUtil.getBundle("AcheivementValue.blankspace.not.null")));
				//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
			//}
			}
		
		
		
		
		
		
		
		
//		if(roleLevelModel.getParentRoleLevelId()==0) {
//			exceptions.put("ddlselect", new DataNotFound(messageUtil.getBundle("ddlselect")));
//		}
	//	
//		if(Util.isEmpty(roleLevelModel.getLevelName())) {
//			exceptions.put("emptyfield", new DataNotFound(messageUtil.getBundle("emptyfield")));
	//
//		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
//		List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findAll();
//		List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
	}
	
public void AcheivementUpdateFieldValueValidate(AcheivementFieldValueModel acheivementFieldValueModel) throws DataNotFound, RecordFound{
		
		if(logger.isDebugEnabled()){
			logger.debug("acheivementUpdateValueValidate-Start");
		}
		System.out.println("Naru Seth");
		
		//System.out.println(acheivementModel);
		
		if(Util.isEmpty(acheivementFieldValueModel.getFieldValue())){
			throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null"));
		}
		
		
		
		

		
		
	
		if(logger.isDebugEnabled()){
			logger.debug("acheivementValidation-Ends");
		}
		
	}








}
