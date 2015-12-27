package com.axis.controller;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.FileUpload;
import com.axis.common.MessageUtil;
import com.axis.common.UserDashBoard;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.UserFileUploadModel;
import com.axis.model.UserHeaderImageModel;
import com.axis.service.UserFileUploadService;

@Controller
public class UserFileUploadController {

	private static final Logger logger = Logger
			.getLogger(UserFileUploadController.class);
	
	private String filePath = "";
	
	@Autowired
	private UserFileUploadService userFileUploadService;
	
	@Autowired
	private MessageUtil messageutil;
	
	@Autowired
	private UserDashBoard userDashBoard;
	
	@RequestMapping(value = "/viewUserUploadFile", method = RequestMethod.GET)
	public String userUploadFile(Model model, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("View User File Upload page-Start ");
		}
		
		int userId = (int) httpSession.getAttribute("userId"); // Session Of Current User
		
		List<UserFileUploadModel> userFileUploadModels = null;
		
		try {
			userFileUploadModels = userFileUploadService.fetchUserFileByUserId(userId);
			model.addAttribute("userFileUploadList", userFileUploadModels);
			
			
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("userFileUpload", e.getMessage());
			return "view-userFileUpload";
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
		
		if (logger.isDebugEnabled()) {
			logger.debug("View User File Upload page-End ");
		}

		return "view-userFileUpload";
	}
	
	@RequestMapping(value = "/addUserUploadFile", method = RequestMethod.POST)
	public String addUserUploadFile(Model model,HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("addUserUploadFile-Start ");
		}
		
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

		if (logger.isDebugEnabled()) {
			logger.debug("addUserUploadFile-End ");
		}

		return "add-userUploadFile";
	}
	
	@RequestMapping(value = "/saveUserUploadFile", method = RequestMethod.POST)
	public String saveuserUploadFile(Model model,
			@RequestParam("imageName") MultipartFile[] imageName,
			HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("saveuserUploadFile-Start ");
		}
		
		UserFileUploadModel userFileUploadModel = new UserFileUploadModel();
		
		int roleId = (int) httpSession.getAttribute("roleId");
		int userId = (int) httpSession.getAttribute("userId");
		
		for (int i = 0; i < imageName.length; i++) {

			MultipartFile file = imageName[i];

			if (file.getSize() > 0) {

				System.out.println("file.getOriginalFilename() ============= "+file.getOriginalFilename()+".................."+file.getSize());
				
				RoleModel roleModel = new RoleModel();
				
				roleModel.setRoleId(roleId);
				
				userFileUploadModel.setFileName(file.getOriginalFilename());
				userFileUploadModel.setFileSize(String.valueOf(file.getSize()));
				userFileUploadModel.setCreatedBy(userId);
				
				userFileUploadModel.setRoleModel(roleModel);
				
				// Upload the User Files In the Project Folder
				filePath = "/UserFileUpload/";
				FileUpload.uploadFile(filePath, file,
						file.getOriginalFilename(), request, response);
				
					userFileUploadService.insertUserFileUpload(userFileUploadModel);
					
					redirectAttributes.addFlashAttribute("fileUploaded", messageutil.getBundle("File.Saved")); 
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveuserUploadFile-End ");
		}

		return "redirect:/viewUserUploadFile";
	}
	
	@RequestMapping(value = "/updateUserUploadFile", method = RequestMethod.GET)
	public String updateUserUploadFile(Model model,
			HttpServletRequest request,final RedirectAttributes redirectAttributes,
			@RequestParam("id") int userFileUploadId) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateUserUploadFile-Start ");
		}

		try {
			userFileUploadService.updateUserFileUpload(userFileUploadId);
			redirectAttributes.addFlashAttribute("fileUploaded", messageutil.getBundle("File.Deleted")); 
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("fileUploaded", e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateUserUploadFile-End ");
		}
		
		return "redirect:/viewUserUploadFile";
		
	}
}
