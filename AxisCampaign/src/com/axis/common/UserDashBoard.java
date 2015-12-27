package com.axis.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignFileModel;
import com.axis.model.CampaignFileUserModel;
import com.axis.model.CampaignModel;
import com.axis.model.CommonModel;
import com.axis.model.NotificationModel;
import com.axis.model.UserFileUploadModel;
import com.axis.model.UserModel;
import com.axis.service.UserLoginService;

@Component
public class UserDashBoard {

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private MessageUtil messageUtil;

	private List<NotificationModel> topFiveNotifications;
	private List<CommonModel> commonModels;
	private List<CampaignModel> topThreeCampaigns;
	private List campaignFiles = new LinkedList();
	private List<CampaignModel> allCampaigns;
	private Map<String,Exception> exceptions = new HashMap<String,Exception>();


	public void getUserDashBoardDetails(UserModel userModel) throws DataNotFound {
		


		if (!campaignFiles.isEmpty())
			campaignFiles.clear();

		this.topFiveNotifications = userLoginService.getTopFiveNotifications(userModel);
		
		
		if(this.topFiveNotifications == null || this.topFiveNotifications.isEmpty() || this.topFiveNotifications.size() == 0) {
			
			this.exceptions.put("notificationsNotFound", new DataNotFound(messageUtil.getBundle("Notification.not.found")));
		}

		this.commonModels = userLoginService.getTopFiveFiles(userModel);
		
		if(this.commonModels == null || this.commonModels.isEmpty() || this.commonModels.size() == 0) {
			
			this.exceptions.put("noFiles", new DataNotFound(messageUtil.getBundle("noFiles")));
		}

		for (CommonModel commonModel : this.commonModels) {
			if (commonModel instanceof CampaignFileModel) {
				System.out.println("CampaignFileModel :: "
						+ ((CampaignFileModel) commonModel).getFileName());
				this.campaignFiles.add((CampaignFileModel) commonModel);
			} else if (commonModel instanceof CampaignFileUserModel) {
				System.out.println("CampaignFileUserModel :: "
						+ ((CampaignFileUserModel) commonModel).getFileName());
				this.campaignFiles.add((CampaignFileUserModel) commonModel);

			} else if (commonModel instanceof UserFileUploadModel) {
                System.out.println("UserFileUploadModel :: "
                        + ((UserFileUploadModel) commonModel).getFileName());
                this.campaignFiles.add((UserFileUploadModel) commonModel);

            }
		}

		this.topThreeCampaigns = userLoginService.getTopThreeCampaigns(userModel);
		
		if(this.topThreeCampaigns == null || this.topThreeCampaigns.isEmpty() || this.topThreeCampaigns.size() == 0) {
			
			this.exceptions.put("campaignNotFound", new DataNotFound(messageUtil.getBundle("campaign.not.found")));
		}
		
		

		this.allCampaigns = userLoginService.getAllCampaigns(userModel);
		
		if(this.allCampaigns == null || this.allCampaigns.isEmpty() || this.allCampaigns.size() == 0) {
			
			this.exceptions.put("campaignNotFound", new DataNotFound(messageUtil.getBundle("campaign.not.found")));
		}
		
		System.out.println("SIZE :: "+this.exceptions.size());

	
	}

	public List getAllCampaignFiles(UserModel userModel) {

		List<CommonModel> commonModels = userLoginService.getAllFiles(userModel);
		
		List allCampaignFiles = new LinkedList();

		for (CommonModel commonModel : commonModels) {
			if (commonModel instanceof CampaignFileModel) {
				System.out.println("CampaignFileModel :: "
						+ ((CampaignFileModel) commonModel).getFileName());
				allCampaignFiles.add((CampaignFileModel) commonModel);
			} else if (commonModel instanceof CampaignFileUserModel) {
				System.out.println("CampaignFileUserModel :: "
						+ ((CampaignFileUserModel) commonModel).getFileName());
				allCampaignFiles.add((CampaignFileUserModel) commonModel);

			} else if (commonModel instanceof UserFileUploadModel) {
                System.out.println("UserFileUploadModel :: "
                        + ((UserFileUploadModel) commonModel).getFileName());
                allCampaignFiles.add((UserFileUploadModel) commonModel);

            }
		}

		return allCampaignFiles;
	}
	
	
		public void getDashBoardDetails(HttpSession session, Model model) throws DataNotFound, ObjectNotFound, FormExceptions {
			
		UserModel userModel = userLoginService.userLoginCheck((UserModel)session.getAttribute("userDetails"));
		session.setAttribute("userDetails", userModel);
		session.setAttribute("profilePicture",
				userModel.getProfilePicture());
		
		getUserDashBoardDetails(userModel);                // getting Notifications Files and Campaign Lists.
		
		List<NotificationModel> topFiveNotifications = getTopFiveNotifications();   // getting top 5 notifications
		model.addAttribute("topFiveNotifications", topFiveNotifications);
		
		for(NotificationModel notificationModel : topFiveNotifications) {
			System.out.println("IN COmmon METHOD :: "+notificationModel.getMessage());
		}
		
		List campaignFiles = getCampaignFiles();		// getting top 5 files
		model.addAttribute("campaignFiles", campaignFiles);
		session.setAttribute("noOfFiles", (userModel.getCampaignFilesCount() + userModel.getCampaignFilesUserCount() + userModel.getUserUploadedFilesCount()));
		
		
		List<CampaignModel> topThreeCampaigns = getTopThreeCampaigns(); // getting top 3 campaigns
		model.addAttribute("topThreeCampaigns", topThreeCampaigns);

		List<CampaignModel> allCampaigns = getAllCampaigns(); // getting all campaigns
		model.addAttribute("allCampaigns", allCampaigns);


	}

	public List<CampaignModel> getAllCampaigns() {
		return allCampaigns;
	}

	public void setAllCampaigns(List<CampaignModel> allCampaigns) {
		this.allCampaigns = allCampaigns;
	}

	public List<NotificationModel> getTopFiveNotifications() {
		return topFiveNotifications;
	}

	public void setTopFiveNotifications(
			List<NotificationModel> topFiveNotifications) {
		this.topFiveNotifications = topFiveNotifications;
	}

	public List<CommonModel> getCommonModels() {
		return commonModels;
	}

	public void setCommonModels(List<CommonModel> commonModels) {
		this.commonModels = commonModels;
	}

	public List getCampaignFiles() {
		return campaignFiles;
	}

	public void setCampaignFiles(List campaignFiles) {
		this.campaignFiles = campaignFiles;
	}

	public List<CampaignModel> getTopThreeCampaigns() {
		return topThreeCampaigns;
	}

	public void setTopThreeCampaigns(List<CampaignModel> topThreeCampaigns) {
		this.topThreeCampaigns = topThreeCampaigns;
	}

	public Map<String, Exception> getExceptions() {
		return exceptions;
	}

	public void setExceptions(Map<String, Exception> exceptions) {
		this.exceptions = exceptions;
	}

}
