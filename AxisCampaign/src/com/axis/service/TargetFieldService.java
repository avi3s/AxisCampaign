package com.axis.service;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.TargetFieldConverter;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.TargetFieldDao;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.TargetFieldEntity;
import com.axis.entity.TargetFieldValueEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.TargetFieldModel;
import com.axis.validation.TargetFieldValidation;

@Service
@Transactional
public class TargetFieldService {
	
	private static final Logger logger = Logger.getLogger(TargetFieldService.class);
	
	@Autowired
	private TargetFieldConverter targetFieldConveter;

	@Autowired
	private TargetFieldDao targetFieldDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	 private RoleCampaignDao roleCampaignDao;
	
	@Autowired
	private TargetFieldValidation targetFieldValidation;
	
	/**
	 * get all targets 
	 * @return
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public List<TargetFieldModel> getAllTarget() throws ObjectNotFound, DataNotFound{

		if (logger.isDebugEnabled()) {
			logger.debug("getAllTarget-Start");
		}

		List<TargetFieldEntity> targetList = targetFieldDao.findAll();
		
		if(targetList == null || targetList.isEmpty() || targetList.size() == 0){
			throw new ObjectNotFound(messageUtil.getBundle("target.object.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllTarget-End");
		}

		return targetFieldConveter.entityListToModelList(targetList);

	}
	
	/**
	 * Save target fields
	 * @param targetFieldModel
	 * @throws DataNotFound
	 * @throws FormExceptions 
	 */
	public int insertTargetService(TargetFieldModel targetFieldModel) throws DataNotFound, FormExceptions{

		if (logger.isDebugEnabled()) {
			logger.debug("insertTargetService-Start");
		}		
		
		
		String fieldName_array[];
		
	
		fieldName_array = targetFieldModel.getFieldName_array();
		System.out.println("fieldName_array length: "+fieldName_array.length);		
		
		targetFieldValidation.targetFieldCreateValidate(targetFieldModel);
		
		//convert string array to list
        List<String> tmpList = Arrays.asList(fieldName_array);
        //create a treeset with the list, which eliminates duplicates
        TreeSet<String> unique = new TreeSet<String>(tmpList);
        System.out.println("unique element size: "+ unique.size());
		
       int noOfRecords=0;
        
		if(fieldName_array.length > 0) {
			
			if(fieldName_array.length != unique.size()){
				noOfRecords = 0;
			} else{
			
				for(int i=0; i < unique.size(); i++) {				
					
					targetFieldModel.setFieldName(fieldName_array[i]);
/*					targetFieldModel.setCreatedBy(1);
					targetFieldModel.setUpdatedBy(1);*/				
					
					
					int totalTargetFields = targetFieldDao.checkDuplicateTargetName(targetFieldConveter.modelToEntity(targetFieldModel));
					System.out.println("totalTargetFields: TargetFieldService: "+ totalTargetFields);
					if(totalTargetFields == 0){
						noOfRecords++;
						targetFieldDao.create(targetFieldConveter
							.modelToEntity(targetFieldModel));
					}
				}
			}
			
			/*if(fieldName_array.length == unique.size()){
				noOfRecords = 0;
			}*/
			
		}	

		if (logger.isDebugEnabled()) {
			logger.debug("insertTargetService-End");
		}
		
		return noOfRecords;
	}
	
	
	/**
	 * get Target By ID
	 * @param id
	 * @return
	 * @throws ObjectNotFound
	 */
	public TargetFieldModel getTargetByID(int id) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getTargetByID-Start");
		}		
		
		TargetFieldEntity targetField = targetFieldDao.find(id);
		
		if(targetField == null){
			throw new ObjectNotFound(messageUtil.getBundle("target.id.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getTargetByID-End");
		}

		return targetFieldConveter.entityToModel(targetField);

	}
	
	/**
	 * 
	 * @param targetFieldModel
	 * @throws DataNotFound
	 * @throws FormExceptions 
	 */
	public int updateTargetField(TargetFieldModel targetFieldModel) throws FormExceptions, DataNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateTargetField-Start");
		}
		
		targetFieldValidation.targetFieldUpdateValidate(targetFieldModel);
		
		int updateRecord = 0;
		
		System.out.println("****** Id "+targetFieldModel.getTargetFieldId());			
		
		TargetFieldEntity targetField = targetFieldDao.find(targetFieldModel.getTargetFieldId());
		
		//To fetch RoleCampaign id from DB
		  RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(targetFieldModel.getRoleId(), targetFieldModel.getCampaignId());
		  
		  targetField.setRoleCampaignEntity(roleCampaign);
		
		  //To check duplicate Target name
		  int totalTargetFields = targetFieldDao.fetchDuplicateTargetName(targetFieldConveter.modelToEntity(targetFieldModel), targetFieldModel.getTargetFieldId());
			System.out.println("totalTargetFields: TargetFieldService: "+ totalTargetFields);
			if(totalTargetFields == 0){
				updateRecord=0;	//updated
				targetFieldDao.update(targetFieldConveter.updateModelToEntity(targetFieldModel, targetField));
			}else{
				updateRecord=1;  //Not-updated
			}
		
		//targetFieldDao.update(targetFieldConveter.modelToEntity(targetFieldModel));

		if (logger.isDebugEnabled()) {
			logger.debug("deleteTargetService-End");
		}
		return updateRecord;
	}
	
	
	/**
	 * 
	 * @param targetFieldModel
	 * @throws DataNotFound
	 */
	@SuppressWarnings("unused")
	public void deleteTargetFieldValue(TargetFieldModel targetFieldModel) throws DataNotFound{
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTargetField-Start");
		}
						
		TargetFieldEntity targetField = targetFieldDao.find(targetFieldModel.getTargetFieldId());		
		
		TargetFieldValueEntity targetFieldValue = new TargetFieldValueEntity();
		targetFieldValue.setTargetFieldValueId(targetField.getTargetFieldId());
		
		System.out.println("Target Field id for inactive: "+targetFieldValue.getTargetFieldValueId());
		
		targetFieldDao.deactivate(targetFieldValue.getTargetFieldValueId());
		
		if (targetField == null) {
			throw new DataNotFound(messageUtil.getBundle("target.id.not.found"));
		}
		targetField.setStatus(Status.INACTIVE);
		targetFieldDao.update(targetFieldConveter.updateModelToEntity(targetFieldModel, targetField));
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTargetField-End");
		}
	}

	
	
	public int checkTargetFieldNameService(int roleId, int campId, String fieldName) {
		
		int totalTargetFields = targetFieldDao.getTargetFieldName(roleId, campId, fieldName);
		
		return totalTargetFields;
	}
}
