package com.axis.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.EscalationMatrixConverter;
import com.axis.dao.EscalationMatrixDao;
import com.axis.entity.EscalationMatrixEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.EscalationMatrixModel;



@Component
public class EscalationBusinessDataValidation {
	
	private static final Logger logger = Logger
			.getLogger(EscalationBusinessDataValidation.class);
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private EscalationMatrixDao escalationMatrixDao;
	
	@Autowired
	private EscalationMatrixConverter escalationMatrixConverter;
	
	public void escalationMatrixCreateValidation(EscalationMatrixModel escalationMatrixModel) throws DataNotFound,
	RecordFound, FormExceptions, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		if (logger.isDebugEnabled()) {
			logger.debug("escalationMatrixCreateValidation(EscalationMatrix escalationMatrix)-Start");
		}
		
		
		if(Util.isEmpty(escalationMatrixModel.getType())) {
			exceptions.put("typeShouldNotBeNull", new DataNotFound(messageUtil.getBundle("type.should.not.be.null")));
		}
		
		if (Util.isEmpty(escalationMatrixModel.getName())) {
			exceptions.put("escalationNameNotNull", new DataNotFound(messageUtil.getBundle("escalation.name.not.null")));
		}
		
		if (Util.isEmpty(escalationMatrixModel.getContactNumber())) {
			exceptions.put("contactNumberShouldNotBeNull", new DataNotFound(messageUtil.getBundle("escalation.contactno.not.null")));
		}
		else
			if(!Util.mNoValidate(escalationMatrixModel.getContactNumber())){
				
				exceptions.put("ContactNumberNotValid", new DataNotFound(messageUtil.getBundle("ContactNumber.not.valid")));
			}
		if (Util.isEmpty(escalationMatrixModel.getEmail())) {
			exceptions.put("emailIdNotBeNull", new DataNotFound(messageUtil.getBundle("escalation.email.not.null")));
			
		}
		else
			if(!Util.emailValidate(escalationMatrixModel.getEmail())){
				
				exceptions.put("emailIdNotValid", new DataNotFound(messageUtil.getBundle("email.not.valid")));
			}
			
		
		if (escalationMatrixModel.getCampaignId() ==0){
			exceptions.put("SelectCampaignId", new DataNotFound(messageUtil.getBundle("escalation.id.not.null")));
		}
		
		
		
		
		if(escalationMatrixDao.isContactNumberExists(escalationMatrixModel.getContactNumber())){
			exceptions.put("ContactNumberAlreadyExist", new DataNotFound(messageUtil.getBundle("ContactNumber.already.exist")));
        }
        
        if(escalationMatrixDao.isEmailExists(escalationMatrixModel.getEmail())){
        	exceptions.put("EmailAlreadyExist", new DataNotFound(messageUtil.getBundle("emailId.already.exist")));
        }
        
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		/*List<EscalationMatrixEntity> escalationMatrixEntities = escalationMatrixDao.findAll();
		
		List<EscalationMatrixModel> escalationMatrixModels = escalationMatrixConverter.entityListToModelList(escalationMatrixEntities);
        */
		if (logger.isDebugEnabled()) {
			logger.debug("escalationMatrixCreateValidation(EscalationMatrix escalationMatrix)-End");
		}


}
	
	public void escalationMatrixuserUpdateValidate(EscalationMatrixModel escalationMatrixModel) throws DataNotFound, RecordFound,FormExceptions, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		if (logger.isDebugEnabled()) {
			logger.debug("escalationMatrixuserUpdateValidate(EscalationMatrix escalationMatrix)-Start");
		}
		
		if(Util.isEmpty(escalationMatrixModel.getContactNumber())){
			exceptions.put("contactNumberShouldNotBeNull", new DataNotFound(messageUtil.getBundle("escalation.contactno.not.null")));
		}
		else
		     if(!Util.mNoValidate(escalationMatrixModel.getContactNumber())){
				
		exceptions.put("ContactNumberNotValid", new DataNotFound(messageUtil.getBundle("ContactNumber.not.valid")));
		}
		if(Util.isEmpty(escalationMatrixModel.getName())){
			exceptions.put("escalationNameNotNull", new DataNotFound(messageUtil.getBundle("escalation.name.not.null")));
		}
		if (escalationMatrixModel.getCampaignId() == 0){
			exceptions.put("SelectCampaignId", new DataNotFound(messageUtil.getBundle("escalation.id.not.null")));
		}
		if(Util.isEmpty(escalationMatrixModel.getEmail()) ){
			exceptions.put("emailIdNotBeNull", new DataNotFound(messageUtil.getBundle("escalation.email.not.null")));
		}
		else
		if(!Util.emailValidate(escalationMatrixModel.getEmail())){
			
			exceptions.put("emailIdNotValid", new DataNotFound(messageUtil.getBundle("email.not.valid")));
		}
		
		if(Util.isEmpty(escalationMatrixModel.getType())) {
			exceptions.put("typeShouldNotBeNull", new DataNotFound(messageUtil.getBundle("type.should.not.be.null")));
		}
		
		if(escalationMatrixDao.findByEmailAndId(escalationMatrixModel.getEmail(), escalationMatrixModel.getId())){
			exceptions.put("EmailAlreadyExist", new DataNotFound(messageUtil.getBundle("emailId.already.exist")));
			
		}
		
		
		if(escalationMatrixDao.findByContactAndId(escalationMatrixModel.getContactNumber(), escalationMatrixModel.getId())){
			exceptions.put("ContactNumberAlreadyExist", new DataNotFound(messageUtil.getBundle("ContactNumber.already.exist")));
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		/*List<EscalationMatrixEntity> escalationMatrixEntities = escalationMatrixDao.findAll();
		
		List<EscalationMatrixModel> escalationMatrixModels = escalationMatrixConverter.entityListToModelList(escalationMatrixEntities);*/
        
		
		if (logger.isDebugEnabled()) {
			logger.debug("escalationMatrixuserUpdateValidate(EscalationMatrix escalationMatrix)-End");
		}
	}
}
