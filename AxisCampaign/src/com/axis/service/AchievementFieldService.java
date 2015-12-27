package com.axis.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
//import com.axis.business.AdminCampaignFieldBusiness;
import com.axis.converter.AchievementFieldConverter;
import com.axis.converter.AchievementFieldValueConverter;
import com.axis.dao.AchievementFieldDao;
import com.axis.dao.AchievementFieldValueDao;
import com.axis.dao.CampaignDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
//import com.axis.entity.TargetField;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementLeaderModel;
import com.axis.model.AcheivementModel;
import com.axis.model.AchievementLeaderNamedModel;
import com.axis.model.ExcellSheetUploadModel;
import com.axis.validation.AcheivementDataValidation;
import com.google.gson.Gson;

//import com.axis.model.CampaignFieldModel;
//import com.axis.model.CampaignFieldModel;

@Service
@Transactional
public class AchievementFieldService {
	@Autowired
	AchievementFieldDao achievementFieldDao;
	@Autowired
	AchievementFieldConverter achievementFieldConverter;
	@Autowired
	RoleCampaignDao roleCampaignDao;
	@Autowired
	CampaignDao campaignDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	AchievementFieldValueDao achievementFieldValueDao;
	@Autowired
	AchievementFieldValueConverter achievementFieldValueConverter;
	@Autowired
	AcheivementDataValidation acheivementDataValidation;
	// @Autowired
	// AcheivementDataValidation acheivementDataValidation2;
	@Autowired
	private MessageUtil massageUtil;

	private static final Logger logger = Logger
			.getLogger(AchievementFieldService.class);

	public List<AcheivementModel> fetchAllAcheivementField()
			throws DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignFieldbyId-Start");
		}

		List<AchievementFieldEntity> AchievementFieldEntities = achievementFieldDao.findAllValuesAgainstStatus();

