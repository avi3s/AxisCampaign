package com.axis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.converter.FaqConverter;
import com.axis.dao.FaqDao;
import com.axis.entity.FaqEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.FaqModel;
import com.axis.validation.FaqBusinessValidation;

@Service
@Transactional
public class FaqService {

	@Autowired
	FaqDao faqDao;

	@Autowired
	FaqConverter faqConverter;
	@Autowired
	private FaqBusinessValidation faqBusinessValidation;

	public static final Logger logger = Logger.getLogger(FaqService.class);

	/**
	 * public List<ContentManagementModel> viewAllContent() throws
	 * ObjectNotFound {
	 * 
	 * if (logger.isDebugEnabled()) { logger.debug("viewAllContent()-Start"); }
	 * 
	 * List<com.axis.entity.ContentManagement> cmsEntity=
	 * cmsDao.getAllContentManagement();
	 * 
	 * if (logger.isDebugEnabled()) { logger.debug("viewAllContent()-End"); }
	 * 
	 * return cmsConverter.entityListToModelList(cmsEntity);
	 * 
	 * }
	 * 
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * 
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<FaqModel> getAllFaqs() throws DataNotFound, ObjectNotFound {
		// TODO Auto-generated method stub
		List<FaqEntity> faqEntityList = faqDao.getAllFaqsfromDB();
		return faqConverter.entityListToModelList(faqEntityList);
	}

	public void insertFaq(FaqModel faqmodel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("insertFaq(FaqModel faqmodel)-Start");
		}

		faqBusinessValidation.faqCreateValidation(faqmodel);

		FaqEntity faqentity = faqConverter.modelToEntity(faqmodel);
		faqDao.create(faqentity);

		if (logger.isDebugEnabled()) {
			logger.debug("insertFaq(FaqModel faqmodel)-End");
		}
	}

	public void updateFaq(FaqModel faqmodel) throws FormExceptions,
			DataNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("updateFaqBusiness(FaqModel faqmodel)-Start");
		}

		faqBusinessValidation.faqupdateValidation(faqmodel);

		FaqEntity faqentity = faqDao.getFaqsfromDBById(faqmodel.getFaqId());

		faqentity = faqConverter.updateModelToEntity(faqmodel, faqentity);
		faqDao.update(faqentity);

		if (logger.isDebugEnabled()) {
			logger.debug("updateFaqBusiness(FaqModel faqmodel)-End");
		}
	}

	public FaqModel getFaqById(FaqModel faqmodel) throws ObjectNotFound {

		logger.debug("Start------------------------------------------------------getFaqById");

		FaqModel faqmodel1 = faqConverter.entityToModel(faqDao
				.getFaqsfromDBById(faqmodel.getFaqId()));
		logger.debug("End------------------------------------------------------getFaqById");

		return faqmodel1;
	}

	public void inactiveFaqService(FaqModel faqmodel) {
		if (logger.isDebugEnabled()) {
			logger.debug("inactiveFaqService(FaqModel faqmodel)-Start");
		}
		FaqEntity faqentity = faqDao.getFaqsfromDBById(faqmodel.getFaqId());
		faqentity = faqConverter.inActiveModelToEntity(faqentity);
		faqDao.update(faqentity);
		if (logger.isDebugEnabled()) {
			logger.debug("inactiveFaqService(FaqModel faqmodel)-Start");
		}
	}
	
	/*********************************************** User Module For FAQ 
	 * @throws ObjectNotFound 
	 * @throws DataNotFound ******************************************************/
	
	public List<FaqModel> viewAllFAQForUser (int campaignId) throws DataNotFound, ObjectNotFound {
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewAllFAQForUser - Start");
		}
		
		List<FaqModel> faqModels = faqConverter.entityListToModelList(faqDao.fetchFAQForUserByCampaignId(campaignId));
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewAllFAQForUser - End");
		}
		
		return faqModels;
	}

}
