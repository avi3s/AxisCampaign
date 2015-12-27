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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignModel;
import com.axis.model.RoleModel;
import com.axis.model.TargetFieldModel;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.service.TargetFieldService;

@Controller
@RequestMapping(value="/Admin")
public class TargetFieldController {
	
	private static final Logger logger = Logger.getLogger(TargetFieldController.class);
	
	
	@Autowired
	private TargetFieldService targetFieldService;
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Display All The Active Target List To the Admin
	 * @return
	 */
	@RequestMapping(value="/viewTargetField", method=RequestMethod.GET)
	public String viewTargetField(Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("view TargetFieldController - Start");
		}
		
		List<TargetFieldModel> targetList = null;

		try {
			targetList = targetFieldService.getAllTarget();
			
			
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("targetNotFound", e.getMessage());
		} catch (DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("targetNotFound", e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("targetList", targetList);
		
		if(logger.isDebugEnabled()){
			logger.debug("view TargetFieldController - End");
		}
		
		System.out.println("target List: "+ targetList);
		
		return "view-target-field";
		
	}
	
	
	/**
	 * Show campaign list based on role id
	 * @param roleid
	 * @param request
	 * @param model
	 * @return
	 */
	//@RequestMapping(value="/showCampaignList", method=RequestMethod.POST)
	 @RequestMapping(value="/showCampaignList", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<CampaignModel> createDynamicFields(@RequestParam("roleid") int roleid, HttpServletRequest request, Model model){
		
		//System.out.println("Ok. All right."+ roleid);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Show CampaignList Controller-Start ");
		}		
		
		// Fetching the Campaign by role List From DB
		List<CampaignModel> campaignList = null;
		
		if(roleid != 0){
			campaignList=new ArrayList<CampaignModel>();
		}
		try {			
			campaignList = campaignService.findAllActiveCampaignByRole(roleid);
			
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			//model.addAttribute("campaignNotFound", e.getMessage());
		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		if (logger.isDebugEnabled()) {
			logger.debug("Show CampaignList Controller-End ");
		}		
		
		//System.out.println(campaignList);
		
		return campaignList;
	}
	
	
	/**
	 * Add Target Dummy Page Link
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addTargetField", method={RequestMethod.POST,RequestMethod.GET})
	public String addTargetField(Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("Add Dummy TargetFieldController - Start");
		}
		
		
		//Fetching the Role List from DB
		List<RoleModel> roleList = null;
		
		try {
			roleList = roleService.getAllActiveRoleByStatus();
			
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("NoActiveRoleFound", e.getMessage());
		}	

		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Dummy Controller-End ");
		}
		
		
		
		System.out.println("Role List: "+ roleList);
		
		if(logger.isDebugEnabled()){
			logger.debug("Add Dummy TargetFieldController - End");
		}
		
		//model.addAttribute("campaignList", campaignList);
		model.addAttribute("roleList", roleList);
		
		return "add-target-field";
	}
	
	/**
	 * Save target field
	 * @param model
	 * @param targetFieldModel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/saveTargetField", method = RequestMethod.POST)
	public String saveTarget(Model model, @ModelAttribute TargetFieldModel targetFieldModel,
			final RedirectAttributes redirectAttributes,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Controller-Start ");
		}

		System.out.println("Target Add Controller");

		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			targetFieldModel.setCreatedBy(createdBy);
			int targetFieldCount=targetFieldService.insertTargetService(targetFieldModel);
			
			System.out.println("targetFieldCount: "+ targetFieldCount);
			
			if(targetFieldCount != 0){
				redirectAttributes.addFlashAttribute("targetFieldCount", targetFieldCount + " Field Name(s) added successfully.");
				return ("redirect:/Admin/viewTargetField");
			}
			else{
				redirectAttributes.addFlashAttribute("targetFieldCount", " Target Field not added due to duplicate name.");
				redirectAttributes.addFlashAttribute("targetFieldModel",targetFieldModel);
				return ("redirect:/Admin/addTargetField");
			}
			
		  //return ("redirect:/Admin/viewTargetField");
			//return "/Admin/viewTargetField";

		} catch ( FormExceptions e) {
			logger.debug(e.getMessage(), e);
			//redirectAttributes.addFlashAttribute("targetFieldModel", targetFieldModel);
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getMessage());
			}
			System.out.println("Length============"+targetFieldModel.getFieldName_array().length);
			targetFieldModel.setLength(targetFieldModel.getFieldName_array().length);
			redirectAttributes.addFlashAttribute("targetFieldModel",targetFieldModel);			
			
			return ("redirect:/Admin/addTargetField");
			
		}  catch (DataNotFound e1) {
			System.out.println("EXCEPTION Caught in saveTargetField :: "+e1.getMessage());
			model.addAttribute("noDataFoundForTargetField",e1.getMessage());
			return ("redirect:/Admin/addTargetField");
		}
	}
	
	
	/**
	 * Display edit target field
	 * @param model
	 * @param request
	 * @param targetModel
	 * @return
	 */
	@RequestMapping(value = "/editTargetField", method={RequestMethod.GET,RequestMethod.POST})
	public String editTarget(Model model, HttpServletRequest request,
			@ModelAttribute TargetFieldModel targetModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit Target Field Controller-Start ");
		}			
		
		
		List<RoleModel> roleList = null;
		List<CampaignModel> campaignList = null;
		
		System.out.println("targetModel.getTargetFieldId(): "+ targetModel.getTargetFieldId());
		
		if(targetModel.getTargetFieldId() == 0){
			//System.out.println("hi");			
			try{
				targetModel = targetFieldService.getTargetByID(Integer.parseInt(request.getParameter("id")));
			}catch(ObjectNotFound e){
				logger.debug(e.getMessage(), e);				
			}
		}
		else{
			//System.out.println("Ok");
			try {
				targetModel = targetFieldService.getTargetByID(targetModel.getTargetFieldId());
			} catch (ObjectNotFound e) {
				logger.debug(e.getMessage(), e);	
			}
		}

		try {
				roleList = roleService.getAllActiveRoleByStatus();
				
				int roleId = targetModel.getRoleId();
				
				campaignList = campaignService.findAllActiveCampaignByRole(roleId);
				
				System.out.println("campaign List: "+campaignList);
				System.out.println("Target Model: "+targetModel);
				
				/*targetModel = targetFieldService.getTargetByID(targetModel.getTargetFieldId());
				System.out.println("targetModel"+ targetModel);*/
				
				model.addAttribute("campaignList", campaignList);
				model.addAttribute("roleList", roleList);
				model.addAttribute("targetModel", targetModel);

				if (logger.isDebugEnabled()) {
					logger.debug("Edit Target Controller-End ");
				}				
				
				model.addAttribute("update", 1);
				//targetModel.setRequestType(1);
				
				return "add-target-field";
			
			
			
		} catch(DataNotFound e){
			model.addAttribute("update", 1);
			   model.addAttribute("campaignList", campaignList);
			   model.addAttribute("roleList", roleList);
			   model.addAttribute("targetModel", targetModel);
			   model.addAttribute("targetNotFound", e.getMessage());
			   return "add-target-field";
		} catch (ObjectNotFound e) {
			model.addAttribute("update", 1);
			   model.addAttribute("campaignList", campaignList);
			   model.addAttribute("roleList", roleList);
			   model.addAttribute("targetModel", targetModel);
			   model.addAttribute("targetNotFound", e.getMessage());
			   return "add-target-field";
		}
		
	}
	
	
	/**
	 * 
	 * @param model
	 * @param targetFieldModel
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/updateTargetField", method = {RequestMethod.POST,RequestMethod.GET})
	public String updateTarget(Model model, @ModelAttribute final TargetFieldModel targetFieldModel,
			HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Update Admin Tareget Field Controller-End ");
		}
		
		try {
			int updatedBy = (int) httpSession.getAttribute("userId");
			targetFieldModel.setUpdatedBy(updatedBy);
			int updateRecord = targetFieldService.updateTargetField(targetFieldModel);		
			
			System.out.println("updateRecord: "+updateRecord);
			if( updateRecord == 0 ){
				redirectAttributes.addFlashAttribute("targetFieldCount", " Target Field updated successfully.");
				return ("redirect:/Admin/viewTargetField");
			}
			else{
				redirectAttributes.addFlashAttribute("targetFieldCount", " Target Field not updated due to duplicate name.");
				redirectAttributes.addFlashAttribute("targetFieldModel",targetFieldModel);
				return ("redirect:/Admin/editTargetField");
			}
			
		} catch (final FormExceptions e) {
			
			logger.error("Target Field Value Update not Successfully.", e);			
			
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getMessage());
			}
			//System.out.println("Length============"+targetFieldModel.getFieldName_array().length);
			//targetFieldModel.setLength(targetFieldModel.getFieldName_array().length);
			redirectAttributes.addFlashAttribute("targetFieldModel",targetFieldModel);
			
			
			return ("redirect:/Admin/editTargetField");			
			
		} catch (DataNotFound e) {
			return ("redirect:/Admin/editTargetField");
		}		
		
	}
	
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @param targetFieldModel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/deleteTargetField", method = RequestMethod.GET)
	public String deleteTargetField(Model model, HttpServletRequest request,
			@ModelAttribute TargetFieldModel targetFieldModel,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete TargetField Controller-Start ");
		}
		
		targetFieldModel.setTargetFieldId(Integer.parseInt(request.getParameter("id")));		
		
		try{
			targetFieldModel = targetFieldService.getTargetByID(targetFieldModel.getTargetFieldId());
			
			try{
				targetFieldService.deleteTargetFieldValue(targetFieldModel);
				redirectAttributes.addFlashAttribute("targetFieldCount", " Field Name(s) deleted successfully.");
				
				return ("redirect:/Admin/viewTargetField");
			} catch(DataNotFound e){
				return ("redirect:/Admin/viewTargetField");	
			}
		} catch(ObjectNotFound e){
			return ("redirect:/Admin/viewTargetField");
		}		
		
	}
	
	
	@RequestMapping(value="/checkFieldName", method=RequestMethod.POST)
	public @ResponseBody String checkFieldName(@RequestParam("roleId") int roleId, @RequestParam("campId") int campId, @RequestParam("fieldName") String fieldName,  Model model){
		
		if (logger.isDebugEnabled()) {
			logger.debug("Check TargetField Name Controller-Start ");
		}
		
		System.out.println("RoleId: "+ roleId+ " camp Id: "+ campId+ " fieldName: "+fieldName);
		
		int totalTargetFields = targetFieldService.checkTargetFieldNameService(roleId, campId, fieldName);
		
		if(totalTargetFields != 0)		
			return "Target Field '" + fieldName + "' is already available"; 
		else
			return "";
	}
	
}
