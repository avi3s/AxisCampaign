package com.axis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.axis.model.SubRoleLevelModel;
import com.axis.service.RoleLevelService;
import com.axis.service.SubRoleLevelService;

@Controller
public class SubRoleLevelController {

	@Autowired
	private SubRoleLevelService subRoleLevelService;

	@Autowired
	private RoleLevelService roleLevelService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	

	@RequestMapping(value = "/Admin/viewSubRoleLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String dummyLinkAddSubRoleLevel(Model model,
			HttpServletRequest httpServletRequest) throws ObjectNotFound {

		System.out.println("in dummyLinkAddSubRoleLevel controller :: "
				+ httpServletRequest.getMethod());
		
		List<SubRoleLevelModel> subRoleLevelModels;
		
		try {
			subRoleLevelModels = subRoleLevelService.subRoleLevelList();
			model.addAttribute("subRoleLevelModelListTable", subRoleLevelModels);

		} catch (DataNotFound e) {
			model.addAttribute("noSubRoleLevelsPresent",e.getMessage());
		}
		

		return "view-subrolelevel";
		
	}

	@RequestMapping(value = "/Admin/addSubRoleLevelForm", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String dummyLinkAddSubRoleLevelForm(Model model,
			HttpServletRequest httpServletRequest) throws ObjectNotFound {

		System.out.println("in dummyLinkAddSubRoleLevelFormmm controller :: "
				+ httpServletRequest.getMethod());
		
		List<RoleLevelModel> roleLevelModels;
		try {
			roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
			httpServletRequest.setAttribute("roleLevelModelList", roleLevelModels);
		} catch (DataNotFound e) {
			System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e.getMessage());
			model.addAttribute("noRoleLevelDataFoundForDDL",e.getMessage());
		}
		

		return "add-subrolelevel";
	}

