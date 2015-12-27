package com.axis.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axis.entity.NotificationEntity;
import com.axis.enumeration.Status;

@Repository
public class NotificationDao extends GenericDao<NotificationEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(NotificationDao.class);

	public NotificationDao() {
		super(NotificationEntity.class);
		// TODO Auto-generated constructor stub
	}

	/*@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllRecievedNotificationById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where  n.status=:status and n.receivedUserId.id = :receivedUserId order by n.createTimeStamp desc ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("receivedUserId", id); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}
		return notifications;
	}

	public void delete(NotificationEntity notificationEntity) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "delete from NotificationEntity n where n.id = :id ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", notificationEntity.getNotificationId());
			int result = query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}

	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllSentNotificationById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where n.status=:status and n.sentUserId.id = :sentUserId order by n.createTimeStamp desc ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("sentUserId", id); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-End");
		}
		return notifications;
	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> getLastFiveReceivedNotificationbyId(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-Start");
		}

		System.out.println("Id :" + id);
		String hql = "from NotificationEntity n where n.status=:status and n.receivedUserId.id =:receivedUserId order by n.createTimeStamp desc";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("receivedUserId", id);
			query.setParameter("status", Status.ACTIVE);
			query.setMaxResults(5);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-End");
		}
		return notifications;
	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> getLastFiveSentNotificationbyId(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-Start");
		}

		System.out.println("Id :" + id);
		String hql = "from NotificationEntity n where n.status=:status and n.sentUserId.id =:sentUserId order by n.createTimeStamp desc";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("sentUserId", id);
			query.setParameter("status", Status.ACTIVE);
			query.setMaxResults(5);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-End");
		}
		return notifications;
	}

	public List<NotificationEntity> getNotification(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getNotification-Start");
		}

		// System.out.println("Id :"+id);

		List<NotificationEntity> notificationsList = null;

		try {
			Session session = sessionFactory.openSession();

			// WHERE n.sentUserId = 1
			Query query = session
					.createQuery("from NotificationEntity n where n.status=:status and n.receivedUserId =:receivedUserId order by n.createDate desc ");

			query.setParameter("receivedUserId", id);
			query.setParameter("status", Status.ACTIVE);

			notificationsList = query.list();

			for (NotificationEntity n : notificationsList) {
				// System.out.println("value:");
				// System.out.println("value is"+n.getSentUser().getUserName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getNotification-End");
		}
		return notificationsList;
	}

	public NotificationEntity getAdminNotificationServiceById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationServiceById-Start");
		}

		NotificationEntity notification = null;

		String hql = "from NotificationEntity n where n.status=:status and n.notificationId =:id";

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("status", Status.ACTIVE);
			notification = (NotificationEntity) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"getAdminNotificationServiceById-NotificationDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationServiceById-End");
		}

		return notification;

	}

	public void updateViewStatus(int receiverUserId) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateViewStatus-Start");
		}

		String hql = "update NotificationEntity n set n.viewStatus =:viewStatus where n.status=:status and n.receivedUserId.id =:receiverUserId";

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("viewStatus", Status.ACTIVE);
			query.setParameter("receiverUserId", receiverUserId);

			int modifications = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"getAdminNotificationServiceById-NotificationDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updateViewStatus-End");
		}

	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllNotificationBydate(Date date) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where n.status=:status and n.createTimeStamp = :date";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("date", date); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-End");
		}
		return notifications;
	}

	public List<NotificationEntity> countUnseenNotification(int receiverUserId) {

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-Start");
		}

		String hql = " from NotificationEntity n where n.status=:status and n.receiverUserId.id =:receiverUserId and n.viewStatus=:viewStatus";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("receiverUserId", receiverUserId);

			query.setParameter("viewStatus", 1);

			query.setParameter("status", Status.ACTIVE);

			notifications = query.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-End");
		}

		return notifications;
	}

	public void makeInactive(int id) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "update NotificationEntity n set status=:status where n.id = :id ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("status", Status.INACTIVE);
			int result = query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}

	}

	public List<NotificationEntity> getUnseenNotification(int userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-Start");
		}

		String hql = " from NotificationEntity n where n.status=:status and n.receivedUserId.id =:receiverUserId and n.viewStatus=:viewStatus";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("receiverUserId", userId);

			query.setParameter("viewStatus", "0");

			query.setParameter("status", Status.ACTIVE);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-End");
		}

		return notifications;
	}*/
	
	
	/*@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllRecievedNotificationById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where  n.status=:status and n.receiveStatus=:recievedStatus and n.receivedUserId.id = :receivedUserId order by n.createTimeStamp desc ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("receivedUserId", id); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("recievedStatus", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}
		return notifications;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllRecievedNotificationById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where  n.status=:status and n.receivedUserId.id = :receivedUserId order by n.createTimeStamp desc ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("receivedUserId", id); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}
		return notifications;
	}

	public void delete(NotificationEntity notificationEntity) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "delete from NotificationEntity n where n.id = :id ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", notificationEntity.getNotificationId());
			int result = query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}

	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllSentNotificationById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where n.status=:status and n.receiveStatus=:recievedStatus and n.sentUserId.id = :sentUserId order by n.createTimeStamp desc ";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("sentUserId", id); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("recievedStatus", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-End");
		}
		return notifications;
	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> getLastFiveReceivedNotificationbyId(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-Start");
		}

		System.out.println("Id :" + id);
		String hql = "from NotificationEntity n where n.status=:status and n.receivedUserId.id =:receivedUserId order by n.createTimeStamp desc";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("receivedUserId", id);
			query.setParameter("status", Status.ACTIVE);
			query.setMaxResults(5);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-End");
		}
		return notifications;
	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> getLastFiveSentNotificationbyId(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-Start");
		}

		System.out.println("Id :" + id);
		String hql = "from NotificationEntity n where n.status=:status and n.sentUserId.id =:sentUserId order by n.createTimeStamp desc";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("sentUserId", id);
			query.setParameter("status", Status.ACTIVE);
			query.setMaxResults(5);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getLastFiveNotification-End");
		}
		return notifications;
	}

	public List<NotificationEntity> getNotification(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getNotification-Start");
		}

		// System.out.println("Id :"+id);

		List<NotificationEntity> notificationsList = null;

		try {
			Session session = sessionFactory.openSession();

			// WHERE n.sentUserId = 1
			Query query = session
					.createQuery("from NotificationEntity n where n.status=:status and n.receiveStatus=:recievedStatus and n.receivedUserId =:receivedUserId order by n.createDate desc ");

			query.setParameter("receivedUserId", id);
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("recievedStatus", Status.ACTIVE);

			notificationsList = query.list();

			for (NotificationEntity n : notificationsList) {
				// System.out.println("value:");
				// System.out.println("value is"+n.getSentUser().getUserName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getNotification-End");
		}
		return notificationsList;
	}

	public NotificationEntity getAdminNotificationServiceById(int id) {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationServiceById-Start");
		}

		NotificationEntity notification = null;

		String hql = "from NotificationEntity n where n.status=:status and n.notificationId =:id";

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("status", Status.ACTIVE);
			notification = (NotificationEntity) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"getAdminNotificationServiceById-NotificationDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminNotificationServiceById-End");
		}

		return notification;

	}

	public void updateViewStatus(int receiverUserId) {

		if (logger.isDebugEnabled()) {
			logger.debug("updateViewStatus-Start");
		}

		String hql = "update NotificationEntity n set n.viewStatus =:viewStatus where n.status=:status and n.receivedUserId.id =:receiverUserId";

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("viewStatus", Status.ACTIVE);
			query.setParameter("receiverUserId", receiverUserId);

			int modifications = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"getAdminNotificationServiceById-NotificationDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updateViewStatus-End");
		}

	}

	@SuppressWarnings("unchecked")
	public List<NotificationEntity> findAllNotificationBydate(Date date) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "from NotificationEntity n where n.status=:status and n.createTimeStamp = :date";
		List<NotificationEntity> notifications = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("date", date); // 1 = Admin
			query.setParameter("status", Status.ACTIVE);
			notifications = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllSentNotificationById-End");
		}
		return notifications;
	}

	public List<NotificationEntity> countUnseenNotification(int receiverUserId) {

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-Start");
		}

		String hql = " from NotificationEntity n where n.status=:status and n.receiverUserId.id =:receiverUserId and n.viewStatus=:viewStatus";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("receiverUserId", receiverUserId);

			query.setParameter("viewStatus", 1);

			query.setParameter("status", Status.ACTIVE);

			notifications = query.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-End");
		}

		return notifications;
	}

	public void makeInactive(int id) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-Start");
		}

		// System.out.println("ID : "+id);
		String hql = "update NotificationEntity n set receiveStatus=:receiveStatus where n.id = :id ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("receiveStatus", Status.INACTIVE);
			int result = query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllRecievedNotificationById-End");
		}

	}

	public List<NotificationEntity> getUnseenNotification(int userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-Start");
		}

		String hql = " from NotificationEntity n where n.status=:status and n.receivedUserId.id =:receiverUserId and n.viewStatus=:viewStatus";

		List<NotificationEntity> notifications = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("receiverUserId", userId);

			query.setParameter("viewStatus", "0");

			query.setParameter("status", Status.ACTIVE);

			notifications = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("countUnseenNotification-End");
		}

		return notifications;
	}
}