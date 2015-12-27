package com.axis.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axis.common.MessageUtil;
import com.axis.converter.AchievementFieldConverter;
import com.axis.converter.AchievementFieldValueConverter;
import com.axis.dao.AchievementFieldValueDao;
import com.axis.dao.CampaignDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.RoleDao;
import com.axis.dao.UserTargetDao;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.entity.TargetFieldEntity;
import com.axis.entity.TargetFieldValueEntity;
import com.axis.exception.DataNotFound;
import com.axis.model.UserTargetModel;
import com.axis.validation.AcheivementDataValidation;

@Service
@Transactional
public class UserTargetService {
	
	@Autowired
	private UserTargetDao userTargetDao;	
	
	@Autowired
	RoleCampaignDao roleCampaignDao;
	
	@Autowired
	CampaignDao campaignDao;
	
	@Autowired
	RoleDao roleDao;
	
	
	@Autowired
	private MessageUtil massageUtil;


	public UserTargetModel getTargetFieldValues(int campId,
			int roleId) throws DataNotFound {

		final List<String> headers = new ArrayList<>();
		final List<List<String>> bodyContents = new ArrayList<>();
		
		UserTargetModel userTargetModel = new UserTargetModel();
		RoleEntity roleEntity = roleDao.find(roleId);

		if (roleEntity == null) {
			throw new DataNotFound(massageUtil.getBundle("role.id.not.found"));
		}

		String roleName = roleEntity.getRoleName();

		System.out.println("RoleNAme:::" + roleName);

		CampaignEntity campaignEntity = campaignDao.find(campId);

		if (campaignEntity == null) {
			throw new DataNotFound(
					massageUtil.getBundle("campaign.id.not.found"));
		}

		RoleCampaignEntity roleCampaignEntity = roleCampaignDao
				.finfIdagainstRoleandCampaign(roleEntity, campaignEntity);

		if (roleCampaignEntity == null) {
			throw new DataNotFound(
					massageUtil.getBundle("role-campaign.id.not.found"));
		}

		/**
		 * Role Name Circle Head Section Details
		 */

		//if (roleName.equals("Circle Head")) {
			List<TargetFieldEntity> targetFieldEntities = userTargetDao
					.findAllAgainstRoleCampaign(roleCampaignEntity);

			if (targetFieldEntities == null
					|| targetFieldEntities.isEmpty()
					|| targetFieldEntities.size() == 0) {

				throw new DataNotFound(
						massageUtil
								.getBundle("Target.roleCampaign.not.exist"));

			}

			for (TargetFieldEntity entity : targetFieldEntities) {

				headers.add(entity.getFiledName());

				final List<String> strings = new ArrayList<>();

				List<TargetFieldValueEntity> targetFieldValueEntities = userTargetDao
						.findAllAgainstTargetId(entity);

				for (TargetFieldValueEntity valueEntity : targetFieldValueEntities) {
					strings.add(valueEntity.getFieldValue());
				}

				bodyContents.add(strings);
			}

			System.out.println("---------------Shiba------------------");
			for (String header : headers) {
				System.out.print(" | " + header + " | ");
			}
			System.out.println("\n------------------------------------------");
			int length = bodyContents.get(0).size();

			/*for (int i = 0; i < length; i++) {
				for (List<String> strings : bodyContents) {
					System.out.print(" | " + strings.get(i) + " | ");
					finallists.add(strings.get(i));
				}
				System.out.println("\n");
			}
			System.out.println("Final list sizes values are:::::" + finallists);

			System.out.println("\n------------------------------------------");
			System.out
					.println("====================***=======================");
*/
	//	}

		
		
		
			

		/* ........ END of NORMAL HEAD ROLE TYPE............................ */
		
		
		userTargetModel.setHeaderList(headers);
		userTargetModel.setBodycontains(bodyContents);
		
		
		if (roleName.equals("Circle Head")) {
			userTargetModel.setRoleName("CircleHead");
		} 

		return userTargetModel;

	}
}
