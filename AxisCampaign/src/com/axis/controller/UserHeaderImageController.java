package com.axis.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.FileUpload;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.UserHeaderImageModel;
import com.axis.service.UserHeaderImageService;

@Controller
public class UserHeaderImageController {
	
	@Autowired
	private UserHeaderImageService userHeaderImageService;


	private static final Logger logger = Logger
			.getLogger(UserHeaderImageController.class);
	
	private String filePath = "";
	
	/**
	 * When click on the Admin header section it will call the view controller
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Admin/viewUserHeaderPicture", method = RequestMethod.GET)
	public String viewUserHeaderPicture(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("viewUserHeaderPicture-Start ");
		}

		// Fetching the Campaign List From DB
		List<UserHeaderImageModel> userHeaderImageList = null;
		try {

			userHeaderImageList = userHeaderImageService.fetchAllUserHeaderImage();

			// Adding the Campaign List as Model Attribute
			model.addAttribute("userHeaderImageList", userHeaderImageList);

		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("userHeaderPic", e.getMessage());
			return "view-userHeaderPicture";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewUserHeaderPicture-End ");
		}

		return "view-userHeaderPicture";
	}
	
	
	/**
	 * User click on the Upload Image for User Header
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/Admin/addUserHeader", method = RequestMethod.GET)
	public String addUserHeader(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add User header Dummy Controller-Start ");
		}

		System.out.println("Dummy Campaign Add Controller");

		// Fetching the Role List From DB
		
		if (logger.isDebugEnabled()) {
			logger.debug("Add User Header Controller-End ");
		}
		// Adding the Role List as Model Attribute
		return "add-userHeaderPicture";
	}
	
	
	/**
	 * When the Admin Click on the upload user Header Picture Button
	 * @param model
	 * @param adminCampaignModel
	 * @param fileName
	 * @param campaignLogoFile
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param redirectAttributes
	 * @return
	 */

	@SuppressWarnings("null")
	@RequestMapping(value = "/Admin/saveUserHeaderPic", method = RequestMethod.POST)
	public String saveUserHeaderPic(Model model,
			
			@RequestParam("imageName") MultipartFile[] imageName,
			
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Save User Header Picture Upload Controller-Start ");
		}

		
		List<UserHeaderImageModel> userHeaderImageList = new ArrayList<UserHeaderImageModel>();
		
		for (int i = 0; i < imageName.length; i++) {

			MultipartFile file = imageName[i];

			if (file.getSize() > 0) {

				System.out.println("file.getOriginalFilename() ============= "+file.getOriginalFilename()+".................."+file.getSize());
				
				UserHeaderImageModel userHeaderImageModel=new UserHeaderImageModel();
				userHeaderImageModel.setImageName(file.getOriginalFilename());
				userHeaderImageList.add(userHeaderImageModel);
				

				// Upload the Campaign Files In the Project Folder
				filePath = "/resources/UserHeaderImage/";
				FileUpload.uploadFile(filePath, file,
						file.getOriginalFilename(), request, response);
				
				userHeaderImageService.insertUserHeaderImage(userHeaderImageModel);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Save User Header Picture Controller-End ");
		}

		return "redirect:/Admin/viewUserHeaderPicture";
	}
	
	/**
	 * Edit User Header Picture Page By ADMIN
	 * */
	@RequestMapping(value = "/Admin/edituserHeaderImage", method = RequestMethod.GET)
	public String edituserHeaderImage(Model model,
			HttpServletRequest request,
			@RequestParam("id") int userHeaderImageId
			) {

		if (logger.isDebugEnabled()) {
			logger.debug("editUserImageFileUpload-Start ");
		}

		System.out.println("Edit User image File Upload Controller");

		try {
			UserHeaderImageModel userHeaderImageModel=userHeaderImageService.fetchUserHeaderImageById(userHeaderImageId);
			
			model.addAttribute("userHeaderImageModel", userHeaderImageModel);
			

			if (logger.isDebugEnabled()) {
				logger.debug("editUserHeaderImageUpload-End ");
			}

			return "edit-userHeaderImage";
		} catch (NumberFormatException | ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
			return "edit-userHeaderImage";
		}

	}
	/**
	 * Delete User Header Picture Page By ADMIN
	 * */
	@RequestMapping(value = "/Admin/deleteuserHeaderImage", method = RequestMethod.GET)
	public String deleteuserHeaderImage(Model model,
			HttpServletRequest request,
			@RequestParam("id") int userHeaderImageId
			) {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteuserHeaderImage-Start ");
		}


		try {
			UserHeaderImageModel userHeaderImageModel=userHeaderImageService.fetchUserHeaderImageById(userHeaderImageId);
			
			userHeaderImageService.deleteUserHeaderImage(userHeaderImageModel);
			
			if (logger.isDebugEnabled()) {
				logger.debug("deleteuserHeaderImage-End ");
			}

			return "redirect:/Admin/viewUserHeaderPicture";
		} catch (NumberFormatException | ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("promotionalFileNotFound", e.getMessage());
			return "redirect:/Admin/viewUserHeaderPicture";
		}

	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/Admin/updateUserHeaderPic", method = RequestMethod.POST)
	public String updateUserHeaderPic(Model model,@ModelAttribute UserHeaderImageModel userHeaderImageModel,
			@RequestParam("imageName1") MultipartFile imageName,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Update User Header Picture Upload Controller-Start ");
		}
		
			MultipartFile file = imageName;

			if (file.getSize() > 0) {

				System.out.println("file.getOriginalFilename() ============= "+file.getOriginalFilename()+".................."+file.getSize());
				
				userHeaderImageModel.setImageName(file.getOriginalFilename());

				// Upload the Campaign Files In the Project Folder
				filePath = "/UserHeaderImage/";
				FileUpload.uploadFile(filePath, file,
						file.getOriginalFilename(), request, response);
				
				try {
					userHeaderImageService.updateUserHeaderImage(userHeaderImageModel);
				} catch (ObjectNotFound e) {
					logger.debug(e.getMessage(), e);
					model.addAttribute("promotionalFileNotFound", e.getMessage());
					return "redirect:/Admin/viewUserHeaderPicture";
				}
			}

		if (logger.isDebugEnabled()) {
			logger.debug("Save User Header Picture Controller-End ");
		}

		return "redirect:/Admin/viewUserHeaderPicture";
	}
	
	/* ----------------------------------------------------- User Module --------------------------------------------------------------------*/
	
}
