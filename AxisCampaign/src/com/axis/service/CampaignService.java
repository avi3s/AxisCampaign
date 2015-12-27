package com.axis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.common.DateGenerator;
import com.axis.converter.CampaignConverter;
import com.axis.converter.CampaignFileConverter;
import com.axis.converter.RoleCampaignConverter;
import com.axis.dao.CampaignDao;
import com.axis.dao.CampaignFileDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.CampaignFileEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignFileModel;
import com.axis.model.CampaignModel;
import com.axis.model.RoleCampaignModel;
import com.axis.validation.CampaignValidation;

@Service
@Transactional
public class CampaignService {

	Logger logger = Logger.getLogger(CampaignService.class);

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private CampaignConverter campaignConverter;

	@Autowired
	private RoleCampaignDao roleCampaignDao;

	@Autowired
	private RoleCampaignConverter roleCampaignConverter;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignFileDao campaignFileDao;

	@Autowired
	private CampaignFileConverter campaignFileConverter;

	@Autowired
	private CampaignValidation campaignValidation;

	public List<CampaignModel> findAllActiveCampaignByStatus()
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByStatus-Start");
		}

		List<CampaignModel> campaignmodels = campaignConverter
				.entityListToModelList(campaignDao
						.findAllActiveCampaignByStatus());

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByStatus-End");
		}

		return campaignmodels;
	}

	public List<CampaignModel> findAllActiveCampaignByRole(int roleId)
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-Start");
		}

		List<CampaignModel> campaignmodels = new ArrayList<CampaignModel>();

		List<RoleCampaignModel> roleCampaignModels = roleCampaignConverter
				.entityListToModelList(roleCampaignDao
						.findAllActiveCampaignByRole(roleId));

		for (RoleCampaignModel roleCampaignModel : roleCampaignModels) {

			campaignmodels.add(roleCampaignModel.getCampaignModel());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}

		return campaignmodels;
	}

	public void insertCampaign(CampaignModel campaignModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("insertCampaign-Start");
		}

		// campaignValidation.campaignCreateValidate(campaignModel);

		List<Date> dates = null;
		Date quarterlyStart = new Date();
		Date quarterlyEnd = new Date();

		// campaignModel.setCreatedBy(1); // Setting Created By 1 for Admin

		if (Integer.parseInt(campaignModel.getQuarterId()) == 1) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else if (Integer.parseInt(campaignModel.getQuarterId()) == 2) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else if (Integer.parseInt(campaignModel.getQuarterId()) == 3) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);
		}

		campaignDao.create(campaignConverter.modelToEntity(campaignModel));

	}

	public void updateCampaignFile(CampaignModel campaignModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFile-Start");
		}

		// campaignModel.setUpdatedBy(1); // Setting Created By 1 for Admin
		campaignFileDao.campaignFileDelete(campaignModel.getCampaignId());

		if (campaignModel.getCampaignFileModels().size() > 0) {

			for (CampaignFileModel campaignFileModel : campaignModel
					.getCampaignFileModels()) {

				CampaignFileEntity campaignFileEntity = campaignFileConverter
						.updateModelToEntity1(campaignFileModel,
								campaignModel.getCampaignId(),
								campaignModel.getUpdatedBy());

				campaignFileDao.create(campaignFileEntity);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaignFile-End");
		}
	}

	public void updateCampaign(CampaignModel campaignModel)
			throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaign-Start");
		}

		// campaignValidation.campaignUpdateValidate(campaignModel);

		List<Date> dates = null;
		Date quarterlyStart = new Date();
		Date quarterlyEnd = new Date();

		// campaignModel.setUpdatedBy(1); // Setting Created By 1 for Admin

		if (Integer.parseInt(campaignModel.getQuarterId()) == 1) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else if (Integer.parseInt(campaignModel.getQuarterId()) == 2) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else if (Integer.parseInt(campaignModel.getQuarterId()) == 3) {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);

		} else {

			dates = DateGenerator.dateGeneratorbyId(
					Integer.parseInt(campaignModel.getQuarterId()),
					campaignModel.getFinancialYear());

			if (dates != null) {

				quarterlyStart = dates.get(0);
				quarterlyEnd = dates.get(1);
			}

			campaignModel.setQuarterStartDate(quarterlyStart);
			campaignModel.setQuarterEndDate(quarterlyEnd);
		}

		CampaignEntity campaignEntity = campaignDao.find(campaignModel
				.getCampaignId());

		/*
		 * CampaignModel campaignModel2 = campaignConverter
		 * .entityToModel(campaignDao.find(campaignModel .getCampaignId()));
		 */

		if (campaignEntity != null) {
			campaignDao.update(campaignConverter.updateModelToEntity(
					campaignModel, campaignEntity));

			int campaignId = campaignEntity.getCampaignId();

			int updatedBy = campaignModel.getUpdatedBy();

			String role_array[] = campaignModel.getRoleID_array();

			if (role_array.length > 0) {

				for (int i = 0; i < role_array.length; i++) {

					int roleId = Integer.parseInt(role_array[i]);

					RoleCampaignEntity roleCampaignEntity = roleCampaignConverter
							.modelToEntity1(roleId, campaignId, updatedBy);

					roleCampaignDao.create(roleCampaignEntity);
				}
			}

		} else
			throw new ObjectNotFound(
					messageUtil.getBundle("campaign.id.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("updateCampaign-End");
		}
	}

	public void deleteCampaign(CampaignModel campaignModel)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCampaign-Start");
		}

		campaignFileDao.campaignFileDeleteByCampaignId(campaignModel
				.getCampaignId());

		CampaignEntity campaignEntity = campaignDao.find(campaignModel
				.getCampaignId());

		CampaignModel campaignModel2 = campaignConverter
				.entityToModel(campaignDao.find(campaignModel.getCampaignId()));

		if (campaignEntity != null)
			campaignDao.update(campaignConverter.deleteModelToEntity(
					campaignModel2, campaignEntity));
		else
			throw new ObjectNotFound(
					messageUtil.getBundle("campaign.id.not.found"));

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCampaign-End");
		}
	}

	public CampaignModel fetchCampaignById(int campaignId)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignById-Start");
		}

		CampaignModel campaignModel = campaignConverter
				.entityToModel(campaignDao.find(campaignId));

		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignById-End");
		}

		return campaignModel;
	}

	public void deleteCampaignFile(int campaign_file_id, String filename)
			throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCampaignFile-Start");
		}

		// Campaign campaign =
		// adminCampaignDao.find(campaignModel.getId());// session
		// open

		CampaignFileEntity campaignFileEntity = campaignFileDao
				.find(campaign_file_id);

		if (campaignFileEntity == null) {
			throw new ObjectNotFound(
					messageUtil.getBundle("Campaign.FileID.not.found"));
		}

		campaignFileDao.campaignFileDelete(campaign_file_id);

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCampaignFile-End");
		}

	}

	public CampaignFileModel findCampaignFileByID(int id) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("findCampaignByID-Start");
		}

		CampaignFileEntity campaignFileEntity = campaignFileDao.find(id);

		if (campaignFileEntity == null) {
			throw new ObjectNotFound(
					messageUtil.getBundle("Campaign.FileID.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findCampaignByID-Start");
		}

		return campaignFileConverter.entityToModel(campaignFileEntity);
	}

	public void campaignServiceforCheckingandSettingState() {

		// campaignDao.setStateWithCurrentDate();

		List<CampaignEntity> allCampaignList = campaignDao.findAll();
		List<CampaignEntity> activeCampaignList = campaignDao
				.getActiveStateCampaignList();

		for (int i = 0; i < activeCampaignList.size(); i++) {
			// setting state = active while iterating
			System.out.println(activeCampaignList.get(i).getCampaignName());
			campaignDao.setCampaignStateActive(activeCampaignList.get(i));
		}

		allCampaignList.removeAll(activeCampaignList);// removing active state
														// campaign entities

		for (int i = 0; i < allCampaignList.size(); i++) {
			// setting state = inactive while iterating
			System.out.println(allCampaignList.get(i).getCampaignName());
			campaignDao.setCampaignStateInActive(allCampaignList.get(i));
		}

	}
}
