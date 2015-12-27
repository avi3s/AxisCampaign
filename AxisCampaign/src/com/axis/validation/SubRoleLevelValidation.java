package com.axis.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.SubRoleLevelConverter;
import com.axis.dao.SubRoleLevelDao;
import com.axis.entity.SubRoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.SubRoleLevelModel;

@Component
public class SubRoleLevelValidation {
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private SubRoleLevelDao subRoleLevelDao;
	
	@Autowired
	private SubRoleLevelConverter subRoleLevelConverter;
	
	
	public void validateForm(SubRoleLevelModel subRoleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in SubRoleLevelValidation validation :: "+subRoleLevelModel.getDescription()+"  "+subRoleLevelModel.getUniqueId()+" "+subRoleLevelModel.getRoleLevelId()+"  "+subRoleLevelModel.getSubRoleLevelParentId());
		
		if(subRoleLevelModel.getRoleLevelId()==0) {
			exceptions.put("ddlselectForRoleLevel", new DataNotFound(messageUtil.getBundle("ddlselectForRoleLevel")));
		}
		
		if(subRoleLevelModel.getSubRoleLevelParentId()==0) {
			exceptions.put("ddlselectForSubRoleLevelParentId", new DataNotFound(messageUtil.getBundle("ddlselectForSubRoleLevelParentId")));
		}
		
		if(Util.isEmpty(subRoleLevelModel.getUniqueId())) {
			exceptions.put("emptyfieldForSolId", new DataNotFound(messageUtil.getBundle("emptyfieldForSolId")));

		}
		
		if(Util.isEmpty(subRoleLevelModel.getDescription())) {
			exceptions.put("emptyfieldForSubRoleName", new DataNotFound(messageUtil.getBundle("emptyfieldForSubRoleName"))); //emptyfieldForSolId

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<SubRoleLevelEntity> subRoleLevelEntities=subRoleLevelDao.findAll();
		List<SubRoleLevelModel> subRoleLevelModels=subRoleLevelConverter.entityListToModelList(subRoleLevelEntities);
	
		for(SubRoleLevelModel subRoleLevelModel2 : subRoleLevelModels) {
			if(subRoleLevelModel2.getDescription().replaceAll("\\s+","").toLowerCase().equals(subRoleLevelModel.getDescription().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateSubRoleLevelName", new DataNotFound(messageUtil.getBundle("duplicateSubRoleLevelName")));
				break;
			}
		}
		
		for(SubRoleLevelModel subRoleLevelModel2 : subRoleLevelModels) {
			if(subRoleLevelModel2.getUniqueId().replaceAll("\\s+","").toLowerCase().equals(subRoleLevelModel.getUniqueId().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateSolId", new DataNotFound(messageUtil.getBundle("duplicateSolId")));
				break;
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}
	
	
	public void validateFormForEdit(SubRoleLevelModel subRoleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in SubRoleLevelValidation validation :: "+subRoleLevelModel.getDescription()+"  "+subRoleLevelModel.getUniqueId()+" "+subRoleLevelModel.getRoleLevelId()+"  "+subRoleLevelModel.getSubRoleLevelParentId());
		
		if(subRoleLevelModel.getRoleLevelId()==0) {
			exceptions.put("ddlselectForRoleLevel", new DataNotFound(messageUtil.getBundle("ddlselectForRoleLevel")));
		}
		
		if(subRoleLevelModel.getSubRoleLevelParentId()==0) {
			exceptions.put("ddlselectForSubRoleLevelParentId", new DataNotFound(messageUtil.getBundle("ddlselectForSubRoleLevelParentId")));
		}
		
		if(Util.isEmpty(subRoleLevelModel.getUniqueId())) {
			exceptions.put("emptyfieldForSolId", new DataNotFound(messageUtil.getBundle("emptyfieldForSolId")));

		}
		
		if(Util.isEmpty(subRoleLevelModel.getDescription())) {
			exceptions.put("emptyfieldForSubRoleName", new DataNotFound(messageUtil.getBundle("emptyfieldForSubRoleName"))); //emptyfieldForSolId

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<SubRoleLevelEntity> subRoleLevelEntities=subRoleLevelDao.findOthers(subRoleLevelModel);
		List<SubRoleLevelModel> subRoleLevelModels=subRoleLevelConverter.entityListToModelList(subRoleLevelEntities);
	
		for(SubRoleLevelModel subRoleLevelModel2 : subRoleLevelModels) {
			if(subRoleLevelModel2.getDescription().replaceAll("\\s+","").toLowerCase().equals(subRoleLevelModel.getDescription().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateSubRoleLevelName", new DataNotFound(messageUtil.getBundle("duplicateSubRoleLevelName")));
				break;
			}
		}
		
		for(SubRoleLevelModel subRoleLevelModel2 : subRoleLevelModels) {
			if(subRoleLevelModel2.getUniqueId().replaceAll("\\s+","").toLowerCase().equals(subRoleLevelModel.getUniqueId().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateSolId", new DataNotFound(messageUtil.getBundle("duplicateSolId")));
				break;
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}
	

}
