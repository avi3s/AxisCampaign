package com.axis.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.axis.common.UserDashBoard;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.UserTargetModel;
import com.axis.service.CampaignService;
import com.axis.service.UserTargetService;

@Controller
public class UserTargetController {

	@Autowired
	private UserTargetService userTargetService;

	@Autowired
	private UserDashBoard userDashBoard;
	
	@Autowired
	private CampaignService campaignService;


	/**
	 * When the User Click on the Leader Board Button it will Show all the
	 * Campaign Click on it
	 * 
	 * 
	 * @param CampaignId
	 *            HttpSession RoleId
	 * @return LeaderBoardVeiw Page or
	 * @return BranchHeadView Page
	 * 
	 */
	@RequestMapping(value = "/userTarget", method = RequestMethod.GET)
	public String userTargetView(ModelMap modelMap, HttpServletRequest request,
			HttpSession httpSession, Model model,
			@RequestParam("campaignid") int campaignId) {

		System.out.println("Under the controller of leader board");

		int roleId = (int) httpSession.getAttribute("roleId");

		UserTargetModel userTargetModel = new UserTargetModel();

		try {
			CampaignModel campaignModel = campaignService
					.fetchCampaignById(campaignId);
			modelMap.addAttribute("CampaignName",
					campaignModel.getCampaignName());
			
			String quarterId = campaignModel.getQuarterId(); System.out.println("========================"+quarterId);
			
			if(quarterId.equals("1")) {
				quarterId = "Q1";
			} else if(quarterId.equals("2")) {
				quarterId = "Q2";
			}else if(quarterId.equals("3")) {
				quarterId = "Q3";
			} else {
				quarterId = "Q4";
			}
			
			modelMap.addAttribute("CampaignQuater",quarterId);
		} catch (ObjectNotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			userTargetModel = userTargetService.getTargetFieldValues(
					campaignId, roleId);
			modelMap.addAttribute("headerList", userTargetModel.getHeaderList());
			modelMap.addAttribute("bodycontents",
					userTargetModel.getBodycontains());

			//userDashBoard.getDashBoardDetails(httpSession, model);

		} catch (DataNotFound e) {
			modelMap.addAttribute("DataNotFound", e.getMessage());
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
					model.addAttribute("logoIcon",UserLoginController.getIconPath(request));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "user-target-view";
	}

}
