package com.axis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
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
import com.axis.common.Util;
import com.axis.exception.DataNotFound;
import com.axis.exception.EmptyValueException;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignModel;
import com.axis.model.EscalationMatrixModel;
import com.axis.service.CampaignService;
import com.axis.service.EscalationMatrixService;

@Controller
public class EscalationMatrixController {

	Logger logger = Logger.getLogger(EscalationMatrixController.class);

	@Autowired
	private EscalationMatrixService escalationMatrixService;

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private UserDashBoard userDashBoard;

	@RequestMapping(value = "/Admin/viewEscalationMatrix", method = RequestMethod.GET)
	public ModelAndView adminLoginPage(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("View Escalation Matrix Details Dummy Controller-Start ");
		}
		List<EscalationMatrixModel> EscalationMatrixModels = null;

		try {
			EscalationMatrixModels = escalationMatrixService
					.findAllActiveEscalationMatrixByStatus();

		} catch (DataNotFound | ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("escalationMatrixNotFound", e.getMessage());
			return new ModelAndView("view-escalationmatrix");
		}
		model.addAttribute("adminEscalationMatrixList", EscalationMatrixModels);

		if (logger.isDebugEnabled()) {
			logger.debug("View Escalation Matrix Details Dummy Controller-End ");
		}

