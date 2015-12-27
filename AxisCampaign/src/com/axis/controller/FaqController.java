package com.axis.controller;

import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.MessageUtil;
import com.axis.common.UserDashBoard;
import com.axis.converter.CampaignConverter;
import com.axis.dao.CampaignDao;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.FaqModel;
import com.axis.service.CampaignService;
import com.axis.service.FaqService;
import com.axis.validation.FaqBusinessValidation;

import org.apache.log4j.Logger;

@Controller
public class FaqController {

	private static Logger logger = Logger.getLogger(FaqController.class);

	@Autowired
	FaqService faqService;

	@Autowired
	CampaignService campaignService;

	@Autowired
	private FaqBusinessValidation faqBusinessValidation;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private UserDashBoard userDashBoard;

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private CampaignConverter campaignConverter;

	@RequestMapping(value = "/Admin/addFaq", method = RequestMethod.GET)
	public ModelAndView addFaqsController(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("addFaqsController(FaqModel faqmodel)-Start");
		}
		List<CampaignModel> campaignModel = null;
		try {
			campaignModel = campaignService.findAllActiveCampaignByStatus();
			model.addAttribute("allactiveCampaign", campaignModel);
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("NoActiveRoleFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("addFaqsController(FaqModel faqmodel)-End");
		}
		return new ModelAndView("add-faq");
	}

	@RequestMapping(value = "/Admin/viewFaq", method = RequestMethod.GET)
	public ModelAndView allFaqViewController(Model model,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("allFaqViewController(FaqModel faqmodel)-Start");
		}
		List<FaqModel> faqModelList = null;

		try {
			faqModelList = faqService.getAllFaqs();
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("faqNotFound", e.getMessage());
		}
		model.addAttribute("allFaqsView", faqModelList);

		if (logger.isDebugEnabled()) {
			logger.debug("allFaqViewController(FaqModel faqmodel)-End");
		}
		/*
		 * if (logger.isDebugEnabled()) {
		 * logger.debug("allContentViewController-End "); }
		 */

