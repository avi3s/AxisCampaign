package com.axis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.FileDownload;
import com.axis.common.FileUpload;
import com.axis.common.MessageUtil;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignFileUserModel;
import com.axis.model.CampaignModel;
import com.axis.model.UserModel;
import com.axis.service.CampaignFileUserService;
import com.axis.service.CampaignService;
import com.axis.service.UserService;

@Controller
public class CampaignFileUserController {

	private String filePath = "";
	private static final Logger logger = Logger
			.getLogger(CampaignFileUserController.class);

	@Autowired
	private CampaignFileUserService campaignFileUserService;

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageUtil messageUtil;
	

	/**
	 * View Promotional File For ADMIN
	 * */
	@RequestMapping(value = "/Admin/viewPromotionalFileUpload", method = RequestMethod.GET)
	public String viewPromotionalFileUpload(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("View PromotionalFile Details Dummy Controller-Start ");
		}

		System.out.println("Dummy View PromotionalFile Controller :: ");

		try {
			List<CampaignFileUserModel> promotionalFileModels = campaignFileUserService
					.fetchAllCampaignFileUser();

			model.addAttribute("adminpromotionalFileList",
					promotionalFileModels);
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("View PromotionalFile Details Dummy Controller-End ");
		}

		return "view-promotional-file";
	}

	/**
	 * Add Promotional File Page By ADMIN
	 * */
	@RequestMapping(value = "/Admin/addpromotionalfileuploadpage", method = RequestMethod.GET)
	public String addPromotionalFileUpload(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add PromotionalFile Dummy Controller-Start ");
		}

		System.out.println("Dummy Add PromotionalFile Controller");

		List<CampaignModel> campaignList = null;
		try {

			campaignList = campaignService.findAllActiveCampaignByStatus();
			List<UserModel> userModels = userService.fetchAllUserByStatus();
			model.addAttribute("campaignList", campaignList);
			model.addAttribute("userList", userModels);

		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Add PromotionalFile Dummy Controller-End ");
		}
		System.out.println("returning to add-promotional-file");
		return "add-promotional-file";
	}

	@RequestMapping(value = "/Admin/ajaxCallForFetchingUserByCampaignId", method = RequestMethod.GET)
	public String ajaxCallForFetchingUserByCampaignId(
			@ModelAttribute CampaignModel campaignModel, Model model,
			HttpServletResponse httpServletResponse) throws IOException,
			DataNotFound, ObjectNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("ajaxCallForFetchingUserByCampaignId-Start ");
		}

		System.out.println("ajaxCallForFetchingUserByCampaignId Controller :: "
				+ campaignModel.getCampaignId());
		PrintWriter pw = httpServletResponse.getWriter();

		List<UserModel> userModels = userService
				.getUserListByCampaignId(campaignModel);

		for (UserModel userModel : userModels) {

			pw.println(userModel);

		}

		// List<CampaignModel> campaignList = null;
		// try {
		//
		// campaignList = campaignService.findAllActiveCampaignByStatus();
		// List<UserModel> userModels = userService.fetchAllUserByStatus();
		// model.addAttribute("campaignList", campaignList);
		// model.addAttribute("userList", userModels);
		//
		// } catch (ObjectNotFound | DataNotFound e) {
		// logger.debug(e.getMessage(), e);
		// model.addAttribute("campaignNotFound", e.getMessage());
		// }
		//
		// if (logger.isDebugEnabled()) {
		// logger.debug("Add PromotionalFile Dummy Controller-End ");
		// }
		// System.out.println("returning to add-promotional-file");
		return null;
	}

	/**
	 * Save Promotional File By ADMIN
	 * */
	@RequestMapping(value = "/Admin/savepromotionalfile", method = RequestMethod.POST)
	public String savePromotionalFileUpload(Model model,
			@ModelAttribute CampaignFileUserModel promotionalFileModel,
			@RequestParam("fileName1") MultipartFile[] fileName1,
			HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Save PromotionalFile Controller-Start ");
		}

		System.out.println("Save PromotionalFile Controller");

		int createdBy = (int) httpSession.getAttribute("userId");
		
		promotionalFileModel.setCreatedBy(createdBy);
		
		List<String> fileList = new ArrayList<String>();

		for (int i = 0; i < fileName1.length; i++) {

			MultipartFile file = fileName1[i];
			System.out.println("File Name : " + file.getOriginalFilename());
			fileList.add(file.getOriginalFilename());
			fileList.add(String.valueOf(file.getSize()));
			
			// Upload the Campaign Files In the Project Folder
			filePath = "/PromotionalFiles/";
			FileUpload.uploadFile(filePath, file, file.getOriginalFilename(),
					request, response);
		}
		promotionalFileModel.setPromotionalFiles(fileList);

		try {
			campaignFileUserService
					.insertCampaignFileUser(promotionalFileModel);
		} catch (FormExceptions e1) {
			
			for(Entry<String, Exception> entry : ((FormExceptions) e1).getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			logger.debug(e1.getMessage(), e1);
			
			redirectAttributes.addFlashAttribute("promotionalFileModel",
					promotionalFileModel);
			
			return ("redirect:/Admin/addpromotionalfileuploadpage");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Save PromotionalFile Dummy Controller-End ");
		}
		redirectAttributes.addFlashAttribute("userFileAdded",messageUtil.getBundle("Campaign.User.File.Inserted"));
		return "redirect:/Admin/viewPromotionalFileUpload";
	}

	/**
	 * Edit Promotional File Page By ADMIN
	 * */
	@RequestMapping(value = "/Admin/editpromotionalfile", method = RequestMethod.GET)
	public String editPromotionalFileUpload(Model model,
			HttpServletRequest request,
			@RequestParam("id") int campaignFileUserId,
			@ModelAttribute CampaignFileUserModel promotionalFileModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("editPromotionalFileUpload-Start ");
		}

		System.out.println("Edit PromotionalFile Controller");

		try {
			promotionalFileModel = campaignFileUserService
					.fetchCampaignFileUserId(campaignFileUserId);

			model.addAttribute("promotionalFileModel", promotionalFileModel);
			List<CampaignModel> campaignList = campaignService
					.findAllActiveCampaignByStatus();
			List<UserModel> userModels = userService
					.getUserListByCampaignId(promotionalFileModel
							.getCampaignModel());

			model.addAttribute("campaignList", campaignList);
			model.addAttribute("userList", userModels);

			if (logger.isDebugEnabled()) {
				logger.debug("editPromotionalFileUpload-End ");
			}

			//promotionalFileModel.setRequestType(1);

			return "edit-promotional-file";
		} catch (NumberFormatException | ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
			return "edit-promotional-file";
		}

	}

	/**
	 * Download Promotional File
	 * */

	@RequestMapping(value = "/Admin/downloadpromotionalfile", method = RequestMethod.GET)
	public String downloadcampaignfile(Model model, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("id") int campaignFileUserId,
			@ModelAttribute CampaignFileUserModel promotionalFileModel)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("Download Promotional File Controller-Start ");
		}

		System.out.println("Dummy Promotional File Download Controller");

		String filename = request.getParameter("name");

		// Download Promotional File
		filePath = "/PromotionalFiles/";
		FileDownload.downloadFile(filePath, filename, request, response);

		try {
			promotionalFileModel = campaignFileUserService
					.fetchCampaignFileUserId(campaignFileUserId);

			List<CampaignModel> campaignList = campaignService
					.findAllActiveCampaignByStatus();

			List<UserModel> userModels = userService.fetchAllUserByStatus();

			model.addAttribute("promotionalFileModel", promotionalFileModel);
			model.addAttribute("campaignList", campaignList);
			model.addAttribute("userList", userModels);

			if (logger.isDebugEnabled()) {
				logger.debug("editPromotionalFileUpload-End ");
			}

			promotionalFileModel.setRequestType(1);

			return "edit-promotional-file";
		} catch (NumberFormatException | ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
			return "edit-promotional-file";
		}
	}

	@RequestMapping(value = "/Admin/updatepromotionalfile", method = RequestMethod.POST)
	public String updatecampaign(Model model,
			@ModelAttribute CampaignFileUserModel promotionalFileModel,
			@RequestParam("fileName1") MultipartFile fileName1,final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Update Promotional Controller-Start ");
		}

		System.out.println("Update Promotional Controller"
				+ promotionalFileModel.getCampaignFileUserId());

		int updatedBy = (int) httpSession.getAttribute("userId");
		promotionalFileModel.setUpdatedBy(updatedBy);

		try {
			if (fileName1.getSize() > 0) {
				promotionalFileModel.setFileName(fileName1
						.getOriginalFilename());

				// Upload the Promotional Files In the Project Folder
				filePath = "/PromotionalFiles/";
				FileUpload.uploadFile(filePath, fileName1,
						fileName1.getOriginalFilename(), request, response);

				campaignFileUserService
						.updateCampaignFileUser(promotionalFileModel);

				return "redirect:/Admin/viewPromotionalFileUpload";
			} else {

				campaignFileUserService
						.updateCampaignFileUser(promotionalFileModel);
				redirectAttributes.addFlashAttribute("userFileAdded",messageUtil.getBundle("Campaign.User.File.Edited"));
				return "redirect:/Admin/viewPromotionalFileUpload";
			}
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
			return "redirect:/Admin/editpromotionalfile?id="
					+ promotionalFileModel.getCampaignFileUserId();
			// return "add-promotional-file";
		} catch (Exception e1) {
			logger.debug(e1.getMessage(), e1);
			model.addAttribute("promotionalFileNotFound",
					"Please Select A File");
			return "redirect:/Admin/editpromotionalfile?id="
					+ promotionalFileModel.getCampaignFileUserId();
			// return "add-promotional-file";
		}

	}

	/**
	 * Delete Promotional File Page By ADMIN
	 * */
	@RequestMapping(value = "/Admin/deletepromotionalfile", method = RequestMethod.GET)
	public String deletePromotionalFileUpload(Model model,
			@RequestParam("id") int campaignFileUserId,
			@ModelAttribute CampaignFileUserModel promotionalFileModel,
			HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("deletePromotionalFileUpload-Start ");
		}

		System.out.println("Delete PromotionalFile Controller");

		try {
			promotionalFileModel = campaignFileUserService
					.fetchCampaignFileUserId(campaignFileUserId);

			campaignFileUserService
					.deleteCampaignFileUser(promotionalFileModel);

			if (logger.isDebugEnabled()) {
				logger.debug("deletePromotionalFileUpload-End ");
			}
			
			redirectAttributes.addFlashAttribute("userFileAdded",messageUtil.getBundle("Campaign.User.File.Deleted"));
			return "redirect:/Admin/viewPromotionalFileUpload";
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("promotionalFileNotFound", e.getMessage());
			return "redirect:/Admin/viewPromotionalFileUpload";

		}
	}
}
