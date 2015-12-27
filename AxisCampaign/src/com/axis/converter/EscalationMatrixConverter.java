package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.dao.CampaignDao;
import com.axis.dao.EscalationMatrixDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.EscalationMatrixEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.EscalationMatrixModel;

@SuppressWarnings("serial")
@Component
public class EscalationMatrixConverter implements
		NecessaryConverter<EscalationMatrixEntity, EscalationMatrixModel> {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private EscalationMatrixDao escalationMatrixDao;

	@Override
	public EscalationMatrixEntity modelToEntity(EscalationMatrixModel m) {

		EscalationMatrixEntity escalationMatrixEntity = new EscalationMatrixEntity();

		CampaignEntity campaignEntity = new CampaignEntity();

		campaignEntity.setCampaignId(m.getCampaignId());
		escalationMatrixEntity.setName(m.getName());
		escalationMatrixEntity.setContactNumber(m.getContactNumber());
		escalationMatrixEntity.setEmail(m.getEmail());
		escalationMatrixEntity.setType(m.getType());
		escalationMatrixEntity.setStatus(Status.ACTIVE);
		escalationMatrixEntity.setCreateTimeStamp(new Date());

		escalationMatrixEntity.setCampaignEntity(campaignEntity);

		return escalationMatrixEntity;

	}

	@Override
	public EscalationMatrixEntity updateModelToEntity(EscalationMatrixModel m,
			EscalationMatrixEntity e) {

		CampaignEntity campaignEntity = campaignDao.find(m.getCampaignId());

		System.out.println("Campaign Converter::"
				+ campaignEntity.getCampaignId());

		e.setName(m.getName());
		e.setEmail(m.getEmail());
		e.setContactNumber(m.getContactNumber());
		e.setType(m.getType());

		e.setCampaignEntity(campaignEntity);

		return e;
	}

	public EscalationMatrixModel entityToModel(EscalationMatrixEntity e)
			throws ObjectNotFound {

		EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();

		CampaignModel campaignModel = new CampaignModel();

		if (e != null) {

			campaignModel.setCampaignId(e.getCampaignEntity().getCampaignId());

			escalationMatrixModel.setContactNumber(e.getContactNumber());
			escalationMatrixModel.setId(e.getEscalationMatrixId());
			escalationMatrixModel.setEmail(e.getEmail());
			escalationMatrixModel.setName(e.getName());
			escalationMatrixModel.setType(e.getType());
			escalationMatrixModel.setStatus(e.getStatus());
			escalationMatrixModel.setCreateDate(e.getCreateTimeStamp());
			escalationMatrixModel.setCampaignName(e.getCampaignEntity()
					.getCampaignName());
			escalationMatrixModel.setCampaignId(campaignModel.getCampaignId());

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("escalationMatrix.id.not.found"));

		return escalationMatrixModel;
	}

	public List<EscalationMatrixModel> entityListToModelList(
			List<EscalationMatrixEntity> es) throws DataNotFound,
			ObjectNotFound {

		List<EscalationMatrixModel> escalationMatrixModels = null;

		if (es.size() > 0) {
			escalationMatrixModels = new ArrayList<EscalationMatrixModel>();

			for (EscalationMatrixEntity escalationMatrixEntity : es) {
				escalationMatrixModels
						.add(entityToModel(escalationMatrixEntity));
			}

		} else
			throw new DataNotFound(
					messageUtil.getBundle("escalationMatrix.object.not.found"));

		return escalationMatrixModels;
	}

	public List<EscalationMatrixEntity> modelListToEntityList(
			List<EscalationMatrixModel> ms) {
		// TODO Auto-generated method stub

		return null;
	}

	@SuppressWarnings("null")
	public List<EscalationMatrixModel> roleCampaignListToEscalationMatrixList(
			List<RoleCampaignEntity> roleCampaignEntities) throws DataNotFound,
			ObjectNotFound {

		List<EscalationMatrixEntity> escalationMatrixEntities = new ArrayList<EscalationMatrixEntity>();

		if (roleCampaignEntities.size() > 0) {

			//System.out.println("Role Campaign Not Empty");
			for (RoleCampaignEntity roleCampaignEntity : roleCampaignEntities) {

				/*System.out.println("roleCampaignEntity.getRoleCampaignId()"
						+ roleCampaignEntity.getCampaignEntity()
								.getCampaignId());*/

				escalationMatrixEntities.addAll(escalationMatrixDao
						.findEscalationMatirx(roleCampaignEntity
								.getCampaignEntity().getCampaignId()));
			}

		} else
			throw new DataNotFound(
					messageUtil.getBundle("escalationMatrix.object.not.found"));

		return entityListToModelList(escalationMatrixEntities);
	}

}