package com.axis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.log4j.Logger;

import com.axis.exception.DataNotFound;
import com.axis.exception.EmailExits;
import com.axis.exception.ObjectNotFound;
import com.axis.exception.PhoneNoExits;
import com.axis.model.CampaignModel;
import com.axis.model.EscalationMatrixModel;
import com.axis.model.UserModel;
import com.axis.service.CampaignService;
import com.axis.service.EscalationMatrixService;
import com.axis.service.EscalationMatrixUploadservice;

@Controller
public class EscalationMatrixUploadController {
	private static final Logger logger = Logger
			.getLogger(EscalationMatrixUploadController.class);

	@Autowired
	private EscalationMatrixUploadservice escalationMatrixUploadservice;

	@Autowired
	private EscalationMatrixService escalationMatrixService;

	@Autowired
	private CampaignService campaignService;

	@RequestMapping(value = "/Admin/escalationUploadPage", method = RequestMethod.GET)
	public ModelAndView addEscalationMatrixUploadController(Model model)
			{

		List<CampaignModel> campaignList = null;
		try {
			campaignList = campaignService.findAllActiveCampaignByStatus();
		} catch (ObjectNotFound | DataNotFound e) {
			logger.debug(e.getMessage(), e);
			model.addAttribute("NoActiveRoleFound", e.getMessage());
		}	
		model.addAttribute("campaignList", campaignList);

		return new ModelAndView("upload-escalationMatrix");

	}

	
	@RequestMapping(value = "/Admin/addEscalationMatrixUpload", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public ModelAndView uploadTargetFieldValue(@RequestParam int campaignId, String type, @RequestParam("fileName") MultipartFile fileName,
			ModelAndView modelAndView, RedirectAttributes redir)
			throws Exception{
		

		String name = fileName.getOriginalFilename();
		
		
		if(campaignId==0){
			redir.addFlashAttribute("file_not_supported", "Please Select any Campaign");
			modelAndView.setViewName("redirect:/Admin/escalationUploadPage");
			return modelAndView;
		}
		else if(type.contains("0")){
			redir.addFlashAttribute("file_not_supported", "Please Select Type");
			modelAndView.setViewName("redirect:/Admin/escalationUploadPage");
			return modelAndView;
		}
		
		
		if (name.trim().equals("")){
			redir.addFlashAttribute("file_not_supported", "No File specified");
			modelAndView.setViewName("redirect:/Admin/escalationUploadPage");
			return modelAndView;
			//throw new DataNotFound("File not specified");
		}
		if (name.contains(".xls") || name.contains(".xlsx")) {
			Map<String,List<EscalationMatrixModel>> mapList = escalationMatrixUploadservice.getFileandSave(campaignId,type, fileName);
		//	System.out.println(unsortedList);

			modelAndView.setViewName("redirect:/Admin/escalationUploadPage");
			List<Integer> mismatchedLists = new ArrayList<>();
			if(mapList.get("mismatchedRowsList") != null)
			{
				for(EscalationMatrixModel escalationMatrixModel : mapList.get("mismatchedRowsList"))
				{
					mismatchedLists.add(escalationMatrixModel.getId());
				}
			}
			redir.addFlashAttribute("mismatchedRowsList", mismatchedLists);
			redir.addFlashAttribute("errorRowLists", mapList.get("errorRowLists"));
			List<EscalationMatrixModel> escalationMatrixList = mapList.get("errorRowCount");
			int totRows = 0;
			int errorRows = 0;
			for(EscalationMatrixModel ee : escalationMatrixList)
			{
				totRows = Integer.parseInt(ee.getTotalRows());
				errorRows = Integer.parseInt(ee.getErrorRowCount());
			}
			System.out.println("totRows::::::::::::");
			System.out.println(totRows);
			System.out.println("errorRows::::::::::::");
			System.out.println(errorRows);
			
			if((totRows-1) == errorRows)
			{
				redir.addFlashAttribute("successMessage", "File not uploaded.");
				
			}else if(!mapList.get("existRecordLists").isEmpty())
			{
				redir.addFlashAttribute("successMessage", "File uploaded partially.");
			}
			else{
				redir.addFlashAttribute("successMessage", "File uploaded successfully.");
			}
			redir.addFlashAttribute("unstoredUserList", mapList.get("existRecordLists"));
			return modelAndView;

		}else{
			redir.addFlashAttribute("wrongFileFormat", "Please Select file of .xls or .xlsx format");
			//modelAndView.setViewName("upload-escalationMatrix");
			modelAndView.setViewName("redirect:/Admin/escalationUploadPage");
			return modelAndView;
		}
		//return null;
	}
	
	
	
	
}
