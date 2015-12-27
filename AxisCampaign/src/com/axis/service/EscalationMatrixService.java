package com.axis.service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.EscalationMatrixConverter;
import com.axis.dao.EscalationMatrixDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.EscalationMatrixEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.EmptyValueException;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;
import com.axis.model.EscalationMatrixModel;
import com.axis.validation.EscalationBusinessDataValidation;

@Service
@Transactional
public class EscalationMatrixService {

	private static final Logger logger = Logger
			.getLogger(EscalationMatrixService.class);

	@Autowired
	private EscalationMatrixDao escalationMatrixDao;

	@Autowired
	private com.axis.common.MessageUtil messageUtil;

	@Autowired
	private EscalationBusinessDataValidation escalationBusinessDataValidation;

	@Autowired
	private EscalationMatrixConverter escalationMatrixConverter;

	@Autowired
	private RoleCampaignDao roleCampaignDao;

	public List<EscalationMatrixModel> findAllActiveEscalationMatrixByStatus()
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveEscalationMatrixByStatus-Start");
		}

		List<EscalationMatrixEntity> escalationMatrixEntities = escalationMatrixDao
				.findAllActiveEscalationMatrixByStatus();

		if (escalationMatrixEntities == null
				|| escalationMatrixEntities.isEmpty()
				|| escalationMatrixEntities.size() == 0) {
			throw new ObjectNotFound(
					messageUtil.getBundle("escalationMatrix.object.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveEscalationMatrixByStatus-End");
		}
		return escalationMatrixConverter
				.entityListToModelList(escalationMatrixEntities);
	}

	public void insertescalationmatrixService(
			EscalationMatrixModel escalationMatrixModel) throws DataNotFound,
			RecordFound, FormExceptions, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("insertescalationmatrixService-Start");
		}

		escalationBusinessDataValidation
				.escalationMatrixCreateValidation(escalationMatrixModel);

		// EscalationMatrixEntity escalationMatrixEntity =
		// escalationMatrixDao.findByContactNumber(escalationMatrixModel.getContactNumber());

		// EscalationMatrixEntity escalationMatrixEntity1 =
		// escalationMatrixDao.findByEmailId(escalationMatrixModel.getEmail());

		EscalationMatrixEntity escalationMatrixEntity = escalationMatrixConverter
				.modelToEntity(escalationMatrixModel);

		System.out.println("*** Entity Converted :: "
				+ escalationMatrixEntity.getContactNumber() + "  "
				+ escalationMatrixEntity.getEmail() + " "
				+ escalationMatrixEntity.getName());

		escalationMatrixDao.create(escalationMatrixEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("insertescalationmatrixService-End");
		}
	}

	public EscalationMatrixModel getEscalationMatrixByID(int id)
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getEscalationMatrixByID-Start");
		}

		EscalationMatrixEntity escalationMatrixEntity = escalationMatrixDao
				.find(id);

		if (escalationMatrixEntity == null) {

			throw new DataNotFound(
					messageUtil.getBundle("escalationMatrix.id.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getEscalationMatrixByID-End");
		}

		return escalationMatrixConverter.entityToModel(escalationMatrixEntity);

	}

	public void updateContent(EscalationMatrixModel escalationMatrixModel)
			throws ObjectNotFound, DataNotFound, RecordFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("updateEscalationMatrix(EscalationMatrixModel escalationMatrixModel)-Start");
		}
		escalationBusinessDataValidation
				.escalationMatrixuserUpdateValidate(escalationMatrixModel);
		/*
		 * if(escalationMatrixDao.findByContactAndId(escalationMatrixModel.
		 * getContactNumber(), escalationMatrixModel.getId())){ throw new
		 * RecordFound(messageUtil.getBundle("contactNumber.already.exist")); }
		 * if
		 * (escalationMatrixDao.findByEmailAndId(escalationMatrixModel.getEmail
		 * (),escalationMatrixModel.getId())){ throw new
		 * RecordFound(messageUtil.getBundle("emailId.already.exist")); }
		 */EscalationMatrixEntity escalationMatrixEntity = escalationMatrixDao
				.find(escalationMatrixModel.getId());

		if (escalationMatrixEntity == null) {
			// (employeeEntities.isEmpty() && employeeEntities.size() == 0
			throw new ObjectNotFound(
					messageUtil.getBundle("escalation.object.not.found"));

		}

		escalationMatrixEntity = escalationMatrixConverter.updateModelToEntity(
				escalationMatrixModel, escalationMatrixEntity);

		escalationMatrixDao.update(escalationMatrixEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("updateEscalationMatrix(EscalationMatrixModel escalationMatrixModel)-End");
		}

	}

	public void deleteEscalationMatrix(int escalationId) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser(int userId)-Start");
		}

		EscalationMatrixEntity escalationMatrixEntity = escalationMatrixDao
				.find(escalationId);

		escalationMatrixEntity.setStatus(Status.INACTIVE);
	}

	/***********************************************
	 * User Module For Escalation Matrix
	 * 
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 ******************************************************/

	public List<EscalationMatrixModel> fetchEscalationMatrixByRole(int roleId)
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchEscalationMatrixByRole - Start");
		}

		List<EscalationMatrixModel> escalationMatrixModels = escalationMatrixConverter
				.roleCampaignListToEscalationMatrixList(roleCampaignDao
						.findAllActiveCampaignByRole(roleId));

		if (logger.isDebugEnabled()) {
			logger.debug("fetchEscalationMatrixByRole - End");
		}

		return escalationMatrixModels;
	}
}
