package com.axis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.RoleLevelConverter;
import com.axis.converter.SubRoleLevelConverter;
import com.axis.dao.RoleLevelDao;
import com.axis.dao.SubRoleLevelDao;
import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.entity.SubRoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;
import com.axis.model.RoleModel;
import com.axis.model.SubRoleLevelModel;
import com.axis.validation.SubRoleLevelValidation;

@Service
@Transactional
public class SubRoleLevelService {

	@Autowired
	private SubRoleLevelConverter subRoleLevelConverter;

	@Autowired
	private SubRoleLevelDao subRoleLevelDao;

	@Autowired
	private RoleLevelDao roleLevelDao;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private SubRoleLevelValidation subRoleLevelValidation;

	public void insertSubRoleLevelData(SubRoleLevelModel subRoleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		subRoleLevelValidation.validateForm(subRoleLevelModel);

		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelConverter
				.modelToEntity(subRoleLevelModel);
		System.out.println("*** Entity Converted :: "
				+ subRoleLevelEntity.getDescription()
				+ "  "
				+ subRoleLevelEntity.getRoleLevelEntity().getRowLevelId()
				+ " "
				+ subRoleLevelEntity.getSubRoleLevelEntity()
						.getSubRoleLevelId());
		subRoleLevelDao.create(subRoleLevelEntity);

	}

	public List<SubRoleLevelModel> getSubRoleLevelListById(SubRoleLevelModel subRoleLevelModel) throws DataNotFound,ObjectNotFound {
		
		RoleLevelEntity roleLevelEntity = roleLevelDao.find(subRoleLevelModel.getRoleLevelId());
		
		List<SubRoleLevelEntity> subRoleLevelEntities=null;
		if(roleLevelEntity!=null)
			subRoleLevelEntities= subRoleLevelDao.getSubRoleLevelListById(roleLevelEntity.getRoleLevelEntity().getRowLevelId());
		
		if(subRoleLevelEntities==null || subRoleLevelEntities.isEmpty() || subRoleLevelEntities.size()==0)
			throw new DataNotFound(messageUtil.getBundle("no_parentSubRoleLevel_found"));
		
		List<SubRoleLevelModel> subRoleLevelModels = subRoleLevelConverter.entityListToModelList(subRoleLevelEntities);

		return subRoleLevelModels;
	}

	public List<SubRoleLevelModel> subRoleLevelList() throws DataNotFound,
			ObjectNotFound {
		
		List<SubRoleLevelEntity> subRoleLevelEntities = subRoleLevelDao.subRoleLevelList();
		
		if(subRoleLevelEntities==null || subRoleLevelEntities.isEmpty() || subRoleLevelEntities.size()==0)
			throw new DataNotFound(messageUtil.getBundle("subRoleLevel_Not_Found"));
		
		for (SubRoleLevelEntity subRoleLevelEntity : subRoleLevelEntities)
			System.out
					.println("******* here is the view list :: subrolelevelid ->"
							+ subRoleLevelEntity.getSubRoleLevelId()
							+ " desc -> "
							+ subRoleLevelEntity.getDescription()
							+ "  level name -> "
							+ subRoleLevelEntity.getRoleLevelEntity()
									.getLevelName()
							+ " parent name-> "
							+ subRoleLevelEntity.getSubRoleLevelEntity()
									.getDescription());
		List<SubRoleLevelModel> subRoleLevelModels = subRoleLevelConverter
				.entityListToModelList(subRoleLevelEntities);
		return subRoleLevelModels;

	}

	// public List<SubRoleLevelModel>
	// getSubRoleLevelModelForEdit(SubRoleLevelModel subRoleLevelModel) throws
	// DataNotFound, ObjectNotFound{
	// List<SubRoleLevelEntity>
	// subRoleLevelEntities=subRoleLevelDao.getSubRoleLevelModelForEdit(subRoleLevelModel);
	// for(SubRoleLevelEntity subRoleLevelEntity : subRoleLevelEntities)
	// System.out.println("******* here is the view list :: subrolelevelid ->"+subRoleLevelEntity.getSubRoleLevelId()+" desc -> "+subRoleLevelEntity.getDescription()+"  level name -> "+subRoleLevelEntity.getRoleLevelEntity().getLevelName()+" parent name-> "+subRoleLevelEntity.getSubRoleLevelEntity().getDescription());
	// List<SubRoleLevelModel>
	// subRoleLevelModels=subRoleLevelConverter.entityListToModelList(subRoleLevelEntities);
	// return subRoleLevelModels;
	//
	// }

