package com.axis.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.FileDownload;
import com.axis.common.MessageUtil;
import com.axis.dao.CampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;
import com.axis.model.CampaignModel;
import com.axis.model.RoleModel;
import com.axis.service.AchievementFieldValueService;
import com.axis.service.RoleService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/Admin")
public class AchievementFieldValueController {

	private String filePath = "";
	
	@Autowired
	AchievementFieldValueService achievementFieldValueService;

	@Autowired
	RoleService roleService;

	@Autowired
	RoleDao roleDao;
	@Autowired
	CampaignDao campaignDao;

	@Autowired
	MessageUtil messageUtil;

	// .............Getting All The Acheivement Field VAlues..................//

	private static final Logger logger = Logger
			.getLogger(AchievementFieldController.class);

	@RequestMapping(value = "/viewacheivementfieldvalueManagement", method = RequestMethod.GET)
	public String viewCampaignFields(ModelMap modelMap) throws DataNotFound,
			ObjectNotFound {
		if (logger.isDebugEnabled()) {
			logger.debug("View Acheivement Fields Dummy Controller-Start ");
		}

		// ...........Fetching the Acheivement Field Values List From
		// DB......................//
		List<AcheivementFieldValueModel> acheivementFieldValueModels = null;
		acheivementFieldValueModels = achievementFieldValueService
				.getAllAcheivementFieldVAlues();

		// ......If getting no Values from the Acheivement Field Master
		// Table........//
		if (acheivementFieldValueModels == null) {

			String message = messageUtil.getBundle("Achievement.not.found");
			modelMap.addAttribute("acheivementFieldValueNotFound", message);
			return "view-acheivementfieldvalue";
		} else {

			if (logger.isDebugEnabled()) {
				logger.debug("View Campaign Fields Dummy Controller-End ");
			}
			modelMap.addAttribute("acheivementFieldValueModels",
					acheivementFieldValueModels);
			return "view-acheivementfieldvalue";
		}

	}

	// ...................Adding Acheivement Field Values to the Acheivement
	// Field Value Master Table.............//
	@RequestMapping(value = "/addAcheivementFieldValues", method = RequestMethod.GET)
	public String addCampaignFields(ModelMap modelMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Acheivement Fields Dummy Controller-Start ");
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

		/*
		 * if(roleModels==null){ return "add-acheivement-field-value-page"; }
		 * else{ if (logger.isDebugEnabled()) {
		 * logger.debug("Add Acheivement Fields Dummy Controller-End "); }
		 */
		modelMap.addAttribute("roleModels", roleModels);
		return "add-acheivementfieldvalue";
	}

	// }

	@RequestMapping(value = "/createDynamicFieldforAcheivementField", method = RequestMethod.GET)
	@ResponseBody
	public String campaignAgainstRoleId(HttpServletRequest request,
			ModelMap modelMap, @RequestParam String roleId,
			@RequestParam String campId) throws DataNotFound, ObjectNotFound {

		Integer roleId1 = Integer.valueOf(roleId);
		Integer campId1 = Integer.valueOf(campId);

		List<AcheivementModel> acheivementModels = achievementFieldValueService
				.getroleId(roleId1, campId1);

		Gson gson = new Gson();
		String acheivementmodel1s = gson.toJson(acheivementModels);
		return acheivementmodel1s;

	}
	
	
	/**
     * For getting Generated Excel Sheet
	 * @throws IOException 
     */
    @RequestMapping(value = "/downloadAcheivementFieldValueExcel", method = RequestMethod.GET)
    public String downloadAcheivementFieldValueExcel(HttpServletRequest request,HttpServletResponse response,
			ModelMap modelMap, @RequestParam String roleId,final RedirectAttributes redirectAttributes,
			@RequestParam String campId) throws DataNotFound, ObjectNotFound, IOException {
    	
    	System.out.println("in excell ajax");

		Integer roleId1 = Integer.valueOf(roleId);
		Integer campId1 = Integer.valueOf(campId);
		
		

		List<AcheivementModel> acheivementModels = achievementFieldValueService
				.getroleId(roleId1, campId1);
		
		for(AcheivementModel acheivementModel : acheivementModels) {
			System.out.println("excell values :: "+acheivementModel.getFieldName());
		}
		
		
		
		/* ******************** Excell Starts ********************  */
		
		  HSSFWorkbook myWorkBook = new HSSFWorkbook();
		  HSSFSheet mySheet = myWorkBook.createSheet();
		  
		  
		 
		   Row headerRow = mySheet.createRow(0);
		   headerRow.setHeightInPoints(50);
		   
		   int i=0;
		   
		   for(AcheivementModel acheivementModel : acheivementModels) {
			   
			    Cell monthCell = headerRow.createCell(i++);
		    	monthCell.setCellValue(acheivementModel.getFieldName());
			   
		   }
		   
		   String fileName = roleId1+"_"+campId1+".xls";
		   filePath = "/RoleCampaignExcel/";
		   
		// get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        System.out.println("appPath = " + appPath);
	 
	        // Creating the directory to store file
			
			File dir = new File(appPath + File.separator + filePath);
	        
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getPath() + File.separator
					+ fileName);
		   
			
		   //File file = new File("D:\\"+roleId1+"_"+campId1+".xls");
			
