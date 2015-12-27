package com.axis.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

import com.axis.change.logo.Duration;
import com.axis.change.logo.Icon;
import com.axis.change.logo.Icons;
import com.axis.common.FileDownload;
import com.axis.common.FlagStatus;
import com.axis.common.MessageUtil;
import com.axis.common.UserDashBoard;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.NotificationModel;
import com.axis.model.UserHeaderImageModel;
import com.axis.model.UserModel;
import com.axis.service.CampaignService;
import com.axis.service.NotificationService;
import com.axis.service.UserHeaderImageService;
import com.axis.service.UserLoginService;

@Controller
public class UserLoginController {

	private static final Logger logger = Logger
			.getLogger(UserLoginController.class);

	private String filePath = "";

	private static final List<String> MONTHS = Arrays.asList("JANUARY",
			"FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
			"SEPTEMBER", "OCTOBER", "NOVENBER", "DECEMBER");

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private UserDashBoard userDashBoard;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private UserHeaderImageService userHeaderImageService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logindummylink() {
		System.out.println("Dummy Index Controller");

		return "user-login";
		// return "redirect:/Admin/loginAdmin";
	}

	/*
	 *  ****************************************************** USER LOGIN STARTS
	 * *****************************************************************
	 */

	@RequestMapping(value = "/userLogin", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView adminLoginCheck(@ModelAttribute UserModel userModel,
			Model model, HttpSession session,
			final RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest) {

		if ((UserModel) session.getAttribute("userDetails") != null) {
			return new ModelAndView("redirect:/getDashboard");
		}

		try { // Parent Try Block Starts

			if (FlagStatus.count == 1) {
				System.out.println("db accessed");
				campaignService.campaignServiceforCheckingandSettingState();
				FlagStatus.count++;

			} else {
				System.out.println("db skipped");
			}

			userModel = userLoginService.userLoginCheck(userModel); // User
																	// Login
																	// Check and
																	// getting
																	// all data
																	// related
																	// to User

			if (!userModel.getRoleName().equals("ADMIN")) {
				session.setAttribute("userId", userModel.getUserId());
				session.setAttribute("employeeName",
						userModel.getEmployeeName());
				session.setAttribute("employeeNumber",
						userModel.getEmployeeNumber());
				session.setAttribute("roleId", userModel.getRoleId());
				session.setAttribute("emailId", userModel.getEmailAddress());
				session.setAttribute("about", userModel.getAbout());
				session.setAttribute("primaryTelephoneNumber",
						userModel.getPrimaryTelephoneNumber());
				session.setAttribute("profilePicture",
						userModel.getProfilePicture());
				session.setAttribute("branchName",
						userModel.getSubRoleLevelname());

				model.addAttribute("userDetails", userModel);

				model.addAttribute("userDetails", userModel);
				session.setAttribute("userDetails", userModel);

				userDashBoard.getUserDashBoardDetails(userModel); // getting
																	// Notifications,
																	// Files and
																	// Campaign
																	// Lists

				List<NotificationModel> topFiveNotifications = userDashBoard
						.getTopFiveNotifications(); // getting top 5
													// notifications
				model.addAttribute("topFiveNotifications", topFiveNotifications); // Setting
																					// top
																					// 5
																					// notifications
																					// List
																					// to
																					// Model

				List campaignFiles = userDashBoard.getCampaignFiles(); // getting
																		// top 5
																		// files
				
				model.addAttribute("campaignFiles", campaignFiles); // Setting
																	// top 5
																	// files
																	// List to
																	// Model

				model.addAttribute("noOfFiles", (userModel
						.getCampaignFilesCount() + userModel
						.getCampaignFilesUserCount() + userModel.getUserUploadedFilesCount())); // Setting no of files
														// Count to Model
				System.out.println("HERE 1");


				List<CampaignModel> topThreeCampaigns = userDashBoard
						.getTopThreeCampaigns(); // getting top 3 campaigns
				model.addAttribute("topThreeCampaigns", topThreeCampaigns); // Setting
																			// top
																			// 3
																			// campaigns
																			// List
																			// to
																			// Model

				List<CampaignModel> allCampaigns = userDashBoard
						.getAllCampaigns(); // getting all campaigns
				model.addAttribute("allCampaigns", allCampaigns); // Setting all
																	// campaigns
																	// List to
																	// Model

				/* ************ Getting User Logo ************* */

				try {
					session.setAttribute("logoIcon",
							getIconPath(httpServletRequest));
				} catch (JAXBException e) {
					model.addAttribute("generalException",
							"Some Error Occoured...");
				} catch (Exception e) {
					model.addAttribute("generalException",
							"Some Error Occoured...");
				}

				/* ************ Getting User Logo Ends ************* */

				try {
				List<UserHeaderImageModel> headerImageModels = userHeaderImageService
						.fetchAllUserHeaderImage();
				model.addAttribute("userHeaderImagesList", headerImageModels);
				}
				catch(Exception e){
					System.out.println("HERE 2");

				}
				System.out.println("HERE 3");


				if (userDashBoard.getExceptions().size() > 0)
					throw new FormExceptions(userDashBoard.getExceptions());

				return new ModelAndView("user-home");
			}

			else {

				redirectAttributes.addFlashAttribute("invalidLogin",
						messageUtil.getBundle("invalid.login.credentials"));
				return new ModelAndView("redirect:/login");
			}

		} // Parent Try Block Ends

		catch (FormExceptions e) {
			System.out.println("in FormExceptions catch of USER DASHBOARD:: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				model.addAttribute(entry.getKey(), entry.getValue()
						.getMessage());
			}

			return new ModelAndView("user-home");
		}

		catch (DataNotFound e) {
			System.out.println("forwarding to error page");
			e.printStackTrace();
			redirectAttributes
					.addFlashAttribute("invalidLogin", e.getMessage());
			return new ModelAndView("redirect:/login");
		}

		catch (ObjectNotFound e) {
			System.out.println("forwarding to error page");
			e.printStackTrace();
			redirectAttributes
					.addFlashAttribute("invalidLogin", e.getMessage());
			return new ModelAndView("redirect:/login");
		}

		catch (Exception e1) {
			redirectAttributes.addFlashAttribute("generalException",
					e1.getMessage());
		}

		return null;

	}

