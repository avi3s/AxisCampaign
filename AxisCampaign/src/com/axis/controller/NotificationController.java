package com.axis.controller;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
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

import com.axis.common.GetAdminDetails;
import com.axis.common.MessageUtil;
import com.axis.common.UserDashBoard;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.NotificationModel;
import com.axis.model.RoleModel;
import com.axis.model.UserModel;
import com.axis.service.NotificationService;
import com.axis.service.RoleService;
import com.axis.service.UserService;

@Controller
public class NotificationController {

	private static final Logger logger = Logger
			.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private GetAdminDetails getAdminDetails;

	@Autowired
	private UserDashBoard userDashBoard;

	/**
	 * This method only show for addContent page
	 * 
	 * @throws DataNotFound
	 * */
	@RequestMapping(value = "/Admin/addNotificationPage", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addNotificationPageController(Model model)
			throws DataNotFound {

		// Fetching the user List From DB
		List<UserModel> userModels = null;
		List<RoleModel> roleModels = null;
		try {
			userModels = userService.fetchAllUserByStatus();
			roleModels = roleService.getAllActiveRoleByStatus();
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		model.addAttribute("userlist", userModels);
		model.addAttribute("roleList", roleModels);

		return new ModelAndView("add-notification");
	}

	@RequestMapping(value = "/Admin/addNotification", method = RequestMethod.POST)
	public ModelAndView addNotificationController(
			@ModelAttribute NotificationModel notificationModel,
			HttpSession httpSession, ModelAndView modelAndView,
			RedirectAttributes redir) throws ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("addNotificationController-Start " + notificationModel);
		}

		int adminId = (int) httpSession.getAttribute("userId");

		// Fetching the user List From DB
		List<UserModel> userModels = null;

		try {

			if (notificationModel.getRoleId() != 0) {
				userModels = userService.fetchAllUserByRole(notificationModel
						.getRoleId());

				for (UserModel userModel : userModels) {
					// when sending to particular role

					NotificationModel notificationModel1 = new NotificationModel();
					notificationModel1.setMessage(notificationModel
							.getMessage());
					notificationModel1.setSubject(notificationModel
							.getSubject());
					notificationModel1.setCreatedBy(userModel.getUserId());
					notificationModel1.setCreateTimeStamp(new Date());
					notificationModel1.setSentUserId(adminId);// need to get
																// userid
					// from session
					notificationModel1.setReceivedUserId(userModel.getUserId());
					notificationModel1.setViewStatus("0");
					notificationModel1.setSentStatus(Status.ACTIVE);
					notificationModel1.setStatus(Status.ACTIVE);
					notificationModel1.setRoleId(notificationModel.getRoleId());
					notificationModel1.setReceiveStatus(Status.ACTIVE);
					notificationService
							.insertnotificationService(notificationModel1);

				}

			}

			else {
				if (notificationModel.getUserId_array() == null) {

					// when admin replying to particular user
					notificationModel.setCreatedBy(adminId);
					notificationModel.setSentUserId(adminId);
					notificationModel.setViewStatus("0");
					notificationModel.setCreateTimeStamp(new Date());
					notificationModel.setSentStatus(Status.ACTIVE);
					notificationModel.setStatus(Status.ACTIVE);
					notificationModel.setReceiveStatus(Status.ACTIVE);
					notificationService
							.insertnotificationService(notificationModel);
				} else {
					// when admin sending to multiple users
					for (int elem : notificationModel.getUserId_array()) {

						NotificationModel notificationModel1 = new NotificationModel();
						notificationModel1.setMessage(notificationModel
								.getMessage());
						notificationModel1.setSubject(notificationModel
								.getSubject());
						notificationModel1.setCreatedBy(adminId);
						notificationModel1.setSentUserId(adminId);
						if (notificationModel1.getReceivedUserId() == 0) {
							notificationModel1.setReceivedUserId(elem);
						}
						notificationModel1.setViewStatus("0");
						notificationModel1.setCreateTimeStamp(new Date());
						notificationModel1.setSentStatus(Status.ACTIVE);
						notificationModel1.setStatus(Status.ACTIVE);
						notificationModel1.setReceiveStatus(Status.ACTIVE);
						notificationService
								.insertnotificationService(notificationModel1);
					}

				}
				if (logger.isDebugEnabled()) {
					logger.debug("contentCreateController-End ");
				}

			}

			redir.addFlashAttribute("notificationAdded",
					messageUtil.getBundle("Notification.Added"));

			return new ModelAndView("redirect:/Admin/viewNotification");

		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redir.addFlashAttribute("notificationModel", notificationModel);
			modelAndView.setViewName("redirect:/Admin/addNotificationPage");
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		} catch (FormExceptions e) {

			if (notificationModel.getType() != null) {

				if (notificationModel.getType().equals("1")) {
					redir.addFlashAttribute("reply", 1);
					redir.addFlashAttribute("replyNotification",
							notificationModel);
				}
			}

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				// System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				redir.addFlashAttribute(entry.getKey(), entry.getValue()
						.getMessage());
				// model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			modelAndView.setViewName("redirect:/Admin/addNotificationPage");
			redir.addFlashAttribute("errorMessage", e.getMessage());
			return modelAndView;
		}

	}

	/**
	 * This method is getting all content details
	 * 
	 * @throws DataNotFound
	 * */
	@RequestMapping(value = "/Admin/viewNotification", method = RequestMethod.GET)
	public ModelAndView allNotificationViewController(Model model,
			HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-Start ");
		}

		int adminId = (int) httpSession.getAttribute("userId");

		List<NotificationModel> sentNotificationList = null;
		List<NotificationModel> recievedNotificationList = null;

		try {
			sentNotificationList = notificationService
					.getSentNotificationService(adminId);

			model.addAttribute("sentNotificationList", sentNotificationList);

		} catch (ObjectNotFound | DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("sendNotificationNotFound", e.getMessage());
		}

		try {
			recievedNotificationList = notificationService
					.getReceivedNotificationService(adminId);

			model.addAttribute("recievedNotificationList",
					recievedNotificationList);

		} catch (ObjectNotFound | DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("receiveNotificationNotFound", e.getMessage());

		}

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-End ");
		}

		return new ModelAndView("notification-new-view");
	}

	/**
	 * This method is getting all content details
	 * 
	 * @throws DataNotFound
	 * */
	@RequestMapping(value = "/Admin/deleteNotificationById", method = RequestMethod.GET)
	public ModelAndView deleteNotificationById(@RequestParam("id") int id,
			RedirectAttributes redir) throws DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-Start ");
		}

		notificationService.inactiveNotification(id);

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-End ");
		}

		redir.addFlashAttribute("notificationAdded",
				messageUtil.getBundle("Notification.Deleted"));
		return new ModelAndView("redirect:/Admin/viewNotification");

	}

	@RequestMapping(value = "/Admin/replyToNotificationById", method = RequestMethod.GET)
	public ModelAndView replyToNotificationByIdController(Model model,
			@RequestParam("id") int id) throws DataNotFound {

		if (logger.isDebugEnabled()) {

			logger.debug("viewNotificationByIdController-Start " + id);
		}

		/*
		 * List<UserModel> userModels =null; List<RoleModel> roleModels =null;
		 */

		try {
			NotificationModel notificationModel = notificationService
					.viewNotification(id);

			NotificationModel replyNotification = new NotificationModel();
			replyNotification.setSubject(notificationModel.getMessage());
			replyNotification.setReceivedUserId(notificationModel
					.getSentUserId());
			replyNotification.setSentUserId(notificationModel
					.getReceivedUserId());

			model.addAttribute("replyNotification", replyNotification);

			model.addAttribute("reply", 1);

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewContentByIdController-End ");
		}

		return new ModelAndView("add-notification");

	}

	/*********************************************** User Module For Notification ******************************************************/

	@RequestMapping(value = "/insertComment", method = RequestMethod.POST)
	public String insertComment(final RedirectAttributes redirectAttributes,
			HttpSession httpSession, Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute NotificationModel notificationModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("insertComment-Start ");
		}

		int sentUserId = (int) httpSession.getAttribute("userId"); // Session Of
																	// Current
																	// User

		notificationModel.setSentUserId(sentUserId);
		notificationModel.setCreatedBy(sentUserId);
		notificationModel.setViewStatus("0"); // Not Viewed
		notificationModel.setStatus(Status.ACTIVE); // Active
		notificationModel.setReceiveStatus(Status.ACTIVE);
		notificationModel.setSentStatus(Status.ACTIVE);
		notificationModel.setReceivedUserId(getAdminDetails.fetchAdminInfo()
				.getUserId()); // Get Admin UserId
		notificationModel.getSubject().trim();

		try {
			notificationService.insertnotificationService(notificationModel);
			redirectAttributes.addFlashAttribute("commentStatusSuccess",
					messageUtil.getBundle("Comment.Added"));

		} catch (DataNotFound | FormExceptions e) {

			for (Entry<String, Exception> entry : ((FormExceptions) e)
					.getExceptions().entrySet()) {
				redirectAttributes.addFlashAttribute("commentStatusFailure",
						entry.getValue().getMessage());
			}

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
			logger.debug("insertComment-End ");
		}

		return "redirect:/viewFaqForUser?campaignid="+notificationModel.getCampaignid();
	}
}