		return new ModelAndView("view-escalationmatrix");
	}

	@RequestMapping(value = "/Admin/addescalationmatrixpage", method = RequestMethod.GET)
	public String addescalationmatrix(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Escalation Matrix Details Dummy Controller-Start ");
		}

		System.out.println("Dummy Add Escalation Matrix Controller");

		// Fetching the Campaign List From DB
		List<CampaignModel> campaignList = null;

		try {
			campaignList = campaignService.findAllActiveCampaignByStatus();
			model.addAttribute("campaignList", campaignList);

		} catch (DataNotFound | ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Add Escalation Matrix Details Dummy Controller-End ");
		}

		return "add-escalationmatrix";
	}

	@RequestMapping(value = "/Admin/saveEscalationMatrix", method = RequestMethod.POST)
	public ModelAndView addContentController(
			@ModelAttribute EscalationMatrixModel escalationMatrixModel,
			ModelAndView modelAndView, final RedirectAttributes redir,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Escalation Matrix Controller-Start saveEscalationMatrix "
					+ escalationMatrixModel);
		}

		try {
			escalationMatrixService
					.insertescalationmatrixService(escalationMatrixModel);

			System.out.println("type...:::" + escalationMatrixModel.getType());

			if (logger.isDebugEnabled()) {
				logger.debug("Add Escalation Matrix Controller-Start saveEscalationMatrix");
			}
			redir.addFlashAttribute("escalationMessage",
					messageUtil.getBundle("Escalation.added"));
			return new ModelAndView("redirect:/Admin/viewEscalationMatrix");

		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redir.addFlashAttribute("escalationMatrixModel",
					escalationMatrixModel);
			modelAndView.setViewName("redirect:/Admin/addescalationmatrixpage");
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redir.addFlashAttribute("escalationMatrixModel",
					escalationMatrixModel);
			modelAndView.setViewName("redirect:/Admin/addescalationmatrixpage");
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		} catch (RecordFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/addescalationmatrixpage");
			redir.addFlashAttribute("escalationMatrixModel",
					escalationMatrixModel);
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		} catch (FormExceptions e) {
			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redir.addFlashAttribute(entry.getKey(), entry.getValue()
						.getMessage());
				model.addAttribute(entry.getKey(), entry.getValue()
						.getMessage());
			}
			redir.addFlashAttribute("escalationMatrixModel",
					escalationMatrixModel);

			// logger.debug("escalationMatrixModel.getEmail() within catch"+escalationMatrixModel.getEmail());
			modelAndView.setViewName("redirect:/Admin/addescalationmatrixpage");
			redir.addFlashAttribute("errorMessage", e.getMessage());

			return modelAndView;
		}

	}

	@RequestMapping(value = "/Admin/editescalationmatrix", method = RequestMethod.GET)
	public String editEscalationMatrix(Model model, HttpServletRequest request,
			@ModelAttribute EscalationMatrixModel escalationMatrixModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit EscalationMatrix Dummy Controller-Start ");
		}
		try {
			escalationMatrixModel = escalationMatrixService
					.getEscalationMatrixByID(Integer.parseInt((request
							.getParameter("id"))));
			/*
			 * } catch (NumberFormatException e) { logger.debug(e.getMessage(),
			 * e); model.addAttribute("invalidID", "Please Enter a Valid ID");
			 * 
			 * } catch (ObjectNotFound e) { logger.debug(e.getMessage(), e);
			 * model.addAttribute("campaignNotFound", e.getMessage()); }
			 */

			List<CampaignModel> campaignList = null;

			try {

				campaignList = campaignService.findAllActiveCampaignByStatus();

				model.addAttribute("campaignList", campaignList);
				model.addAttribute("adminescalationMatrixModel",
						escalationMatrixModel);
				model.addAttribute("update", 1);

				if (logger.isDebugEnabled()) {
					logger.debug("Edit EscalationMatrix Dummy Controller-End ");
				}

				return ("add-escalationmatrix");

			} catch (DataNotFound | ObjectNotFound e) {
				logger.debug(e.getMessage(), e);
				model.addAttribute("campaignNotFound", e.getMessage());
				return ("/Admin/addescalationmatrixpage");

			}
		} catch (DataNotFound | ObjectNotFound e1) {
			logger.debug(e1.getMessage(), e1);
			model.addAttribute("escalationMatrixNotFound", e1.getMessage());
			return ("/Admin/viewEscalationMatrix");
		}

	}

	@RequestMapping(value = "/Admin/updateescalationmatrix", method = RequestMethod.POST)
	public ModelAndView contentUpdateController(
			@ModelAttribute EscalationMatrixModel escalationMatrixModel,
			ModelAndView modelAndView, RedirectAttributes redir)
			throws RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("updateescalationmatrix-Start "
					+ escalationMatrixModel);
		}

		try {
			escalationMatrixService.updateContent(escalationMatrixModel);
		} catch (ObjectNotFound e) {
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/editescalationmatrix?id="
					+ escalationMatrixModel.getId());
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		} catch (DataNotFound e) {
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/editescalationmatrix?id="
					+ escalationMatrixModel.getId());
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		} catch (FormExceptions e) {
			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redir.addFlashAttribute(entry.getKey(), entry.getValue()
						.getMessage());
				// model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			modelAndView.setViewName("redirect:/Admin/editescalationmatrix?id="
					+ escalationMatrixModel.getId());
			return modelAndView;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updateescalationmatrix-End ");
		}
		redir.addFlashAttribute("escalationMessage",
				messageUtil.getBundle("Escalation.update"));
		return new ModelAndView("redirect:/Admin/viewEscalationMatrix");

	}

	@RequestMapping(value = "/Admin/deleteescalationmatrix", method = RequestMethod.GET)
	public ModelAndView deleteEscalationMatrixById(
			@RequestParam("id") int escalationId, RedirectAttributes redir) {

		if (logger.isDebugEnabled()) {

			logger.debug("deleteContentByIdController-Start " + escalationId);
		}

		try {
			escalationMatrixService.deleteEscalationMatrix(escalationId);

		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("deleteContentByIdController-End ");
		}

		redir.addFlashAttribute("escalationMessage",
				messageUtil.getBundle("Escalation.delete"));
		return new ModelAndView("redirect:/Admin/viewEscalationMatrix");

	}

	/*********************************************** User Module For Escalation Matrix ******************************************************/

	@RequestMapping(value = "/viewUserEscalationMatrix", method = RequestMethod.GET)
	public String viewUserEscalationMatrix(Model model, HttpSession httpSession, HttpServletRequest httpServletRequest) {

		if (logger.isDebugEnabled()) {
			logger.debug("viewUserEscalationMatrix - Start ");
		}

		int roleId = (int) httpSession.getAttribute("roleId"); // Session Of
																// Current User
		System.out.println("roleId ================= " + roleId);
		try {
			List<EscalationMatrixModel> escalationMatrixModels = escalationMatrixService
					.fetchEscalationMatrixByRole(roleId);

			model.addAttribute("escalationMatrixModels", escalationMatrixModels);

			/*userDashBoard.getDashBoardDetails(httpSession, model);

			if (userDashBoard.getExceptions().size() > 0)
				throw new FormExceptions(userDashBoard.getExceptions());*/

		} catch (DataNotFound | ObjectNotFound e) {
			model.addAttribute("NoEscalationMatrixFound", e.getMessage());
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

			}
			finally {
				try {
					model.addAttribute("logoIcon",UserLoginController.getIconPath(httpServletRequest));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("viewUserEscalationMatrix - End ");
		}

		return "escalationmatrix";
	}

}
