package cn.ustc.utils;

import java.text.DecimalFormat;

public class TypeChangeUtils {
	public static String doubleToString(Double d){
		DecimalFormat df = new DecimalFormat("0");
		return df.format(d);
	}
}
