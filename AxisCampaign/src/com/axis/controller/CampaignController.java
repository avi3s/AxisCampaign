package com.axis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.*;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.CampaignFileModel;
import com.axis.model.CampaignModel;
import com.axis.model.RoleModel;
import com.axis.service.CampaignFileUserService;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.service.UserFileUploadService;
import com.axis.validation.CampaignValidation;

@Controller
public class CampaignController {

	private String filePath = "";

	private static final Logger logger = Logger
			.getLogger(CampaignController.class);

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CampaignValidation campaignValidation;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CampaignFileUserService campaignFileUserService;

	@Autowired
    private UserFileUploadService userFileUploadService;
	
	/**
	 * When the Admin clicks on the Campaign Management Link Hits this method
	 * and fetch all the campaigns present in the DB.
	 * 
	 * @param campaignList
	 * @return viewCampaign.jsp
	 * */
	@RequestMapping(value = "/Admin/viewCampaign", method = RequestMethod.GET)
	public String viewCampaign(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("View Campaign Details Dummy Controller-Start ");
		}

		System.out.println("Dummy Campaign View Controller");

		// Fetching the Campaign List From DB
		List<CampaignModel> campaignList = null;
		try {

			campaignList = campaignService.findAllActiveCampaignByStatus();

			// Adding the Campaign List as Model Attribute
			model.addAttribute("campaignList", campaignList);

		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("View Campaign Details Dummy Controller-End ");
		}

