package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.RoleModel;
import com.axis.model.UserModel;

@Component
public class AdminValidation {
	
	@Autowired
	private MessageUtil messageUtil;
	
	public void validateForm(UserModel userModel) throws FormExceptions, DataNotFound, ObjectNotFound {
		
		Map<String,Exception> exceptions = new HashMap<String,Exception>();

		
		System.out.println("in RoleLevelValidation validation :: ");
		
		if(Util.isEmpty(userModel.getUserName())) {
			exceptions.put("emptyfieldForUserName", new DataNotFound(messageUtil.getBundle("emptyfieldForUserName")));
		}
		
		if(Util.isEmpty(userModel.getPassword())) {
			exceptions.put("emptyfieldForPassword", new DataNotFound(messageUtil.getBundle("emptyfieldForPassword")));

		}
		
		if(exceptions.size()>0)
			throw new FormExceptions(exceptions);
	}
		

}
