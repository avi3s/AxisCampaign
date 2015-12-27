package com.axis.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.axis.common.MessageUtil;
import com.axis.entity.RoleLevelEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleLevelModel;
import com.axis.service.RoleLevelService;

@Controller
public class RoleLevelController {
	
	@Autowired
	private RoleLevelService roleLevelService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/Admin/viewRoleLevel",method = {RequestMethod.POST,RequestMethod.GET})
	public String dummyLinkAddRoleLevel(Model model) {
		
		System.out.println("in dummyLinkViewRoleLevel controller :: ");
		List<RoleLevelModel> roleLevelModels;
		
		try {
			roleLevelModels = roleLevelService.getActiveRoleLevelListForTableView();
			model.addAttribute("roleLevelModelListTable",roleLevelModels);

		} catch (DataNotFound e) {
			System.out.println("EXCEPTION Caught in dummyLinkViewRoleLevel :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForTableView",e.getMessage());
		} catch(Exception e) {
			model.addAttribute("generalException",e.getMessage());
		}
		
		return "view-rolelevel";
	}
	
	
	/*  EDIT SECTION STARTS  */
	

	@RequestMapping(value = "/Admin/editRoleLevel",method = {RequestMethod.POST,RequestMethod.GET})
	public String dummyLinkEditRoleLevel(Model model,@ModelAttribute RoleLevelModel roleLevelModel,HttpServletRequest httpServletRequest) {
		
		System.out.println("in dummyLinkEditRoleLevel controller :: "+roleLevelModel.getRowLevelId());
		RoleLevelModel roleLevelModel2=null;
		
		try {
			roleLevelModel2=roleLevelService.getRoleLevelModelForEdit(roleLevelModel.getRowLevelId());
			model.addAttribute("roleLevelModel",roleLevelModel2);
		} 
		
		catch (FormExceptions e) {
			
			System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			List<RoleLevelModel> roleLevelModels;
			
			try {
				roleLevelModels = roleLevelService.getActiveRoleLevelListForTableView();
				model.addAttribute("roleLevelModelListTable",roleLevelModels);

			} 
			
			catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in dummyLinkViewRoleLevel :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForTableView",e1.getMessage());
			} 
			
			catch(Exception e1) {
				model.addAttribute("generalException",e1.getMessage());
			}
			
			
			return "view-rolelevel";

		}
		
		catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}
		
		
		List<RoleLevelModel> roleLevelModels;

		try {
			
			roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
			model.addAttribute("roleLevelModelListDDL",roleLevelModels);
			if(roleLevelModels.contains(roleLevelModel2)) {
				
			}
			else {
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
		return "edit-rolelevel";
	}
	
	
	@RequestMapping(value = "/Admin/editRoleLevelData",method = {RequestMethod.POST,RequestMethod.GET})
	public String editRoleLevelData(@ModelAttribute RoleLevelModel roleLevelModel,Model model,HttpServletRequest httpServletRequest,HttpSession httpSession) {
		
		System.out.println("in dummyLinkEditRoleLevelData controller :: "+roleLevelModel.getParentRoleLevelId()+"  "+roleLevelModel.getLevelName());
		roleLevelModel.setUpdatedBy(1);
		
		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			roleLevelModel.setCreatedBy(createdBy);
			roleLevelService.editRoleLevelData(roleLevelModel);
			model.addAttribute("actionCompletionMessage",messageUtil.getBundle("editSuccessful"));
			
		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			List<RoleLevelModel> roleLevelModels;
			
			try {
				
				roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
				model.addAttribute("roleLevelModelListDDL",roleLevelModels);	

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			} catch(Exception e1) {
				model.addAttribute("generalException",e1.getMessage());
			}
			
			return "edit-rolelevel";	
			
		}
		
		catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}
		
		List<RoleLevelModel> roleLevelModels;
		try {
			roleLevelModels = roleLevelService.getActiveRoleLevelListForTableView();
			model.addAttribute("roleLevelModelListTable",roleLevelModels);

		} catch (DataNotFound | ObjectNotFound e) {
			System.out.println("EXCEPTION Caught in dummyLinkViewRoleLevel :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForTableView",e.getMessage());
		}
		catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}

		return "view-rolelevel";
	}
	
	
	
	/*  EDIT SECTION ENDS   */
	
	
	
	@RequestMapping(value = "/Admin/addRoleLevelForm",method = {RequestMethod.POST,RequestMethod.GET})
	public String dummyLinkAddRoleLevelForm(Model model) throws ObjectNotFound{
		
		System.out.println("in dummyLinkAddRoleLevelFormmm controller :: ");
		
		List<RoleLevelModel> roleLevelModels;
		
		try {
			
			roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
			model.addAttribute("roleLevelModelListDDL",roleLevelModels);	

		} catch (DataNotFound e) {
			System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForDDL",e.getMessage());
		} catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}
				
		return "add-rolelevel";
	}
	
	@RequestMapping(value = "/Admin/insertRoleLevelData",method = {RequestMethod.POST,RequestMethod.GET})
	public String insertRoleLevelData(@ModelAttribute RoleLevelModel roleLevelModel,Model model,HttpSession httpSession) {
		
		System.out.println("in dummyLinkAddRoleLevelData controller :: ");
		roleLevelModel.setCreatedBy(1);
		
		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			roleLevelModel.setCreatedBy(createdBy);
			roleLevelService.insertRoleLevelData(roleLevelModel);
			model.addAttribute("actionCompletionMessage",messageUtil.getBundle("insertSuccessful"));
			
		} catch (FormExceptions e) {
			System.out.println("in FormExceptions catch :: ");
		
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			List<RoleLevelModel> roleLevelModels;
			
			try {
				
				roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
				model.addAttribute("roleLevelModelListDDL",roleLevelModels);	

			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			} catch(Exception e1) {
				model.addAttribute("generalException",e1.getMessage());
			}
			
			
			model.addAttribute("roleLevelModel",roleLevelModel);
			return "add-rolelevel";
		}
		
		catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}
		
		List<RoleLevelModel> roleLevelModels;
		
		try {
			
			roleLevelModels = roleLevelService.getActiveRoleLevelListForTableView();
			model.addAttribute("roleLevelModelListTable",roleLevelModels);

		} catch (DataNotFound e) {
			System.out.println("EXCEPTION Caught in insertRoleLevelData :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForTableView",e.getMessage());
		} catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}

		return "view-rolelevel";
	}
	

	
	@RequestMapping(value = "/Admin/deleteRoleLevel",method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteRoleLevelData(@ModelAttribute RoleLevelModel roleLevelModel,Model model,HttpServletRequest httpServletRequest) {
		
		System.out.println("in dummyLinkEditRoleLevelData controller :: "+roleLevelModel.getRowLevelId());
		roleLevelModel.setUpdatedBy(1);
		
		roleLevelService.deleteRoleLevelData(roleLevelModel);
		model.addAttribute("actionCompletionMessage",messageUtil.getBundle("deletionSuccessful"));

		List<RoleLevelModel> roleLevelModels;
		try {
			roleLevelModels = roleLevelService.getActiveRoleLevelListForTableView();
			model.addAttribute("roleLevelModelListTable",roleLevelModels);

		} catch (DataNotFound e) {
			System.out.println("EXCEPTION Caught in dummyLinkViewRoleLevel :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForTableView",e.getMessage());
		} catch(Exception e1) {
			model.addAttribute("generalException",e1.getMessage());
		}

		return "view-rolelevel";
	}
	

}
