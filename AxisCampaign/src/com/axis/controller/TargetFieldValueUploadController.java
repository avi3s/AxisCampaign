package com.axis.controller;

import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.TargetFieldValueModel;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.service.TargetFieldValueService;
import com.axis.service.TargetFieldValueUploadService;

@Controller
@RequestMapping(value="/Admin")
public class TargetFieldValueUploadController {

	private static final Logger logger = Logger.getLogger(TargetFieldValueUploadController.class);

	@Autowired
	private TargetFieldValueService targetFieldValueService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private TargetFieldValueUploadService targetFieldValueUploadService;
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addTargetFieldValueUpload", method={RequestMethod.POST, RequestMethod.GET})
	public String uploadTargetFieldValue(Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("Add Dummy TargetFieldValue Upload Controller - Start");
		}		
		
		//Fetching the Role List from DB
		List<RoleModel> roleList = null;
		
		try {
			roleList = roleService.getAllActiveRoleByStatus();
			
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("NoActiveRoleFound", e.getMessage());
		}	
		
		model.addAttribute("roleList", roleList);
		
		if(logger.isDebugEnabled()){
			logger.debug("Add Dummy TargetFieldValueUploadController - End");
		}	
		
		return "upload-target-field-value";
	}
	
	
	@RequestMapping(value = "/uploadTargetFieldValue", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public String uploadTargetFieldValue(@RequestParam int roleId, @RequestParam  int campaignId, @RequestParam("fileName") MultipartFile fileName, Model model, RedirectAttributes redirectAttributes) throws Exception {
		
		
		if(logger.isDebugEnabled()){
			logger.debug("TargetFieldValueUploadController - Start");
		}
		
		String name= fileName.getOriginalFilename();
		System.out.println("fielName: "+name);
		
		
		List<TargetFieldValueModel> targetValueList = null;
		
		if(roleId == 0 || campaignId == 0 || name.equals("")){
			try{
				targetFieldValueUploadService.getFileandSave(roleId,campaignId,fileName);
			}
			catch(FormExceptions e){
				logger.error("Target Field Value Upload not Successfully.", e);
				
				for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
					System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
					//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
					redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getMessage());
				}
				
				redirectAttributes.addFlashAttribute("roleId", roleId);
				redirectAttributes.addFlashAttribute("campaignId", campaignId);
				redirectAttributes.addFlashAttribute("name", name);
				
				return ("redirect:/Admin/addTargetFieldValueUpload");
			}
		}
 		
		
		if(name.contains(".xls") || name.contains(".xlsx")) {
			try{
				List<TargetFieldValueModel> targetList = targetFieldValueUploadService.getFileandSave(roleId,campaignId,fileName);
				System.out.println("Targetlist : "+ targetList);
				
				List<Integer> mismatchedLists = null;
				Integer count = null;
				
				System.out.println("target size: "+targetList.size());
				if(targetList.get(targetList.size() - 1) != null){
					count = targetList.get(targetList.size() - 1).getSuccessNo();
					System.out.println("success Count: "+count);
					mismatchedLists = targetList.get(targetList.size() - 1)
						.getUnmatchedLists();
					System.out.println("mismatchList: "+ mismatchedLists);
					if(mismatchedLists == null)
						targetList.remove(targetList.get(targetList.size() - 1));
				}
				else{
					targetList.remove(targetList.get(targetList.size() - 1));
					count = targetList.size();
				}
				
				/*System.out.println("targetList: "+ targetList);
				model.addAttribute("update", 1);
				model.addAttribute("mismatchedRowsList", mismatchedLists);
				//targetList.remove(targetList.get(targetList.size() - 1));
				model.addAttribute("totalTargetValue", count+ " number of rows uploaded successfully.");
				model.addAttribute("targetList", targetList);
				
				targetValueList = targetFieldValueService.getAllTargetValue();
				model.addAttribute("targetValueList", targetValueList);
				
				return "view-target-field-value";*/
				
				redirectAttributes.addFlashAttribute("fileFormatNotMatch", count+ " number of values uploaded successfully.");
				redirectAttributes.addFlashAttribute("mismatchedRowsList", mismatchedLists);
				
				return ("redirect:/Admin/addTargetFieldValueUpload");
				
			}catch(DataNotFound e){
				logger.debug(e.getMessage(), e);
				//return ("redirect:/Admin/addTargetFieldValueUpload?errorMessage=" + e.getMessage());
				redirectAttributes.addFlashAttribute("roleId", roleId);
				redirectAttributes.addFlashAttribute("campaignId", campaignId);
				redirectAttributes.addFlashAttribute("fileFormatNotMatch", "File Format Not Matched.");
				return ("redirect:/Admin/addTargetFieldValueUpload");
			}
		}
		
		else{
			redirectAttributes.addFlashAttribute("roleId", roleId);
			redirectAttributes.addFlashAttribute("campaignId", campaignId);
			redirectAttributes.addFlashAttribute("fileFormatNotMatch", "File Format Not Matched.");
			return ("redirect:/Admin/addTargetFieldValueUpload");
		}
		//return null;
		
		
		
	}
	
}
