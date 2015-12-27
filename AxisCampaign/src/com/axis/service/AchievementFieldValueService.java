package com.axis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;








import com.axis.common.MessageUtil;
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
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;
import com.axis.validation.AcheivementDataValueValidation;



@Service
@Transactional
public class AchievementFieldValueService {
	private static final Logger logger = Logger.getLogger(AchievementFieldValueService.class);
	
@Autowired
AchievementFieldValueDao achievementFieldValueDao;
@Autowired
AchievementFieldValueConverter achievementFieldValueConverter;
@Autowired
RoleDao roleDao;
@Autowired
CampaignDao campaignDao;
@Autowired
RoleCampaignDao roleCampaignDao;
@Autowired
AchievementFieldDao achievementFieldDao;
@Autowired
AchievementFieldConverter achievementFieldConverter;
@Autowired
AcheivementDataValueValidation acheivementDataValueValidation;
@Autowired
private MessageUtil massageUtil;

/**
 * Getting All Achievement Field Values	
 * @return
 * @throws DataNotFound
 * @throws ObjectNotFound
 */

 public List<AcheivementFieldValueModel> getAllAcheivementFieldVAlues() throws DataNotFound, ObjectNotFound{
	 
	 List<AcheivementFieldValueModel> acheivementFieldValueModels=null;
	 
	 List<AchievementFieldValueEntity> achievementFieldValueEntities=achievementFieldValueDao.findAllFieldValuesAgainstStatus();
	 
	 if (achievementFieldValueEntities == null|| achievementFieldValueEntities.isEmpty()|| achievementFieldValueEntities.size() == 0) {
	 //if(achievementFieldValueEntities.isEmpty()){
		 return acheivementFieldValueModels;
	 }
	 else{
		 acheivementFieldValueModels=achievementFieldValueConverter.entityListToModelList(achievementFieldValueEntities) ;
		 
	      
		 return acheivementFieldValueModels; 
	 }
	 

 }
 
                                    /*...................END...................*/
 /**
  * Getting the role Id from the Table 
  * @param roleId1
  * @param campId1
  * @return
  * @throws DataNotFound
  * @throws ObjectNotFound
  */
 
 public List<AcheivementModel> getroleId(Integer roleId1,Integer campId1) throws DataNotFound, ObjectNotFound{
	 
		RoleEntity roleEntity=roleDao.find(roleId1);
		
		CampaignEntity campaignEntity=campaignDao.find(campId1);
		
		RoleCampaignEntity roleCampaignEntity=roleCampaignDao.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);
		
		List<AchievementFieldEntity> achievementFieldEntities=achievementFieldDao.findAllAgainstRoleCampaign(roleCampaignEntity);
		
		System.out.println("Role and Campaign....."+achievementFieldEntities.size());
		
		List<AcheivementModel> acheivementModels=new ArrayList<AcheivementModel>();
		
