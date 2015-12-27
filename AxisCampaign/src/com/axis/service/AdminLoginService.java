package com.axis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.UserConverter;
import com.axis.dao.UserDao;
import com.axis.entity.UserEntity;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.ObjectNotFound;
import com.axis.model.UserModel;
import com.axis.validation.AdminValidation;

@Service
@Transactional
public class AdminLoginService {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private AdminValidation adminValidation;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserConverter userConverter;

	public UserModel adminLoginCheck(UserModel userModel) throws DataNotFound,
			ObjectNotFound, FormExceptions {

		adminValidation.validateForm(userModel);

		List<UserEntity> userEntities = userDao.adminLoginCheck(userModel);

		if (userEntities.isEmpty() || userEntities == null
				|| userEntities.size() == 0) {

			System.out.println("noooo user found");
			throw new DataNotFound(
					messageUtil.getBundle("invalid.login.credentials"));
		} else {
			for (UserEntity userEntity : userEntities) {
				System.out.println("data ---> " + userEntity.getUserName()
						+ "  " + userEntity.getEmployeeName() + " "
						+ userEntity.getRoleEntity().getRoleName());
				userModel = userConverter.entityToModel(userEntity);
			}
		}

		return userModel;

	}
}
