package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.TargetFieldValueConverter;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.TargetFieldValueDao;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.TargetFieldValueEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.TargetFieldValueModel;
import com.axis.validation.TargetFieldValueValidation;

@Service
@Transactional
public class TargetFieldValueService {
	
private static final Logger logger = Logger.getLogger(TargetFieldValueService.class);
	
	@Autowired
	private TargetFieldValueConverter targetFieldValueConveter;

	@Autowired
	private TargetFieldValueDao targetFieldValueDao;
	
	@Autowired
    private RoleCampaignDao roleCampaignDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	
	@Autowired
	private TargetFieldValueValidation targetFieldValueValidation;
	
	/**
	 * 
	 * @param roleid
	 * @param campid
	 * @return
	 * @throws ObjectNotFound
	 */
	public List<String> findAllFieldNames(int roleid, int campid) throws ObjectNotFound{

		  if (logger.isDebugEnabled()) {
	            logger.debug("getAllFieldNames (TargetFieldValueService)-Start");
	        }
	        
	        //To fetch RoleCampaign id from DB
	        RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(roleid, campid);
	        
	        return targetFieldValueDao.findFieldNames(roleCampaign.getRoleCampaignId());

	}
	
	
	/**
	 * 
	 * @param targetFieldValueModel
	 * @throws DataNotFound
	 * @throws FormExceptions 
	 */
	public int insertTargetFieldValueService(TargetFieldValueModel targetFieldValueModel) throws DataNotFound, FormExceptions{

		if (logger.isDebugEnabled()) {
			logger.debug("insertTargetFieldValueService-Start");
		}
		
		String fieldValue_array[];
		fieldValue_array = targetFieldValueModel.getFieldValue_array();
		//targetFieldValueModel.setFieldValue("NOT");
		/*String arr[] = {""};
		targetFieldValueModel.setFieldValue_array(arr);*/
		
		targetFieldValueValidation.targetFieldValueCreateValidate(targetFieldValueModel);
		
		
		List<Integer> fieldName_id;		
		
		//System.out.println("CamaignId: "+targetFieldValueModel.getCampId());
		
		/*String campid=targetFieldValueModel.getCampId();
		fieldName_id = targetFieldValueDao.findFieldNameId(Integer.parseInt(campid));
		fieldValue_array = targetFieldValueModel.getFieldName_array();*/
		
		
		RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(targetFieldValueModel.getRoleId(), targetFieldValueModel.getCampaignId());
		
		fieldName_id = targetFieldValueDao.findFieldNameId(roleCampaign.getRoleCampaignId());
			
		
			
		
		
		if(fieldValue_array.length > 0) {
			
			for(int i=0; i < fieldValue_array.length; i++) {				
				
				targetFieldValueModel.setFieldValue(fieldValue_array[i]);
/*				targetFieldValueModel.setCreatedBy(1);
				targetFieldValueModel.setUpdatedBy(1);*/
				targetFieldValueModel.setTargetId(fieldName_id.get(i)); 
				
				targetFieldValueDao.create(targetFieldValueConveter
						.modelToEntity(targetFieldValueModel));
			}
		}	

		if (logger.isDebugEnabled()) {
			logger.debug("insertTargetFieldValueService-End");
		}
		
		return fieldValue_array.length;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws ObjectNotFound
	 * @throws DataNotFound 
	 */
	public List<TargetFieldValueModel> getAllTargetValue() throws ObjectNotFound, DataNotFound{

		if (logger.isDebugEnabled()) {
			logger.debug("getAllTargetValue-Start");
		}

		List<TargetFieldValueEntity> targetValueList = targetFieldValueDao.findAll();
		
		if(targetValueList == null || targetValueList.isEmpty() || targetValueList.size() == 0){
			throw new ObjectNotFound(messageUtil.getBundle("targetvalue.object.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllTarget-End");
		}

		return targetFieldValueConveter.entityListToModelList(targetValueList);

	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ObjectNotFound
	 */
	public TargetFieldValueModel getTargetValueByID(int id) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getTargetValueByID-Start");
		}

		TargetFieldValueEntity targetFieldValue = targetFieldValueDao.find(id);
		
		if(targetFieldValue == null){
			throw new ObjectNotFound(messageUtil.getBundle("targetvalue.id.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getTargetByID-End");
		}

		return targetFieldValueConveter.entityToModel(targetFieldValue);

	}
	
	/**
	 * 
	 * @param targetFieldValueModel
	 * @throws DataNotFound
	 * @throws FormExceptions 
	 */	
	public void updateTargetFieldValue(TargetFieldValueModel targetFieldValueModel) throws DataNotFound, FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateTargetFieldValue-Start");
		}
		
		targetFieldValueValidation.targetFieldValueUpdateValidate(targetFieldValueModel);
		
		TargetFieldValueEntity targetFieldValue = targetFieldValueDao.find(targetFieldValueModel.getTargetFieldValueId());		
		
		if (targetFieldValue == null) {
			throw new DataNotFound(messageUtil.getBundle("targetvalue.id.not.found"));
		}	
		targetFieldValue.setStatus(Status.ACTIVE);
		targetFieldValueDao.update(targetFieldValueConveter.updateModelToEntity(targetFieldValueModel, targetFieldValue));

		if (logger.isDebugEnabled()) {
			logger.debug("updateTargetFieldValue-End");
		}
	}
	
	
	/**
	 * 
	 * @param targetFieldValueModel
	 * @throws DataNotFound
	 */	
	public void deleteTargetFieldValue(TargetFieldValueModel targetFieldValueModel) throws DataNotFound{
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTargetFieldValue-Start");
		}
		
		System.out.println("getTargetFieldValueId "+targetFieldValueModel.getTargetFieldValueId());
		
		TargetFieldValueEntity targetFieldValue = targetFieldValueDao.find(targetFieldValueModel.getTargetFieldValueId());		
		
		if (targetFieldValue == null) {
			throw new DataNotFound(messageUtil.getBundle("targetvalue.id.not.found"));
		}
		targetFieldValue.setStatus(Status.INACTIVE);
		targetFieldValueDao.update(targetFieldValueConveter.updateModelToEntity(targetFieldValueModel, targetFieldValue));
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTargetFieldValue-End");
		}
	}
}