	/* ********** USER LOGIN ENDS ********** */

	@RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
	public String getDashboard(HttpSession session, Model model) {

		try {
			List<UserHeaderImageModel> headerImageModels = userHeaderImageService
					.fetchAllUserHeaderImage();
			model.addAttribute("userHeaderImagesList", headerImageModels);
			
			userDashBoard.getDashBoardDetails(session, model);

			if (userDashBoard.getExceptions().size() > 0)
				throw new FormExceptions(userDashBoard.getExceptions());

		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key111 :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				model.addAttribute(entry.getKey(), entry.getValue()
						.getMessage());
			}

		} catch (DataNotFound e) {
			e.printStackTrace();
			//model.addAttribute("invalidLogin", e.getMessage());
		}

		catch (ObjectNotFound e) {
			e.printStackTrace();
			//model.addAttribute ("invalidLogin", e.getMessage());
		}

		return "user-home";

	}

	@RequestMapping(value = "/viewAllNotifications", method = RequestMethod.GET)
	public String viewAllNotifications(HttpSession session, Model model)
			throws ObjectNotFound, DataNotFound {

		List<NotificationModel> viewAllNotificationsList = notificationService
				.getReceivedNotificationService((int) session
						.getAttribute("userId"));

		model.addAttribute("viewAllNotificationsList", viewAllNotificationsList);

		notificationService.setNotificationViewed((int) session
				.getAttribute("userId"));

		try {
			userDashBoard.getDashBoardDetails(session, model);
		} catch (FormExceptions e) {

			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				model.addAttribute(entry.getKey(), entry.getValue()
						.getMessage());
			}
		}

		return "notification-listing";

	}

	@RequestMapping(value = "/viewSingleNotification", method = RequestMethod.GET)
	public String viewAllNotifications(HttpSession session, Model model,
			@ModelAttribute NotificationModel notificationModel)
			throws ObjectNotFound, DataNotFound, FormExceptions {

		notificationModel = notificationService
				.viewNotification(notificationModel.getId());
		model.addAttribute("singleNotification", notificationModel);

		notificationService.setSingleNotificationViewed(notificationModel
				.getId());

		try {
			userDashBoard.getDashBoardDetails(session, model);
		} catch (FormExceptions e) {

			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				model.addAttribute(entry.getKey(), entry.getValue()
						.getMessage());
			}
		}

		return "single-notification";

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/viewAllFiles", method = RequestMethod.GET)
	public String viewAllFiles(HttpSession session, Model model) {

		try {
			List allCampaignFiles = userDashBoard
					.getAllCampaignFiles((UserModel) session
							.getAttribute("userDetails")); // Getting all
															// Campaign-Files.
			
			System.out.println("ALL FILES :: "+allCampaignFiles.size());
			model.addAttribute("allCampaignFiles", allCampaignFiles); // Setting
																		// All
																		// Campaign
																		// Files
																		// to
																		// Model
		}

		catch (Exception e) {
			model.addAttribute("generalException", "Some error occoured.....");
		} finally {

			try {
				userDashBoard.getDashBoardDetails(session, model); // getting
																	// Notifications,
																	// Files and
																	// Campaign
																	// Lists
			} catch (DataNotFound e) {

			} catch (ObjectNotFound e) {

			} catch (FormExceptions e) {

				for (Entry<String, Exception> entry : e.getExceptions()
						.entrySet()) {
					model.addAttribute(entry.getKey(), entry.getValue()
							.getMessage());
				}

			} catch (Exception e) {
				model.addAttribute("generalException",
						"Some error occoured.....");
			}

			if (userDashBoard.getExceptions().size() > 0)
				try {
					throw new FormExceptions(userDashBoard.getExceptions());
				} catch (FormExceptions e) {
					for (Entry<String, Exception> entry : e.getExceptions()
							.entrySet()) {
						model.addAttribute(entry.getKey(), entry.getValue()
								.getMessage());
					}
				} catch (Exception e) {
					model.addAttribute("generalException",
							"Some error occoured.....");
				}

		}

		return "file-listing";
	}

	/**
	 * User Logout Service
	 * 
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest httpServletRequest) {

		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.removeAttribute("userId");
		httpSession.removeAttribute("employeeName");
		httpSession.removeAttribute("employeeNumber");
		httpSession.invalidate();
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/downloadCampaignFile", method = RequestMethod.GET)
	public String downloadCampaignFile(Model model, HttpServletRequest request,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("campaign_file_id") int campaign_file_id) {

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-Start ");
		}

		System.out.println("Dummy Campaign File Download Controller");

		String filename = "";
		try {
			filename = campaignService.findCampaignFileByID(campaign_file_id)
					.getFileName();

		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignFileDetails", e.getMessage());

			return "redirect:/viewAllFiles";
		}

		// Download Campaign File
		filePath = "/CampaignFiles/";
		try {
			FileDownload.downloadFile(filePath, filename, request, response);
			redirectAttributes.addFlashAttribute("campaignFileDetails",
					messageUtil.getBundle("Campaign.File.Download"));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignFileDetails",
					messageUtil.getBundle("Campaign.FileID.not.found"));
			return "redirect:/viewAllFiles";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-End ");
		}

		return "redirect:/viewAllFiles";
	}

	/**
	 * changing ICON periodically after 4 months
	 */
	public static String getIconPath(HttpServletRequest request)
			throws JAXBException {

		String fileLocation = null;

		String iconlogoconfig = request.getServletContext().getInitParameter(
				"iconlogoconfig");

		JAXBContext jaxbContext = JAXBContext.newInstance(Icons.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		Icons icons = (Icons) jaxbUnmarshaller.unmarshal(new java.io.File(
				request.getServletContext().getRealPath(iconlogoconfig)));

		Calendar calendar = Calendar.getInstance();

		int currentMonth = calendar.get(Calendar.MONTH);

		for (Icon ic : icons.getIcon()) {

			Duration duration = ic.getDuration();

			int startMonth = MONTHS.indexOf(duration.getStartMonth()
					.toUpperCase());

			int endMonth = MONTHS.indexOf(duration.getEndMonth().toUpperCase());

			if (currentMonth >= startMonth && currentMonth <= endMonth) {

				fileLocation = ic.getFile().getName();

				break;
			}

		}

		return fileLocation;

	}

}