	@RequestMapping(value = "/Admin/insertSubRoleLevelData", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String insertRoleLevelData(
			@ModelAttribute SubRoleLevelModel subRoleLevelModel, Model model,
			HttpServletRequest httpServletRequest,final RedirectAttributes redirectAttributes,HttpSession httpSession) throws DataNotFound,
			ObjectNotFound {
		int createdBy = (int) httpSession.getAttribute("userId");
		subRoleLevelModel.setCreatedBy(createdBy);
		
		try {
			subRoleLevelService.insertSubRoleLevelData(subRoleLevelModel);
			redirectAttributes.addFlashAttribute("actionCompletionMessage",messageUtil.getBundle("insertSuccessful"));

		} catch (FormExceptions e) {
			
			System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			List<RoleLevelModel> roleLevelModels;
			try {
				roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
				httpServletRequest.setAttribute("roleLevelModelList", roleLevelModels);
			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}
			try {
			model.addAttribute("subRoleLevelModelList",
					populateSubRoleLevelList(subRoleLevelModel));
			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}
			redirectAttributes.addFlashAttribute("subRoleLevelModel", subRoleLevelModel);
			return "add-subrolelevel";
		}
		
		

		return "redirect:/Admin/viewSubRoleLevel";
	}

	@RequestMapping(value = "/Admin/ajaxCallForSubRoleLevelList", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String ajaxCallForSubRoleLevelList(
			@ModelAttribute SubRoleLevelModel subRoleLevelModel, Model model,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ObjectNotFound, IOException {

		System.out.println("in ajaxCallForSubRoleLevelList controller :: "
				+ subRoleLevelModel.getRoleLevelId());
		PrintWriter pw = httpServletResponse.getWriter();
		
		try {
			
			for (SubRoleLevelModel subRoleLevelModel2 : subRoleLevelService.getSubRoleLevelListById(subRoleLevelModel))
			pw.println(subRoleLevelModel2);
		} catch (DataNotFound e) {
			pw.println(e.getMessage());
		}

		return null;
	}

	public List<SubRoleLevelModel> populateSubRoleLevelList(
			SubRoleLevelModel subRoleLevelModel) throws DataNotFound,
			ObjectNotFound {

		System.out.println("in populateSubRoleLevelList controller :: "
				+ subRoleLevelModel.getRoleLevelId());
		List<SubRoleLevelModel> subRoleLevelModels = subRoleLevelService
				.getSubRoleLevelListById(subRoleLevelModel);

		return subRoleLevelModels;
	}

	@RequestMapping(value = "/Admin/editSubRoleLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String dummyLinkEditSubRoleLevel(Model model,
			@ModelAttribute SubRoleLevelModel subRoleLevelModel,
			HttpServletRequest httpServletRequest,HttpSession httpSession) throws DataNotFound,
			ObjectNotFound {

		System.out.println("in dummyLinkEditSubRoleLevel controller :: "
				+ httpServletRequest.getMethod() + "  "
				+ subRoleLevelModel.getSubRoleLevelId());
		int updatedBy = (int) httpSession.getAttribute("userId");
		subRoleLevelModel.setUpdatedBy(updatedBy);
		subRoleLevelModel = subRoleLevelService.getSubRoleLevelModelForEdit(subRoleLevelModel);

		model.addAttribute("subRoleLevelModel", subRoleLevelModel);

		List<RoleLevelModel> roleLevelModels = roleLevelService
				.getActiveRoleLevelListForDDL();
		httpServletRequest.setAttribute("roleLevelModelList", roleLevelModels);

		model.addAttribute("subRoleLevelModelList",
				populateSubRoleLevelList(subRoleLevelModel));

		return "edit-subrolelevel";
	}
	

	@RequestMapping(value = "/Admin/editSubRoleLevelData", method = {RequestMethod.POST, RequestMethod.GET })
	public String editSubRoleLevelData(@ModelAttribute SubRoleLevelModel subRoleLevelModel, Model model,HttpServletRequest httpServletRequest,final RedirectAttributes redirectAttributes,HttpSession httpSession) throws ObjectNotFound {
		int updatedBy = (int) httpSession.getAttribute("userId");
		subRoleLevelModel.setUpdatedBy(updatedBy);

		try {
			subRoleLevelService.updateSubRoleData(subRoleLevelModel);
			redirectAttributes.addFlashAttribute("actionCompletionMessage",messageUtil.getBundle("editSuccessful"));

			
		} catch (FormExceptions e) {
				System.out.println("in FormExceptions catch :: ");
			
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			
			List<RoleLevelModel> roleLevelModels;
			try {
				roleLevelModels = roleLevelService.getActiveRoleLevelListForDDL();
				httpServletRequest.setAttribute("roleLevelModelList", roleLevelModels);
			} catch (DataNotFound e1) {
				System.out.println("EXCEPTION Caught in addRoleLevelForm :: "+e1.getMessage());
				model.addAttribute("noRoleLevelDataFoundForDDL",e1.getMessage());
			}
			
			try {
				model.addAttribute("subRoleLevelModelList",populateSubRoleLevelList(subRoleLevelModel));
			} catch (DataNotFound e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("subRoleLevelModel", subRoleLevelModel);

			return "edit-subrolelevel";
			
			
		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<SubRoleLevelModel> subRoleLevelModels;
		try {
			subRoleLevelModels = subRoleLevelService.subRoleLevelList();
			model.addAttribute("subRoleLevelModelListTable", subRoleLevelModels);

		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/Admin/viewSubRoleLevel";
		
	}

	@RequestMapping(value = "/Admin/deleteSubRoleLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String deleteSubRoleLevel(
			@ModelAttribute SubRoleLevelModel subRoleLevelModel, Model model,
			HttpServletRequest httpServletRequest) throws DataNotFound,
			ObjectNotFound {
		System.out.println("in deleteSubRoleLevel controller :: "
				+ subRoleLevelModel.getSubRoleLevelId());
		subRoleLevelModel.setUpdatedBy(1);
		subRoleLevelService.deleteSubRoleData(subRoleLevelModel);
		model.addAttribute("actionCompletionMessage",messageUtil.getBundle("deletionSuccessful"));

		List<SubRoleLevelModel> subRoleLevelModels;
		
		try {
			subRoleLevelModels = subRoleLevelService.subRoleLevelList();
			model.addAttribute("subRoleLevelModelListTable", subRoleLevelModels);

		} catch (DataNotFound e) {
			model.addAttribute("noSubRoleLevelsPresent",e.getMessage());
		}
		return "view-subrolelevel";

	}

}
