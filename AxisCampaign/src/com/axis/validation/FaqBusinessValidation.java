package com.axis.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.converter.FaqConverter;
import com.axis.dao.FaqDao;
import com.axis.entity.FaqEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.FaqModel;

@Component
public class FaqBusinessValidation {

	private static final Logger logger = Logger
			.getLogger(FaqBusinessValidation.class);

	@Autowired
	private MessageUtil messageUtil;
	@Autowired
	private FaqDao faqDao;
	@Autowired
	private FaqConverter faqConverter;

	public void faqCreateValidation(FaqModel faqModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("faqCreateValidation(FaqModel faqModel)-Start");
		}
		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (faqModel.getCampaignId() == 0) {
			// throw new
			// DataNotFound(messageUtil.getBundle("CampaignId.not.null"));
			exceptions.put(
					"CampaignIdNotNull",
					new DataNotFound(messageUtil
							.getBundle("Campaign.not.null")));

		}
		// question
		if (Util.isEmpty(faqModel.getQuestion())) {
			// throw new
			// DataNotFound(messageUtil.getBundle("question.not.null"));
			exceptions
					.put("QuestionIdNotNull",
							new DataNotFound(messageUtil
									.getBundle("question.not.null")));

		} else {
			faqModel.setQuestion(faqModel.getQuestion().trim());
		}

		if (Util.isEmpty(faqModel.getAnswer())) {
			// throw new DataNotFound(messageUtil.getBundle("answer.not.null"));
			exceptions.put("AnswerIdNotNull",
					new DataNotFound(messageUtil.getBundle("answer.not.null")));

		} else {
			faqModel.setAnswer(faqModel.getAnswer().trim());
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("faqCreateValidation(FaqModel faqModel)-End");
		}
	}

	public void faqupdateValidation(FaqModel faqModel) throws DataNotFound,
			FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("faqupdateValidation(FaqModel faqModel)-Start");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (faqModel.getCampaignId() == 0) {
			// throw new
			// DataNotFound(messageUtil.getBundle("CampaignId.not.null"));
			exceptions.put(
					"CampaignIdNotNull",
					new DataNotFound(messageUtil
							.getBundle("Campaign.not.null")));

		}
		// question
		if ((faqModel.getQuestion().equalsIgnoreCase("")) || faqModel.getQuestion() == null) {
			// throw new
			// DataNotFound(messageUtil.getBundle("question.not.null"));
			exceptions
					.put("QuestionIdNotNull",
							new DataNotFound(messageUtil
									.getBundle("question.not.null")));

		} else {
			faqModel.setQuestion(faqModel.getQuestion().trim());
		}

		if (Util.isEmpty(faqModel.getAnswer())) {
			// throw new DataNotFound(messageUtil.getBundle("answer.not.null"));
			exceptions.put("AnswerIdNotNull",
					new DataNotFound(messageUtil.getBundle("answer.not.null")));

		} else {
			faqModel.setAnswer(faqModel.getAnswer().trim());
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("faqupdateValidation(FaqModel faqModel)-End");
		}

	}
}
