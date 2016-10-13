package br.com.cgr.lucrocerto.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat dateFormat_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	public static String getString(Date date, DateFormat dateFormat) {
		String string = null;
		if (date != null) {
			string = dateFormat.format(date);
		}
		return string;
	}

}
