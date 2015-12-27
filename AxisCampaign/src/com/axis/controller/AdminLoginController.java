package com.axis.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.log4j.Logger;

import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.UserModel;
import com.axis.service.AdminLoginService;
import com.axis.service.CampaignService;
import com.axis.service.RoleService;
import com.axis.service.UserService;

@Controller
@RequestMapping(value = "/Admin", method = RequestMethod.GET)
public class AdminLoginController {

	Logger logger = Logger.getLogger(AdminLoginController.class);

	@Autowired
	private AdminLoginService adminLoginService;

	@RequestMapping(value = "/loginAdmin", method = RequestMethod.GET)
	public ModelAndView adminLoginPage() {

		if (logger.isDebugEnabled()) {
			logger.debug("adminLoginPage");
		}

		return new ModelAndView("admin-login");
	}

	@RequestMapping(value = "/adminLogin", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String adminLoginCheck(@ModelAttribute UserModel userModel,
			Model model, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("adminHome Page");
		}
		
		try {

			try {
				userModel = adminLoginService.adminLoginCheck(userModel);
				
			} catch (FormExceptions e) {
				System.out.println("in FormExceptions catch :: ");

				for (Entry<String, Exception> entry : e.getExceptions()
						.entrySet()) {
					System.out.println("key :: " + entry.getKey()
							+ " value :: " + entry.getValue().getMessage());
					redirectAttributes.addFlashAttribute(entry.getKey(), entry
							.getValue().getMessage());
				}

				return ("redirect:/Admin/loginAdmin");
			}

			if (userModel.getRoleName().equals("ADMIN")) {
				session.setAttribute("userId", userModel.getUserId());
				session.setAttribute("employeeName",
						userModel.getEmployeeName());
				session.setAttribute("userName",
						userModel.getUserName());
				session.setAttribute("password",
						userModel.getPassword());
				session.setAttribute("employeeNumber",
						userModel.getEmployeeNumber());
				session.setAttribute("Admin", userModel.getUserId());
				return ("admin-home");
			}
		} catch (DataNotFound e) {
			System.out.println("forwarding to error page");
			e.printStackTrace();
			redirectAttributes
					.addFlashAttribute("invalidLogin", e.getMessage());
			return ("redirect:/Admin/loginAdmin");
		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			redirectAttributes.addFlashAttribute("generalException",
					e1.getMessage());
			return ("redirect:/Admin/loginAdmin");
		}
		return ("admin-home");

	}
	
	@RequestMapping(value = "/refreshAdminHome", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String refreshAdminHome(@ModelAttribute UserModel userModel,
			Model model, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (logger.isDebugEnabled()) {
			logger.debug("adminHome Page Refresh");
		}
		
		try {
			
			userModel.setUserName((String)session.getAttribute("userName"));
			userModel.setPassword((String)session.getAttribute("password"));
			
			try {
				userModel = adminLoginService.adminLoginCheck(userModel);
				
			} catch (FormExceptions e) {
				System.out.println("in FormExceptions catch :: ");

				for (Entry<String, Exception> entry : e.getExceptions()
						.entrySet()) {
					System.out.println("key :: " + entry.getKey()
							+ " value :: " + entry.getValue().getMessage());
					redirectAttributes.addFlashAttribute(entry.getKey(), entry
							.getValue().getMessage());
				}

				return ("redirect:/Admin/loginAdmin");
			}

			if (userModel.getRoleName().equals("ADMIN")) {
				session.setAttribute("userId", userModel.getUserId());
				session.setAttribute("employeeName",
						userModel.getEmployeeName());
				session.setAttribute("userName",
						userModel.getUserName());
				session.setAttribute("password",
						userModel.getPassword());
				session.setAttribute("employeeNumber",
						userModel.getEmployeeNumber());
				session.setAttribute("Admin", userModel.getUserId());
				return ("admin-home");
			}
		} catch (DataNotFound e) {
			System.out.println("forwarding to error page");
			e.printStackTrace();
			redirectAttributes
					.addFlashAttribute("invalidLogin", e.getMessage());
			return ("redirect:/Admin/loginAdmin");
		} catch (ObjectNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			redirectAttributes.addFlashAttribute("generalException",
					e1.getMessage());
			return ("redirect:/Admin/loginAdmin");
		}
		return ("admin-home");

	}

	/**
	 * Admin Logout Service
	 * 
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest httpServletRequest) {

		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.removeAttribute("userId");
		httpSession.removeAttribute("employeeName");
		httpSession.removeAttribute("employeeNumber");
		httpSession.invalidate();
		return new ModelAndView("redirect:/Admin/loginAdmin");
	}
}