		for(AchievementFieldEntity achievementFieldEntity:achievementFieldEntities){
			
			AcheivementModel acheivementModel=achievementFieldConverter.entityToModel(achievementFieldEntity);
			
			if(acheivementModel != null)
			
				acheivementModels.add(acheivementModel);	
		}
		return acheivementModels;
	 }
 
 /**
  * Inserting Achievement Field Values
  * @param acheivementFieldValueModel
  * @throws DataNotFound
  * @throws ObjectNotFound
  * @throws RecordFound
  * @throws FormExceptions
  */
 
 public void insertAcheivementFieldValueService(AcheivementFieldValueModel acheivementFieldValueModel) throws DataNotFound, ObjectNotFound, RecordFound,FormExceptions{

		if (logger.isDebugEnabled()) {
			logger.debug("insertTargetFieldValueService-Start");
		}
		//System.out.println("Length of the ARRAY ISSS:::::"+acheivementFieldValueModel.getFieldValue_array().length);
		
		
		acheivementFieldValueModel.setDuplicates("false");
		
		/* ..........Blank Space Checking.................*/
		//if(acheivementFieldValueModel.getFieldValue_array() !=null){
		int duplicate=0;
		
		acheivementFieldValueModel.setBlankspace("false");
		
		if(acheivementFieldValueModel.getFieldValue_array() !=null){
			
		for(int i=0;i<acheivementFieldValueModel.getFieldValue_array().length;i++){
			
			if(acheivementFieldValueModel.getFieldValue_array()[i].equals("")){
				
				System.out.println("Blank space is on there");
				
				duplicate=1;
				
				acheivementFieldValueModel.setBlankspace("true");
			}
		
		}
		//}
		
		if(duplicate==0){
			
		List<String> duplicates=Arrays.asList(acheivementFieldValueModel.getFieldValue_array());
		
		System.out.println("Sizes of the array list is::::"+duplicates.size());
		
		Set<String> foo = new HashSet<String>(duplicates);
		
		System.out.println("Sizes of the set is::::"+foo.size());
		
		if(duplicates.size()!=foo.size()){
			
			acheivementFieldValueModel.setDuplicates("true");
		}
	}
}
			
		acheivementFieldValueModel.setFieldValue("NOT");
		
		acheivementDataValueValidation.validateFormValueAcheivement(acheivementFieldValueModel);
		
		String fieldValue_array1[];
		
		fieldValue_array1 = acheivementFieldValueModel.getFieldValue_array();
		
		if(fieldValue_array1.length > 0) {
			
		for(int i=0; i < fieldValue_array1.length; i++){
		
			System.out.println("Values inside the Acheivement VAlues are"+fieldValue_array1[i]);
		
			acheivementFieldValueModel.setFieldValue(fieldValue_array1[i]);
		
			acheivementDataValueValidation.validateFormValueAcheivement(acheivementFieldValueModel);
		}
	}
		
		String fieldValue_array[];
		
		Integer acheivement_id[];
		
		List<Integer> fieldName_id;		
				
		fieldValue_array = acheivementFieldValueModel.getFieldValue_array();
		
		acheivement_id=acheivementFieldValueModel.getAcheivement_id();
	
		
		
		if(fieldValue_array.length > 0) {
			int maxlevel = achievementFieldValueDao
					.getLastLevelValue();
			int nextLevel = maxlevel + 1;
			
			for(int i=0; i < fieldValue_array.length; i++) {	
				acheivementFieldValueModel.setAcheivementId(acheivement_id[i]);				
				
				acheivementFieldValueModel.setFieldValue(fieldValue_array[i]);
				
				AchievementFieldEntity achievementFieldEntity=achievementFieldDao.find(acheivementFieldValueModel.getAcheivementId());
				
				acheivementFieldValueModel.setAchievementFieldEntity(achievementFieldEntity);
				
				acheivementFieldValueModel.setFieldLevel(String.valueOf(nextLevel));
				
				List<AchievementFieldValueEntity> achievementFieldValueEntities=achievementFieldValueDao.getDuplicateValueCheck(acheivementFieldValueModel);
				
				if(achievementFieldValueEntities.size()==0){
					
					achievementFieldValueDao.create(achievementFieldValueConverter
							.modelToEntity(acheivementFieldValueModel));
				}
				
				else{
					throw new RecordFound(massageUtil.getBundle("cms.path.already.present"));
				}
				
				
				
			}
		}	

		if (logger.isDebugEnabled()) {
			logger.debug("insertAcheivementFieldValueService-End");
		}
	}
 /**
  * Edit Achievement Field Values Services
  * @param acheivementFieldValueId
  * @return
  * @throws DataNotFound
  * @throws ObjectNotFound
  */
 
 public AcheivementFieldValueModel editAcheivementFieldValueServices(Integer acheivementFieldValueId) throws DataNotFound, ObjectNotFound{

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Acheivement Field Value-Start");
		}
		 
		AchievementFieldValueEntity achievementFieldValueEntity=achievementFieldValueDao.find(acheivementFieldValueId);	
		AcheivementFieldValueModel acheivementFieldValueModel=achievementFieldValueConverter.entityToModel(achievementFieldValueEntity);
		if (logger.isDebugEnabled()) {
			logger.debug("EditAcheivementFieldValueService-End");
		}
		return acheivementFieldValueModel;
	}
 
 /**
  * Saving the Edited Achievement Field Value Services
  * @param acheivementFieldValueModel
  * @throws DataNotFound
  * @throws RecordFound
  */
 
 public void saveEditedAcheivementFieldValueServices(AcheivementFieldValueModel acheivementFieldValueModel) throws DataNotFound,RecordFound{

		if (logger.isDebugEnabled()) {
			logger.debug("SaveEditedFieldValueService-Start");
		}
		acheivementDataValueValidation.AcheivementUpdateFieldValueValidate(acheivementFieldValueModel);
		AchievementFieldValueEntity achievementFieldValueEntity=achievementFieldValueDao.find(acheivementFieldValueModel.getAcheivementFieldValueId());
		achievementFieldValueEntity.setFieldValue(acheivementFieldValueModel.getFieldValue());

		achievementFieldValueDao.update(achievementFieldValueEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("insertAcheivementFieldValueService-End");
		}
	}
 
 /**
  * Deleting the Achievement Field Values....
  * @param acheivementFieldValueModel
  * @throws DataNotFound
  */
 
 public void deleteAcheivementFieldValueServices(AcheivementFieldValueModel acheivementFieldValueModel) throws DataNotFound{

		if (logger.isDebugEnabled()) {
			logger.debug("DeleteFieldValueService-Start");
		}
		AchievementFieldValueEntity achievementFieldValueEntity=achievementFieldValueDao.find(acheivementFieldValueModel.getAcheivementFieldValueId());
		
		achievementFieldValueEntity.setStatus(Status.INACTIVE);

		achievementFieldValueDao.update(achievementFieldValueEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("deleteAcheivementFieldValueService-End");
		}
	}

}
