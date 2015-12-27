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
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementModel;



@Component
public class AcheivementDataValidation {
 
	private static final Logger logger = Logger.getLogger(AcheivementDataValidation.class);
	
	@Autowired
	private MessageUtil massageUtil;
	
	
public void AcheivementCreateValidate(AcheivementModel acheivementModel) throws DataNotFound, RecordFound{
		
		if(logger.isDebugEnabled()){
			logger.debug("acheivementValidate-Start");
		}
		System.out.println("Naru Seth");
		
		System.out.println(acheivementModel);
		

		if(acheivementModel.getRoleId()==null){
			throw new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null"));
		}
		
		
		if(acheivementModel.getCampaignId()==null){
			throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		}
		
		if(Util.isEmpty(acheivementModel.getFieldType())){
			throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null"));
		}
		
//		if(Util.isEmpty(acheivementModel.getFieldName())){
//			throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
//		}
		if(Util.isEmpty(acheivementModel.getFieldName_array())){
			throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
		}
		
	
		if(logger.isDebugEnabled()){
			logger.debug("acheivementValidation-Ends");
		}
		
	}

public void validateFormAcheivement(AcheivementModel acheivementModel) throws FormExceptions, DataNotFound {
	
	Map<String,Exception> exceptions = new HashMap<String,Exception>();

	
	//System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
	
	if(acheivementModel.getRoleId()==null){
		exceptions.put("ddlselectroloeId", new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null")));
		
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null"));
	}
	
	
	if(acheivementModel.getCampaignId()==null){
		exceptions.put("ddlselectcampaignId", new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	
	if(Util.isEmpty(acheivementModel.getFieldType())){
		exceptions.put("ddlselectfieldType", new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null"));
	}
	
//	if(Util.isEmpty(acheivementModel.getFieldName())){
//		throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
//	}
	if(Util.isEmpty(acheivementModel.getFieldName_array())){
		exceptions.put("ddlselectfieldnamearray", new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null"));
	}
	
	if(!acheivementModel.getFieldName().equals("NOT")){
		if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
			exceptions.put("ddlselectfieldnamearray1", new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		}
		}
	if(!acheivementModel.getUpdatefieldName().equals("NOT")){
	if(acheivementModel.getUpdatefieldName()==null || acheivementModel.getUpdatefieldName()==""){
		exceptions.put("ddlselectfieldnamearray2", new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	}
	if(acheivementModel.getDuplicates().equals("true")){
		//if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
			exceptions.put("ddlselectfieldnamearray8", new DataNotFound(massageUtil.getBundle("Acheivement.duplicatefieldName.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		//}
		}
	if(acheivementModel.getBlankspace().equals("true")){
		//if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
		exceptions.put("ddlselectfieldnamearray9", new DataNotFound(massageUtil.getBundle("Acheivement.blankspace.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		//}
		}
	
	
	
	
	
	
	
	
//	if(roleLevelModel.getParentRoleLevelId()==0) {
//		exceptions.put("ddlselect", new DataNotFound(messageUtil.getBundle("ddlselect")));
//	}
//	
//	if(Util.isEmpty(roleLevelModel.getLevelName())) {
//		exceptions.put("emptyfield", new DataNotFound(messageUtil.getBundle("emptyfield")));
//
//	}
	
	if(exceptions.size()>0)
		throw new FormExceptions(exceptions);
	
//	List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findAll();
//	List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
}
public void validateFormAcheivementupdate(AcheivementModel acheivementModel) throws FormExceptions, DataNotFound {
	
	Map<String,Exception> exceptions = new HashMap<String,Exception>();

	
	//System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
	
	if(acheivementModel.getRoleId()==null){
		exceptions.put("ddlselectroloeId", new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null")));
		
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null"));
	}
	
	
	if(acheivementModel.getCampaignId()==null){
		exceptions.put("ddlselectcampaignId", new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	
	if(Util.isEmpty(acheivementModel.getFieldType())){
		exceptions.put("ddlselectfieldType", new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.fieldType.not.null"));
	}
	

	
	if(!acheivementModel.getFieldName().equals("NOT")){
		if(acheivementModel.getFieldName()==null || acheivementModel.getFieldName()==""){
			exceptions.put("ddlselectfieldnamearray1", new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null")));
			//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
		}
		}
	if(!acheivementModel.getUpdatefieldName().equals("NOT")){
	if(acheivementModel.getUpdatefieldName()==null || acheivementModel.getUpdatefieldName()==""){
		exceptions.put("ddlselectfieldnamearray2", new DataNotFound(massageUtil.getBundle("Acheivement.fieldName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	}
	
	
	
	
	
	
	
	
	
	

	
	if(exceptions.size()>0)
		throw new FormExceptions(exceptions);
	
//	List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findAll();
//	List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
}



public void validateFormAcheivementExcell(String roleID, String campaignId,String name) throws FormExceptions {
	
	Map<String,Exception> exceptions = new HashMap<String,Exception>();

	
	//System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
	
	if(roleID.equals("0")||roleID.equals("")){
		exceptions.put("ddlselectroloeId", new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null")));
		
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.roleName.not.null"));
	}
	
	
	if(campaignId.equals("0")||campaignId.equals("")){
		exceptions.put("ddlselectcampaignId", new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	if(name==null||!name.contains(".xls")){
		exceptions.put("ddlselectfile", new DataNotFound(massageUtil.getBundle("Acheivement.excell.not.null")));
		//throw new DataNotFound(massageUtil.getBundle("Acheivement.campaignName.not.null"));
	}
	
	
	
	
	
	
	
	
	

	
	if(exceptions.size()>0)
		throw new FormExceptions(exceptions);
	

}




}
