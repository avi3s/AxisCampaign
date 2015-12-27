package com.axis.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.EscalationMatrixConverter;
import com.axis.dao.EscalationMatrixDao;
import com.axis.entity.EscalationMatrixEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.EmailExits;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.PhoneNoExits;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignModel;
import com.axis.model.EscalationMatrixModel;
import com.axis.validation.EscalationBusinessDataValidation;

@Service
@Transactional
public class EscalationMatrixUploadservice {

	private static final Logger logger = Logger.getLogger(EscalationMatrixUploadservice.class);
	
	@Autowired
	private EscalationMatrixConverter escalationMatrixConverter;

	@Autowired
	private EscalationMatrixDao escalationMatrixDao;

	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private EscalationMatrixService escalationMatrixService;

	@Autowired
	private EscalationBusinessDataValidation escalationBusinessDataValidation;
	


	
	

	public Map<String,List<EscalationMatrixModel>> getFileandSave(int campaignId,String type,MultipartFile file) throws EmailExits, PhoneNoExits, DataNotFound,
			RecordFound, FormExceptions, ObjectNotFound {
		
		if(logger.isDebugEnabled()){
			logger.debug("getFileAndService - EscalationMatrixUploadservice - Start.");
		}
		
		int errorCell = 0;
		int errorRowCount = 0;
		int rows = 0;
		int rowMissMatchCount = 0;
		StringBuffer sb = new StringBuffer("Test");
		
		Map<String,List<EscalationMatrixModel>> failedmodels;
		failedmodels = new HashMap();
		List<EscalationMatrixModel> mismatchedRowsList = new ArrayList<EscalationMatrixModel>();
		List<EscalationMatrixModel> existRecordLists = new ArrayList<>();
		List<EscalationMatrixModel> errorRowLists = new ArrayList<>();
		String nameFromExcel = "";
		String emailFromExcel = "";

		String contactNoFromExcel = "";
		//String typeFromExcel = "";

		try {
			StringBuffer sb1 = new StringBuffer("");
			int user_upload_rows = 3;

			System.out.println(file);

			List<EscalationMatrixEntity> userList = escalationMatrixDao
					.findAllActiveEscalationMatrixByStatus();


			
			if (!file.getOriginalFilename().contains(".xlsx")) {
				
				// excel
				POIFSFileSystem fileSystem = null;
				fileSystem = new POIFSFileSystem(new ByteArrayInputStream(
						file.getBytes()));
				HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
				HSSFSheet sheet = workbook.getSheetAt(0);
				rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Total Rows :-" + rows);
				for (int i = 0; i < rows; i++) {
					boolean valid_flag = true;
					errorCell = errorCell + 1;
					HSSFRow row = sheet.getRow(i);
					if (row != null) {
						// excel
						int cells = row.getPhysicalNumberOfCells();
						String value = "";
						//
						for (int j = 0; j < cells; j++) {
							//
							HSSFCell cell = row.getCell(j);
							if (cell != null) {
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_FORMULA:
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									value += (long) cell.getNumericCellValue()
											+ ",";
									break;
								case HSSFCell.CELL_TYPE_STRING:
									value += cell.getStringCellValue() + ",";
									break;
								default:
									break;
								}
							}
						}

						String[] val = value.split(",");

						if (val.length == user_upload_rows) {
							 rowMissMatchCount = rowMissMatchCount + 1;
							System.out.println(val.length);

							
							CampaignModel campaignModel = new CampaignModel();
							
	     	            
							if (i > 0) {

								
								if (user_upload_rows > 0) {

									for (int k = 1; k < val.length-1; k++) {
										EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();
										EscalationMatrixEntity escalationMatrixEntity = new EscalationMatrixEntity();
										nameFromExcel = val[0];
										emailFromExcel = val[1];
										contactNoFromExcel = val[2];
									//typeFromExcel = val[3];

										escalationMatrixModel
												.setName(nameFromExcel);

										escalationMatrixModel
												.setEmail(emailFromExcel);

										escalationMatrixModel.setContactNumber(contactNoFromExcel);

										escalationMatrixModel.setCampaignId(campaignId);
										escalationMatrixModel
												.setCreateDate(new Date());
										escalationMatrixModel.setType(type);

										EscalationMatrixEntity emailEscalationMatrixEntity = escalationMatrixDao
												.findByEmailId(emailFromExcel);

										EscalationMatrixEntity phoneNOEscalationMatrixEntity = escalationMatrixDao
												.findByContactNumber(contactNoFromExcel);
										
										if(!Util.emailValidate(emailFromExcel) && !Util.mNoValidate(contactNoFromExcel))
										{
											escalationMatrixModel.setIssue("Email and MobileNumber is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}
										else if (!Util.emailValidate(emailFromExcel)) {
											escalationMatrixModel.setIssue("Email format is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;

										} else if (!Util.mNoValidate(contactNoFromExcel)) {
											escalationMatrixModel.setIssue("Mobile number is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
											
										}
										else if(emailEscalationMatrixEntity != null && phoneNOEscalationMatrixEntity != null)
										{
											escalationMatrixModel.setIssue("Email and Mobile already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}
										else if (emailEscalationMatrixEntity != null) {
											escalationMatrixModel.setIssue("Email already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}else if (phoneNOEscalationMatrixEntity != null) {
											escalationMatrixModel.setIssue("Mobile no already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}else{
											escalationMatrixDao.create(escalationMatrixConverter.modelToEntity(escalationMatrixModel));
										}
									}
											}
										
									
								
							}
						} else {

							if (user_upload_rows > 0)  {
								rowMissMatchCount = rowMissMatchCount + 1;
								EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();
								escalationMatrixModel.setRowMissMatchCount(rowMissMatchCount);
								errorRowLists.add(escalationMatrixModel);
							System.out.println("System.out.println(rowMissMatchCount);");
							System.out.println(rowMissMatchCount);
		                 }
						}
						}
			}
				} else if (file.getOriginalFilename().contains(".xlsx")) {
				File excelfile = convert(file);
				FileInputStream inputStream = new FileInputStream(excelfile);
				// excel
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				rows = sheet.getPhysicalNumberOfRows();
				for (int i = 0; i < rows; i++) {
					boolean valid_flag = true;
					errorCell = errorCell + 1;
					XSSFRow row = sheet.getRow(i);
					if (row != null) {
						// excel
						int cells = row.getPhysicalNumberOfCells();
						String value = "";
						//
						for (int j = 0; j < cells; j++) {
							//
							XSSFCell cell = row.getCell(j);
							if (cell != null) {
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									break;
								case XSSFCell.CELL_TYPE_NUMERIC:
									value += (long) cell.getNumericCellValue()
											+ ",";
									break;
								case XSSFCell.CELL_TYPE_STRING:
									value += cell.getStringCellValue() + ",";
									break;
								default:
									break;
								}
							}
						}
			
						String[] val = value.split(",");

						if (val.length == user_upload_rows) {
						 rowMissMatchCount = rowMissMatchCount + 1;
							System.out.println(val.length);

							
							CampaignModel campaignModel = new CampaignModel();
							
	     	            
							if (i > 0) {

								
								if (user_upload_rows > 0) {

									for (int k = 1; k < val.length-1; k++) {
										EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();
										EscalationMatrixEntity escalationMatrixEntity = new EscalationMatrixEntity();
										nameFromExcel = val[0];
										emailFromExcel = val[1];
										contactNoFromExcel = val[2];
									//typeFromExcel = val[3];

										escalationMatrixModel
												.setName(nameFromExcel);

										escalationMatrixModel
												.setEmail(emailFromExcel);

										escalationMatrixModel.setContactNumber(contactNoFromExcel);

										escalationMatrixModel.setCampaignId(campaignId);
										escalationMatrixModel
												.setCreateDate(new Date());
										escalationMatrixModel.setType(type);

										EscalationMatrixEntity emailEscalationMatrixEntity = escalationMatrixDao
												.findByEmailId(emailFromExcel);

										EscalationMatrixEntity phoneNOEscalationMatrixEntity = escalationMatrixDao
												.findByContactNumber(contactNoFromExcel);
										
										if(!Util.emailValidate(emailFromExcel) && !Util.mNoValidate(contactNoFromExcel))
										{
											escalationMatrixModel.setIssue("Email and MobileNumber is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}
                                           
										else if (!Util.emailValidate(emailFromExcel)) {
											escalationMatrixModel.setIssue("Email format is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;

										} else if (!Util.mNoValidate(contactNoFromExcel)) {
											escalationMatrixModel.setIssue("MobileNumber is invalid");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
											
										}
										else if(emailEscalationMatrixEntity != null && phoneNOEscalationMatrixEntity != null)
										{
											escalationMatrixModel.setIssue("Email and Mobile already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}
										else if (emailEscalationMatrixEntity != null) {
											escalationMatrixModel.setIssue("Email already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}else if (phoneNOEscalationMatrixEntity != null) {
											escalationMatrixModel.setIssue("Mobile no already exist");
											existRecordLists.add(escalationMatrixModel);
											errorRowCount = errorRowCount + 1;
										}else{
											escalationMatrixDao.create(escalationMatrixConverter.modelToEntity(escalationMatrixModel));
										}
									}
											}
										
									
								
							}
						} else {

							if (user_upload_rows  > 0) {
								rowMissMatchCount = rowMissMatchCount + 1;
								EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();
								escalationMatrixModel.setRowMissMatchCount(rowMissMatchCount);
								errorRowLists.add(escalationMatrixModel);
							System.out.println("System.out.println(rowMissMatchCount);");
							System.out.println(rowMissMatchCount);
		                 }
						}
						}
			}
				}
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//failedmodels.add(unmatchedRows);
		EscalationMatrixModel escalationMatrixModel = new EscalationMatrixModel();
		
		escalationMatrixModel.setErrorRowCount(String.valueOf(errorRowCount));
		escalationMatrixModel.setTotalRows(String.valueOf(rows));
        List<EscalationMatrixModel> escalationMatrixList = new ArrayList<>();
        escalationMatrixList.add(escalationMatrixModel);
        
		failedmodels.put("existRecordLists", existRecordLists);
		failedmodels.put("errorRowCount", escalationMatrixList);
		failedmodels.put("errorRowLists", errorRowLists);
			
		return failedmodels;
	}

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;

	}

}