package com.axis.common;

public class MyThreadForMail extends Thread {
	
	String mail_subject1 = "";
	String email1 = "";
	String mail_body1 = "";
		
	public MyThreadForMail(String email, String mail_subject, String mail_body)
	{
		this.email1 = email;
		this.mail_subject1 = mail_subject;
		this.mail_body1 = mail_body;
	}
	public void run()
	{
		System.out.println("in thread");
		System.out.println("Before MAIL-----------------");
		String result = SendingMail.mail(email1, mail_subject1, mail_body1);
		System.out.println("After MAIL-----------------"+result);
	}
}
