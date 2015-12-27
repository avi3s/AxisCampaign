package com.axis.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.dao.RoleDao;
import com.axis.entity.RoleEntity;
import com.axis.model.UserModel;

@Component
public class GetAdminDetails {

	@Autowired
	private RoleDao roleDao;

	public UserModel fetchAdminInfo() {

		RoleEntity role = roleDao.getAdminDetails();

		UserModel userModel = new UserModel();
		userModel.setUserId(role.getUserEntities().get(0).getUserId());
		userModel.setUserName(role.getUserEntities().get(0).getUserName());
		userModel.setEmailAddress(role.getUserEntities().get(0)
				.getEmailAddress());
		userModel.setEmployeeName(role.getUserEntities().get(0).getEmployeeName());
		userModel.setEmployeeNumber(role.getUserEntities().get(0).getEmployeeNumber());
		userModel.setPassword(role.getUserEntities().get(0).getPassword());
		userModel.setPrimaryTelephoneNumber(role.getUserEntities().get(0).getPrimaryTelephoneNumber());
		
		return userModel;

	}

}
