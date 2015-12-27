package com.axis.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.MessageUtil;
import com.axis.common.UserDashBoard;
import com.axis.dao.RoleDao;
import com.axis.entity.RoleEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementLeaderModel;
import com.axis.model.AcheivementModel;
import com.axis.model.CampaignModel;
import com.axis.model.ExcellSheetUploadModel;
import com.axis.model.RoleModel;
import com.axis.service.AchievementFieldService;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.validation.AcheivementDataValidation;
import com.google.gson.Gson;

import java.util.Map.Entry;

//import com.axis.model.CampaignFieldModel;

@Controller
// @RequestMapping(value = "/Admin")
public class AchievementFieldController {

	@Autowired
	AchievementFieldService achievementFieldService;

	@Autowired
	RoleService roleService;

	@Autowired
	CampaignService campaignService;

	@Autowired
	AcheivementDataValidation acheivementDataValidation;

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	RoleDao roleDao;

	@Autowired
	private UserDashBoard userDashBoard;

	private static final Logger logger = Logger
			.getLogger(AchievementFieldController.class);

	@RequestMapping(value = "/Admin/viewacheivementfield", method = RequestMethod.GET)
	public String viewCampaignFields(ModelMap modelMap) throws DataNotFound,
			ObjectNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("View Acheivement Fields  Controller-Start ");
		}

		// Fetching the Acheivement Field List From DB
		List<AcheivementModel> acheivementModels = achievementFieldService
				.fetchAllAcheivementField();

		if (logger.isDebugEnabled()) {
			logger.debug("view AcheivementFieldValueController - End");
		}
		if (acheivementModels == null) {
			System.out.println("Its coming null value of its data");
			String message = messageUtil.getBundle("Achievement.not.found");
			modelMap.addAttribute("acheivementNotFound", message);
			return "view-acheivementfield";

		} else {
			for (AcheivementModel acheivementModel : acheivementModels) {

				modelMap.addAttribute("acheivementModels", acheivementModels);
				return "view-acheivementfield";
			}
			modelMap.addAttribute("acheivementModels", acheivementModels);
			return "view-acheivementfield";
		}

	}

	// For Adding the Acheivements

	@RequestMapping(value = "/Admin/addAcheivementFields", method = RequestMethod.GET)
	public String addCampaignFields(ModelMap modelMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Campaign Fields Dummy Controller-Start ");
		}

		// Fetching the Role List From DB

		List<RoleModel> roleModels = null;
		try {
			roleModels = roleService.getAllActiveRoleByStatus();

		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			modelMap.addAttribute("NoActiveRoleFound", e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Show CampaignList Controller-End ");
		}
		if (roleModels == null) {
			return "add-acheivementfield";
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Add Acheivement Fields Dummy Controller-End ");
			}
			modelMap.addAttribute("roleModels", roleModels);
			return "add-acheivementfield";
		}

	}

	// For RoleId Generate appropiate Campaign Names
	// It will return appropate
	@RequestMapping(value = "/Admin/campaignagainstRoleId", method = RequestMethod.GET)
	@ResponseBody
	public String campaignAgainstRoleId(HttpServletRequest request,
			ModelMap modelMap, @RequestParam String q) throws DataNotFound,
			ObjectNotFound {

		Integer roleId1 = Integer.valueOf(q);

		if (logger.isDebugEnabled()) {
			logger.debug("Campaign against role ID getting  Controller-Start ");
		}

		List<CampaignModel> campaignModels = null;
		try {
			campaignModels = campaignService
					.findAllActiveCampaignByRole(roleId1);
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			// model.addAttribute("campaignNotFound", e.getMessage());
		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String campaigns = gson.toJson(campaignModels);
		return campaigns;

	}

	// calling of campaign against role ID on load CAll
	@RequestMapping(value = "/Admin/campaignagainstRoleIdonload", method = RequestMethod.GET)
	@ResponseBody
	public String campaignAgainstRoleIdonload(HttpServletRequest request,
			ModelMap modelMap, @RequestParam String q) throws DataNotFound,
			ObjectNotFound {
		Integer roleId1 = Integer.valueOf(q);
		List<CampaignModel> campaignModels = campaignService
				.findAllActiveCampaignByRole(roleId1);

		Gson gson = new Gson();
		String campaigns = gson.toJson(campaignModels);
		modelMap.addAttribute("campaignModels", campaignModels);
		return campaigns;

	}

	// SAVING ACHEIVEMENT FIELD NAME
	@RequestMapping(value = "/Admin/saveAcheivementFields", method = RequestMethod.POST)
	public String saveCampaignFields(Model model, ModelMap modelMap,
			@ModelAttribute AcheivementModel acheivementModel,
			final RedirectAttributes redirectAttributes) throws DataNotFound,
			ObjectNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("Save Acheivement  Field Name Dummy Controller-Start ");
		}

		try {
			achievementFieldService.insertFieldAcheivement(acheivementModel);
			String afterAdded = messageUtil
					.getBundle("Achievement.data.Inserted.found");
			model.addAttribute("afterAdded", afterAdded);
			redirectAttributes.addFlashAttribute("afterAdded", afterAdded);
			return ("redirect:/Admin/viewacheivementfield");

		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}
			try {

				List<RoleModel> roleModels = null;

				roleModels = roleService.getAllActiveRoleByStatus();
				model.addAttribute("roleModels", roleModels);

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "
						+ e1.getMessage());
				// model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}

		} catch (RecordFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String duplicateValue = messageUtil
					.getBundle("Achievement.data.Duplicate.found");
			System.out.println("************data duplicated******************");
			redirectAttributes.addFlashAttribute("duplicateValue",
					duplicateValue);
			// return new ModelAndView("redirect:/Admin/addContentPage");

			try {

				List<RoleModel> roleModels = null;

				roleModels = roleService.getAllActiveRoleByStatus();
				model.addAttribute("roleModels", roleModels);

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "
						+ e1.getMessage());
				// model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}
		}

		redirectAttributes.addFlashAttribute("a", acheivementModel);
		// model.addAttribute("a", acheivementModel);
		System.out.println("look here role id :: "
				+ acheivementModel.getRoleId());
		return "redirect:/Admin/addAcheivementFields";

	}

	/**
	 * For Edit in the Achievement Section
	 * 
	 * @param modelMap
	 * @param request
	 * @param id
	 * @return
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */

	@RequestMapping(value = "/Admin/editAcheivement", method = RequestMethod.GET)
	public String editCampaign(ModelMap modelMap, HttpServletRequest request,
			@RequestParam("id") int id) throws ObjectNotFound, DataNotFound {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Acheivement Dummy Controller-Start ");
		}

		AcheivementModel acheivementModel = new AcheivementModel();

		// Get the details of the Acheivement respect of the Selected ID
		try {
			acheivementModel = achievementFieldService.getAcheivementByID(id);
		} catch (NumberFormatException e) {
			logger.debug(e.getMessage(), e);
			modelMap.addAttribute("invalidID", "Please Enter a Valid ID");
		} catch (DataNotFound e) {
			logger.debug(e.getMessage(), e);
			modelMap.addAttribute("campaignNotFound", e.getMessage());
		}

		List<RoleModel> roleModels = null;
		try {
			roleModels = roleService.getAllActiveRoleByStatus();

		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			modelMap.addAttribute("campaignNotFound", e.getMessage());
		}
		modelMap.addAttribute("acheivementModel", acheivementModel);
		modelMap.addAttribute("roleModels", roleModels);

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Acheivement Dummy Controller-End ");
		}
		return "edit-acheivementfield";
	}

	@RequestMapping(value = "/Admin/deleteAcheivementValue", method = RequestMethod.GET)
	public String deleteAcheivementFieldValue(ModelMap modelMap,
			@ModelAttribute AcheivementModel acheivementModel,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Acheivement Field Value Controller-Start ");
		}

		Integer acheivementFieldId = acheivementModel.getAcheivementId();

		try {
			achievementFieldService
					.deleteAcheivementFieldServices(acheivementModel);
			String deleteValues = messageUtil
					.getBundle("Achievement.data.Delete.found");
			// model.addAttribute("updateValues", updateValues);
			redirectAttributes.addFlashAttribute("deleteValues", deleteValues);
			return ("redirect:/Admin/viewacheivementfield");

		} catch (Exception e1) {
			logger.debug(e1.getMessage(), e1);

			return ("redirect:/Admin/viewacheivementfield?errorMessage=" + e1
					.getMessage());
		}
	}

	@RequestMapping(value = "/Admin/saveEditedAcheivementValues", method = RequestMethod.POST)
	public String saveEditedAcheivementFieldValues(Model model,
			@ModelAttribute AcheivementModel acheivementModel,
			final RedirectAttributes redirectAttributes) throws DataNotFound,
			ObjectNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("Save Edited Acheivement Field  Controller-Start ");
		}

		try {
			achievementFieldService
					.saveEditedAcheivementFieldServices(acheivementModel);
			String updateValues = messageUtil
					.getBundle("Achievement.data.Update.found");
			model.addAttribute("updateValues", updateValues);
			redirectAttributes.addFlashAttribute("updateValues", updateValues);
			return ("redirect:/Admin/viewacheivementfield");

		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}
			try {

				List<RoleModel> roleModels = null;

				roleModels = roleService.getAllActiveRoleByStatus();
				model.addAttribute("roleModels", roleModels);

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "
						+ e1.getMessage());
				// model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}

		} catch (RecordFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String duplicateValue = messageUtil
					.getBundle("Achievement.data.Duplicate.found");
			System.out.println("************data duplicated******************");
			redirectAttributes.addFlashAttribute("duplicateValue",
					duplicateValue);
			// return new ModelAndView("redirect:/Admin/addContentPage");

			try {

				List<RoleModel> roleModels = null;

				roleModels = roleService.getAllActiveRoleByStatus();
				model.addAttribute("roleModels", roleModels);

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "
						+ e1.getMessage());
				// model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}
		}

		redirectAttributes.addFlashAttribute("b", acheivementModel);
		System.out.println("Role ID isssss in update"
				+ acheivementModel.getRoleId());
		// acheivementModel.getAcheivementId()
		return "redirect:/Admin/editAcheivement?id="
				+ acheivementModel.getAcheivementId();
	}

	@RequestMapping(value = "/Admin/saveuploadAcheivementExcellSheet", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public String acheivementExcellFileUpload(@RequestParam String campaignId,
			@RequestParam String roleId,
			@RequestParam("fileName") MultipartFile fileName,
			ModelMap modelMap, final RedirectAttributes redirectAttributes)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(".......Excell Sheet Upload first Step..... ");
		}

		System.out.println("When the data came properly");

		// acheivementDataValidation.validateFormAcheivementExcell(roleId,
		// campaignId);
		// Fetching the Role List From DB
		//

		/*
		 * if (name.trim().equals("")) throw new
		 * DataNotFound("File not specified");
		 */
		String name = fileName.getOriginalFilename();
		try {

			// ExcellSheetUploadModel excellSheetUploadModel1 =
			// achievementFieldService.getFileandSave(campaignId, roleId,
			// fileName, name);

			achievementFieldService.getvalidation(campaignId, roleId, fileName,
					name);
			Integer campaignid = Integer.valueOf(campaignId);
			Integer roleid = Integer.valueOf(roleId);

			System.out.println("Original file name is ......" + name);

			if (name.contains(".xls")) {
				ExcellSheetUploadModel excellSheetUploadModel = achievementFieldService
						.getFileandSave(campaignId, roleId, fileName, name);
				System.out.println("data UNDER THE CONTROLLER ISSS"
						+ excellSheetUploadModel.getErrormap().size());
				redirectAttributes.addFlashAttribute("mapsValue",
						excellSheetUploadModel.getErrormap());
				redirectAttributes.addFlashAttribute("insertedRows",
						excellSheetUploadModel.getInsertedRows());
				redirectAttributes.addFlashAttribute("numberOfRows",
						excellSheetUploadModel.getRowsInserted());
				redirectAttributes.addFlashAttribute("blankSpaceRows",
						excellSheetUploadModel.getBlankSpaceString());
				redirectAttributes.addFlashAttribute("sizeofmaps",
						excellSheetUploadModel.getErrormap().size());
				redirectAttributes.addFlashAttribute("sizeofrowsinserted",
						excellSheetUploadModel.getInsertedRows().size());
				return "redirect:/Admin/updateacheivementexcellsheet";
			}

		}

		catch (FormExceptions e) {

			System.out.println("in FormExceptions catch :: ");

			for (Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: " + entry.getKey() + " value :: "
						+ entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry
						.getValue().getMessage());
			}

		}
		redirectAttributes.addFlashAttribute("roleId", roleId);
		redirectAttributes.addFlashAttribute("campaignId", campaignId);
		System.out.println("Filename ::::" + name);
		redirectAttributes.addFlashAttribute("name", name);

		return "redirect:/Admin/updateacheivementexcellsheet";

	}

	@RequestMapping(value = "/Admin/updateacheivementexcellsheet", method = RequestMethod.GET)
	public String updateacheivementexcellsheet(ModelMap modelMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Campaign Fields Dummy Controller-Start ");
		}

		// Fetching the Role List From DB
		//

		List<RoleModel> roleModels = null;
		try {
			roleModels = roleService.getAllActiveRoleByStatus();

		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			modelMap.addAttribute("NoActiveRoleFound", e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Add Acheivement Fields Dummy Controller-End ");
		}
		modelMap.addAttribute("roleModels", roleModels);
		System.out.println("sizes of errormap isssss");
		return "upload-AcheivementExcelSheet";
	}

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
	@RequestMapping(value = "/leaderBoardAcheivement", method = RequestMethod.GET)
	public String leaderBoardAcheivement(ModelMap modelMap,
			HttpServletRequest request,
			@RequestParam("campaignid") int campaignid, Model model) {

		System.out.println("Under the controller of leader board");

		HttpSession httpSession = request.getSession(true);

		int campaignId = campaignid;

		int roleId = (int) httpSession.getAttribute("roleId");
		
		// ................Getting Employee Number from
		// Session......................//
		String employeeNumber = (String) httpSession
				.getAttribute("employeeNumber");
		// Integer roleId=3;

		System.out.println("Employee Number isssss:::::" + employeeNumber);

		System.out.println("RoleId are::::" + roleId);
		System.out.println("Camp ID are::::" + campaignId);

		try {
			CampaignModel campaignModel = campaignService
					.fetchCampaignById(campaignId);
			modelMap.addAttribute("CampaignName",
					campaignModel.getCampaignName());
			
			String quarterId = campaignModel.getQuarterId();
			
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
		// RoleEntity roleEntity=roleDao.find(roleId);

		// System.out.println("Role NAme issss"+roleEntity.getRoleName());

		AcheivementLeaderModel acheivementLeaderModel = new AcheivementLeaderModel();

		try {

			acheivementLeaderModel = achievementFieldService
					.getAcheivementFieldValues(campaignId, roleId,
							employeeNumber);
			modelMap.addAttribute("finalList",
					acheivementLeaderModel.getFinalList());
			modelMap.addAttribute("headerList",
					acheivementLeaderModel.getHeaderList());
			modelMap.addAttribute("bodycontents",
					acheivementLeaderModel.getBodycontains());
			modelMap.addAttribute("finalList2",
					acheivementLeaderModel.getFinalList2());
			modelMap.addAttribute("headerList2",
					acheivementLeaderModel.getHeaderList2());
			modelMap.addAttribute("bodycontents2",
					acheivementLeaderModel.getBodycontains2());
			modelMap.addAttribute("LeaderHeader",
					acheivementLeaderModel.getAchievementLeaderNamedModels());
			modelMap.addAttribute("bodycontainsdetails",
					acheivementLeaderModel.getAchievementbodyContains());
			modelMap.addAttribute("LeaderHeaderTop20", acheivementLeaderModel
					.getAchievementLeaderNamedModelsTop20());
			modelMap.addAttribute("bodycontainsdetailsTop20",
					acheivementLeaderModel.getAchievementbodyContainsTop20());

		} catch (DataNotFound e) {
			acheivementLeaderModel = achievementFieldService
					.getRoleNameagainstId(roleId);

			modelMap.addAttribute("DataNotFound", e.getMessage());

			System.out
					.println("Exception Messages isssss::::" + e.getMessage());

			e.printStackTrace();
			if (acheivementLeaderModel.getRoleNameController().equals(
					"Circle Head")) {

				return "leaderboard-circle-head-view";
			} else if (acheivementLeaderModel.getRoleNameController().equals(
					"Branch Head")) {

				return "leaderboard-branch-head-view";
			} else {

				System.out.println("Normal Head.........Catch");

				return "leaderboard";
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

			} finally {
				try {
					model.addAttribute("logoIcon",
							UserLoginController.getIconPath(request));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (acheivementLeaderModel.getRoleName().equals("CircleHead")) {

			return "leaderboard-circle-head-view";
		} else if (acheivementLeaderModel.getRoleName().equals("BranchHead")) {

			return "leaderboard-branch-head-view";
		} else {

			return "leaderboard";
		}

	}

}
