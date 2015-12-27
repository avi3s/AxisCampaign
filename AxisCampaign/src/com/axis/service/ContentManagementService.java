package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.ContentManagementConverter;
import com.axis.dao.ContentManagementDao;
import com.axis.entity.ContentManagementDetailsEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.ContentManagementDetailsModel;
import com.axis.validation.ContentManagementDataValidation;

@Service
@Transactional
public class ContentManagementService {
	
	private static final Logger logger = Logger.getLogger(ContentManagementService.class);

	@Autowired
	private ContentManagementDataValidation businessDataValidation;
	
	@Autowired
	private ContentManagementConverter cmsConverter;

	@Autowired
	private ContentManagementDao cmsDao;
	
	@Autowired
	private MessageUtil massageUtil;
	
	/**
	 * 
	 * This method is use to create content(ADMIN or OTHERS) and hit the database
	 * 
	 * @param cmsModel
	 *            Cms
	 * @throws DataNotFound
	 * @throws ObjectNotFound
	 * @throws RecordFound 
	 * @throws FormExceptions 
	 * */	
	
	public void createContent(ContentManagementDetailsModel cmsModel) throws DataNotFound, ObjectNotFound, RecordFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("createUser-Start");
		}

		businessDataValidation.CmsCreateValidate(cmsModel);
		
		//checking for unique path
		String path = cmsModel.getPath();
		ContentManagementDetailsEntity cmsobj = cmsDao.getCMSFromPath(path);
		if (cmsobj == null){
			ContentManagementDetailsEntity cmsEntity = cmsConverter.modelToEntity(cmsModel);
		cmsDao.create(cmsEntity); // hit a db
		}
		else
		{
			throw new RecordFound(massageUtil.getBundle("cms.path.already.present"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createUser-End");
		}

	}
	
	/**
	 * 
	 * This method is use to view content details by Id(ADMIN or OTHERS) 
	 * 
	 * @param contentId  ContentManagement
	 *           
	 * @return cmsModel ContentManagement class object
	 * @throws ObjectNotFound 
	 * */
	public ContentManagementDetailsModel viewContent(int cmsId) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewCMSDetails(int userId)-Start");
		}

		ContentManagementDetailsEntity cmsEntity = cmsDao.find(cmsId);

		if (cmsEntity == null) {
			throw new ObjectNotFound(massageUtil.getBundle("cms.object.not.found"));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewCMSDetails(int cmsId)-End");
		}

		return cmsConverter.entityToModel(cmsEntity);

	}
	
	
	/**
	 * 
	 * This method is use to view content details by path(ADMIN or OTHERS) 
	 * 
	 * @param contentId  ContentManagement
	 *           
	 * @return cmsModel ContentManagement class object
	 * @throws ObjectNotFound 
	 * */
	public ContentManagementDetailsModel viewContentbyPath(String path) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewCMSDetails(String path)-Start");
		}

		ContentManagementDetailsEntity cmsEntity = cmsDao.getCMSFromPath(path);

		if (cmsEntity == null) {
			throw new ObjectNotFound(massageUtil.getBundle("cms.object.not.found"));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewCMSDetails(String path)-End");
		}

		return cmsConverter.entityToModel(cmsEntity);

	}
	
	
	
	/**
	 * 
	 * This method is use to view all user details (ADMIN or OTHERS) 
	 * 
	 *           
	 * @return userModel User class list of object
	 * @throws ObjectNotFound 
	 * @throws DataNotFound 
	 * */
	public List<ContentManagementDetailsModel> viewAllContent() throws ObjectNotFound, DataNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewAllContent()-Start");
		}

		List<ContentManagementDetailsEntity> cmsEntity= cmsDao.getAllContentManagement();
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewAllContent()-End");
		}

		return cmsConverter.entityListToModelList(cmsEntity);

	}
	/**
	 * 
	 * This method is use to update content details (ADMIN or OTHERS) 
	 * @return cmsModel ContentManagementModel class list of object
	 * @throws ObjectNotFound 
	 * @throws DataNotFound 
	 * @throws RecordFound 
	 * @throws FormExceptions 
	 * 
	 * */
	public void updateContent(ContentManagementDetailsModel cmsModel) throws ObjectNotFound, DataNotFound, RecordFound, FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateContent(ContentManagementModel cmsModel)-Start");
		}
		businessDataValidation.CmsCreateValidate(cmsModel);
		//checking for unique path
		String path = cmsModel.getPath();
		ContentManagementDetailsEntity cmsobj = cmsDao.getCMSFromPath(path);
		
		ContentManagementDetailsEntity cmsEntity = cmsDao.find(cmsModel.getId());
 				
		if (cmsEntity == null) {

			throw new ObjectNotFound(massageUtil.getBundle("cms.object.not.found"));
		}
		
		try {
 			if (cmsobj == cmsEntity || cmsobj == null)
			{
				//setting the values in entity class
				
				cmsEntity.setPageContent(cmsModel.getPageContent());
				cmsEntity.setPageName(cmsModel.getPageName());
				cmsEntity.setPath(cmsModel.getPath());
						
				cmsDao.update(cmsEntity);
			}
 			else{
 				throw new RecordFound(massageUtil.getBundle("cms.path.already.present"));
 			}
 			
 		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("cms path already present in db");
		}
		
		catch (RecordFound e){
			e.printStackTrace();
		}
		
		

		if (logger.isDebugEnabled()) {
			logger.debug("updateContent(User userModel)-End");
		}

	}


	/***
	 * 
	 * This method is use to delete user details by Id (ADMIN or OTHERS) 
	 * 
	 * @param userId  User 
	 * @throws ObjectNotFound 
	 * 
	 ***/
	public void deleteContent(int cmsId) throws ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser(int userId)-Start");
		}

		ContentManagementDetailsEntity cmsEntity = cmsDao.find(cmsId);
		

		

		if (cmsEntity == null) {
			throw new ObjectNotFound(massageUtil.getBundle("cms.object.not.found"));
		}
		
		//setting status as 0 i.e inactive
		cmsEntity.setStatus(Status.INACTIVE);
		
		cmsDao.update(cmsEntity);
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteContent(int cmsId)-End");
		}

	}
	
	
}



