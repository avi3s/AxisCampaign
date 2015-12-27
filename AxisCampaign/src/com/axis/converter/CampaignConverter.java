package com.axis.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axis.common.MessageUtil;
import com.axis.dao.CampaignDao;
import com.axis.dao.CampaignFileDao;
import com.axis.dao.RoleCampaignDao;
import com.axis.dao.RoleDao;
import com.axis.entity.CampaignEntity;
import com.axis.entity.CampaignFileEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.enumeration.Status;
import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;
import com.axis.model.CampaignFileModel;
import com.axis.model.CampaignModel;

@Component
public class CampaignConverter implements
		NecessaryConverter<CampaignEntity, CampaignModel> {

	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleCampaignDao roleCampaignDao;
	
	@Autowired
	private CampaignDao campaignDao;
	
	@Autowired
	private CampaignFileDao campaignFileDao;

	@Override
	public CampaignEntity modelToEntity(CampaignModel m) {

		CampaignEntity campaignEntity = new CampaignEntity();
		List<CampaignFileEntity> campaignFileEntities = null;
		
		campaignEntity.setCampaignDescription(m.getCampaignDescription());
		campaignEntity.setCampaignLogo(m.getCampaignLogo());
		campaignEntity.setCampaignName(m.getCampaignName());
		campaignEntity.setCreateTimeStamp(new Date());
		campaignEntity.setCreatedBy(m.getCreatedBy());
		campaignEntity.setFinancialYear(m.getFinancialYear());
		campaignEntity.setQuarterEndDate(m.getQuarterEndDate());
		campaignEntity.setQuarterId(m.getQuarterId());
		campaignEntity.setQuarterStartDate(m.getQuarterStartDate());
		campaignEntity.setStatus(Status.ACTIVE);
		
		if(m.getCampaignFileModels() != null) {
			
			for(CampaignFileModel campaignFileModel:m.getCampaignFileModels()) {
				
				if (campaignFileEntities == null) {
					campaignFileEntities = new ArrayList<>();
				}
				
				CampaignFileEntity campaignFileEntity = new CampaignFileEntity();
				
				campaignFileEntity.setCreatedBy(m.getCreatedBy());
				campaignFileEntity.setCreateTimeStamp(new Date());
				campaignFileEntity.setFileName(campaignFileModel.getFileName());
				campaignFileEntity.setStatus(Status.ACTIVE);
				if(Double.parseDouble(campaignFileModel.getFileSize()) < 1024.0D)
					campaignFileEntity.setFileSize(campaignFileModel.getFileSize()+" bytes");
					else if(Double.parseDouble(campaignFileModel.getFileSize()) >=1024.0D && Double.parseDouble(campaignFileModel.getFileSize()) < (1024.0D*1024.0D))
					campaignFileEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(campaignFileModel.getFileSize())/1024.0D))+" kb");
					else
					campaignFileEntity.setFileSize(String.valueOf(Math.round(Double.parseDouble(campaignFileModel.getFileSize())/(1024.0D*1024.0D)))+" mb");
				campaignFileEntity.setView_status(Status.ACTIVE);
				campaignFileEntity.setCampaignEntity(campaignEntity);
				
				campaignFileEntities.add(campaignFileEntity);
			}
			campaignEntity.setCampaignFileEntities(campaignFileEntities);
		}
		
		
		String role_array[] = m.getRoleID_array();
		List<RoleCampaignEntity> roleCampaignEntities = new ArrayList<>();

		if (role_array.length > 0) {

			for (int i = 0; i < role_array.length; i++) {

				RoleCampaignEntity roleCampaignEntity = new RoleCampaignEntity();
				RoleEntity role1 = roleDao.find(Integer.parseInt(role_array[i]));

				roleCampaignEntity.setCampaignEntity(campaignEntity);
				roleCampaignEntity.setRoleEntity(role1);
				roleCampaignEntity.setCreatedBy(m.getCreatedBy());
				roleCampaignEntity.setCreateTimeStamp(new Date());
				
				roleCampaignEntities.add(roleCampaignEntity);
			}
		}

		campaignEntity.setRoleCampaignEntities(roleCampaignEntities);
		
		return campaignEntity;
	}

	@Override
	public CampaignEntity updateModelToEntity(CampaignModel m, CampaignEntity campaignEntity) {

		campaignEntity.setCampaignDescription(m.getCampaignDescription());
		campaignEntity.setCampaignLogo(m.getCampaignLogo());
		campaignEntity.setCampaignName(m.getCampaignName());
		campaignEntity.setUpdatedBy(m.getUpdatedBy());
		campaignEntity.setLastUpdateTimeStamp(new Date());
		campaignEntity.setFinancialYear(m.getFinancialYear());
		campaignEntity.setQuarterEndDate(m.getQuarterEndDate());
		campaignEntity.setQuarterId(m.getQuarterId());
		campaignEntity.setQuarterStartDate(m.getQuarterStartDate());
		campaignEntity.setStatus(Status.ACTIVE);
		
		/*if(m.getCampaignFileModels() != null) {
			
			for(CampaignFileModel campaignFileModel:m.getCampaignFileModels()) {
				
				if (campaignFileEntities == null) {
					campaignFileEntities = new ArrayList<>();
				}
				
				CampaignFileEntity campaignFileEntity = new CampaignFileEntity();
				
				campaignFileEntity.setUpdatedBy(m.getUpdatedBy());
				campaignFileEntity.setLastUpdateTimeStamp(new Date());
				campaignFileEntity.setFileName(campaignFileModel.getFileName());
				campaignFileEntity.setStatus(Status.ACTIVE);
				
				campaignFileEntities.add(campaignFileEntity);
			}
			campaignEntity.setCampaignFileEntities(campaignFileEntities);
		}*/
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		List<RoleCampaignEntity> roleCampaignEntities2 = roleCampaignDao.findAllActiveRoleByCampaign(campaignEntity.getCampaignId());
		
		for(RoleCampaignEntity roleCampaignEntity :roleCampaignEntities2) {
			
			roleCampaignDao.deleteRoleCampaign(roleCampaignEntity.getRoleEntity().getRoleId(), campaignEntity.getCampaignId());
		}
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		return campaignEntity;
	}
	
	@Override
	public CampaignModel entityToModel(CampaignEntity e) {

		CampaignModel campaignModel = new CampaignModel();
		List<CampaignFileModel> campaignFileModels = null;

		if (e != null) {

			campaignModel.setCampaignId(e.getCampaignId());
			campaignModel.setCampaignName(e.getCampaignName());
			campaignModel.setCampaignDescription(e.getCampaignDescription());
			campaignModel.setCampaignLogo(e.getCampaignLogo());
			campaignModel.setCreatedBy(e.getCreatedBy());
			campaignModel.setCreateTimeStamp(e.getCreateTimeStamp());
			campaignModel.setFinancialYear(e.getFinancialYear());
			campaignModel.setUpdatedBy(e.getUpdatedBy());
			campaignModel.setLastUpdateTimeStamp(e.getLastUpdateTimeStamp());
			campaignModel.setQuarterEndDate(e.getQuarterEndDate());
			campaignModel.setQuarterId(e.getQuarterId());
			campaignModel.setQuarterStartDate(e.getQuarterStartDate());
			campaignModel.setState(e.getState());
			campaignModel.setStatus(e.getStatus());
			
			for(CampaignFileEntity campaignFileEntity:e.getCampaignFileEntities()) {
				
				if(campaignFileModels == null) {
					campaignFileModels = new ArrayList<>();
				}
				
				CampaignFileModel campaignFileModel = new CampaignFileModel();
				
				campaignFileModel.setCampaignFileId(campaignFileEntity.getCampaignFileId());
				campaignFileModel.setCreatedBy(campaignFileEntity.getCreatedBy());
				campaignFileModel.setCreateTimeStamp(campaignFileEntity.getCreateTimeStamp());
				campaignFileModel.setFileName(campaignFileEntity.getFileName());
				campaignFileModel.setLastUpdateTimeStamp(campaignFileEntity.getLastUpdateTimeStamp());
				campaignFileModel.setStatus(campaignFileEntity.getStatus());
				campaignFileModel.setUpdatedBy(campaignFileEntity.getUpdatedBy());
				//campaignFileModel.setVersion(campaignFileEntity.getVersion());
				
				campaignFileModels.add(campaignFileModel);
				
			}
			campaignModel.setCampaignFileModels(campaignFileModels);
			
			List<Integer> roleID_array = null;

			List<RoleCampaignEntity> roleCampaignEntities = e.getRoleCampaignEntities();

			if (!(roleCampaignEntities == null || roleCampaignEntities.isEmpty() || roleCampaignEntities.size() == 0)) {
				
				if(roleID_array == null){
					roleID_array = new ArrayList();
				}
				for (RoleCampaignEntity roleCampaignEntity : roleCampaignEntities) {

					roleID_array.add(roleCampaignEntity.getRoleEntity().getRoleId());
				}
				//System.out.println("role_array++++++++++++"+roleID_array);

				campaignModel.setRoleID_array_to_show(roleID_array);
			
			} 
			/*else
				throw new ObjectNotFound(messageUtil.getBundle("role-campaign.not.found"));*/
			
		} else {
			//System.out.println("Coming");
			}
			//throw new ObjectNotFound(messageUtil.getBundle("campaign.id.not.found"));

		return campaignModel;
	}

	@Override
	public List<CampaignModel> entityListToModelList(List<CampaignEntity> es)
			throws DataNotFound, ObjectNotFound {

		List<CampaignModel> campaignModels = null;

		if (es.size() > 0) {

			campaignModels = new ArrayList<CampaignModel>();

			for (CampaignEntity campaignEntity : es) {
				campaignModels.add(entityToModel(campaignEntity));
			}
		} else
			throw new DataNotFound(messageUtil.getBundle("campaign.not.found"));

		return campaignModels;
	}

	@Override
	public List<CampaignEntity> modelListToEntityList(List<CampaignModel> ms) {
		// TODO Auto-generated method stub
		return null;
	}

	public CampaignEntity deleteModelToEntity(CampaignModel m, CampaignEntity campaignEntity) {

		campaignEntity.setStatus(Status.INACTIVE);
		
		return campaignEntity;
	}

}