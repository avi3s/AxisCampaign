/**
 * @author Supratim Sarkar
 * This class is use for common utill purpose
 * 
 * */

package com.axis.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	/*
	 * public static boolean threeTestsForRoleCampaign(RoleCampaign
	 * rolecampaign){
	 * 
	 * int present_year=Calendar.getInstance().get(Calendar.YEAR);
	 * 
	 * int financial_year_start=Integer.parseInt(rolecampaign.getCampaign().
	 * getFinancialYear().substring(0,4)); int
	 * financial_year_end=Integer.parseInt
	 * (rolecampaign.getCampaign().getFinancialYear().substring(5,9));
	 * 
	 * Date dt=new Date(); long present_quarter_date=dt.getTime();
	 * 
	 * long
	 * start_quarter_date=rolecampaign.getCampaign().getQuarterlyStart().getTime
	 * (); long
	 * end_quarter_date=rolecampaign.getCampaign().getQuarterlyEnd().getTime();
	 * 
	 * if(rolecampaign.getCampaign().getStatus() >0 &&
	 * (present_year>=financial_year_start && present_year<=financial_year_end)
	 * && (present_quarter_date>=start_quarter_date &&
	 * present_quarter_date<=end_quarter_date)) return true; else return false;
	 * 
	 * }
	 */

	public static boolean isEmpty(Integer value) {

		return value == 0;
	}

	public static boolean emailValidate(String h) {
		Pattern pattern;
		Matcher matcher;

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(h);
		return matcher.matches();
	}

	public static boolean mNoValidate(String h) {
		Pattern pattern;
		Matcher matcher;

		String Mno_PATTERN = "\\d{10}";
		pattern = Pattern.compile(Mno_PATTERN);
		matcher = pattern.matcher(h);
		return matcher.matches();
	}

	public static boolean isEmpty(String value) {

		return value == null || value.isEmpty();
	}

	public static boolean isNumeric(String value) {
		Pattern pattern = Pattern.compile("\\d+");

		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static String generateQueryParam(Map<String, Object> params) {

		StringBuffer stringBuffer = new StringBuffer();

		if (params != null) {

			for (String s : params.keySet()) {

				stringBuffer.append(s).append("=").append(params.get(s))
						.append("&");

			}
		}

		return stringBuffer.toString();

	}

	public static boolean isEmpty(String[] fieldName_array) {

		return fieldName_array == null || fieldName_array.length == 0;
	}

	public static boolean isEmptyValue(String[] fieldValue_array) {

		return fieldValue_array == null || fieldValue_array.length == 0;
	}

	public static boolean RegularExpressionDemo(String checkingStringName) {

		// Regular expression pattern to test input
		String regex = "(.)*(\\d)(.)*$";
		Pattern pattern = Pattern.compile(regex);

		Scanner reader = new Scanner(System.in);
		// String input = checkingStringName;
		String character = checkingStringName;

		System.out
				.println("Please enter input, must contain at-least one digit");
		int flag = 0;
		// while (!input.equalsIgnoreCase(regex)) {
		System.out.println("Under teh while looping part case");

		// input = reader.nextLine();

		// Pattern pattern = Pattern.compile(regex); // Don't do this, creating
		// Pattern is expensive
		// Matcher matcher = pattern.matcher(input);

		// boolean isMatched = matcher.matches();

		if (character.contains("*")) {
			System.out.println("Under the character constains field ");
			flag = 1;

		} else {
			flag = 0;

		}

		if (flag == 1) {
			return true;
		} else {
			return false;
		}

	}
}
