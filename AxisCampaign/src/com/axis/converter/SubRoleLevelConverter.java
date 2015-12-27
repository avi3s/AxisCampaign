package com.axis.converter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.entity.SubRoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.SubRoleLevelModel;

@Component
public class SubRoleLevelConverter implements NecessaryConverter<SubRoleLevelEntity,SubRoleLevelModel> {

	@Override
	public SubRoleLevelEntity modelToEntity(SubRoleLevelModel m) {
	SubRoleLevelEntity subRoleLevelEntity=new SubRoleLevelEntity();
	subRoleLevelEntity.setDescription(m.getDescription());
	
	RoleLevelEntity roleLevelEntity=new RoleLevelEntity();
	roleLevelEntity.setRowLevelId(m.getRoleLevelId());
	
	SubRoleLevelEntity subRoleLevelEntity2=new SubRoleLevelEntity();
	subRoleLevelEntity2.setSubRoleLevelId(m.getSubRoleLevelParentId());
	
	subRoleLevelEntity.setRoleLevelEntity(roleLevelEntity);
	subRoleLevelEntity.setSubRoleLevelEntity(subRoleLevelEntity2);
	
	subRoleLevelEntity.setStatus(Status.ACTIVE);
	subRoleLevelEntity.setCreatedBy(m.getCreatedBy());
	subRoleLevelEntity.setCreateTimeStamp(new Date());
	subRoleLevelEntity.setUniqueId(m.getUniqueId());
	
	

		return subRoleLevelEntity;
	}

	@Override
	public SubRoleLevelEntity updateModelToEntity(SubRoleLevelModel m,
			SubRoleLevelEntity e) {
		
		e.setDescription(m.getDescription());
		e.setUniqueId(m.getUniqueId());
		
		RoleLevelEntity roleLevelEntity=new RoleLevelEntity();
		roleLevelEntity.setRowLevelId(m.getRoleLevelId());
		
		e.setRoleLevelEntity(roleLevelEntity);
		
		SubRoleLevelEntity subRoleLevelEntity=new SubRoleLevelEntity();
		subRoleLevelEntity.setSubRoleLevelId(m.getSubRoleLevelParentId());
		
		e.setSubRoleLevelEntity(subRoleLevelEntity);
		e.setUpdatedBy(m.getUpdatedBy());
		e.setLastUpdateTimeStamp(new Date());
		
		return e;
	}

	@Override
	public SubRoleLevelModel entityToModel(SubRoleLevelEntity e)
			throws ObjectNotFound {
		
		SubRoleLevelModel subRoleLevelModel=new SubRoleLevelModel();
		subRoleLevelModel.setSubRoleLevelId(e.getSubRoleLevelId());
		subRoleLevelModel.setDescription(e.getDescription());
		subRoleLevelModel.setUniqueId(e.getUniqueId());
		subRoleLevelModel.setRoleLevelId(e.getRoleLevelEntity().getRowLevelId());
		subRoleLevelModel.setSubRoleLevelParentId(e.getSubRoleLevelEntity().getSubRoleLevelId());
		return subRoleLevelModel;
	}

	@Override
	public List<SubRoleLevelModel> entityListToModelList(
			List<SubRoleLevelEntity> es) throws DataNotFound, ObjectNotFound {
		
		 List<SubRoleLevelModel> subRoleLevelModels=new LinkedList<SubRoleLevelModel>();
		
		for(SubRoleLevelEntity temp : es){
			if(temp.getStatus()==Status.ACTIVE){
			SubRoleLevelModel subRoleLevelModel=new SubRoleLevelModel();
			subRoleLevelModel.setSubRoleLevelId(temp.getSubRoleLevelId());
			subRoleLevelModel.setRoleLevelId(temp.getRoleLevelEntity().getRowLevelId());
			subRoleLevelModel.setDescription(temp.getDescription());
			subRoleLevelModel.setCategory(temp.getRoleLevelEntity().getLevelName());
			subRoleLevelModel.setParent(temp.getSubRoleLevelEntity().getDescription());
			subRoleLevelModel.setUniqueId(temp.getUniqueId());
			subRoleLevelModels.add(subRoleLevelModel);
			}
		}
		return subRoleLevelModels;
	}

	@Override
	public List<SubRoleLevelEntity> modelListToEntityList(
			List<SubRoleLevelModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SubRoleLevelEntity deleteModelToEntity(SubRoleLevelModel m, SubRoleLevelEntity e) {
		e.setStatus(Status.INACTIVE);
		
		return e;
	}

	

}