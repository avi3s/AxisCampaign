package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;





import com.axis.entity.RoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.model.RoleLevelModel;

@Repository
public class RoleLevelDao extends GenericDao<RoleLevelEntity, Integer> {

	private static final Logger logger = Logger.getLogger(RoleLevelDao.class);

	public RoleLevelDao() {
		super(RoleLevelEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<RoleLevelEntity> getActiveRoleLevelListForTableView() {

		List<RoleLevelEntity> roleLevelEntities = null;
				
		
		String hql = "from RoleLevelEntity rle1 where rle1.roleLevelEntity.rowLevelId != rle1.rowLevelId and rle1.status= :status order by rle1.createTimeStamp desc";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("status",Status.ACTIVE);
			roleLevelEntities =  query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleLevelEntities;
	}

	public RoleLevelEntity checkDuplicateLevelName(String levelName) {
		RoleLevelEntity roleLevelEntity = null;
				
		
		String hql = "from RoleLevelEntity rle1 where rle1.status= :status and rle1.levelName= :levelName";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("levelName",levelName);
			query.setParameter("status",Status.ACTIVE);
			roleLevelEntity =  (RoleLevelEntity) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleLevelEntity;
	}

	public List<RoleLevelEntity> findOthers(RoleLevelModel roleLevelModel) {
List<RoleLevelEntity> roleLevelEntities = null;
				
		
		String hql = "from RoleLevelEntity rle1 where rle1.status= :status and rle1.rowLevelId != :rowLevelId";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("rowLevelId",roleLevelModel.getRowLevelId());
			query.setParameter("status",Status.ACTIVE);
			roleLevelEntities =  query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleLevelEntities;
	}
	
	
}