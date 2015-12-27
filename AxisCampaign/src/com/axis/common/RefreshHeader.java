/*package com.axis.comon;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.axis.business.DashBoardBusiness;
import com.axis.business.NotificationBusiness;
import com.axis.change.logo.Duration;
import com.axis.change.logo.Icon;
import com.axis.change.logo.Icons;
import com.axis.controller.DashBoardLoginController;
import com.axis.exception.ObjectNotFound;
import com.axis.model.DashBoard;
import com.axis.model.LoginModel;
import com.axis.model.NotificationModel;

@Component
public class RefreshHeader {

	Logger logger = Logger.getLogger(RefreshHeader.class);

	private static final List<String> MONTHS = Arrays.asList("JANUARY",
			   "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
			   "SEPTEMBER", "OCTOBER", "NOVENBER", "DECEMBER");
	
	@Autowired
	private DashBoardBusiness dashBoardBusiness;

	@Autowired
	private NotificationBusiness notificationBusiness;

	public void refresh(HttpServletRequest httpServletRequest, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("refresh-Start ");
		}

		LoginModel loginModel = (LoginModel) httpServletRequest.getSession()
				.getAttribute("userLoginModelDetails");

		DashBoard dashBoardModel = dashBoardBusiness
				.displayDashBoard(loginModel.getUserName());

		List<NotificationModel> notificationDetails = null;
		try {
			notificationDetails = notificationBusiness
					.getLastFiveNotificationService(loginModel.getId());
		} catch (ObjectNotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int count = 0;
		try {
			count = notificationBusiness
					.countUnseenNotificationForUser(loginModel.getId());
		} catch (ObjectNotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("dashBoardFAQModels",
				dashBoardModel.getFaqModels());
		
		model.addAttribute("dashBoardModelCampaign",
				dashBoardModel.getCampaignModels());// add all Campaign of user
													// in request scope
		model.addAttribute("top5CampaignFileDetails",dashBoardModel.getCampaignFileModelSort());// add top
																		// 5
																		// Campaign
																		// Files and campaign user file
																		// of
																		// user
																		// in
																		// request
																		// scope
		model.addAttribute("CampaignFileDetails",
				dashBoardModel.getCampaignFileModels());// add Campaign Files of
														// user in request scope
		model.addAttribute("top5NotificationDetails", notificationDetails);// add
																			// top
																			// 5
																			// Notification
																			// of
																			// user
																			// in
																			// request
																			// scope
		model.addAttribute("top3Campaign",
				dashBoardBusiness.showTop3CampaignInDashBoard());// add
																	// top3Campaign
																	// of user
																	// in
																	// request
																	// scope
		model.addAttribute("countUnseenNotification", count); // add unseen
																// notification
																// no of user in
																// request scope
		httpServletRequest.getSession(true).setAttribute("userDashBoardModel",
				dashBoardModel.getUserModel());// add all detail of user in
												// session scope

		try {
			model.addAttribute("logoIcon", getIconPath(httpServletRequest));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("dashBoardModel.getUserModel().getImage(); "+dashBoardModel.getUserModel().getImage());
		if (logger.isDebugEnabled()) {
			logger.debug("refresh-End ");
		}

	}

	*//**
	 * changing ICON periodically after 4 months
	 *//*
	private String getIconPath(HttpServletRequest request) throws JAXBException {

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
*/