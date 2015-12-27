package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.dao.ContentManagementDao;
import com.axis.dao.UserDao;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.exception.RecordFound;
import com.axis.model.ContentManagementDetailsModel;
import com.axis.model.UserModel;

@Component
public class UserDataValidation {

	private static final Logger logger = Logger
			.getLogger(UserDataValidation.class);

	@Autowired
	private MessageUtil massageUtil;

	@Autowired
	private UserDao userDao;

	/**
	 * This method is use for UserData validation purpose
	 * 
	 * @param UserData
	 *            model data
	 * @throws RecordFound
	 * @throws FormExceptions
	 * */

	public void UserCreateValidate(UserModel userModel) throws FormExceptions,
			DataNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("userValidate-End");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (Util.isEmpty(userModel.getEmailAddress())) {
			exceptions.put(
					"userEmailNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.email.not.null")));
		}

		if (Util.isEmpty(userModel.getUserName())) {
			exceptions.put(
					"userNameNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.name.not.null")));
		}

		if (Util.isEmpty(userModel.getEmployeeName())) {
			exceptions.put(
					"employeeNameNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.employeename.not.null")));
		}

		if (Util.isEmpty(userModel.getEmployeeNumber())) {
			exceptions.put("userEmployeeNumberNotnull", new DataNotFound(
					massageUtil.getBundle("user.employeenumber.not.null")));
		}

		if (Util.isEmpty(userModel.getPrimaryTelephoneNumber())) {
			exceptions.put("userPrimaryTelephoneNotnull", new DataNotFound(
					massageUtil.getBundle("user.primaryTelephone.not.null")));
		}

		if (Util.isEmpty((userModel.getSubRoleLevelId()))) {

			System.out.println("in validation _______________ >>> "
					+ userModel.getRoleId() + "  "
					+ !Util.isEmpty((userModel.getRoleId())));
			if (!Util.isEmpty((userModel.getRoleId())))
				exceptions.put("userSubRoleNotnull", new DataNotFound(
						massageUtil.getBundle("user.subroleId.not.null")));
		}

		if (Util.isEmpty((userModel.getRoleId()))) {
			exceptions.put(
					"userRoleNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.roleId.not.null")));
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("userValidation-Ends");
		}

	}

	public void AdminUpdateValidate(UserModel userModel) throws FormExceptions,
			DataNotFound, RecordFound {

		if (logger.isDebugEnabled()) {
			logger.debug("adminValidate-Start");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (Util.isEmpty(userModel.getEmailAddress())) {
			exceptions.put(
					"userEmailNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.email.not.null")));
		}

		if (Util.isEmpty(userModel.getUserName())) {
			exceptions.put(
					"userNameNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.name.not.null")));
		}

		if (Util.isEmpty(userModel.getEmployeeName())) {
			exceptions.put(
					"employeeNameNotnull",
					new DataNotFound(massageUtil
							.getBundle("user.employeename.not.null")));
		}

		if (Util.isEmpty(userModel.getEmployeeNumber())) {
			exceptions.put("userEmployeeNumberNotnull", new DataNotFound(
					massageUtil.getBundle("user.employeenumber.not.null")));
		}

		if (Util.isEmpty(userModel.getPrimaryTelephoneNumber())) {
			exceptions.put("userPrimaryTelephoneNotnull", new DataNotFound(
					massageUtil.getBundle("user.primaryTelephone.not.null")));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("adminValidation-Ends");
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

	}

	public void cmsUpdateValidate(ContentManagementDetailsModel cmsModel) {
		// TODO Auto-generated method stub

	}

}