		   FileOutputStream fop = new FileOutputStream(serverFile);  
		   myWorkBook.write(fop);
		   
		   try {
				FileDownload.downloadFile(filePath,fileName, request, response);
				redirectAttributes.addFlashAttribute("campaignAdded", messageUtil.getBundle("File.Download"));
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("campaignAdded", messageUtil.getBundle("File.not.found"));
			}
 

		//PrintWriter pw=response.getWriter();
		//pw.println(serverFile.getPath());
		
		return "upload-AcheivementExcelSheet";

	}
	
	

	@RequestMapping(value = "/saveAcheivementFieldValue", method = RequestMethod.POST)
	public String saveAcheivementFieldValue(
			Model model,
			@ModelAttribute AcheivementFieldValueModel acheivementFieldValueModel,
			final RedirectAttributes redirectAttributes) throws DataNotFound,
			ObjectNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Acheivement Field Value Controller-Start ");
		}

		try {
			achievementFieldValueService
					.insertAcheivementFieldValueService(acheivementFieldValueModel);
			// targetFieldValueBusiness.insertTargetFieldValueService(targetFieldValueModel);
			String afterAdded =  messageUtil.getBundle("Achievement.data.Inserted.found");
			model.addAttribute("afterAdded", afterAdded);
			redirectAttributes.addFlashAttribute("afterAdded", afterAdded);
			return ("redirect:/Admin/viewacheivementfieldvalueManagement");

		} catch (FormExceptions e) {

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
			String duplicateValue =  messageUtil.getBundle("Achievement.Value.Duplicate.found");
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

		redirectAttributes.addFlashAttribute("a", acheivementFieldValueModel);
		return "redirect:/Admin/addAcheivementFieldValues";

	}

	@RequestMapping(value = "/editAcheivementFieldValue", method = RequestMethod.GET)
	public String editAcheivementFieldValue(
			ModelMap modelMap,
			@ModelAttribute AcheivementFieldValueModel acheivementFieldValueModel1,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Acheivement Field Value Controller-Start ");
		}

		Integer acheivementFieldValueId = acheivementFieldValueModel1
				.getAcheivementFieldValueId();

		try {
			AcheivementFieldValueModel acheivementFieldValueModel = achievementFieldValueService
					.editAcheivementFieldValueServices(acheivementFieldValueId);
			modelMap.addAttribute("acheivementFieldValueModel",
					acheivementFieldValueModel);

			return ("edit-acheivementfieldvalue");

		} catch (Exception e1) {
			logger.debug(e1.getMessage(), e1);

			return ("redirect:/Admin/viewacheivementfieldvalueManagement?errorMessage=" + e1
					.getMessage());
		}
	}

	@RequestMapping(value = "/deleteAcheivementFieldValue", method = RequestMethod.GET)
	public String deleteAcheivementFieldValue(
			ModelMap modelMap,
			@ModelAttribute AcheivementFieldValueModel acheivementFieldValueModel1,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete Acheivement Field Value Controller-Start ");
		}

		Integer acheivementFieldValueId = acheivementFieldValueModel1
				.getAcheivementFieldValueId();

		try {
			achievementFieldValueService
					.deleteAcheivementFieldValueServices(acheivementFieldValueModel1);
			// modelMap.addAttribute("acheivementFieldValueModel",
			// acheivementFieldValueModel);

			return ("redirect:/Admin/viewacheivementfieldvalueManagement");

		} catch (Exception e1) {
			logger.debug(e1.getMessage(), e1);

			return ("redirect:/Admin/viewacheivementfieldvalueManagement?errorMessage=" + e1
					.getMessage());
		}
	}

	@RequestMapping(value = "/saveEditedAcheivementFieldValues", method = RequestMethod.POST)
	public String saveEditedAcheivementFieldValues(
			Model model,
			@ModelAttribute AcheivementFieldValueModel acheivementFieldValueModel,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Save Edited Acheivement Field Value Controller-Start ");
		}

		try {
			achievementFieldValueService
					.saveEditedAcheivementFieldValueServices(acheivementFieldValueModel);
			// targetFieldValueBusiness.insertTargetFieldValueService(targetFieldValueModel);
			String updatedValue =messageUtil.getBundle("Achievement.Value.Update.found");
			redirectAttributes.addFlashAttribute("updatedValue", updatedValue);
			return ("redirect:/Admin/viewacheivementfieldvalueManagement");

		} catch (Exception e1) {
			logger.debug(e1.getMessage(), e1);
			String datanotThere = messageUtil.getBundle("Achievement.Value.Update.failure");
			redirectAttributes.addFlashAttribute("datanotThere", datanotThere);
			redirectAttributes.addFlashAttribute("acheivementFieldValueModel",
					acheivementFieldValueModel);
			return "redirect:/Admin/editAcheivementFieldValue?acheivementFieldValueId="
					+ acheivementFieldValueModel.getAcheivementFieldValueId();
		}
	}
	
	
	

}
