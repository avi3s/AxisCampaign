package com.axis.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.axis.converter.TargetFieldValueConverter;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.TargetFieldValueDao;
import com.axis.entity.RoleCampaignEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.model.TargetFieldValueModel;
import com.axis.validation.TargetFieldValueUploadValidation;

@Service
@Transactional
public class TargetFieldValueUploadService {

private static final Logger logger = Logger.getLogger(TargetFieldValueUploadService.class);
	
	@Autowired
	private TargetFieldValueConverter targetFieldValueConveter;
	
	@Autowired
	private TargetFieldValueDao targetFieldValueDao;
	
	@Autowired
	private RoleCampaignDao roleCampaignDao;
	
	@Autowired
	private MessageUtil massageUtil;
	
	@Autowired
	private TargetFieldValueUploadValidation targetFieldValueUploadValidation;
	
	
	@SuppressWarnings("null")
	public List<TargetFieldValueModel> getFileandSave(int roleId, int campaignId, MultipartFile file) throws DataNotFound, FormExceptions{
	    
		if(logger.isDebugEnabled()){
			logger.debug("getFileAndService - TargetFieldValueUploadService - Start.");
		}
		
		targetFieldValueUploadValidation.targetFieldValueUploadCreateValidate(roleId, campaignId, file);
		
		List<TargetFieldValueModel> targetValueList = new ArrayList<TargetFieldValueModel>();
		List<Integer> fieldName_id = new  ArrayList<Integer>();
		List<Integer> mismatchedRowsList = new ArrayList<Integer>();
		int successCount = 0;
		
		try { 
	        	
			   String fileName= file.getOriginalFilename();
	    	   System.out.println("file: **** "+fileName);
	    	   
	    	
	    	   int rows;
	    	   if (!file.getOriginalFilename().contains(".xlsx")) {
	    	   
	        	POIFSFileSystem fileSystem = null; 
	        	fileSystem = new POIFSFileSystem(new ByteArrayInputStream(file.getBytes()));            
	        	// excel  
	            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
	            HSSFSheet sheet = workbook.getSheetAt(0);  
	            // excel  
	            rows = sheet.getPhysicalNumberOfRows();  	            
	            
	            System.out.println("total rows in excel: "+rows);
	            
             
            // fieldName_id = targetFieldValueDao.findFieldNameId(Integer.parseInt(campaignId));
             
             RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(roleId, campaignId);     		
     		
     		
     		fieldName_id = targetFieldValueDao.findFieldNameId(roleCampaign.getRoleCampaignId());
     		
             System.out.println("fieldName_id: "+fieldName_id);
             
	            //   
	            for (int i = 0; i <= rows; i++) {  
	                //   
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
	                                    value += (long)cell.getNumericCellValue()+",";  
	                                    break;  
	                                case HSSFCell.CELL_TYPE_STRING:  
	                                    value += cell.getStringCellValue()+",";  
	                                    break;  
	                                default:  
	                                    break;  
	                            }  
	                        }  
	                    }  
	                    
	                    System.out.println("Value for excel cell: "+ value);
	                    
	                    String[] val = value.split(",");
	                    
	                    
	                    if(val.length == fieldName_id.size()){
	                    
	                    //System.out.println(val.length);
	                    
	                    if(i > 0){
	                    
		            		if(fieldName_id.size() > 0) {	            			
		            			
		            			for(int k=0; k < fieldName_id.size(); k++) {
		            				TargetFieldValueModel targetFieldValueModel = new TargetFieldValueModel();
		            				targetFieldValueModel.setFieldValue(val[k]);
		            				//targetFieldValueModel.setStatus(Status.ACTIVE);
		            				targetFieldValueModel.setCreatedBy(1);
		            				targetFieldValueModel.setUpdatedBy(1);
		            				targetFieldValueModel.setTargetId(fieldName_id.get(k));
		            				//targetFieldValueModel.setTargetFieldValue_id(targetFieldValue_id);
		            				targetFieldValueDao.create(targetFieldValueConveter
		            						.modelToEntity(targetFieldValueModel));	
		            				
		            				targetValueList.add(targetFieldValueModel);
		            				successCount++;		            				
		            			}
		            		}		            		
		                  
		                  }	
	                    }
	                    else{
	                    	
	                    	mismatchedRowsList.add(i + 1);
	                    }
	                }  
	            } 
	           
	    	   }
	           
	    	   else if(file.getOriginalFilename().contains(".xlsx")){
	    		   File excelfile = convert(file);
					FileInputStream inputStream = new FileInputStream(excelfile);

					XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
					XSSFSheet sheet = workbook.getSheetAt(0);
					rows = sheet.getPhysicalNumberOfRows();
					
					 //List<Integer> fieldName_id;
			            // fieldName_id = targetFieldValueDao.findFieldNameId(Integer.parseInt(campaignId));
			             
			             RoleCampaignEntity roleCampaign = roleCampaignDao.findRoleCampaign(roleId, campaignId);     		
			     		
			     		
			     		fieldName_id = targetFieldValueDao.findFieldNameId(roleCampaign.getRoleCampaignId());
			     		
			             System.out.println("fieldName_id: "+fieldName_id);
			             
				            //   
				            for (int i = 0; i <= rows; i++) {  
				                //   
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
				                                    value += (long)cell.getNumericCellValue()+",";  
				                                    break;  
				                                case XSSFCell.CELL_TYPE_STRING:  
				                                    value += cell.getStringCellValue()+",";  
				                                    break;  
				                                default:  
				                                    break;  
				                            }  
				                        }  
				                    }  
				                    
				                    System.out.println("Value for excel cell: "+ value);
				                    
				                    String[] val = value.split(",");
				                    
				                    System.out.println("cell value length: "+val.length +" field name length: "+fieldName_id.size());
				                    
				                    if(val.length == fieldName_id.size()){
				                    if(i > 0){
				                    
					            		if(fieldName_id.size() > 0) {	            			
					            			
					            			for(int k=0; k < fieldName_id.size(); k++) {
					            				TargetFieldValueModel targetFieldValueModel = new TargetFieldValueModel();
					            				targetFieldValueModel.setFieldValue(val[k]);
					            				//targetFieldValueModel.setStatus(Status.ACTIVE);
					            				targetFieldValueModel.setCreatedBy(1);
					            				targetFieldValueModel.setUpdatedBy(1);
					            				targetFieldValueModel.setTargetId(fieldName_id.get(k));
					            				//targetFieldValueModel.setTargetFieldValue_id(targetFieldValue_id);
					            				targetFieldValueDao.create(targetFieldValueConveter
					            						.modelToEntity(targetFieldValueModel));	            				
					            				
					            				targetValueList.add(targetFieldValueModel);
					            				successCount++;
					            			}
					            		}
				                    }
				                    	
				                    } //end of val.length == fieldName_id.size()
				                    else{	
				                    	mismatchedRowsList.add(i + 1);
				                    }	   
				                }  
				            } //end of outer loop i<rows				           
	    	   		}
	    	   } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  	            
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  	            
	        }
		
		if(logger.isDebugEnabled()){
			logger.debug("getFileAndService - TargetFieldValueUploadService - End.");
		}
		
		TargetFieldValueModel unmatchedRows = null;
		System.out.println("mismatchedRowsList isEmpty: "+ mismatchedRowsList.isEmpty());
		if(!mismatchedRowsList.isEmpty()){
	        unmatchedRows = new TargetFieldValueModel();
			unmatchedRows.setSuccessNo(successCount);
			unmatchedRows.setUnmatchedLists(mismatchedRowsList);
		}
		
		targetValueList.add(unmatchedRows);
		return targetValueList;
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
