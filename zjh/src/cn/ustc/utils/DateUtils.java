package cn.ustc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import sun.util.calendar.CalendarUtils;

/**
 * 日期工具类
 * 
 * @author liu
 * 
 */
public class DateUtils {
	private static String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private static String TIMEFORMAT = "yyyyMMddHHmmss";
    private static String DATEFORMAT = "yyyy-MM-dd";
    private static String SIMPLEFORMAT = "yyyyMMdd";

	/**
	 * yyyy-MM-dd HH:mm:ss 将日期转换成字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIMEFORMAT);
		return sdf.format(date);
	}

	/**
	 * 格式化日期为字符串
	 * @param date
	 * @param parttern 日期的字符串类型
	 * @return
	 */
	public static String dateToString(Date date, String parttern) {
		 if (date == null)
	            return "";
	        try {
	            return new SimpleDateFormat(parttern).format(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	}
	
	/**
	 * 判断字符串是不是日期
	 * @param date
	 * @param parttern 字符串对应日期的类型
	 * @return
	 */
	public static Date stringToDate(String date, String parttern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	private static SimpleDateFormat getDateFormat(String parttern)
			throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}
}
