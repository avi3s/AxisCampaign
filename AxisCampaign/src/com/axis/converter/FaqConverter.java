package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.axis.common.MessageUtil;
import com.axis.dao.CampaignDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.FaqEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.FaqModel;

@Component
public class FaqConverter implements NecessaryConverter<FaqEntity, FaqModel> {

	public static final Logger logger = Logger.getLogger(FaqConverter.class);
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	CampaignDao campaigndao;

	@Override
	public FaqEntity modelToEntity(FaqModel m) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("Model to Entity--------------------Start");
		}
		FaqEntity faqentity = new FaqEntity();
		CampaignEntity campaignentity = campaigndao.find(m.getCampaignId());
		faqentity.setAnswer(m.getAnswer());
		faqentity.setQuestion(m.getQuestion());
		faqentity.setCampaignEntity(campaignentity);
		faqentity.setCreatedBy(1);
		faqentity.setLastUpdateTimeStamp(new Date());
		faqentity.setUpdatedBy(1);
		faqentity.setCreateTimeStamp(new Date());
		faqentity.setStatus(Status.ACTIVE);

		if (logger.isDebugEnabled()) {
			logger.debug("Model to Entity--------------------End");
		}
		return faqentity;
	}

	@Override
	public FaqEntity updateModelToEntity(FaqModel m, FaqEntity e) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity--------------------Start");
		}

		CampaignEntity campaignentity = campaigndao.find(m.getCampaignId());
		e.setAnswer(m.getAnswer());
		e.setQuestion(m.getQuestion());
		e.setCampaignEntity(campaignentity);
		e.setCreatedBy(1);
		e.setLastUpdateTimeStamp(new Date());
		e.setUpdatedBy(1);
		e.setCreateTimeStamp(new Date());
		e.setStatus(Status.ACTIVE);
		if (logger.isDebugEnabled()) {
			logger.debug("updateModelToEntity--------------------End");
		}
		return e;
	}

	/**
	 * @Override public CampaignModel entityToModel(CampaignEntity e) throws
	 *           ObjectNotFound {
	 * 
	 *           CampaignModel campaignModel = new CampaignModel();
	 * 
	 *           if (e != null) {
	 * 
	 *           campaignModel.setCampaignId(e.getCampaignId());
	 *           campaignModel.setCampaignName(e.getCampaignName()); } else
	 *           throw new
	 *           ObjectNotFound(messageUtil.getBundle("campaign.id.not.found"));
	 * 
	 *           return campaignModel; }
	 * 
	 */

	@Override
	public FaqModel entityToModel(FaqEntity e) throws ObjectNotFound {
		// TODO Auto-generated method stub
		FaqModel faqModel = new FaqModel();

		if (e != null) {
			faqModel.setFaqId(e.getFaqId());
			faqModel.setQuestion(e.getQuestion());
			faqModel.setAnswer(e.getAnswer());
			faqModel.setStatus(e.getStatus());
			faqModel.setCampaignId(e.getCampaignEntity().getCampaignId());
			faqModel.setCampaignName(e.getCampaignEntity().getCampaignName());
		} else
			throw new ObjectNotFound(messageUtil.getBundle("faq.id.not.found"));
		return faqModel;
	}

	@Override
	public List<FaqModel> entityListToModelList(List<FaqEntity> es)
			throws DataNotFound, ObjectNotFound {
		List<FaqModel> faqModels = null;

		if (es.size() > 0) {

			faqModels = new ArrayList<FaqModel>();

			for (FaqEntity faqEntity : es) {
				faqModels.add(entityToModel(faqEntity));
			}
		} else
			throw new DataNotFound(messageUtil.getBundle("faq.not.found"));

		return faqModels;
	}

	@Override
	public List<FaqEntity> modelListToEntityList(List<FaqModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

	public FaqEntity inActiveModelToEntity(FaqEntity faqentity) {
		faqentity.setStatus(Status.INACTIVE);
		return faqentity;
	}

}
