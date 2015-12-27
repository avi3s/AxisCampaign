package com.axis.converter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.axis.entity.RoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;

@Component
public class RoleLevelConverter implements NecessaryConverter<RoleLevelEntity,RoleLevelModel> {

	@Override
	public RoleLevelEntity modelToEntity(RoleLevelModel m) {
		RoleLevelEntity roleLevelEntity=new RoleLevelEntity();
		roleLevelEntity.setLevelName(m.getLevelName());
		roleLevelEntity.setStatus(Status.ACTIVE);
		roleLevelEntity.setCreateTimeStamp(new Date());
		roleLevelEntity.setCreatedBy(m.getCreatedBy());
		
		RoleLevelEntity roleLevelEntityTemp=new RoleLevelEntity();
		roleLevelEntityTemp.setRowLevelId(m.getParentRoleLevelId());
		roleLevelEntity.setRoleLevelEntity(roleLevelEntityTemp);
		
		return roleLevelEntity;
	}
	
	

	@Override
	public RoleLevelEntity updateModelToEntity(RoleLevelModel m,
			RoleLevelEntity e) {
		e.setLevelName(m.getLevelName());
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		
		RoleLevelEntity roleLevelEntityTemp=new RoleLevelEntity();
		roleLevelEntityTemp.setRowLevelId(m.getParentRoleLevelId());
		e.setRoleLevelEntity(roleLevelEntityTemp);
		
		return e;
	}
	
	
	public RoleLevelEntity deleteModelToEntity(RoleLevelModel m,
			RoleLevelEntity e) {
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		e.setStatus(Status.INACTIVE);
		
		
		return e;
	}

	@Override
	public RoleLevelModel entityToModel(RoleLevelEntity roleLevelEntity)
			throws ObjectNotFound {
		RoleLevelModel roleLevelModel=new RoleLevelModel();
		roleLevelModel.setRowLevelId(roleLevelEntity.getRowLevelId());
		roleLevelModel.setLevelName(roleLevelEntity.getLevelName());
		roleLevelModel.setParentRoleLevelId(roleLevelEntity.getRoleLevelEntity().getRowLevelId());
		return roleLevelModel;
	}

	@Override
	public List<RoleLevelModel> entityListToModelList(List<RoleLevelEntity> es)
			throws DataNotFound, ObjectNotFound {
		
		List<RoleLevelModel> roleLevelModels=new LinkedList<RoleLevelModel>();
		
		for(RoleLevelEntity temp : es){
			if(temp.getStatus()==Status.ACTIVE){
			RoleLevelModel roleLevelModel=new RoleLevelModel();
			roleLevelModel.setParentRoleLevelId(temp.getRowLevelId());
			roleLevelModel.setLevelName(temp.getLevelName());
			roleLevelModel.setParentRoleLevelIdExtra(temp.getRoleLevelEntity().getRowLevelId());
			roleLevelModel.setParentRoleLevelName(temp.getRoleLevelEntity().getLevelName());
			
			roleLevelModels.add(roleLevelModel);
			}
		}
		return roleLevelModels;
	}
	
	
	public List<RoleLevelModel> entityToModelList(List<RoleLevelEntity> es)
			throws DataNotFound, ObjectNotFound {
		
		List<RoleLevelModel> roleLevelModels=new LinkedList<RoleLevelModel>();
		
		for(RoleLevelEntity temp : es){
			if(temp.getStatus()==Status.ACTIVE){
			RoleLevelModel roleLevelModel=new RoleLevelModel();
			roleLevelModel.setRowLevelId(temp.getRowLevelId());
			roleLevelModel.setParentRoleLevelId(temp.getRowLevelId());
			roleLevelModel.setLevelName(temp.getLevelName());
			
			if(temp.getRoleLevelEntity()!=null)
				roleLevelModel.setParentRoleLevelName(temp.getRoleLevelEntity().getLevelName());
			
			roleLevelModels.add(roleLevelModel);
			}
		}
		return roleLevelModels;
	}
	
	

	@Override
	public List<RoleLevelEntity> modelListToEntityList(List<RoleLevelModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public RoleLevelEntity modelToEntity(RoleLevelModel roleLevelModel){
//		
//		RoleLevelEntity roleLevelEntity=new RoleLevelEntity();
//		roleLevelEntity.setLevelName(roleLevelModel.getLevelName());
//		roleLevelEntity.setStatus(Status.ACTIVE);
//		roleLevelEntity.setCreateTimeStamp(new Date());
//		roleLevelEntity.setCreatedBy(roleLevelModel.getCreatedBy());
//		
//		RoleLevelEntity roleLevelEntityTemp=new RoleLevelEntity();
//		roleLevelEntityTemp.setRowLevelId(roleLevelModel.getParentRoleLevelId());
//		roleLevelEntity.setRoleLevelEntity(roleLevelEntityTemp);
//		
//		return roleLevelEntity;
//	}
//
//	
	
}