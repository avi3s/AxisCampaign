package com.axis.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.common.MessageUtil;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;
import com.axis.model.RoleModel;
import com.axis.service.RoleLevelService;
import com.axis.service.RoleService;

@Controller
public class RoleController {
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleLevelService roleLevelService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	
	@RequestMapping(value = "/Admin/viewRole",method = {RequestMethod.POST,RequestMethod.GET})
	public String dummyLinkAddRole(Model model){
		
		if (logger.isDebugEnabled()) {
			logger.debug("dummyLinkAddRole-Starts ");
		}
		
		System.out.println("within dummyLinkAddRole controller");
		
		

		List<RoleModel> roleModels;
		
		try {
			roleModels = roleService.getAllActiveRoleByStatus();
			model.addAttribute("roleModelListTable",roleModels);

		} catch (DataNotFound e) {
			
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleModelListNotFound",e.getMessage());
			
		} 
		
		catch(ObjectNotFound e){
			
		}
		
		catch(Exception e) {
			model.addAttribute("generalException",e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("dummyLinkAddRole-Ends ");
		}
		
		return "view-role";
	}
	
	
	@RequestMapping(value = "/Admin/addRoleForm",method = {RequestMethod.POST,RequestMethod.GET})
	public String addRoleForm(Model model,HttpServletRequest httpServletRequest){
		
		if (logger.isDebugEnabled()) {
			logger.debug("addRoleForm-Starts");
		}
		
		System.out.println("within addRoleForm controller");
		
		
		try{	
		List<RoleLevelModel> roleLevelModels=roleLevelService.getActiveRoleLevelListForDDL();
		httpServletRequest.setAttribute("roleLevelModelList",roleLevelModels);
		}
		catch(ObjectNotFound e1){

			logger.debug(e1.getMessage(), e1);
			model.addAttribute("roleLevelListNotFound",e1.getMessage());
			
		}
		catch(DataNotFound e2){
			
			logger.debug(e2.getMessage(), e2);
			model.addAttribute("roleLevelListNotFound",e2.getMessage());
			
			
		}
		catch(Exception e) {
			model.addAttribute("generalException",e.getMessage());
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("addRoleForm-Ends ");
		}
		
		return "add-role";
	}
	
	
	@RequestMapping(value = "/Admin/editRole",method = {RequestMethod.POST,RequestMethod.GET})
	public String dummyLinkEditRoleLevel(Model model,@ModelAttribute RoleModel roleModel,HttpServletRequest httpServletRequest) {
		System.out.println("in dummyLinkEditRoleLevel controller :: "+httpServletRequest.getMethod()+"  "+roleModel.getRoleId());
		RoleModel roleModel2=null;

		
		try {
			roleModel2=roleService.getRoleModelForEdit(roleModel);
			model.addAttribute("roleModel",roleModel2);
		} catch (DataNotFound e2) {
			model.addAttribute("generalException",e2.getMessage());

		} catch (ObjectNotFound e2) {
			model.addAttribute("generalException",e2.getMessage());

		} catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}
		
		try {
		List<RoleLevelModel> roleLevelModels=roleLevelService.getActiveRoleLevelListForDDL();
		model.addAttribute("roleLevelModelList",roleLevelModels);
		
		int flag=0;
		
		for(RoleLevelModel temp : roleLevelModels) {
			
			System.out.println("compare :: "+temp.getParentRoleLevelId()+"===="+roleModel2.getRoleLevelId());
			
			if(temp.getParentRoleLevelId()==roleModel2.getRoleLevelId()) {
				flag++;
				break;
			}
			else {}
		
		}
		System.out.println("** flag :: "+flag);
		if(flag==0) {
			model.addAttribute("parentDeactivated","Parent Role Level Deactivated");

		}
		
		} 
		catch (DataNotFound e) {
		System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e.getMessage());
		model.addAttribute("noRoleLevelDataFoundForDDL",e.getMessage());
		}
		catch(Exception e1) {
		model.addAttribute("generalException",e1.getMessage());
		}

