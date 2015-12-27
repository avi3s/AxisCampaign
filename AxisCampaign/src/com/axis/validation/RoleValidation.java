package com.axis.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.RoleConverter;
import com.axis.dao.RoleDao;
import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;


@Component
public class RoleValidation {
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleConverter roleConverter;
	
	public void validateForm(RoleModel roleModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in RoleLevelValidation validation :: ");
		
		if(roleModel.getRoleLevelId()==0) {
			exceptions.put("ddlselectForRoleLevel", new DataNotFound(messageUtil.getBundle("ddlselectForRoleLevel")));
		}
		
		if(Util.isEmpty(roleModel.getRole_name())) {
			exceptions.put("emptyfieldForRoleName", new DataNotFound(messageUtil.getBundle("emptyfieldForRoleName")));

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<RoleEntity> roleEntities=roleDao.findAll();
		List<RoleModel> roleModels=roleConverter.entityListToModelList(roleEntities);
	
		for(RoleModel roleModel2 : roleModels) {
			
			if(roleModel2.getStatus()==Status.ACTIVE) {
			if(roleModel2.getRole_name().replaceAll("\\s+","").toLowerCase().equals(roleModel.getRole_name().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateRoleName", new DataNotFound(messageUtil.getBundle("duplicateRoleName")));
				break;
			}
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}
	
	
	public void validateFormForEdit(RoleModel roleModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in RoleLevelValidation validation :: ");
		
		if(roleModel.getRoleLevelId()==0) {
			exceptions.put("ddlselectForRoleLevel", new DataNotFound(messageUtil.getBundle("ddlselectForRoleLevel")));
		}
		
		if(Util.isEmpty(roleModel.getRole_name())) {
			exceptions.put("emptyfieldForRoleName", new DataNotFound(messageUtil.getBundle("emptyfieldForRoleName")));

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
		
		List<RoleEntity> roleEntities=roleDao.findOthers(roleModel);
		List<RoleModel> roleModels=roleConverter.entityListToModelList(roleEntities);
	
		for(RoleModel roleModel2 : roleModels) {
			if(roleModel2.getRole_name().replaceAll("\\s+","").toLowerCase().equals(roleModel.getRole_name().replaceAll("\\s+","").toLowerCase())) {
				exceptions.put("duplicateRoleName", new DataNotFound(messageUtil.getBundle("duplicateRoleName")));
				break;
			}
		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	

	}

}