//		if (AchievementFieldEntities == null
//				|| AchievementFieldEntities.isEmpty()
//				|| AchievementFieldEntities.size() == 0) {
//		}
		List<AcheivementModel> acheivementFieldModels = null;

		if (logger.isDebugEnabled()) {
			  
			logger.debug("getAllTarget-End");
		}
		List<AchievementFieldEntity> AchievementFieldEntities1 = new ArrayList<AchievementFieldEntity>();
		
		if (AchievementFieldEntities == null|| AchievementFieldEntities.isEmpty()|| AchievementFieldEntities.size() == 0) {
	
			return acheivementFieldModels;
		
		} 
		else {
			acheivementFieldModels = achievementFieldConverter
					.entityListToModelList(AchievementFieldEntities);
			return acheivementFieldModels;
		}

	}

	public void insertFieldAcheivement(AcheivementModel acheivementModel)
			throws DataNotFound, ObjectNotFound, RecordFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("insertCampaignField-Start");
		}
		acheivementModel.setDuplicates("false");
		int duplicate = 0;
		acheivementModel.setBlankspace("false");
		for (int i = 0; i < acheivementModel.getFieldName_array().length; i++) {
			if (acheivementModel.getFieldName_array()[i].equals("")) {
				System.out.println("Blank space is on there");
				duplicate = 1;
				acheivementModel.setBlankspace("true");
			}

		}

		if (duplicate == 0) {

			List<String> duplicates = Arrays.asList(acheivementModel
					.getFieldName_array());
			System.out.println("Sizes of the array list is::::"
					+ duplicates.size());
			Set<String> foo = new HashSet<String>(duplicates);
			System.out.println("Sizes of the set is::::" + foo.size());
			if (duplicates.size() != foo.size()) {
				acheivementModel.setDuplicates("true");
			}
		}

		// Arrays.asList(acheivementModel.getFieldName_array()).contains(yourValue)

		// acheivementModel.setDuplicates("false");
		// for(int j = 0; j < acheivementModel.getFieldName_array().length;
		// j++){
		// for(int k = 0; k < acheivementModel.getFieldName_array().length;
		// k++){
		// if
		// (acheivementModel.getFieldName_array()[k].equals(acheivementModel.getFieldName_array()[j])){
		// //duplicates = true;
		// System.out.println("Duplicated data exist in the for loop");
		// acheivementModel.setDuplicates("true");
		// }
		// }
		// }

		System.out.println("Sizes of teh ..........****************"
				+ acheivementModel.getFieldName_array().length);
		acheivementModel.setFieldName("NOT");
		acheivementModel.setUpdatefieldName("NOT");
		acheivementDataValidation.validateFormAcheivement(acheivementModel);
		String fieldName_array2[];
		fieldName_array2 = acheivementModel.getFieldName_array();
		for (int i = 0; i < fieldName_array2.length; i++) {
			acheivementModel.setFieldName(fieldName_array2[i]);
			acheivementDataValidation.validateFormAcheivement(acheivementModel);
		}
		AcheivementModel acheivementModel2 = new AcheivementModel();
		RoleEntity roleEntity = roleDao.find(acheivementModel.getRoleId()
				.intValue());
		CampaignEntity campaignEntity = campaignDao.find(acheivementModel
				.getCampaignId().intValue());
		RoleCampaignEntity roleCampaignEntity = roleCampaignDao
				.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);

		acheivementModel2.setFieldName(acheivementModel.getFieldName());
		acheivementModel2.setRoleCampaignEntity(roleCampaignEntity);
		String fieldName_array1[];
		List<AchievementFieldEntity> achievementFieldEntities = null;
		fieldName_array1 = acheivementModel.getFieldName_array();
		for (int i = 0; i < fieldName_array1.length; i++) {
			acheivementModel2.setFieldName(fieldName_array1[i]);
			System.out.println("Dta in the for section");
			achievementFieldEntities = achievementFieldDao
					.getDuplicateValueCheck(acheivementModel2);
		}

		System.out
				.println("The sizes of the achievementFieldEntities are :::::::::"
						+ achievementFieldEntities.size());
		if (achievementFieldEntities == null
				|| achievementFieldEntities.isEmpty()
				|| achievementFieldEntities.size() == 0) {

			String fieldName_array[];

			fieldName_array = acheivementModel.getFieldName_array();

			if (fieldName_array.length > 0) {

				for (int i = 0; i < fieldName_array.length; i++) {

					acheivementModel.setFieldName(fieldName_array[i]);
					// acheivementDataValidation.AcheivementCreateValidate(acheivementModel);

					acheivementModel.setStatus(Status.ACTIVE);
					achievementFieldDao.create(achievementFieldConverter
							.modelToEntity(acheivementModel));
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("insertAcheivementField-End");
			}

		}

		else {

			throw new RecordFound(
					massageUtil.getBundle("cms.path.already.present"));
		}

	}

	public AcheivementModel getAcheivementByID(int id) throws DataNotFound,
			ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("getCampaignByID-Start");
		}
		AchievementFieldEntity achievementFieldEntity = achievementFieldDao
				.find(id);

		if (logger.isDebugEnabled()) {
			logger.debug("getCampaignByID-End");
		}

		return achievementFieldConverter.entityToModel(achievementFieldEntity);

	}

	public void deleteAcheivementFieldServices(AcheivementModel acheivementModel)
			throws DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("DeleteFieldValueService-Start");
		}
		AchievementFieldEntity achievementFieldEntity = achievementFieldDao
				.find(acheivementModel.getAcheivementId());
		achievementFieldEntity.setStatus(Status.INACTIVE);

		achievementFieldDao.update(achievementFieldEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("deleteAcheivementFieldValueService-End");
		}
	}

	public void saveEditedAcheivementFieldServices(
			AcheivementModel acheivementModel) throws DataNotFound,
			ObjectNotFound, RecordFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("SaveEditedFieldValue-Start");
		}
		acheivementModel.setFieldName("NOT");
		acheivementDataValidation
				.validateFormAcheivementupdate(acheivementModel);
		AchievementFieldEntity achievementFieldEntity = achievementFieldDao
				.find(acheivementModel.getAcheivementId());
		RoleEntity roleEntity = roleDao.find(acheivementModel.getRoleId()
				.intValue());
		achievementFieldEntity.setFiledName(acheivementModel
				.getUpdatefieldName());
		achievementFieldEntity.setFieldType(acheivementModel.getFieldType());
		CampaignEntity campaignEntity = campaignDao.find(acheivementModel
				.getCampaignId().intValue());
		RoleCampaignEntity roleCampaignEntity = roleCampaignDao
				.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);

		achievementFieldEntity.setRoleCampaignEntity(roleCampaignEntity);
		achievementFieldDao.update(achievementFieldEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("editedAcheivementFieldService-End");
		}
	}

	public ExcellSheetUploadModel getFileandSave(String campId, String roleId,
			MultipartFile file, String name) throws IOException, FormExceptions {
		// String CampId=String.valueOf(campId);
		// String roleId1=String.valueOf(roleId);
		acheivementDataValidation.validateFormAcheivementExcell(roleId, campId,
				name);
		AcheivementModel acheivementModel = new AcheivementModel();
		Map<Integer, List<String>> errormap = new HashMap<Integer, List<String>>();
		List<Integer> insertedRows = new ArrayList<Integer>();
		List<String> blankSpaceRows = new ArrayList<String>();
		int rowsInserted = 0;
		// try {
		Workbook workbook = null;

		// if (name.endsWith("xlsx")) {
		// workbook = new XSSFWorkbook(inputStream);
		// } else if (name.endsWith("xls")) {
		// workbook = new HSSFWorkbook(inputStream);
		// } else {
		// throw new
		// IllegalArgumentException("The specified file is not Excel file");
		// }
		// FileInputStream inputStream = new FileInputStream(name);
		System.out.println(file);
		POIFSFileSystem fileSystem = null;
		fileSystem = new POIFSFileSystem(new ByteArrayInputStream(
				file.getBytes()));
		// Change code
		if (name.endsWith("xlsx")) {
			// workbook = new XSSFWorkbook(inputStream);
		} else if (name.endsWith("xls")) {
			workbook = new HSSFWorkbook(fileSystem);
		} else {
			throw new IllegalArgumentException(
					"The specified file is not Excel file");
		}

		// excel
		// HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		// excel
		int rows = sheet.getPhysicalNumberOfRows();
		//System.out.println("The number of rows is...." + rows);

		LinkedList<Integer> linkedList1 = new LinkedList<Integer>();
		List<Integer> fieldName_id;
		// fieldName_id =
		// targetFieldValueDao.findFieldNameId(Integer.parseInt(campId));
		//
		Integer roleId1 = Integer.valueOf(roleId);
		Integer campId1 = Integer.valueOf(campId);
		RoleEntity roleEntity = roleDao.find(roleId1);
		CampaignEntity campaignEntity = campaignDao.find(campId1);

		RoleCampaignEntity roleCampaignEntity = roleCampaignDao
				.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);
		List<AchievementFieldEntity> achievementFieldEntities = achievementFieldDao
				.findAllAgainstRoleCampaign(roleCampaignEntity);
		//System.out.println("Acheivement fielsd entitties....."
				//+ achievementFieldEntities.size());

		if (achievementFieldEntities != null) {
			Map<Integer, Object> maps = new HashMap<Integer, Object>();

			for (int i = 0; i <= rows; i++) {
				//
				HSSFRow row = sheet.getRow(i);
				//System.out.println("Rows......" + row);
				if (row != null) {

					LinkedList<String> linkedList = new LinkedList<String>();

					// excel
					int cells = row.getPhysicalNumberOfCells();
					//System.out.println("Sizes of the cell issss" + cells);

					//
					for (int j = 0; j < cells; j++) {
						String value = "";
						//
						HSSFCell cell = row.getCell(j);

						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								value = String.valueOf((long) cell
										.getNumericCellValue());
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							default:
								break;
							}
							linkedList.add(value);
							
							
							
							if(i == 0) {
								//System.out.println("For First Condition");
								for(AchievementFieldEntity achievementFieldEntity: achievementFieldEntities){
									//System.out.println("Value=============="+value);
									if(achievementFieldEntity.getFiledName().equals(value)) {
										//System.out.println("achievementFieldEntity.getFiledName()=============="+achievementFieldEntity.getFiledName());
										//System.out.println("Value=============="+value);
										linkedList1.add(achievementFieldEntity.getAchievementFieldId());
									}
								}
								
								
							}
							
							//System.out.println("List=============="+linkedList1);
						} else {
							System.out
									.println("Blank rows id issss in loop eeeee:::"
											+ (i + 1));
							// int a=i+1;
							// blankSpaceRows.add(String.valueOf(a));
							cells++;
						}

					}
					//System.out.println("Linked list" + linkedList);
					if (achievementFieldEntities.size() == linkedList.size()) {
						ListIterator listIterator = linkedList.listIterator();
						int index = 0;
						if (i != 0)
							insertedRows.add(i + 1);

						int maxlevel = achievementFieldValueDao
								.getLastLevelValue();
						int nextLevel = maxlevel + 1;
						int p=0;
						while (listIterator.hasNext()) {
							String fieldvalue = (String) listIterator.next();
							// System.out.println("value"+fieldvalue);

							rowsInserted++;
							if (i != 0) {

								AcheivementFieldValueModel acheivementFieldValueModel = new AcheivementFieldValueModel();
								int acheivementfieldId = achievementFieldEntities
										.get(index).getAchievementFieldId();
								index++;
								/*AchievementFieldEntity achievementFieldEntity = achievementFieldDao
										.find(acheivementfieldId);*/
								acheivementFieldValueModel
										.setFieldValue(fieldvalue);
								
								
								System.out.println("linkedList1.get(i)=========>>>>>>>>"+linkedList1.get(p));//linkedList1.get(i);
								
								AchievementFieldEntity achievementFieldEntity = achievementFieldDao
										.find(linkedList1.get(p));
								
								acheivementFieldValueModel
										.setAchievementFieldEntity(achievementFieldEntity);

								acheivementFieldValueModel.setFieldLevel(String
										.valueOf(nextLevel));
								// errormap.put(i+1, linkedList);

								achievementFieldValueDao
										.create(achievementFieldValueConverter
												.modelToEntity(acheivementFieldValueModel));
								p++;
							}
						}

					} else {
						errormap.put(i + 1, linkedList);
					}

					// mysql

				}
			}

		}

		ExcellSheetUploadModel excellSheetUploadModel = new ExcellSheetUploadModel();
		excellSheetUploadModel.setInsertedRows(insertedRows);
		excellSheetUploadModel.setBlankSpaceString(blankSpaceRows);
		excellSheetUploadModel.setErrormap(errormap);
		excellSheetUploadModel.setRowsInserted(rowsInserted);

		System.out.println("Sizes of thr eblank rows isss"
				+ blankSpaceRows.size());

		return excellSheetUploadModel;
	}

	public void getvalidation(String campId, String roleId, MultipartFile file,
			String name) throws IOException, FormExceptions {

		acheivementDataValidation.validateFormAcheivementExcell(roleId, campId,
				name);

	}

	public AcheivementLeaderModel getAcheivementFieldValues(int campId,
			int roleId,String employeeNumber) throws DataNotFound {

		final List<String> headers = new ArrayList<>();
		final List<List<String>> bodyContents = new ArrayList<>();
		final List<String> finallists = new ArrayList<String>();
		// ......................For the Second Field Type....................//
		final List<String> headers2 = new ArrayList<>();
		final List<List<String>> bodyContents2 = new ArrayList<>();
		final List<String> finallists2 = new ArrayList<String>();
		// .............................For the User
		// Named..............................//
		final LinkedHashMap<String, List<String>> achievementbodyContains = new LinkedHashMap<String, List<String>>();
		final LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModels = new LinkedHashSet<AchievementLeaderNamedModel>();
		// ...................... Field
		// Types..................................//
		AcheivementLeaderModel acheivementLeaderModel = new AcheivementLeaderModel();
		RoleEntity roleEntity = roleDao.find(roleId);
		

		if (roleEntity == null) {
			throw new DataNotFound(massageUtil.getBundle("role.id.not.found"));
		}

		String roleName = roleEntity.getRoleName();
		if (roleName.equals("Circle Head")) {
			acheivementLeaderModel.setRoleName("CircleHead");
		} else if (roleName.equals("Branch Head")) {
			acheivementLeaderModel.setRoleName("BranchHead");
		} else {
			acheivementLeaderModel.setRoleName("NormalHead");
		}

		System.out.println("RoleNAme:::" + roleName);

		CampaignEntity campaignEntity = campaignDao.find(campId);

		if (campaignEntity == null) {
			throw new DataNotFound(
					massageUtil.getBundle("campaign.id.not.found"));
		}

		RoleCampaignEntity roleCampaignEntity = roleCampaignDao
				.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);

		if (roleCampaignEntity == null) {
			throw new DataNotFound(
					massageUtil.getBundle("role-campaign.id.not.found"));
		}

		/**
		 * Role Name Circle Head Section Details
		 */

		if (roleName.equals("Circle Head")) {
			List<AchievementFieldEntity> achievementFieldEntities = achievementFieldDao
					.findAllAgainstRoleCampaign(roleCampaignEntity);

			if (achievementFieldEntities == null
					|| achievementFieldEntities.isEmpty()
					|| achievementFieldEntities.size() == 0) {

				throw new DataNotFound(
						massageUtil
								.getBundle("Acheivement.roleCampaign.not.exist"));

			}
			List<AchievementFieldValueEntity> achievementFieldValueEntities = null;

			for (AchievementFieldEntity entity : achievementFieldEntities) {

				headers.add(entity.getFiledName());

				final List<String> strings = new ArrayList<>();

			    achievementFieldValueEntities = achievementFieldValueDao
						.findAllAgainstAcheivementId(entity);

				// System.out.println("Achievement Field Entities Values"+achievementFieldValueEntities.size());

				/* If Achievement Field Value Table doesn't return any Values */

				if (achievementFieldValueEntities == null) {
					throw new DataNotFound(
							massageUtil
									.getBundle("Achievement.Value.not.found"));
				}

				for (AchievementFieldValueEntity valueEntity : achievementFieldValueEntities) {
					strings.add(valueEntity.getFieldValue());
				}

				bodyContents.add(strings);
			}

			System.out.println("---------------Subhajit------------------");
			for (String header : headers) {
				System.out.print(" | " + header + " | ");
			}
			System.out.println("\n------------------------------------------");
			if (achievementFieldValueEntities != null) {
				int length = bodyContents.get(0).size();

				for (int i = 0; i < length; i++) {
					for (List<String> strings : bodyContents) {
						System.out.print(" | " + strings.get(i) + " | ");
						finallists.add(strings.get(i));
					}
					System.out.println("\n");
				}
			}
			//System.out.println("Final list sizes values are:::::" + finallists);

			System.out.println("\n------------------------------------------");
			System.out
					.println("====================NARU=======================");

		}

		/*
		 * .................................Circle Head
		 * Section..........................
		 */

		/*
		 * ....................For the Branch Head Role
		 * Type..............................
		 */
		else if (roleName.equals("Branch Head")) {
			List<AchievementFieldEntity> achievementFieldEntitiesFirstFieldType = achievementFieldDao
					.findAllAgainstRoleCampaignFirstType(roleCampaignEntity);
			List<AchievementFieldEntity> achievementFieldEntitiesSecondFieldType = achievementFieldDao
					.findAllAgainstRoleCampaignSecondType(roleCampaignEntity);

			// .....................For the First Field
			// Type.............................//
			List<AchievementFieldValueEntity> achievementFieldValueEntities = null;

			for (AchievementFieldEntity entity : achievementFieldEntitiesFirstFieldType) {
				headers.add(entity.getFiledName());

				final List<String> strings = new ArrayList<>();

				achievementFieldValueEntities = achievementFieldValueDao
						.findAllAgainstAcheivementId(entity);

				for (AchievementFieldValueEntity valueEntity : achievementFieldValueEntities) {
					strings.add(valueEntity.getFieldValue());
				}

				bodyContents.add(strings);
			}

			System.out
					.println("---------------Subhajit First------------------");
			for (String header : headers) {
				System.out.print(" | " + header + " | ");
			}
			System.out.println("\n------------------------------------------");
			if (achievementFieldValueEntities != null) {

				int lengthfirst = bodyContents.get(0).size();

				for (int i = 0; i < lengthfirst; i++) {
					for (List<String> strings : bodyContents) {
						System.out.print(" | " + strings.get(i) + " | ");
						finallists.add(strings.get(i));
					}
					System.out.println("\n");
				}
			}

			System.out.println("\n------------------------------------------");
			System.out
					.println("====================NARU First=======================");

			// .................END of the First Field
			// Type............................//

			// .....................Starting of the Second Field
			// Type........................//
			List<AchievementFieldValueEntity> achievementFieldValueEntities1 = null;
			for (AchievementFieldEntity entity : achievementFieldEntitiesSecondFieldType) {
				
				headers2.add(entity.getFiledName());

				final List<String> strings2 = new ArrayList<>();

				achievementFieldValueEntities1 = achievementFieldValueDao
						.findAllAgainstAcheivementId(entity);

				for (AchievementFieldValueEntity valueEntity : achievementFieldValueEntities1) {
					strings2.add(valueEntity.getFieldValue());
				}

				bodyContents2.add(strings2);
			}

			System.out
					.println("---------------Subhajit Second------------------");
			for (String header2 : headers2) {
				System.out.print(" | " + header2 + " | ");
			}
			System.out.println("\n------------------------------------------");

			if (achievementFieldValueEntities1 != null) {

				int lengthSecond = bodyContents2.get(0).size();

				for (int i = 0; i < lengthSecond; i++) {
					for (List<String> strings2 : bodyContents2) {
						System.out.print(" | " + strings2.get(i) + " | ");
						finallists2.add(strings2.get(i));
					}
					System.out.println("\n");
				}

			}
			// System.out.println("Final list sizes values are:::::"+finallists2);

			System.out.println("\n------------------------------------------");
			System.out
					.println("====================NARU Second=======================");

		}

		// .....................END of the Second field
		// Type..............................//

		// }

		/*
		 * ....................End of the Branch Head Role
		 * Type..............................
		 */

		/*
		 * ............................Start of Normal Head Role
		 * Type......................................
		 */

		else {
			//int empId = 33;
			String empId=employeeNumber; 
			
			
			System.out.println("EMPID issss:::"+empId);
			//
			// String emp_no="PGR-1234";
			// List<Integer> rankNumbers=new ArrayList<Integer>();
			//
			// List<AchievementFieldValueEntity>
			// achievementFieldValueEntities=achievementFieldValueDao.findAgainstEmp_no();
			//
			// for(AchievementFieldValueEntity
			// achievementFieldValueEntity:achievementFieldValueEntities){
			// String fieldLevel=achievementFieldValueEntity.getFieldLevel();
			// }
			//
			// String ranking="Ranking";
			// List<AchievementFieldEntity>
			// achievementFieldEntities=achievementFieldDao.findIdAgainstRanking("ranking");
			// int
			// acheivementIdAgainstRanking=achievementFieldEntities.get(0).getAchievementFieldId();
			//
			// List<AchievementFieldValueEntity>
			// achievementFieldValueEntitiesRank=achievementFieldValueDao.findallAgainstAchieveentIdAgainstRanking(acheivementIdAgainstRanking)
			// ;
			//
			// for(AchievementFieldValueEntity
			// achievementFieldValueEntity:achievementFieldValueEntitiesRank){
			// rankNumbers.add(Integer.valueOf(achievementFieldValueEntity.getFieldValue()));
			//
			//
			// }
			//
			//
			//
			// Collections.sort(rankNumbers);
			//
			// List<AchievementFieldValueEntity>
			// achievementFieldValueEntities2=achievementFieldValueDao.findagainstrankiniIdAndEmpLevel("fieldLevel",acheivementIdAgainstRanking);
			//
			// String
			// rankingNumberOfCurrentEmp=achievementFieldValueEntities2.get(0).getFieldValue();
			//
			// for(int i=0;i<rankNumbers.size();i++){
			//
			// if(rankNumbers.get(i)==Integer.valueOf(rankingNumberOfCurrentEmp)){
			// int currentPosition=i;
			//
			// }
			//
			//
			//
			// }
			//
			System.out.println("Normal head under");

			List<Object[]> achievementFieldValueEntities = achievementFieldValueDao
					.findAllAchievementFieldValueEntityByEmpNowOfSameFieldLavel(empId);
			/**
			 * <h>Emp Number does not exist<h>
			 */
			if (achievementFieldValueEntities == null) {
				throw new DataNotFound(
						massageUtil.getBundle("emp.name.not.found"));
			}
			
			if(achievementFieldValueEntities.size()==0){
				throw new DataNotFound(
						massageUtil.getBundle("emp.name.not.found"));
			}

			ArrayList<String> fieldLavels = new ArrayList<String>();

			fieldLavels
					.add(String.valueOf(achievementFieldValueEntities.get(0)[6]));
			/**
			 * get all Lower field level against ranking level
			 */
			List<AchievementFieldValueEntity> achievementFieldValueEntitiesList = achievementFieldValueDao
					.findAchievementFieldValueEntityListOuperrLevelAndLowerLevel(
							"<", Integer.parseInt(String
									.valueOf(achievementFieldValueEntities
											.get(0)[5])),
							Integer.parseInt(String
									.valueOf(achievementFieldValueEntities
											.get(0)[8])));
			/**
			 * EntityList of Upper And Lower Level
			 */

			// if (achievementFieldValueEntitiesList == null) {
			// throw new
			// DataNotFound(massageUtil.getBundle("emp.id.not.found"));
			// }

			for (Iterator iterator = achievementFieldValueEntitiesList
					.iterator(); iterator.hasNext();) {
				AchievementFieldValueEntity achievementFieldValueEntity = (AchievementFieldValueEntity) iterator
						.next();
				fieldLavels.add(achievementFieldValueEntity.getFieldLevel());
			}
			/**
			 * get all upper field level against ranking level
			 */
			achievementFieldValueEntitiesList = achievementFieldValueDao
					.findAchievementFieldValueEntityListOuperrLevelAndLowerLevel(
							">", Integer.parseInt(String
									.valueOf(achievementFieldValueEntities
											.get(0)[5])),
							Integer.parseInt(String
									.valueOf(achievementFieldValueEntities
											.get(0)[8])));
			for (Iterator iterator = achievementFieldValueEntitiesList
					.iterator(); iterator.hasNext();) {
				AchievementFieldValueEntity achievementFieldValueEntity = (AchievementFieldValueEntity) iterator
						.next();
				fieldLavels.add(achievementFieldValueEntity.getFieldLevel());
			}
			achievementFieldValueEntitiesList = achievementFieldValueDao
					.findAllLevelTop20RankingByLevel(fieldLavels);
			fieldLavels.clear();
			for (Iterator iterator = achievementFieldValueEntitiesList
					.iterator(); iterator.hasNext();) {
				AchievementFieldValueEntity achievementFieldValueEntity = (AchievementFieldValueEntity) iterator
						.next();
				fieldLavels.add(achievementFieldValueEntity.getFieldLevel());
			}
			achievementFieldValueEntitiesList = achievementFieldValueDao
					.findAchievementFieldValueEntityListbyLevel(fieldLavels);
			// System.out.println("query is executed achievementFieldValueDao");
			// System.out.println("achievementFieldValueEntitiesList.size()"+achievementFieldValueEntitiesList.size());
			for (AchievementFieldValueEntity achievementFieldValueEntity : achievementFieldValueEntitiesList) {
				AchievementLeaderNamedModel achievementLeaderNamedModel = new AchievementLeaderNamedModel();
				achievementLeaderNamedModel
						.setHeader(achievementFieldValueEntity
								.getAchievementFieldEntity().getFiledName());

				achievementLeaderNamedModels.add(achievementLeaderNamedModel);

				List<String> bodydetails = null;
				if (achievementbodyContains.get(achievementFieldValueEntity
						.getFieldLevel()) == null) {
					bodydetails = new ArrayList<String>();
				} else {
					bodydetails = achievementbodyContains
							.get(achievementFieldValueEntity.getFieldLevel());
				}

				bodydetails.add(achievementFieldValueEntity.getFieldValue());
				// System.out.println("-------------------------------------Gson val");
				// System.out.println("getFieldValue()="+achievementFieldValueEntity.getFieldValue()+", getFieldLevel()="+achievementFieldValueEntity.getFieldLevel()+", getAchievementFieldEntity()="+achievementFieldValueEntity.getAchievementFieldEntity().getAchievementFieldId());
				achievementbodyContains.put(
						achievementFieldValueEntity.getFieldLevel(),
						bodydetails);

			}

			System.out.println("Header of Achivement field value");
			for (AchievementLeaderNamedModel achievementLeaderNamedModel : achievementLeaderNamedModels) {
				// System.out.println(achievementLeaderNamedModel.getHeader());
			}
			System.out.println("Body of Achivement field value");
			for (Entry<String, List<String>> entry : achievementbodyContains
					.entrySet()) {
				System.out.println("key :: " + entry.getKey());
				for (String string : entry.getValue()) {
					System.out.print(" " + string);
				}
				System.out.println();

			}

			System.out.println("*****************************************");
			getTop20AchievementBySolId(empId, acheivementLeaderModel);

		}

		/* ........ END of NORMAL HEAD ROLE TYPE............................ */

		acheivementLeaderModel.setFinalList(finallists);
		acheivementLeaderModel.setHeaderList(headers);
		acheivementLeaderModel.setBodycontains(bodyContents);
		acheivementLeaderModel.setFinalList2(finallists2);
		acheivementLeaderModel.setHeaderList2(headers2);
		acheivementLeaderModel.setBodycontains2(bodyContents2);
		acheivementLeaderModel
				.setAchievementbodyContains(achievementbodyContains);
		acheivementLeaderModel
				.setAchievementLeaderNamedModels(achievementLeaderNamedModels);
		if (roleName.equals("Circle Head")) {
			acheivementLeaderModel.setRoleName("CircleHead");
		} else if (roleName.equals("Branch Head")) {
			acheivementLeaderModel.setRoleName("BranchHead");
		} else {
			acheivementLeaderModel.setRoleName("NormalHead");
		}

		return acheivementLeaderModel;

	}

	/*
	 * public void getAcheivementFieldValues(int campId,int roleId){
	 * 
	 * List<String> fieldNames=new ArrayList<String>();
	 * 
	 * List<String> fieldValues=new ArrayList<String>(); int i=0; int k=0;
	 * RoleEntity roleEntity=roleDao.find(roleId); CampaignEntity
	 * campaignEntity=campaignDao.find(campId); RoleCampaignEntity
	 * roleCampaignEntity
	 * =roleCampaignDao.finfIdagainstRoleandCampaign(roleEntity,
	 * campaignEntity); List<AchievementFieldEntity>
	 * achievementFieldEntities=achievementFieldDao
	 * .findAllAgainstRoleCampaign(roleCampaignEntity);
	 * System.out.println("Sizes of acheivement field entity are:::"
	 * +achievementFieldEntities.size()); int temp=i; for(AchievementFieldEntity
	 * achievementFieldEntity:achievementFieldEntities){ temp=k; i=temp;
	 * System.out
	 * .println("achievementFieldEntity.getFiledName()"+achievementFieldEntity
	 * .getFiledName()); fieldNames.add(achievementFieldEntity.getFiledName());
	 * //i++; List<AchievementFieldValueEntity>
	 * achievementFieldValueEntities=achievementFieldValueDao
	 * .findAllAgainstAcheivementId(achievementFieldEntity); int
	 * fieldValueSize=achievementFieldValueEntities.size();
	 * System.out.println("Field values sizes"+fieldValueSize);
	 * for(AchievementFieldValueEntity
	 * achievementFieldValueEntity:achievementFieldValueEntities){
	 * System.out.println
	 * ("achievementFieldValueEntity.getFieldValue()"+achievementFieldValueEntity
	 * .getFieldValue()); System.out.println("Values of i"+ i);
	 * fieldValues.add(achievementFieldValueEntity.getFieldValue());
	 * //fieldValues.add(i, achievementFieldValueEntity.getFieldValue());
	 * //fieldValues.a //i= i + fieldValueSize;
	 * System.out.println("For loop running continiously.......");
	 * 
	 * } List<String> finalfieldValue=new ArrayList<String>();
	 * finalfieldValue.add(fieldValues.get(0)); for(i=0;i<fieldValueSize;i++){
	 * 
	 * finalfieldValue.add(e) }
	 * 
	 * 
	 * k++; } System.out.println("After the outer for loop");
	 * System.out.println("Field Names"+fieldNames);
	 * System.out.println("Final List issssss"+fieldValues);
	 * 
	 * 
	 * }
	 */

	public void getTop20AchievementBySolId(String empID,
			AcheivementLeaderModel acheivementLeaderModel) throws DataNotFound {
		final LinkedHashMap<String, List<String>> achievementbodyContains = new LinkedHashMap<String, List<String>>();
		final LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModels = new LinkedHashSet<AchievementLeaderNamedModel>();

		List<Object[]> achievementFieldValueEntities = achievementFieldValueDao
				.findAllAchievementFieldValueEntityByEmpNowOfSameFieldLavelBasedSolID(empID);

		if (achievementFieldValueEntities == null) {
			throw new DataNotFound(
					massageUtil.getBundle("emp.id.solId.not.found"));
		}
		
		if(achievementFieldValueEntities.size()==0){
			throw new DataNotFound(
					massageUtil.getBundle("emp.id.solId.not.found"));
		}

		ArrayList<String> fieldLavels = new ArrayList<String>();

		List<AchievementFieldValueEntity> achievementFieldValueEntitiesList = achievementFieldValueDao
				.findAllLevelBySolId(
						String.valueOf(achievementFieldValueEntities.get(0)[5]),
						Integer.parseInt(String
								.valueOf(achievementFieldValueEntities.get(0)[8])));

		for (Iterator iterator = achievementFieldValueEntitiesList.iterator(); iterator
				.hasNext();) {
			AchievementFieldValueEntity achievementFieldValueEntity = (AchievementFieldValueEntity) iterator
					.next();
			fieldLavels.add(achievementFieldValueEntity.getFieldLevel());
		}

		achievementFieldValueEntitiesList = achievementFieldValueDao
				.findAllLevelTop20RankingByLevel(fieldLavels);
		fieldLavels.clear();
		for (Iterator iterator = achievementFieldValueEntitiesList.iterator(); iterator
				.hasNext();) {
			AchievementFieldValueEntity achievementFieldValueEntity = (AchievementFieldValueEntity) iterator
					.next();
			fieldLavels.add(achievementFieldValueEntity.getFieldLevel());
		}
		achievementFieldValueEntitiesList = achievementFieldValueDao
				.findAchievementFieldValueEntityListbyLevel(fieldLavels);

		for (AchievementFieldValueEntity achievementFieldValueEntity : achievementFieldValueEntitiesList) {
			AchievementLeaderNamedModel achievementLeaderNamedModel = new AchievementLeaderNamedModel();
			achievementLeaderNamedModel.setHeader(achievementFieldValueEntity
					.getAchievementFieldEntity().getFiledName());

			achievementLeaderNamedModels.add(achievementLeaderNamedModel);

			List<String> bodydetails = null;
			if (achievementbodyContains.get(achievementFieldValueEntity
					.getFieldLevel()) == null) {
				bodydetails = new ArrayList<String>();
			} else {
				bodydetails = achievementbodyContains
						.get(achievementFieldValueEntity.getFieldLevel());
			}

			bodydetails.add(achievementFieldValueEntity.getFieldValue());
			// System.out.println("-------------------------------------Gson val");
			// System.out.println("getFieldValue()="+achievementFieldValueEntity.getFieldValue()+", getFieldLevel()="+achievementFieldValueEntity.getFieldLevel()+", getAchievementFieldEntity()="+achievementFieldValueEntity.getAchievementFieldEntity().getAchievementFieldId());
			achievementbodyContains.put(
					achievementFieldValueEntity.getFieldLevel(), bodydetails);

		}

		System.out.println("Header of Achivement field value");
		for (AchievementLeaderNamedModel achievementLeaderNamedModel : achievementLeaderNamedModels) {
			System.out.println(achievementLeaderNamedModel.getHeader());
		}
		System.out.println("Body of Achivement field value");
		for (Entry<String, List<String>> entry : achievementbodyContains
				.entrySet()) {
			System.out.println("key :: " + entry.getKey());
			for (String string : entry.getValue()) {
				System.out.print(" " + string);
			}
			System.out.println();

		}
		acheivementLeaderModel
				.setAchievementbodyContainsTop20(achievementbodyContains);
		acheivementLeaderModel
				.setAchievementLeaderNamedModelsTop20(achievementLeaderNamedModels);

		// return null;
	}
	
	
	public AcheivementLeaderModel getRoleNameagainstId(int roleId){
		AcheivementLeaderModel acheivementLeaderModel=new AcheivementLeaderModel();
		RoleEntity roleEntity =roleDao.find(roleId);
		
		acheivementLeaderModel.setRoleNameController(roleEntity.getRoleName());
		return acheivementLeaderModel;
		
	}

}
