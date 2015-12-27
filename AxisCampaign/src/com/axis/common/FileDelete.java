package com.axis.common;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDelete {

	public static String deleteFile(String filePath,String fileName, HttpServletRequest request,
            HttpServletResponse response) throws Exception 
	{
		try {

			// get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        System.out.println("appPath = " + appPath);
	 
	        // construct the complete absolute path of the file
	        String fullPath = appPath + filePath + fileName;  
	        
				File file = new File(fullPath);

				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}

			} catch (Exception e) {e.printStackTrace();}
		return "success";
	}
	
}