package com.axis.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateGenerator {

	// Date Range for quarter 1
	static final String April = "-04-01 00:00:01";
	static final String June = "-06-30 23:59:59";

	// Date Range for quarter 2
	static final String July = "-07-01 00:00:01";
	static final String September = "-09-30 23:59:59";

	// Date Range for quarter 3
	static final String October = "-10-01 00:00:01";
	static final String December = "-12-31 23:59:59";

	// Date Range for quarter 4
	static final String January = "-10-01 00:00:01";
	static final String March = "-12-31 23:59:59";

	public static List<Date> dateGeneratorbyId(int value, String year) {

		String[] str = year.split("-");
		String initialYear = str[0];
		String finalYear = str[1];
		String startDate = null, endDate = null;

		List<Date> dateList = null;

		if (value != 4) {

			if (value == 1) {
				startDate = initialYear + April;
				endDate = initialYear + June;
			}

			if (value == 2) {
				startDate = initialYear + July;
				endDate = initialYear + September;
			}

			if (value == 3) {
				startDate = initialYear + October;
				endDate = initialYear + December;
			}

		} else {
			startDate = finalYear + January;
			endDate = finalYear + March;
		}

		if (startDate != null && endDate != null) {

			dateList = new ArrayList();

			dateList.add(dateFormatter(startDate));
			dateList.add(dateFormatter(endDate));

		}

		//System.out.println(dateList);
		return dateList;

	}

	public static Date dateFormatter(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = null;

		try {

			date = formatter.parse(dateInString);
			//System.out.println(date);// Wed Apr 01 00:00:01 IST 2015
			//System.out.println(formatter.format(date));// 2015-04-01 12:00:01

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/*public static void main(String[] args) {
		dateGeneratorbyId(4, "2016-2017");
		// TODO Auto-generated method stub

	}
*/
}