		return "edit-role";
	}
	
	
	
	@RequestMapping(value = "/Admin/insertRoleData",method = {RequestMethod.POST,RequestMethod.GET})
	public String insertRoleLevelData(@ModelAttribute RoleModel roleModel,Model model,HttpServletRequest httpServletRequest,final RedirectAttributes redirectAttributes,HttpSession httpSession) throws DataNotFound, ObjectNotFound{
		
		System.out.println("in insertRoleLevelData controller :: "+roleModel.getRole_name()+"  "+roleModel.getRoleLevelId());
		roleModel.setCreatedBy(1);
		
		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			roleModel.setCreatedBy(createdBy);
			roleService.insertRoleData(roleModel);
			model.addAttribute("actionCompletionMessage",messageUtil.getBundle("insertSuccessful"));

			List<RoleModel> roleModels=roleService.getAllActiveRoleByStatus();
			model.addAttribute("roleModelListTable",roleModels);
		} catch (FormExceptions e) {
			
			System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key ----> :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			try{	
				List<RoleLevelModel> roleLevelModels=roleLevelService.getActiveRoleLevelListForDDL();
				httpServletRequest.setAttribute("roleLevelModelList",roleLevelModels);
				}
				catch(ObjectNotFound e1){

					logger.debug(e1.getMessage(), e1);
					model.addAttribute("roleLevelListNotFound",e1.getMessage());
					
				}
				catch(DataNotFound e2){
					
					logger.debug(e2.getMessage(), e2);
					model.addAttribute("roleLevelListNotFound",e2.getMessage());
					
					
				}
				catch(Exception e3) {
					model.addAttribute("generalException",e3.getMessage());
				}
			redirectAttributes.addFlashAttribute("roleModel",roleModel);
			return "add-role";
			
		}

		List<RoleModel> roleModels;
		
		try {
			roleModels = roleService.getAllActiveRoleByStatus();
			model.addAttribute("roleModelListTable",roleModels);

		} catch (DataNotFound e) {
			
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleModelListNotFound",e.getMessage());
			
		} 
		
		
		return "view-role";
	}
	
	@RequestMapping(value = "/Admin/editRoleData",method = {RequestMethod.POST,RequestMethod.GET})
	public String editRoleData(@ModelAttribute RoleModel roleModel,Model model,HttpServletRequest httpServletRequest,HttpSession httpSession) throws DataNotFound, ObjectNotFound{
		
		System.out.println("in editRoleData controller :: "+roleModel.getRoleId()+"  "+roleModel.getRole_name()+" "+roleModel.getRoleLevelId());
		roleModel.setUpdatedBy(1);
		
		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			roleModel.setCreatedBy(createdBy);
			roleService.editRoleData(roleModel);
			model.addAttribute("actionCompletionMessage",messageUtil.getBundle("editSuccessful"));
		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			try{	
				List<RoleLevelModel> roleLevelModels=roleLevelService.getActiveRoleLevelListForDDL();
				httpServletRequest.setAttribute("roleLevelModelList",roleLevelModels);
				}
				catch(ObjectNotFound e1){

					logger.debug(e1.getMessage(), e1);
					model.addAttribute("roleLevelListNotFound",e1.getMessage());
					
				}
				catch(DataNotFound e2){
					
					logger.debug(e2.getMessage(), e2);
					model.addAttribute("roleLevelListNotFound",e2.getMessage());
					
					
				}
				catch(Exception e3) {
					model.addAttribute("generalException",e3.getMessage());
				}
			
			return "edit-role";
			
			
		}
		
		List<RoleModel> roleModels;
		
		try {
			roleModels = roleService.getAllActiveRoleByStatus();
			model.addAttribute("roleModelListTable",roleModels);

		} catch (DataNotFound e) {
			
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleModelListNotFound",e.getMessage());
			
		} 
		
		catch(ObjectNotFound e){
			
		}
		
		catch(Exception e) {
			model.addAttribute("generalException",e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("dummyLinkAddRole-Ends ");
		}
		
		return "view-role";
	}
	
	@RequestMapping(value = "/Admin/deleteRole",method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteRole(@ModelAttribute RoleModel roleModel,Model model,HttpServletRequest httpServletRequest) throws DataNotFound, ObjectNotFound{
		
		System.out.println("in deleteRole controller :: "+roleModel.getRoleId());
		roleModel.setUpdatedBy(1);
		roleService.deleteRoleData(roleModel);
		model.addAttribute("actionCompletionMessage",messageUtil.getBundle("deletionSuccessful"));

		List<RoleModel> roleModels;

		try {
			roleModels = roleService.getAllActiveRoleByStatus();
			model.addAttribute("roleModelListTable",roleModels);

		} catch (DataNotFound e) {
			
			logger.debug(e.getMessage(), e);
			model.addAttribute("roleModelListNotFound",e.getMessage());
			
		} 
		
		catch(ObjectNotFound e){
			
		}
		
		catch(Exception e) {
			model.addAttribute("generalException",e.getMessage());
		}
		
		return "view-role";
	}
	

}
