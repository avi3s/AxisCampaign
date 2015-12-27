package com.axis.model;

import java.util.List;
import java.util.Map;

public class ExcellSheetUploadModel {


	private List<Integer> blankSpaceRows;
	private List<String> blankSpaceString;
	public List<String> getBlankSpaceString() {
		return blankSpaceString;
	}
	public void setBlankSpaceString(List<String> blankSpaceString) {
		this.blankSpaceString = blankSpaceString;
	}
	private Map<Integer,List<String>> errormap;
	private Integer rowsInserted;
	private List<Integer> insertedRows;
public List<Integer> getInsertedRows() {
	return insertedRows;
}
public void setInsertedRows(List<Integer> insertedRows) {
	this.insertedRows = insertedRows;
}
public List<Integer> getBlankSpaceRows() {
	return blankSpaceRows;
}
public void setBlankSpaceRows(List<Integer> blankSpaceRows) {
	this.blankSpaceRows = blankSpaceRows;
}
public Map<Integer, List<String>> getErrormap() {
	return errormap;
}
public void setErrormap(Map<Integer, List<String>> errormap) {
	this.errormap = errormap;
}
public Integer getRowsInserted() {
	return rowsInserted;
}
public void setRowsInserted(Integer rowsInserted) {
	this.rowsInserted = rowsInserted;
}




}
