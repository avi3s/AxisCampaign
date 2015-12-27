package com.axis.dao;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.axis.common.MessageUtil;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.enumeration.Status;
import com.axis.model.AcheivementFieldValueModel;
import com.axis.model.AcheivementModel;

@Repository
public class AchievementFieldValueDao extends
		GenericDao<AchievementFieldValueEntity, Integer> {
	
	@Autowired
	private MessageUtil massageUtil;

	private static final Logger logger = Logger
			.getLogger(AchievementFieldValueDao.class);

	public AchievementFieldValueDao() {
		super(AchievementFieldValueEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getting the Field Values Against the Status
	 * 
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	
	public List<AchievementFieldValueEntity> findAllFieldValuesAgainstStatus(){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		
		String hql = "from AchievementFieldValueEntity afve where afve.status = :status order by afve.achievementFieldValueId desc " ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			query.setParameter("status", Status.ACTIVE); // For Specific Role
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	
	/**
	 * get level for selected ranking by <b>employee no</b>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllAchievementFieldValueEntityByEmpNowOfSameFieldLavel(String  empID){
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		
		String empno=massageUtil.getBundle("emp.no");
		
		String ranking=massageUtil.getBundle("emp.ranking");
		
		String hql = "select afm.* from achievement_field_value_master afm, (select field_level from achievement_field_master tafm, achievement_field_value_master tafvm where tafm.id=tafvm.achievement_id and (tafm.field_name='"+empno+"' and tafvm.field_value='"+empID+"')) as temp, achievement_field_master am where afm.field_level= temp.field_level and am.id=afm.achievement_id and am.field_name='"+ranking+"'" ;
		
		List<Object[]> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
//			query.setParameter("status", Status.ACTIVE); // For Specific Role
			
			System.out.println("try under limited ");
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
	
	}
	
	/**
	 * get level by <B>solid</B>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllAchievementFieldValueEntityByEmpNowOfSameFieldLavelBasedSolID(String empID){
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		
        String empno=massageUtil.getBundle("emp.no");
		
		String ranking=massageUtil.getBundle("emp.ranking");
		
		String solId=massageUtil.getBundle("emp.solId");
		
		String hql = "select afm.* from achievement_field_value_master afm, (select field_level from achievement_field_master tafm, achievement_field_value_master tafvm where tafm.id=tafvm.achievement_id and (tafm.field_name='"+empno+"' and tafvm.field_value='"+empID+"')) as temp, achievement_field_master am where afm.field_level= temp.field_level and am.id=afm.achievement_id and am.field_name='"+solId+"'" ;
		
		List<Object[]> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
//			query.setParameter("status", Status.ACTIVE); // For Specific Role
			
			System.out.println("try under limited ");
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
	
	}
	/**
	 * 
	 * @param value
	 * @param achivementId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> findAllLevelBySolId(String value, int achivementId){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		
		String hql = "select afvm from AchievementFieldValueEntity afvm where afvm.achievementFieldEntity.achievementFieldId=:achivementId and afvm.fieldValue=:value" ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			query.setParameter("achivementId", achivementId); // For Specific Role
			
			query.setParameter("value", value);
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	/**
	 * Top 20 Ranking by level 
	 * @param fieldLavels
	 * @return
	 */
	
	
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> findAllLevelTop20RankingByLevel(List<String> fieldLavels){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		String innerQuery = "";
		
		String param = fieldLavels.toString().replaceAll("\\[|\\]", "").replaceAll(", ","','");
		
		innerQuery += "'"+param+"'";
		
		String hql = "select afvm from AchievementFieldValueEntity afvm, AchievementFieldEntity afm where  afm=afvm.achievementFieldEntity and afvm.fieldLevel in("+ innerQuery +") and afm.filedName='Ranking' order by afvm.fieldValue asc" ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(20);
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	
	/**
	 * get all upper and lower lavel based on selected achivement fieldId and field value
	 * @param upperLower
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> findAchievementFieldValueEntityListOuperrLevelAndLowerLevel(String upperLower, int value, int achivementId){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		String orderSqlString = "";
		
		if("<".equals(upperLower)){
			orderSqlString = " order by afvm.fieldValue desc";
		}
		else{
			// > is upperLower
			orderSqlString = " order by afvm.fieldValue asc";
		}
		String hql = "select afvm from AchievementFieldValueEntity afvm where afvm.achievementFieldEntity.achievementFieldId ="+ achivementId +" and afvm.fieldValue"+ upperLower +""+ value +""+orderSqlString ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(3);
//			query.setParameter("status", Status.ACTIVE); // For Specific Role
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	
	
	/**
	 * get all List<AchievementFieldValueEntity> by level
	 * @param upperLower
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> findAchievementFieldValueEntityListbyLevel(List<String> fieldLavels){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		String innerQuery = "";
		
		String param = fieldLavels.toString().replaceAll("\\[|\\]", "").replaceAll(", ","','");
		
		String instr = fieldLavels.toString().replaceAll("\\[|\\]", "");
		
		instr = "'"+instr+"'";
		
		innerQuery += "'"+param+"'";
		
		System.out.println("innerQuery = "+innerQuery);
		
		String hql = "select afvm from AchievementFieldValueEntity afvm where afvm.fieldLevel in ("+ innerQuery +") order by instr("+ instr +",afvm.fieldLevel), afvm.achievementFieldEntity" ;
		
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
//			query.setParameter("fieldLavel", innerQuery); // For Specific Role
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	public List<AchievementFieldValueEntity> getDuplicateValueCheck(AcheivementModel acheivementModel){
//		if (logger.isDebugEnabled()) {
//			logger.debug("findAllAgainstRoleCampaign-Start");
//		}
//		
//		String hql = "from AchievementFieldValueEntity afve where afve.filedName = :filedName and afve.roleCampaignEntity = :roleCampaignEntity" ;
//		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
//		
//		try {
//			Query query = sessionFactory.getCurrentSession().createQuery(hql);
//			query.setParameter("status", Status.ACTIVE); // For Specific Role
//			
//			achievementFieldValueEntity = query.list();
//
//		} catch (Exception e) {
//			logger.error(
//					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
//		}
//		List<String> string=null;
//		
//		if (logger.isDebugEnabled()) {
//			logger.debug("finfIdagainstRoleandCampaign-End");
//		}
//		return achievementFieldValueEntity;
//		
//	}
	
	/**
	 * Duplicate Values Checking
	 * @param acheivementFieldValueModel
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> getDuplicateValueCheck(AcheivementFieldValueModel acheivementFieldValueModel){
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		//System.out.println("Acheivement details are ::::"+);
		String hql = "from AchievementFieldValueEntity afve where afve.fieldValue = :fieldValue and afve.achievementFieldEntity = :acheivementfieldEntity" ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntity = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			query.setParameter("fieldValue",acheivementFieldValueModel.getFieldValue()); // For Specific Role
			
			query.setParameter("acheivementfieldEntity",acheivementFieldValueModel.getAchievementFieldEntity());
			
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;
		
	}
	/**
	 * Getting All Field Values Against the AcheivementId
	 * for Field Name
	 * @param achievementFieldEntity
	 * @return AcheivementFieldValueEntity
	 */
	
	@SuppressWarnings("unchecked")
	public List<AchievementFieldValueEntity> findAllAgainstAcheivementId(AchievementFieldEntity achievementFieldEntity){
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstAcheivementId-Start");
		}
		
		String hql = "from AchievementFieldValueEntity afve where afve.achievementFieldEntity = :achievementFieldEntity" ;
		List<AchievementFieldValueEntity> achievementFieldValueEntities = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("achievementFieldEntity", achievementFieldEntity); // For Specific Acheivement Field Entity
			
			achievementFieldValueEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"fieldValueAgainstAcheivementFieldName ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntities;
		
	}
	
	//............................END...................................//
	
	/**
	 * Getting Maximum Level Value...
	 */
	@SuppressWarnings("unchecked")
	public int getLastLevelValue(){
		
		if (logger.isDebugEnabled()) {
			logger.debug("getting maximum level start-Start");
		}
		
		String hql = "SELECT MAX(afve.fieldLevel) FROM AchievementFieldValueEntity afve" ;
		
		List<AchievementFieldValueEntity> achievementFieldValueEntities = null;
		
		int maxlevel=0;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//query.setParameter("achievementFieldEntity", achievementFieldEntity); // For Specific Acheivement Field Entity
			
			maxlevel = Integer.parseInt(query.uniqueResult().toString());
			
			System.out.println("Maxlevel"+maxlevel);

		} catch (Exception e) {
			logger.error(
					"fieldValueAgainstAcheivementFieldName ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			
			logger.debug("getting maximum level -End");
		}
		return maxlevel;
		
	}
	
	//....................................END....................................//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}