		return new ModelAndView("view-faq");

	}

	@RequestMapping(value = "/Admin/saveFaqValue", method = RequestMethod.POST)
	public String saveFaqValueController(@ModelAttribute FaqModel faqmodel,
			Model model, final RedirectAttributes redirectAttributes,
			ModelAndView modelAndView) {
		if (logger.isDebugEnabled()) {
			logger.debug("saveFaqValueController(FaqModel faqmodel)-Start");
		}

		try {
			faqService.insertFaq(faqmodel);
		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}
			redirectAttributes.addFlashAttribute("faqModel", faqmodel);
			return ("redirect:/Admin/addFaq");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("saveFaqValueController(FaqModel faqmodel)-End");
		}

		redirectAttributes.addFlashAttribute("faqMessage",
				messageUtil.getBundle("faq.added"));
		return ("redirect:/Admin/viewFaq");
	}

	@RequestMapping(value = "/Admin/updateFaqValue", method = RequestMethod.POST)
	public String updateFaqValueController(@ModelAttribute FaqModel faqmodel,
			Model model, final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateFaqValueControlle(FaqModel faqmodel)-Start");
		}
		try {
			faqService.updateFaq(faqmodel);
		} catch (FormExceptions | DataNotFound e) {
			for (Entry<String, Exception> entry : ((FormExceptions) e)
					.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());

			}
			redirectAttributes.addFlashAttribute("faqModel", faqmodel);
			return ("redirect:/Admin/editContentById");

		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateFaqValueControlle(FaqModel faqmodel)-End");
		}
		redirectAttributes.addFlashAttribute("faqMessage",
				messageUtil.getBundle("faq.update"));

		return ("redirect:/Admin/viewFaq");

		// allFaqViewController(model);
	}

	@RequestMapping(value = "/Admin/inactiveContentById", method = RequestMethod.GET)
	public String InactiveFaqController(@ModelAttribute FaqModel faqmodel,
			Model model, RedirectAttributes redirectAttributes) {
		if (logger.isDebugEnabled()) {
			logger.debug("InactiveFaqController(FaqModel faqmodel)-Start");
		}

		faqService.inactiveFaqService(faqmodel);
		if (logger.isDebugEnabled()) {
			logger.debug("InactiveFaqController(FaqModel faqmodel)-End");
		}
		redirectAttributes.addFlashAttribute("faqMessage",
				messageUtil.getBundle("faq.deleted"));
		return ("redirect:/Admin/viewFaq");

		// allFaqViewController(model);
	}

	@RequestMapping(value = "/Admin/editContentById", method = RequestMethod.GET)
	public ModelAndView editContentById(@ModelAttribute FaqModel faqmodel,
			Model model) throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("editContentByIdFaqController(FaqModel faqmodel)-Start");
		}

		FaqModel faqmodel1 = null;
		List<CampaignModel> campaignModel = null;
		campaignModel = campaignService.findAllActiveCampaignByStatus();
		model.addAttribute("allactiveCampaign", campaignModel);
		faqmodel1 = faqService.getFaqById(faqmodel);

		faqmodel1.setQuestion(faqmodel1.getQuestion().trim());
		faqmodel1.setAnswer(faqmodel1.getAnswer().trim());

		model.addAttribute("faqmodel", faqmodel1);
		model.addAttribute("update", 1);

		if (logger.isDebugEnabled()) {
			logger.debug("editContentByIdFaqController(FaqModel faqmodel)-End");
		}
		return new ModelAndView("add-faq");
	}

	/*********************************************** User Module For FAQ ******************************************************/

	@RequestMapping(value = "/viewFaqForUser", method = RequestMethod.GET)
	public String viewFaqForUser(Model model,
			HttpServletRequest httpServletRequest,
			@RequestParam("campaignid") int campaignId, HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("viewFaqForUser-Start");
		}

		// int campaignId = 5;
		String campaignName = "";

		/*
		 * CampaignModel campaignModel = campaignConverter
		 * .entityToModel(campaignDao.fetchCampaignById(campaignId));
		 * 
		 * if (campaignModel != null) { System.out.println("campaignid" +
		 * campaignModel.getCampaignId());
		 * 
		 * model.addAttribute("CampaignName", campaignModel.getCampaignName());
		 * model.addAttribute("campaignid", campaignModel.getCampaignId()); }
		 * else { model.addAttribute("NoFAQ",
		 * messageUtil.getBundle("campaign.id.not.found")); }
		 */

		try {

			List<FaqModel> faqModels = faqService.viewAllFAQForUser(campaignId);

			if (faqModels.size() > 0) {

				for (FaqModel faqModel : faqModels) {
					campaignName = faqModel.getCampaignName();
					campaignId = faqModel.getCampaignId();
				}

				model.addAttribute("CampaignName", campaignName);
				
			}

			
			model.addAttribute("faqList", faqModels);

			// userDashBoard.getDashBoardDetails(httpSession, model);

		} catch (DataNotFound e) {
			model.addAttribute("NoFAQ", e.getMessage());
			e.printStackTrace();
		} catch (ObjectNotFound e) {
			model.addAttribute("NoFAQ", e.getMessage());
			e.printStackTrace();
		} finally {

			try {
				userDashBoard.getDashBoardDetails(httpSession, model);

			} catch (FormExceptions e) {
				for (Entry<String, Exception> entry : ((FormExceptions) e)
						.getExceptions().entrySet()) {
					System.out.println("key :: " + entry.getKey()
							+ " value :: " + entry.getValue().getMessage());
					model.addAttribute(entry.getKey(), entry.getValue()
							.getMessage());
				}

			} catch (Exception e) {

			} finally {
				try {
					model.addAttribute("logoIcon",
							UserLoginController.getIconPath(httpServletRequest));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewFaqForUser-End");
		}

		System.out.println("campaignid" + campaignId);

		model.addAttribute("campaignid", campaignId);
		
		return "faq";
	}
}
