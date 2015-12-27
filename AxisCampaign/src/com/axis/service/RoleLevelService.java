package com.axis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.RoleLevelConverter;
import com.axis.dao.RoleLevelDao;
import com.axis.entity.RoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;
import com.axis.validation.RoleLevelValidation;

@Service
@Transactional
public class RoleLevelService {
	
	@Autowired
	private RoleLevelConverter roleLevelConverter;
	
	@Autowired
	private RoleLevelDao roleLevelDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private RoleLevelValidation roleLevelValidation;
	
	public void insertRoleLevelData(RoleLevelModel roleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound{
		
		roleLevelValidation.validateForm(roleLevelModel);
		
		RoleLevelEntity roleLevelEntity=roleLevelConverter.modelToEntity(roleLevelModel);
		System.out.println("*** Entity Converted :: "+roleLevelEntity.getLevelName()+"  "+roleLevelEntity.getRoleLevelEntity().getRowLevelId()+" "+roleLevelEntity.getRoleLevelEntity().getLevelName());
		roleLevelDao.create(roleLevelEntity);

		
	}
	
	public void editRoleLevelData(RoleLevelModel roleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound{
		
		roleLevelValidation.validateFormForEdit(roleLevelModel);

		RoleLevelEntity roleLevelEntity=roleLevelDao.find(roleLevelModel.getRowLevelId());
		
		roleLevelEntity=roleLevelConverter.updateModelToEntity(roleLevelModel,roleLevelEntity);

		roleLevelDao.update(roleLevelEntity);
		
	}
	
	public void deleteRoleLevelData(RoleLevelModel roleLevelModel){
		RoleLevelEntity roleLevelEntity=roleLevelDao.find(roleLevelModel.getRowLevelId());
	
		roleLevelEntity=roleLevelConverter.deleteModelToEntity(roleLevelModel,roleLevelEntity);

		roleLevelDao.update(roleLevelEntity);
		
	}
	
	public List<RoleLevelModel> getActiveRoleLevelListForDDL() throws DataNotFound, ObjectNotFound  {
		
		List<RoleLevelEntity> roleLevelEntities=roleLevelDao.findAll();
		
		List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityListToModelList(roleLevelEntities);
		
		if(roleLevelModels==null || roleLevelModels.isEmpty() || roleLevelModels.size()==0) {
			throw new DataNotFound(messageUtil.getBundle("roleLevelModels.object.not.found"));
		}
		
		Collections.sort(roleLevelModels, new Comparator<RoleLevelModel>(){

			@Override
			public int compare(RoleLevelModel obj1, RoleLevelModel obj2) {
				
				if(obj1 != null && obj2 != null){
					
					if(obj1.getLevelName() !=null && obj2.getLevelName() != null)
						return obj1.getLevelName().compareTo(obj2.getLevelName());

				}
				
				return 0;
			}
			
		});
		
		return roleLevelModels;
	
	}
	
	public List<RoleLevelModel> getActiveRoleLevelListForTableView() throws DataNotFound, ObjectNotFound{
		
		List<RoleLevelEntity> roleLevelEntities=roleLevelDao.getActiveRoleLevelListForTableView();
		
		if(roleLevelEntities==null || roleLevelEntities.isEmpty() || roleLevelEntities.size()==0){
			throw new DataNotFound(messageUtil.getBundle("roleLevelModels.object.not.found"));
		}
		
		List<RoleLevelModel> roleLevelModels=roleLevelConverter.entityToModelList(roleLevelEntities);

		return roleLevelModels;
	
	}
	
	public RoleLevelModel getRoleLevelModelForEdit(int rowLevelId) throws FormExceptions, ObjectNotFound {
		
		RoleLevelEntity roleLevelEntity=roleLevelDao.find(rowLevelId);
		roleLevelValidation.validateDataForEdit(roleLevelEntity);
		
		RoleLevelModel roleLevelModel=roleLevelConverter.entityToModel(roleLevelEntity);
		System.out.println("see rolemodel :::: "+roleLevelModel.getLevelName()+" "+roleLevelModel.getRowLevelId()+" "+roleLevelModel.getParentRoleLevelId());

		return roleLevelModel;
	
	}


}
