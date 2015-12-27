package com.axis.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload
{
	/**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
             
    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    //private static String filePath = "/CampaignFiles/";
    
    
	public static  String uploadFile(String filePath,MultipartFile file, String fileName, HttpServletRequest request,
            HttpServletResponse response) 
	{
		if (!file.isEmpty()) 
		{
			try 
			{
				byte[] bytes = file.getBytes();

				// get absolute path of the application
		        ServletContext context = request.getServletContext();
		        String appPath = context.getRealPath("");
		        System.out.println("appPath = " + appPath);
		 
		        // Creating the directory to store file
				
				File dir = new File(appPath + File.separator + filePath);
		        
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getPath() + File.separator
						+ fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location="
						+ serverFile.getPath());
			} catch (Exception e) {
				return "You failed to upload " + fileName + " " + e.getMessage();
			}
		} else {
			return "You failed to upload " + fileName
					+ " because the file was empty.";
			
		}
		return "Done";
	}
}