		return "view-campaign";
	}

	/**
	 * When the Admin clicks on the Add Campaign Management Link Hits this
	 * method and fetch all the roles present in the DB.
	 * 
	 * @param roleList
	 * @return addcampaign.jsp
	 * */
	@RequestMapping(value = "/Admin/addCampaign", method = RequestMethod.GET)
	public ModelAndView addCampaign(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Campaign Dummy Controller-Start ");
		}

		System.out.println("Dummy Campaign Add Controller");

		// Fetching the Role List From DB
		List<RoleModel> roleList = null;
		try {
			roleList = roleService.getAllActiveRoleByStatus();
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleNotFound", e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Add Campaign Dummy Controller-End ");
		}
		// Adding the Role List as Model Attribute
		return new ModelAndView("add-campaign", "roleList", roleList);
	}

	/**
	 * When the Admin clicks on the Save Campaign Button Hits this method Insert
	 * the values in the DB and fetch all the campaigns present in the DB.
	 * 
	 * @param campaignList
	 * @return viewCampaign.jsp
	 * */

	@SuppressWarnings("null")
	@RequestMapping(value = "/Admin/saveCampaign", method = RequestMethod.POST)
	public String saveCampaign(Model model,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("fileName") MultipartFile[] fileName,
			@RequestParam("campaignLogoFile") MultipartFile campaignLogoFile,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Save Campaign Controller-Start ");
		}

		System.out.println("Save Campaign Controller");

		/*
		 * ------------------------------------------- Uploading of Campaign
		 * Logo Starts
		 * ----------------------------------------------------------
		 * ----------------
		 */

		// Get The Name Of Campaign Logo File Upload Name
		adminCampaignModel.setCampaignLogo(campaignLogoFile
				.getOriginalFilename());

		try {
			campaignValidation.campaignCreateValidate(adminCampaignModel);

			// Upload the CampaignLogo File In the Project Folder
			// filePath = "/CampaignLogo/";
			filePath = "/resources/CampaignLogo/";
			FileUpload.uploadFile(filePath, campaignLogoFile,
					campaignLogoFile.getOriginalFilename(), request, response);

			/*
			 * ------------------------------------------- Uploading of Campaign
			 * Logo Ends
			 * ------------------------------------------------------------
			 * --------------
			 */

		} catch (FormExceptions e1) {

			for (Entry<String, Exception> entry : ((FormExceptions) e1)
					.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}

			logger.debug(e1.getMessage(), e1);

			String roleArray[] = adminCampaignModel.getRoleID_array();
			List<Integer> role_array = new ArrayList<Integer>();

			if (roleArray != null) {
				for (int i = 0; i < roleArray.length; i++) {
					role_array.add(Integer.parseInt(roleArray[i]));
				}
			}
			adminCampaignModel.setRoleID_array_to_show(role_array);
			redirectAttributes.addFlashAttribute("CampaignModel",
					adminCampaignModel);
			// redirectAttributes.addFlashAttribute("errorMessage",e1.getMessage());
			return ("redirect:/Admin/addCampaign");
		}

		/*
		 * ------------------------------------------- Uploading of Campaign
		 * File Starts
		 * ----------------------------------------------------------
		 * ----------------
		 */

		List<CampaignFileModel> campaignFileList = new ArrayList<CampaignFileModel>();

		System.out.println("fileName.length" + fileName.length);

		for (int i = 0; i < fileName.length; i++) {

			MultipartFile file = fileName[i];

			if (file.getSize() > 0) {

				System.out.println("file.getOriginalFilename() ============= "
						+ file.getOriginalFilename() + ".................."
						+ file.getSize());
				CampaignFileModel campaignFileModel = new CampaignFileModel();
				campaignFileModel.setFileName(file.getOriginalFilename());
				campaignFileModel.setFileSize(String.valueOf(file.getSize()));

				campaignFileList.add(campaignFileModel);

				// Upload the Campaign Files In the Project Folder
				filePath = "/CampaignFiles/";
				FileUpload.uploadFile(filePath, file,
						file.getOriginalFilename(), request, response);
			}
		}
		if (campaignFileList.size() > 0)
			adminCampaignModel.setCampaignFileModels(campaignFileList);
		// else
		// adminCampaignModel.setCampaignFileModels(null);

		/*
		 * ------------------------------------------- Uploading of Campaign
		 * File Ends
		 * ------------------------------------------------------------
		 * --------------
		 */

		// Calling the Business Layer for Inserting the values in the DB
		try {

			int createdBy = (int) httpSession.getAttribute("userId");
			adminCampaignModel.setCreatedBy(createdBy);
			campaignService.insertCampaign(adminCampaignModel);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignNotFound",
					e.getMessage());

		}

		if (logger.isDebugEnabled()) {
			logger.debug("Save Campaign Controller-End ");
		}

		redirectAttributes.addFlashAttribute("campaignAdded",
				messageUtil.getBundle("Campaign.Inserted"));
		return "redirect:/Admin/viewCampaign";
	}

	/**
	 * When the Admin clicks on the Edit Campaign Button Hits this method fetch
	 * the values from the DB .
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return addcampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/editCampaign", method = RequestMethod.GET)
	public String editCampaign(Model model, HttpServletRequest request,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("id") int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Campaign Dummy Controller-Start ");
		}

		System.out.println("Dummy Campaign Edit Controller");

		// Get the details of the Campaign respect of the Selected ID
		try {
			adminCampaignModel = campaignService.fetchCampaignById(id);
		} catch (NumberFormatException e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("invalidID", "Please Enter a Valid ID");
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		if (adminCampaignModel != null) {
			// adminCampaignModel.setRequestType(1);
			List<RoleModel> roleList = null;
			try {
				roleList = roleService.getAllActiveRoleByStatus();
			} catch (ObjectNotFound | DataNotFound e) {
				logger.debug(e.getMessage(), e);
				model.addAttribute("roleNotFound", e.getMessage());
			}
			model.addAttribute("roleList", roleList);
			model.addAttribute("campaignModel", adminCampaignModel);
			model.addAttribute("campaignFileModels",
					adminCampaignModel.getCampaignFileModels());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Campaign Dummy Controller-End ");
		}

		return "edit-campaign";
	}

	/**
	 * When the Admin clicks on the Download Campaign Logo the file will be
	 * downloaded.
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return viewCampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/downloadCampaignLogo", method = RequestMethod.GET)
	public String downloadCampaignLogo(Model model, HttpServletRequest request,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("id") int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign Logo Controller-End ");
		}

		System.out.println("Dummy Campaign Logo Download Controller");

		// Get the details of the Campaign respect of the Selected ID
		try {
			adminCampaignModel = campaignService.fetchCampaignById(id);
		} catch (NumberFormatException e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("invalidID", "Please Enter a Valid ID");
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		// Download Logo
		// filePath = "/CampaignLogo/";
		filePath = "/resources/CampaignLogo/";
		try {
			FileDownload.downloadFile(filePath,
					adminCampaignModel.getCampaignLogo(), request, response);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.Logo.Download"));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.Logo.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign Logo Controller-End ");
		}

		return "redirect:/Admin/editCampaign?id=" + id;
	}

	/**
	 * When the Admin clicks on the Delete Campaign Button Hits this method
	 * deactivate the Campaign in the DB .
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return viewCampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/deleteCampaign", method = RequestMethod.GET)
	public String deleteCampaign(Model model, HttpServletRequest request,
			@ModelAttribute CampaignModel adminCampaignModel,
			final RedirectAttributes redirectAttributes,
			@RequestParam("id") int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Campaign Dummy Controller-Start ");
		}

		System.out.println("Dummy Campaign Delete Controller");

		// Get the details of the Campaign respect of the Selected ID
		try {
			adminCampaignModel = campaignService.fetchCampaignById(id);
		} catch (NumberFormatException e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("invalidID", "Please Enter a Valid ID");
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		// Deactivate the Campaign
		try {
			campaignService.deleteCampaign(adminCampaignModel);
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Campaign Dummy Controller-End ");
		}

		redirectAttributes.addFlashAttribute("campaignAdded",
				messageUtil.getBundle("Campaign.Deleted"));
		return "redirect:/Admin/viewCampaign";
	}

	/**
	 * When the Admin clicks on the Delete Campaign File the file will be
	 * deleted.
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return viewCampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/deleteCampaignFile", method = RequestMethod.GET)
	public String deleteCampaignFile(Model model, HttpServletRequest request,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("campaign_id") int campaign_id,
			@RequestParam("campaign_file_id") int campaign_file_id) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Campaign File Controller-Start ");
		}

		System.out.println("Dummy Campaign File Delete Controller");

		String filename = "";
		try {
			filename = campaignService.findCampaignFileByID(campaign_file_id)
					.getFileName();
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignFileNotFound", e.getMessage());
		}

		// Delete Campaign File
		filePath = "/CampaignFiles/";
		try {
			FileDelete.deleteFile(filePath, filename, request, response);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("fileNotFound", "File Not Found");
		}

		// Delete the Campaign File From DB
		try {
			campaignService.deleteCampaignFile(campaign_file_id, filename);
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignFileNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Campaign File Controller-End ");
		}

		redirectAttributes.addFlashAttribute("campaignAdded",
				messageUtil.getBundle("Campaign.File.Deleted"));
		return "redirect:/Admin/editCampaign?id=" + campaign_id;
	}

	/**
	 * When the Admin clicks on the Update Campaign Button Hits this method
	 * update the Campaign in the DB .
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return viewCampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/updateCampaign", method = RequestMethod.POST)
	public String updateCampaign(Model model,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("fileName") MultipartFile[] fileName,
			@RequestParam("campaignLogoFile") MultipartFile campaignLogoFile,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Update Campaign Dummy Controller-End ");
		}

		if (null != campaignLogoFile && campaignLogoFile.getSize() > 0) {
			System.out.println("Logo Present");
			// Get The Name Of Campaign Logo File Upload Name
			adminCampaignModel.setCampaignLogo(campaignLogoFile
					.getOriginalFilename());

			// Upload the CampaignLogo File In the Project Folder
			// filePath = "/CampaignLogo/";
			filePath = "/resources/CampaignLogo/";
			FileUpload.uploadFile(filePath, campaignLogoFile,
					campaignLogoFile.getOriginalFilename(), request, response);
		} else {
			try {
				adminCampaignModel.setCampaignLogo(campaignService
						.fetchCampaignById(adminCampaignModel.getCampaignId())
						.getCampaignLogo());
			} catch (ObjectNotFound e) {
				logger.debug(e.getMessage(), e);
				model.addAttribute("campaignNotFound", e.getMessage());
				adminCampaignModel.setRequestType(1);
				redirectAttributes.addFlashAttribute("campaignModel",
						adminCampaignModel);
				return ("redirect:/Admin/addCampaign?errorMessage=" + e
						.getMessage());
			}
		}

		try {
			campaignValidation.campaignUpdateValidate(adminCampaignModel);
		} catch (FormExceptions e1) {

			for (Entry<String, Exception> entry : ((FormExceptions) e1)
					.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}

			logger.debug(e1.getMessage(), e1);

			String roleArray[] = adminCampaignModel.getRoleID_array();
			List<Integer> role_array = new ArrayList<Integer>();

			if (roleArray != null) {
				for (int i = 0; i < roleArray.length; i++) {
					role_array.add(Integer.parseInt(roleArray[i]));
				}
			}
			adminCampaignModel.setRoleID_array_to_show(role_array);
			redirectAttributes.addFlashAttribute("CampaignModel",
					adminCampaignModel);
			// redirectAttributes.addFlashAttribute("errorMessage",e1.getMessage());
			return "redirect:/Admin/editCampaign?id="
					+ adminCampaignModel.getCampaignId();
		}

		// if (null != fileName && fileName.length > 0) {
		System.out.println("FileName Present");
		List<CampaignFileModel> campaignFileList = new ArrayList<CampaignFileModel>();

		for (int i = 0; i < fileName.length; i++) {

			CampaignFileModel campaignFileModel = new CampaignFileModel();
			MultipartFile file = fileName[i];
			System.out.println("file :" + file.getSize());
			if (file.getSize() > 0) {

				campaignFileModel.setFileName(file.getOriginalFilename());

				// System.out.println("-=======================  "+campaignFileModel.getFileName());
				campaignFileList.add(campaignFileModel);

				// Upload the Campaign Files In the Project Folder
				filePath = "/CampaignFiles/";
				FileUpload.uploadFile(filePath, file,
						file.getOriginalFilename(), request, response);
			}
		}

		int updatedBy = (int) httpSession.getAttribute("userId");
		adminCampaignModel.setUpdatedBy(updatedBy);

		System.out.println("campaignFileList.size() :"
				+ campaignFileList.size());
		if (campaignFileList.size() > 0) {

			adminCampaignModel.setCampaignFileModels(campaignFileList);
			campaignService.updateCampaignFile(adminCampaignModel);
		}

		try {

			campaignService.updateCampaign(adminCampaignModel);
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignNotFound", e.getMessage());
			// adminCampaignModel.setRequestType(1);
			redirectAttributes.addFlashAttribute("campaignModel",
					adminCampaignModel);
			return ("redirect:/Admin/addCampaign?errorMessage=" + e
					.getMessage());
		}

		redirectAttributes.addFlashAttribute("campaignAdded",
				messageUtil.getBundle("Campaign.Edited"));
		return "redirect:/Admin/viewCampaign";
	}

	/**
	 * When the Admin clicks on the Download Campaign File the file will be
	 * downloaded.
	 * 
	 * @param campaignList
	 *            ,roleList
	 * @return viewcampaign.jsp
	 * */

	@RequestMapping(value = "/Admin/downloadCampaignFile", method = RequestMethod.GET)
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
				filename = campaignService.findCampaignFileByID(
						campaign_file_id).getFileName();
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignAdded", e.getMessage());
		}

		// Download Campaign File
			filePath = "/CampaignFiles/";
		try {
			FileDownload.downloadFile(filePath, filename, request, response);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.File.Download"));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.FileID.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-End ");
		}

		return "redirect:/Admin/viewCampaign";
	}

	/* ------------------------------------- User module ---------------------------------------------------------------------*/
	/*
	@RequestMapping(value = "/downloadCampaignFileForUser", method = RequestMethod.GET)
	public String downloadCampaignFileForUser(Model model, HttpServletRequest request,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("campaign_file_id") int campaign_file_id,
			@RequestParam("type") String type) {

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-Start ");
		}

		System.out.println("Dummy Campaign File123 Download Controller");

		String filename = "";
		try {
			if (type.equals("CFM"))
				filename = campaignService.findCampaignFileByID(
						campaign_file_id).getFileName();
			else if (type.equals("CFUM"))
				filename = campaignFileUserService.fetchCampaignFileUserId(
						campaign_file_id).getFileName();

			System.out.println("FILENAME :: " + filename);
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignAdded", e.getMessage());
		}

		// Download Campaign File
		if (type.equals("CFM"))
			filePath = "/CampaignFiles/";
		else if (type.equals("CFUM"))
			filePath = "/PromotionalFiles/";
		try {
			FileDownload.downloadFile(filePath, filename, request, response);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.File.Download"));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.FileID.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-End ");
		}

		return "redirect:/Admin/viewCampaign";
	}*/
	
	@RequestMapping(value = "/downloadCampaignFileForUser", method = RequestMethod.GET)
	public String downloadCampaignFileForUser(Model model, HttpServletRequest request,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes,
			@ModelAttribute CampaignModel adminCampaignModel,
			@RequestParam("campaign_file_id") int campaign_file_id,
			@RequestParam("type") String type) {

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-Start ");
		}

		System.out.println("Dummy Campaign File123 Download Controller");

		String filename = "";
		try {
			if (type.equals("CFM"))
				filename = campaignService.findCampaignFileByID(
						campaign_file_id).getFileName();
			else if (type.equals("CFUM"))
				filename = campaignFileUserService.fetchCampaignFileUserId(
						campaign_file_id).getFileName();
			else if (type.equals("UFUM"))
				filename = userFileUploadService.fetchUserFileByFileId(campaign_file_id).getFileName();


			System.out.println("FILENAME :: " + filename);
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("campaignAdded", e.getMessage());
		}

		// Download Campaign File
		if (type.equals("CFM"))
			filePath = "/CampaignFiles/";
		else if (type.equals("CFUM"))
			filePath = "/PromotionalFiles/";  
		else if (type.equals("UFUM"))
				filePath = "/UserFileUpload/";
		try {
			FileDownload.downloadFile(filePath, filename, request, response);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.File.Download"));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("campaignAdded",
					messageUtil.getBundle("Campaign.FileID.not.found"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Download Campaign File Controller-End ");
		}

		return "redirect:/Admin/viewCampaign";
	}
}