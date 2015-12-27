package com.axis.model;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
/**
 * Model class for viewing
 * values in Circle Head and Branch Head 
 * jsp
 * @author Sdf-338
 *
 */

public class AcheivementLeaderModel {
	
	
	private List<String> finalList;
	
	private List<String> headerList;
	
	private List<List<String>> bodycontains;
	
    private List<String> finalList2;
	
	private List<String> headerList2;
	
	private List<List<String>> bodycontains2;
	
	private String roleName;
	
	private String roleNameController;
	
	private LinkedHashMap<String,List<String>> achievementbodyContainsTop20;
	
	private LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModelsTop20;
	
	private LinkedHashMap<String,List<String>> achievementbodyContains;
	
	private LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModels;
	
	public String getRoleNameController() {
		return roleNameController;
	}

	public void setRoleNameController(String roleNameController) {
		this.roleNameController = roleNameController;
	}

	
	
	public LinkedHashMap<String, List<String>> getAchievementbodyContainsTop20() {
		return achievementbodyContainsTop20;
	}

	public void setAchievementbodyContainsTop20(
			LinkedHashMap<String, List<String>> achievementbodyContainsTop20) {
		this.achievementbodyContainsTop20 = achievementbodyContainsTop20;
	}

	public LinkedHashSet<AchievementLeaderNamedModel> getAchievementLeaderNamedModelsTop20() {
		return achievementLeaderNamedModelsTop20;
	}

	public void setAchievementLeaderNamedModelsTop20(
			LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModelsTop20) {
		this.achievementLeaderNamedModelsTop20 = achievementLeaderNamedModelsTop20;
	}
  
	public LinkedHashMap<String, List<String>> getAchievementbodyContains() {
		return achievementbodyContains;
	}

	public void setAchievementbodyContains(
			LinkedHashMap<String, List<String>> achievementbodyContains) {
		this.achievementbodyContains = achievementbodyContains;
	}

	public LinkedHashSet<AchievementLeaderNamedModel> getAchievementLeaderNamedModels() {
		return achievementLeaderNamedModels;
	}

	public void setAchievementLeaderNamedModels(
			LinkedHashSet<AchievementLeaderNamedModel> achievementLeaderNamedModels) {
		this.achievementLeaderNamedModels = achievementLeaderNamedModels;
	}

	public List<String> getFinalList2() {
		return finalList2;
	}

	public void setFinalList2(List<String> finalList2) {
		this.finalList2 = finalList2;
	}

	public List<String> getHeaderList2() {
		return headerList2;
	}

	public void setHeaderList2(List<String> headerList2) {
		this.headerList2 = headerList2;
	}

	public List<List<String>> getBodycontains2() {
		return bodycontains2;
	}

	public void setBodycontains2(List<List<String>> bodycontains2) {
		this.bodycontains2 = bodycontains2;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<List<String>> getBodycontains() {
		return bodycontains;
	}

	public void setBodycontains(List<List<String>> bodycontains) {
		this.bodycontains = bodycontains;
	}

	public List<String> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<String> finalList) {
		this.finalList = finalList;
	}

	public List<String> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList;
	}

}
