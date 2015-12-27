package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.RoleConverter;
import com.axis.dao.RoleDao;
import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;
import com.axis.model.RoleModel;
import com.axis.validation.RoleValidation;

@Service
@Transactional
public class RoleService {

	private static final Logger logger = Logger.getLogger(RoleService.class);
	
	@Autowired
	private RoleConverter roleConverter;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private RoleValidation roleValidation;

	public List<RoleModel> getAllActiveRoleByStatus() throws DataNotFound,
			ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoleByStatus-Start");
		}
		List<RoleModel> roleModels=null;
		
		List<RoleEntity> roleEntities=roleDao.getAllActiveRoleByStatus();
		if (roleEntities == null || roleEntities.isEmpty() || roleEntities.size() == 0) {
			
			throw new DataNotFound(messageUtil.getBundle("roleModels.object.not.found"));		
		}
		
		else{
			 roleModels = roleConverter.entityListToModelList(roleEntities);
		
			 if (logger.isDebugEnabled()) {
					logger.debug("getAllActiveRoleByStatus-End");
				}
				
				return roleModels;
		
		}
		
		
//		if (roleEntities == null || roleEntities.isEmpty() || roleEntities.size() == 0) {
//			throw new ObjectNotFound(
//					messageUtil.getBundle("roleModels.object.not.found"));
//		}
		
		 //roleModels = roleConverter.entityListToModelList(roleEntities);

		

	}
	
	public void insertRoleData(RoleModel roleModel) throws FormExceptions, DataNotFound, ObjectNotFound{
		
		roleValidation.validateForm(roleModel);
		
		RoleEntity roleEntity=roleConverter.modelToEntity(roleModel);
		System.out.println("role Entity Converted :: "+roleEntity.getRoleName()+" "+roleEntity.getRoleLevelEntity().getRowLevelId());
		roleDao.create(roleEntity);

		
	}
	
	public RoleModel getRoleModelForEdit(RoleModel roleModel) throws DataNotFound, ObjectNotFound{
		
		RoleEntity roleEntity=roleDao.find(roleModel.getRoleId());
		System.out.println("see roleentity :::: "+roleEntity.getRoleId()+"  "+roleEntity.getRoleName()+"  "+roleEntity.getRoleLevelEntity().getRowLevelId()+" "+roleEntity.getRoleLevelEntity().getLevelName());
		roleModel=roleConverter.entityToModel(roleEntity);
		System.out.println("see rolemodel :::: "+roleModel.getRole_name()+" "+roleModel.getRole_level_name()+" "+roleModel.getRoleLevelId());

		return roleModel;
	
	}
	
	public void editRoleData(RoleModel roleModel) throws FormExceptions, DataNotFound, ObjectNotFound{
		
		roleValidation.validateFormForEdit(roleModel);

		RoleEntity roleEntity=roleDao.find(roleModel.getRoleId());


		roleEntity=roleConverter.updateModelToEntity(roleModel,roleEntity);

		roleDao.update(roleEntity);
		
	}
	
	public void deleteRoleData(RoleModel roleModel){
		RoleEntity roleEntity=roleDao.find(roleModel.getRoleId());
//		System.out.println("roleLevelModel1 :: "+roleLevelModel.getRowLevelId()+" "+roleLevelModel.getLevelName()+" "+roleLevelModel.getParentRoleLevelId());
//		System.out.println("roleLevelEntity :: "+roleLevelEntity.getRowLevelId()+" "+roleLevelEntity.getLevelName()+" "+roleLevelEntity.getRoleLevelEntity().getRowLevelId());
//		System.out.println("roleLevelModel2 :: "+roleLevelModel.getRowLevelId()+" "+roleLevelModel.getLevelName()+" "+roleLevelModel.getParentRoleLevelId());

		roleEntity=roleConverter.deleteModelToEntity(roleModel,roleEntity);

		roleDao.update(roleEntity);
		
	}
	
}
