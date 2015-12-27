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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axis.service.ContentManagementService;
import com.axis.common.MessageUtil;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.RecordFound;
import com.axis.model.ContentManagementDetailsModel;;

@Controller
public class ContentManagementController {
	
	private static final Logger logger = Logger.getLogger(ContentManagementController.class);

	@Autowired
	private ContentManagementService cmsService;
	
	@Autowired
	private MessageUtil messageUtil;

	/**
	 * This method only show for addContent page
	 * */
	@RequestMapping(value = "/Admin/addContentPage", method = RequestMethod.GET)
	public ModelAndView addContentPageController(Model model) {
		return new ModelAndView("add-content");
	}

	@RequestMapping(value = "/Admin/addContent", method = RequestMethod.POST)
	public ModelAndView addContentController(
			@ModelAttribute ContentManagementDetailsModel cmsModel,ModelAndView modelAndView,RedirectAttributes redir,HttpSession httpSession) {

		if (logger.isDebugEnabled()) {
			logger.debug("cmsCreateController-Start " + cmsModel);
		}

		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			cmsModel.setCreatedBy(createdBy);
			cmsService.createContent(cmsModel);

			if (logger.isDebugEnabled()) {
				logger.debug("contentCreateController-End ");
			}
			redir.addFlashAttribute("cmsAdded", messageUtil.getBundle("Content.Added"));
			return new ModelAndView("redirect:/Admin/viewContent");

		} catch (DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/addContentPage");
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redir.addFlashAttribute("cmsModel",
					cmsModel);
			modelAndView.setViewName("redirect:/Admin/addContentPage");
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;
		} catch (RecordFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/addContentPage");
			redir.addFlashAttribute("cmsModel",
					cmsModel);
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;
		} catch (FormExceptions e) {
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				redir.addFlashAttribute(entry.getKey(),entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			redir.addFlashAttribute("cmsModel",
					cmsModel);
			modelAndView.setViewName("redirect:/Admin/addContentPage");
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;
		}

	}

	@RequestMapping(value = "/Admin/updateContent", method = RequestMethod.POST)
	public ModelAndView contentUpdateController(
			@ModelAttribute ContentManagementDetailsModel cmsModel,ModelAndView modelAndView,RedirectAttributes redir,HttpSession httpSession) throws RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("contentUpdateController-Start " + cmsModel);
		}

		try {
			int createdBy = (int) httpSession.getAttribute("userId");
			cmsModel.setCreatedBy(createdBy);
			cmsService.updateContent(cmsModel);
		} catch (ObjectNotFound e) {
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/viewContentById?id="+cmsModel.getId());
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;
		} catch (DataNotFound e) {
			e.printStackTrace();
			modelAndView.setViewName("redirect:/Admin/viewContentById?id="+cmsModel.getId());
			redir.addFlashAttribute("errorMessage",e.getMessage());
		    return modelAndView;
		} catch (FormExceptions e) {
			for(Entry<String, Exception> entry : e.getExceptions().entrySet()) {
				System.out.println("key :: "+entry.getKey()+" value :: "+entry.getValue().getMessage());
				redir.addFlashAttribute(entry.getKey(),entry.getValue().getMessage());
				//model.addAttribute(entry.getKey(),entry.getValue().getMessage());
			}
			modelAndView.setViewName("redirect:/Admin/viewContentById?id="+cmsModel.getId());
		    return modelAndView;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("contentUpdateController-End ");
		}
		
		redir.addFlashAttribute("cmsAdded", messageUtil.getBundle("Content.Updated"));

		return new ModelAndView("redirect:/Admin/viewContent");

	}

	/**
	 * This method is getting all content details
	 * @throws DataNotFound 
	 * */
	@RequestMapping(value = "/Admin/viewContent", method = RequestMethod.GET)
	public ModelAndView allContentViewController(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-Start ");
		}

		List<ContentManagementDetailsModel> cmsModel = null;

		try {
			cmsModel = cmsService.viewAllContent();
			model.addAttribute("allContentView", cmsModel);

		} catch (ObjectNotFound | DataNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("contentNotFound", e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("allContentViewController-End ");
		}

		return new ModelAndView("view-content");

	}

	/**
	 * This method is getting content details by id
	 * */
	@RequestMapping(value = "/Admin/viewContentById", method = RequestMethod.GET)
	public ModelAndView viewContentByIdController(@RequestParam("id") int id,
			Model model) {

		if (logger.isDebugEnabled()) {

			logger.debug("viewContentByIdController-Start " + id);
		}

		try {
			ContentManagementDetailsModel cmsModel = cmsService.viewContent(id);

			model.addAttribute("viewContentById", cmsModel);

			model.addAttribute("update", 1);

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewContentByIdController-End ");
		}

		return new ModelAndView("add-content");

	}

	/**
	 * This method is deleting content details by id
	 * */
	@RequestMapping(value = "/Admin/deleteContentById", method = RequestMethod.GET)
	public ModelAndView deleteContentByIdController(@RequestParam("id") int id,
			Model model,RedirectAttributes redir) {

		if (logger.isDebugEnabled()) {

			logger.debug("deleteContentByIdController-Start " + id);
		}

		try {
			cmsService.deleteContent(id);
		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("deleteContentByIdController-End ");
		}
		redir.addFlashAttribute("cmsAdded", messageUtil.getBundle("Content.Deleted"));
		return new ModelAndView("redirect:/Admin/viewContent");

	}
	
	@RequestMapping(value = "/Admin/{path}", method = RequestMethod.GET)
	public ModelAndView viewContentHtmlController(@PathVariable String path,
			HttpServletRequest httpServletRequest, Model model) {

		if (logger.isDebugEnabled()) {

			logger.debug("viewContentHTML-Start " + path);
		}	
		try {
			ContentManagementDetailsModel cmsModel = cmsService.viewContentbyPath(path);

			model.addAttribute("htmlcontent", cmsModel.getPageContent());
			model.addAttribute("contentName", cmsModel.getPageName());

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewContentHTML-End ");
		}
		
		return new ModelAndView("admin-view-content");
/*		if(httpServletRequest.getSession(false).getAttribute("userLoginModelDetails") != null){
			return new ModelAndView("userviewContent");
		}
		if(httpServletRequest.getSession(false).getAttribute("adminLoginModelDetails") != null){
			return new ModelAndView("adminviewContent");
		}
		else
			return null;*/
			
	}
	
	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	public ModelAndView viewContentHtmlControllerForUser(@PathVariable String path,
			HttpServletRequest httpServletRequest, Model model) {

		if (logger.isDebugEnabled()) {

			logger.debug("viewContentHTML-Start " + path);
		}	
		try {
			ContentManagementDetailsModel cmsModel = cmsService.viewContentbyPath(path);

			model.addAttribute("htmlcontent", cmsModel.getPageContent());
			model.addAttribute("contentName", cmsModel.getPageName());

		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("viewContentHTML-End ");
		}
		
		return new ModelAndView("user-view-content");
	}

}
