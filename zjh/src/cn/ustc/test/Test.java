package cn.ustc.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar instance = new GregorianCalendar();
		instance.setTime(new Date());
		System.out.println(instance.getTime());
		instance.add(Calendar.MONTH, 1);
		System.out.println(instance.getTime());
	}

}
