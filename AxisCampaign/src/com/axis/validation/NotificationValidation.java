package com.axis.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.validation.NotificationValidation;
import com.axis.common.MessageUtil;
import com.axis.common.Util;
import com.axis.exception.DataNotFound;
import com.axis.exception.FormExceptions;
import com.axis.model.NotificationModel;

@Component
public class NotificationValidation {

	private static final Logger logger = Logger
			.getLogger(NotificationValidation.class);

	@Autowired
	private MessageUtil massageUtil;

	public void notificationValidate(NotificationModel notificationModel)
			throws DataNotFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("notificationCreateValidate-Start");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		if (Util.isEmpty(notificationModel.getMessage())) {
			exceptions.put("NotificationMessageNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.Message.not.null")));
		}

		if (Util.isEmpty(notificationModel.getSubject())
				|| notificationModel.getSubject() == null
				|| notificationModel.getSubject().equals("")) {
			exceptions.put("NotificationSubjectNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.Subject.not.null")));
		}

		if (notificationModel.getSentUserId() == 0) {
			exceptions.put("NotificationSendUserIdNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.sendUserID.not.null")));
		}

		if (notificationModel.getReceivedUserId() == 0) {
			exceptions
					.put("NotificationRecievedUsedIdNotNull",
							new DataNotFound(
									massageUtil
											.getBundle("Notification.recievedUserID.not.null")));
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("notificationCreateValidate-End");
		}
	}
	
	public void userNotificationValidate(NotificationModel notificationModel)
			throws DataNotFound, FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("notificationCreateValidate-Start");
		}

		Map<String, Exception> exceptions = new HashMap<String, Exception>();

		/*if (Util.isEmpty(notificationModel.getMessage())) {
			exceptions.put("NotificationMessageNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.Message.not.null")));
		}*/

		if (Util.isEmpty(notificationModel.getSubject())
				|| notificationModel.getSubject() == null
				|| notificationModel.getSubject().equals("")) {
			exceptions.put("NotificationSubjectNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.Subject.not.null")));
		}

		if (notificationModel.getSentUserId() == 0) {
			exceptions.put("NotificationSendUserIdNotNull", new DataNotFound(
					massageUtil.getBundle("Notification.sendUserID.not.null")));
		}

		if (notificationModel.getReceivedUserId() == 0) {
			exceptions
					.put("NotificationRecievedUsedIdNotNull",
							new DataNotFound(
									massageUtil
											.getBundle("Notification.recievedUserID.not.null")));
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("notificationCreateValidate-End");
		}
	}

}
