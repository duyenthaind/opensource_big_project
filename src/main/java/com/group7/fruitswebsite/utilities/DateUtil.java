package com.group7.fruitswebsite.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static String currentDate() {

		Date date = new Date();
		return formatter.format(date);
	}
}