	public SubRoleLevelModel getSubRoleLevelModelForEdit(
			SubRoleLevelModel subRoleLevelModel) throws DataNotFound,
			ObjectNotFound {

		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelDao
				.find(subRoleLevelModel.getSubRoleLevelId());
		System.out.println("see subRoleLevelEntity :::: "
				+ subRoleLevelEntity.getDescription() + "  "
				+ subRoleLevelEntity.getRoleLevelEntity().getLevelName() + " "
				+ subRoleLevelEntity.getSubRoleLevelEntity().getDescription());
		subRoleLevelModel = subRoleLevelConverter
				.entityToModel(subRoleLevelEntity);
		System.out.println("see rolemodel :::: "
				+ subRoleLevelModel.getDescription() + " "
				+ subRoleLevelModel.getRoleLevelId() + " "
				+ subRoleLevelModel.getSubRoleLevelParentId() + " "
				+ subRoleLevelModel.getSubRoleLevelId());

		return subRoleLevelModel;

	}

	public void deleteSubRoleData(SubRoleLevelModel subRoleLevelModel) {
		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelDao
				.find(subRoleLevelModel.getSubRoleLevelId());
		// System.out.println("roleLevelModel1 :: "+roleLevelModel.getRowLevelId()+" "+roleLevelModel.getLevelName()+" "+roleLevelModel.getParentRoleLevelId());
		// System.out.println("roleLevelEntity :: "+roleLevelEntity.getRowLevelId()+" "+roleLevelEntity.getLevelName()+" "+roleLevelEntity.getRoleLevelEntity().getRowLevelId());
		// System.out.println("roleLevelModel2 :: "+roleLevelModel.getRowLevelId()+" "+roleLevelModel.getLevelName()+" "+roleLevelModel.getParentRoleLevelId());

		subRoleLevelEntity = subRoleLevelConverter.deleteModelToEntity(
				subRoleLevelModel, subRoleLevelEntity);

		subRoleLevelDao.update(subRoleLevelEntity);

	}

	public void updateSubRoleData(SubRoleLevelModel subRoleLevelModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		subRoleLevelValidation.validateFormForEdit(subRoleLevelModel);

		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelDao.find(subRoleLevelModel.getSubRoleLevelId());
		
		
		subRoleLevelEntity = subRoleLevelConverter.updateModelToEntity(subRoleLevelModel, subRoleLevelEntity);

		subRoleLevelDao.update(subRoleLevelEntity);

	}

	public List<SubRoleLevelModel> getSubRoleLevelListByRoleLevelId(
			Integer roleLevelId) throws DataNotFound, ObjectNotFound {
		System.out
				.println("\n\n\n****** in SubRoleLevelService :: selected id= "
						+ roleLevelId);

		List<SubRoleLevelEntity> subRoleLevelEntities = subRoleLevelDao
				.getSubRoleLevelListById(roleLevelId);

		for (SubRoleLevelEntity subRoleLevelEntity : subRoleLevelEntities) {
			System.out.println("\n\n\nentity :: rowlevelid :: "
					+ subRoleLevelEntity.getRoleLevelEntity().getRowLevelId()
					+ "  description" + subRoleLevelEntity.getDescription()
					+ "  " + subRoleLevelEntity.getSubRoleLevelId());
		}

		List<SubRoleLevelModel> subRoleLevelModels = subRoleLevelConverter
				.entityListToModelList(subRoleLevelEntities);

		for (SubRoleLevelModel subRoleLevelModel1 : subRoleLevelModels) {
			System.out.println("\n\n\nmodels :: rowlevelid :: "
					+ subRoleLevelModel1.getRoleLevelId() + "  description"
					+ subRoleLevelModel1.getDescription());
		}
		return subRoleLevelModels;
	}

	public SubRoleLevelEntity getSubRoleLevelbyId(Integer id) {

		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelDao.find(id);
		if (subRoleLevelEntity == null)
			return null;

		return subRoleLevelDao.find(id);

	}

	public SubRoleLevelModel getSubRoleLevelByUniqueId(String uniqueId)
			throws ObjectNotFound {

		SubRoleLevelEntity subRoleLevelEntity = subRoleLevelDao
				.getSubRoleLevelEntityByUniqueId(uniqueId);
		if (subRoleLevelEntity == null)
			return null;

		return subRoleLevelConverter.entityToModel(subRoleLevelEntity);

	}

}
