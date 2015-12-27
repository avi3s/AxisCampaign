package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.dao.CampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.RoleCampaignModel;
import com.axis.model.RoleModel;

@Component
public class RoleCampaignConverter implements NecessaryConverter<RoleCampaignEntity, RoleCampaignModel> {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private CampaignDao campaignDao;
	
	@Autowired
	private MessageUtil messageUtil;

	public RoleCampaignEntity modelToEntity1(int roleId, int campaignId,
			int updatedBy) {

		RoleCampaignEntity roleCampaignEntity = new RoleCampaignEntity();

		RoleEntity role = roleDao.find(roleId);
		CampaignEntity campaignEntity = campaignDao.find(campaignId);

		roleCampaignEntity.setCampaignEntity(campaignEntity);
		roleCampaignEntity.setRoleEntity(role);
		roleCampaignEntity.setUpdatedBy(updatedBy);
		roleCampaignEntity.setLastUpdateTimeStamp(new Date());
		
		return roleCampaignEntity;
	}

	@Override
	public RoleCampaignEntity modelToEntity(RoleCampaignModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleCampaignEntity updateModelToEntity(RoleCampaignModel m,
			RoleCampaignEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleCampaignModel entityToModel(RoleCampaignEntity e)
			throws ObjectNotFound {

		RoleCampaignModel roleCampaignModel = new RoleCampaignModel();
		CampaignModel campaignModel = new CampaignModel();
		RoleModel roleModel = new RoleModel();

		if (e != null) {

			campaignModel.setCampaignId(e.getCampaignEntity().getCampaignId());
			campaignModel.setCampaignName(e.getCampaignEntity()
					.getCampaignName());

			roleModel.setRoleId(e.getRoleEntity().getRoleId());
			roleModel.setRole_name(e.getRoleEntity().getRoleName());

			roleCampaignModel.setRoleCampaignId(e.getRoleCampaignId());
			roleCampaignModel.setCampaignModel(campaignModel);
			roleCampaignModel.setRoleModel(roleModel);

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("role-campaign.id.not.found"));

		return roleCampaignModel;
	}

	@Override
	public List<RoleCampaignModel> entityListToModelList(
			List<RoleCampaignEntity> es) throws DataNotFound, ObjectNotFound {

		List<RoleCampaignModel> campaignModels = null;

		if (es.size() > 0) {

			campaignModels = new ArrayList<RoleCampaignModel>();

			for (RoleCampaignEntity roleCampaignEntity : es) {
				campaignModels.add(entityToModel(roleCampaignEntity));
			}
		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("role-campaign.not.found"));
		return campaignModels;
	}

	@Override
	public List<RoleCampaignEntity> modelListToEntityList(
			List<RoleCampaignModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

}