package com.axis.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.RoleLevelConverter;
import com.axis.dao.RoleLevelDao;
import com.axis.entity.RoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;

@Component
public class RoleLevelValidation {
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private RoleLevelDao roleLevelDao;
	
	@Autowired
	private RoleLevelConverter roleLevelConverter;

	public void validateForm(RoleLevelModel roleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
	
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
		
		if(roleLevelModel.getParentRoleLevelId()==0) {
			exceptions.put("ddlSelectForRoleLevelParentId", new DataNotFound(messageUtil.getBundle("ddlSelectForRoleLevelParentId")));
		}
		
		if(Util.isEmpty(roleLevelModel.getLevelName())) {
			exceptions.put("emptyFieldForLevelName", new DataNotFound(messageUtil.getBundle("emptyFieldForLevelName")));

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findAll();
		List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
	
		for(RoleLevelModel roleLevelModel2 : roleLevelModels) {
			if(roleLevelModel2.getLevelName().replaceAll("\\s+","").toLowerCase().equals(roleLevelModel.getLevelName().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateRoleLevelName", new DataNotFound(messageUtil.getBundle("duplicateRoleLevelName")));
				break;
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}
	
	public void validateFormForEdit(RoleLevelModel roleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in RoleLevelValidation validation :: "+roleLevelModel.getLevelName()+"  "+roleLevelModel.getParentRoleLevelId());
		
		if(roleLevelModel.getParentRoleLevelId()==0) {
			exceptions.put("ddlSelectForRoleLevelParentId", new DataNotFound(messageUtil.getBundle("ddlSelectForRoleLevelParentId")));
		}
		
		if(Util.isEmpty(roleLevelModel.getLevelName())) {
			exceptions.put("emptyFieldForLevelName", new DataNotFound(messageUtil.getBundle("emptyFieldForLevelName")));

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findOthers(roleLevelModel);
		List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
	
		for(RoleLevelModel roleLevelModel2 : roleLevelModels) {
			if(roleLevelModel2.getLevelName().replaceAll("\\s+","").toLowerCase().equals(roleLevelModel.getLevelName().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateRoleLevelName", new DataNotFound(messageUtil.getBundle("duplicateRoleLevelName")));
				break;
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}
	
	public void validateDataForEdit(RoleLevelEntity roleLevelEntity) throws FormExceptions {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();
		
		if(roleLevelEntity==null)
			exceptions.put("roleLevelDataNotFound", new DataNotFound(messageUtil.getBundle("roleLevelDataNotFound")));

		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);

	}
	

}
