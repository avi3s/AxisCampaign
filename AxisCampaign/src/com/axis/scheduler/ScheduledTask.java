package com.axis.scheduler;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.FlagStatus;
import com.axis.service.CampaignService;

// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {





//	int noOfExecution = 1;
	// Add your task here
	public void run() {
		
/*		 if (noOfExecution > 0) {
			 System.out.println("executed");
			 
			 CampaignController controller = new TestResultUploadController();
			 controller.uploadTestResult("someString", 1234); 
			 
				campaignService.campaignServiceforCheckingandSettingState(sessionFactory);

			    noOfExecution--;

			   } else {

			    System.err.println("Time's up!%n");

			   }*/

		
		
		//campaign state task
		
		Calendar date = new GregorianCalendar();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
		Calendar date_field = FlagStatus.date;
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
		if(date.getTimeInMillis() > date_field.getTimeInMillis()){
			FlagStatus.count = 1;
			FlagStatus.date = date;
			System.out.println("count and date updated");
		} 
		System.out.println("Time is :"+ date.getTime());

		
 
		
/*		// today    
		Calendar date = new GregorianCalendar();
		// reset hour, minutes, seconds and millis
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		date.add(Calendar.DAY_OF_MONTH, 1);//next day
		System.out.println("Time is :" + date.getTime()); // Display current time
*/	}
}

