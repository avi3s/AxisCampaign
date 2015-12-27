package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;

@Component
public class RoleConverter implements NecessaryConverter<RoleEntity, RoleModel> {

	@Autowired
	private MessageUtil messageUtil;

	@Override
	public RoleEntity modelToEntity(RoleModel m) {
		//System.out.println("in converter :: rolename :: "+m.getRole_name()+" role_level_id :: "+m.getRoleLevelId());
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setRoleName(m.getRole_name());
		
		RoleLevelEntity roleLevelEntity=new RoleLevelEntity();
		roleLevelEntity.setRowLevelId(m.getRoleLevelId());
		
		roleEntity.setRoleLevelEntity(roleLevelEntity);
		roleEntity.setCreatedBy(m.getCreatedBy());
		roleEntity.setCreateTimeStamp(new Date());
		roleEntity.setStatus(Status.ACTIVE);
		
		return roleEntity;
		}

	@Override
	public RoleEntity updateModelToEntity(RoleModel m, RoleEntity e) {
		e.setRoleName(m.getRole_name());
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(m.getUpdatedBy());
		
		RoleLevelEntity roleLevelEntityTemp=new RoleLevelEntity();
		roleLevelEntityTemp.setRowLevelId(m.getRoleLevelId());
		e.setRoleLevelEntity(roleLevelEntityTemp);
		
		return e;
	}
	
	public RoleEntity deleteModelToEntity(RoleModel m, RoleEntity e) {
		e.setStatus(Status.INACTIVE);
		
		return e;
	}

	@Override
	public RoleModel entityToModel(RoleEntity e) throws ObjectNotFound {

		RoleModel roleModel = new RoleModel();

		if (e != null) {

			roleModel.setRoleId(e.getRoleId());
			roleModel.setRole_name(e.getRoleName());
			roleModel.setRoleLevelId(e.getRoleLevelEntity().getRowLevelId());
			roleModel.setRole_level_name(e.getRoleLevelEntity().getLevelName());
			roleModel.setStatus(e.getStatus());
			

		} else
			throw new ObjectNotFound(messageUtil.getBundle("role.id.not.found"));
		
		return roleModel;
	}

	@Override
	public List<RoleModel> entityListToModelList(List<RoleEntity> es)
			throws DataNotFound, ObjectNotFound {

		List<RoleModel> roleModels = null;
		
		if (es.size() > 0) {

			roleModels = new ArrayList<RoleModel>();

			for (RoleEntity roleEntity : es) {
				roleModels.add(entityToModel(roleEntity));
			}
		} else
			throw new DataNotFound(messageUtil.getBundle("role.not.found"));
		
		return roleModels;
	}

	@Override
	public List<RoleEntity> modelListToEntityList(List<RoleModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

}