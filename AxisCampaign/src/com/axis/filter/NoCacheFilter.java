package com.axis.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import javax.servlet.ServletException;

public class NoCacheFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	private FilterConfig filterConfig;

	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws NullPointerException {

		try {
			if (response instanceof HttpServletResponse) {
				HttpServletResponse httpresponse = (HttpServletResponse) response;
				// Set the C ache-Control and Expires header

				// Print out the URL we're filtering
 				String name = ((HttpServletRequest) request).getRequestURI();
				System.out.println("No Cache Filtering: " + name);
				HttpSession session = ((HttpServletRequest) request)
						.getSession();

				if (session != null) {

					httpresponse.setHeader("Cache-Control",
  							"no-cache, no-store, must-revalidate"); // HTTP 1.1
					httpresponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
					httpresponse.setHeader("Cache-Control","no-store");
					httpresponse.setDateHeader("Expires", 0);

					if (session.getAttribute("userDetails") != null) {

						if (name.equals("/AxisCampaign/login")) {
							httpresponse
									.sendRedirect(((HttpServletRequest) request)
											.getContextPath() + "/getDashboard");
						}
						// if session is invalid,only allow css,images to load
						// and redirect to login page
						if (!name.equals("/AxisCampaign/login")
								&& !name.contains("resources")
								&& session
										.getAttribute("userDetails") == null) {
							session.invalidate();
							RequestDispatcher view = request
									.getRequestDispatcher("login");
							view.forward(request, response);
							return;
						}
						// not allowing normal user to view admin role pages
						if (name.contains("Admin")) {
							httpresponse
									.sendRedirect(((HttpServletRequest) request)
											.getContextPath() + "/getDashboard");

						}
					}
					else if(name.contains("userLogin") && (request.getParameter("userName") == null) ){
						session.invalidate();
						httpresponse
						.sendRedirect(((HttpServletRequest) request)
								.getContextPath() + "/login");
						}
					else {
						if (session.getAttribute("Admin") != null) {
							if (name.equals("/AxisCampaign/Admin/adminLogin")) {
								httpresponse
										.sendRedirect(((HttpServletRequest) request)
												.getContextPath()
												+ "/Admin/adminhome");
							}
							chain.doFilter(request, response);
							return;
						}
						
						if(name.contains("/Admin/adminLogin")){
							chain.doFilter(request, response);
							return;
						}
						// if session is invalid,only allow css,images to load
						// and redirect to adminlogin page
						// verifying which login page to forward if session
						// doesn't contains any object
						else if (!name.contains("resources")
								&& !name.contains("/AxisCampaign/login")
								&& !name.contains("/AxisCampaign/userLogin")
								&& !name.contains("Admin")) {
							session.invalidate();
							RequestDispatcher view = request
									.getRequestDispatcher("login");
							view.forward(request, response);
							return;
						} else if (name.contains("Admin") && !name.equals("/AxisCampaign/Admin/adminLogin")) {
							session.invalidate();
							RequestDispatcher view = request
									.getRequestDispatcher("loginAdmin");
							view.forward(request, response);
							return;
						}

					}

				}
			}
			chain.doFilter(request, response);
		} catch (IOException e) {
			System.out.println("IOException in NoCacheFilter");
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println("ServletException in NoCacheFilter");
			e.printStackTrace();
		}
	}
}
