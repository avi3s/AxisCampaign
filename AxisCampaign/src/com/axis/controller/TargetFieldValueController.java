package com.axis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.TargetFieldValueModel;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.service.TargetFieldValueService;

@Controller
@RequestMapping(value="/Admin")
public class TargetFieldValueController {

	private static final Logger logger = Logger.getLogger(TargetFieldValueController.class);
	
	@Autowired
	private TargetFieldValueService targetFieldValueService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private CampaignService campaignService;
	
	/**
	 * view Target Field Value
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewTargetFieldValue", method=RequestMethod.GET)
	public String viewTargetFieldValue(Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("view TargetFieldValueController - Start");
		}
		
		List<TargetFieldValueModel> targetValueList = null;
		
		try {
			targetValueList = targetFieldValueService.getAllTargetValue();
			
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("targetValueNotFound", e.getMessage());
		} catch (DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("targetValueNotFound", e.getMessage());
		}
		model.addAttribute("targetValueList", targetValueList);
		
		return "view-target-field-value";
		
	}
	
	
	/**
	 * Add Dummy Target Field Value
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addTargetFieldValue", method={RequestMethod.POST, RequestMethod.GET})
	public String addTargetField(Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("Add Dummy TargetFieldValue Controller - Start");
		}
		
		
		//Fetching the Role List from DB
		List<RoleModel> roleList = null;
		
		try {
			roleList = roleService.getAllActiveRoleByStatus();
			
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("NoActiveRoleFound", e.getMessage());
		}	

		
		
		System.out.println("Role List: "+ roleList);
		
		
			if(logger.isDebugEnabled()){
				logger.debug("Add Dummy TargetFieldValueController - End");
			}
			model.addAttribute("roleList", roleList);
			
			return "add-target-field-value";
		
	}
	
	/**
	 * 
	 * @param roleid
	 * @param campid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createDynamicFields", method=RequestMethod.POST)
	public @ResponseBody List<String> createDynamicFields(@RequestParam("roleid") int roleid, @RequestParam("campid") int campid, Model model){
		
		System.out.println("Ok. All right."+ roleid +" & "+campid);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Field Value DynamicFields Controller-Start ");
		}		
		
		List<String> targetFields=null;
		if(campid != 0){
			targetFields=new ArrayList<String>();
		}
		try {			
			 targetFields=targetFieldValueService.findAllFieldNames(roleid, campid);
			targetFields.add(String.valueOf(campid));
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			//model.addAttribute("campaignNotFound", e.getMessage());
		}			
		
		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Field Value DynamicFields Controller-End ");
		}		
		
		System.out.println("createDynamicFields targetFields: "+targetFields);
		
		return targetFields;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param targetFieldValueModel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/saveTargetFieldValue", method = RequestMethod.POST)
	public String saveTargetFieldValue(Model model, @ModelAttribute TargetFieldValueModel targetFieldValueModel,
			final RedirectAttributes redirectAttributes,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Field Value Controller-Start ");
		}

		//System.out.println("value:  *******: "+targetFieldValueModel.getFieldValue_array()[0]);
		
		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			targetFieldValueModel.setCreatedBy(createdBy);
			int totalTargetValue = targetFieldValueService.insertTargetFieldValueService(targetFieldValueModel);
			redirectAttributes.addFlashAttribute("totalTargetValue", totalTargetValue + " Target Field Value added successfully.");
			return ("redirect:/Admin/viewTargetFieldValue");

		} catch (FormExceptions e) {
			
			logger.debug(e.getMessage(), e);
			//redirectAttributes.addFlashAttribute("targetFieldModel", targetFieldModel);
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getMessage());
			}
			if(targetFieldValueModel.getRoleId() != 0 && targetFieldValueModel.getCampaignId() != 0){
				//System.out.println("Length============"+targetFieldValueModel.getFieldValue_array().length);
				//System.out.println("Value: ======= : "+targetFieldValueModel.getFieldValue_array()[0]);
				//targetFieldValueModel.setLength(targetFieldValueModel.getFieldValue_array().length);
			}
			
			redirectAttributes.addFlashAttribute("targetFieldValueModel",targetFieldValueModel);			
			
			return ("redirect:/Admin/addTargetFieldValue");
			
			/*redirectAttributes.addFlashAttribute("targetFieldValueModel", targetFieldValueModel);
			return ("redirect:/Admin/addTargetFieldValue?errorMessage=" + e1.getMessage());*/
			
		} catch (DataNotFound e1) {
			System.out.println("EXCEPTION Caught in saveTargetField :: "+e1.getMessage());
			model.addAttribute("noDataFoundForTargetField",e1.getMessage());
			return ("redirect:/Admin/addTargetFieldValue");
		}
		
		
	}
	
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param targetFieldValueModel
	 * @return
	 */
	@RequestMapping(value = "/editTargetFieldValue", method = {RequestMethod.GET, RequestMethod.POST})
	public String editTarget(Model model, HttpServletRequest request, @ModelAttribute TargetFieldValueModel targetFieldValueModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("Edit TargetFieldValueController-Start ");
		}
		
		// Getting the ID of the selected Target-Field-value from the UI
		System.out.println("Value Of ID = " + targetFieldValueModel.getTargetFieldValueId());
		
		if(targetFieldValueModel.getTargetFieldValueId() == 0){	
		
			// Get the details of the TargetFieldValue respect of the Selected ID
			try {
				targetFieldValueModel = targetFieldValueService.getTargetValueByID(Integer.parseInt((request
						.getParameter("id"))));
				model.addAttribute("targetFieldValueModel", targetFieldValueModel);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ObjectNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				targetFieldValueModel = targetFieldValueService.getTargetValueByID(targetFieldValueModel.getTargetFieldValueId());
				model.addAttribute("targetFieldValueModel", targetFieldValueModel);
			} catch (ObjectNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//System.out.println("targetValueModel: "+targetValueModel);
		
		model.addAttribute("update", 1);
		
		return "add-target-field-value";
	}
	
	
	/**
	 * @param model
	 * @param targetFieldValueModel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/updateTargetFieldValue", method = {RequestMethod.POST, RequestMethod.GET})
	public String updateTarget(Model model, @ModelAttribute final TargetFieldValueModel targetValueModel,			
			final RedirectAttributes redirectAttributes,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("Update TaregetFieldValueController-End ");
		}
		
		try {
			// Update the TargetFieldValue in DB
			int createdBy = (int) httpSession.getAttribute("userId");
			targetValueModel.setUpdatedBy(createdBy);
			targetFieldValueService.updateTargetFieldValue(targetValueModel);
			redirectAttributes.addFlashAttribute("totalTargetValue", " Target Field Value updated successfully.");
			return ("redirect:/Admin/viewTargetFieldValue");
		} catch (FormExceptions e ) {
			logger.error("Target Field Value Update not Successfully.", e);
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
				redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(), entry.getValue().getMessage());
			}
			
			redirectAttributes.addFlashAttribute("targetFieldValueModel",targetValueModel);
			
			//System.out.println("targetValueModel name ****** "+ targetValueModel);
			
			//model.addAttribute("targetFieldValueModel",targetValueModel);
			//model.addAttribute("update", 1);
			
			return ("redirect:/Admin/editTargetFieldValue");
			
		} catch (DataNotFound e) {
			logger.error("Target Field Value Update not Successfully (DataNotFound)", e); 
			return ("redirect:/Admin/editTargetFieldValue");
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param targetFieldValueModel
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/deleteTargetFieldValue", method = RequestMethod.GET)
	public String deletefaq(Model model, HttpServletRequest request,
			@ModelAttribute TargetFieldValueModel targetFieldValueModel,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("Delete TargetFieldValue Controller-Start ");
		}

		targetFieldValueModel.setTargetFieldValueId(Integer.parseInt((request.getParameter("id"))));
		System.out.println("delete id Value : "+targetFieldValueModel.getTargetFieldValueId());
		
		try{
			targetFieldValueModel = targetFieldValueService.getTargetValueByID(targetFieldValueModel.getTargetFieldValueId());
			
			try{
				targetFieldValueService.deleteTargetFieldValue(targetFieldValueModel);
				redirectAttributes.addFlashAttribute("totalTargetValue", " Target Field Value deleted successfully.");
				return ("redirect:/Admin/viewTargetFieldValue");
			} catch(DataNotFound e){
				logger.debug(e.getMessage(), e);				
				redirectAttributes.addFlashAttribute("targetFieldValueModel", targetFieldValueModel);
				return ("redirect:/Admin/viewTargetFieldValue");	
			}
		} catch(ObjectNotFound e){
			return ("redirect:/Admin/viewTargetFieldValue");
		}		
		
	}
	
	
	@RequestMapping(value="/createDynamicTargetFieldValues", method=RequestMethod.POST)
	public @ResponseBody List<String> createDynamicTargetFieldValues(@RequestParam("roleid") int roleid, @RequestParam("campid") int campid, Model model){
		
		System.out.println("Ok. All right."+ roleid +" & "+campid);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Field Value createDynamicTargetFieldValues Controller-Start ");
		}		
		
		List<String> targetFields=null;
		/*if(campid != 0){
			targetFields=new ArrayList<String>();
		}*/
		try {			
			 targetFields=targetFieldValueService.findAllFieldNames(roleid, campid);
			//targetFields.add(String.valueOf(campid));
		} catch (ObjectNotFound e) {
			logger.debug(e.getMessage(), e);
			//model.addAttribute("campaignNotFound", e.getMessage());
		}			
		
		if (logger.isDebugEnabled()) {
			logger.debug("Add Target Field Value createDynamicTargetFieldValues Controller-End ");
		}		
		
		System.out.println("createDynamicTargetFieldValues targetFields: "+targetFields);
		
		return targetFields;
	}
	
}
