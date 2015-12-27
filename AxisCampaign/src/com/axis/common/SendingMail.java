package com.axis.common;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendingMail 
{
	public static String mail(String email, String mail_subject, String messagetext) 
	{
		String result = "";
		String host = "", user = "", pass = "";
		
		host = "mail.rtizen.com";
		user = "noreply-abax-admin@rtizen.com";
		pass = "EnhLG7#VLnLl";
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		String to = email; // out going email id
		String from = "admin@axisbank.co.in"; //Email id of the recipient
		String subject = mail_subject;
		String messageText = messagetext;
		
		boolean sessionDebug = true;
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol.", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.", true);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		try 
		{
			
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setContent(messageText, "text/html"); // use setText if you want to send text
			Transport transport=null;
			transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);

			transport.sendMessage(msg, msg.getAllRecipients());
			result = "true"; // assume it was sent
			transport.close();
		}
		catch (Exception err) 
		{
			//result = "false"; // assume it's a fail
			result=err.getMessage();
		}
			
		return result;

	}
}
