package com.cqtd.common.util;

import java.sql.Timestamp;
import java.util.Map;
import com.google.common.base.Preconditions;

public class StaticMethod {
	/**
	 * 对象转换为long型
	 * 
	 * @param s
	 * @return
	 */
	public static long nullObject2Long(Object s) {
		long i = 0;
   
		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = 0;
		}

		return i;
	}

	/**
	 * 将对象转换为long,如果无法转换则返回temp
	 * 
	 * @param s
	 * @param temp
	 * @return
	 */
	public static long nullObject2Long(Object s, long temp) {
		long i = temp;

		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = temp;
		}

		return i;
	}
	public static double nullObject2Double(Object s){
		double res = 0.00;
		try{
			String str = s.toString();
			res = Double.parseDouble(str);
		}catch(Exception e){
			res = 0.00;
		}
		return res;
	}
	/**
	 * 字符转换函数
	 * 
	 * @param s
	 * @return output:如果字符串为null,返回为空,否则返回该字符串
	 */
	public static String nullObject2String(Object s) {
		String str = "";
		try {
			str = s.toString();
		} catch (Exception e) {
			str = "";
		}
		return str;
	}

	/**
	 * 将一个对象转换为String,如果
	 * 
	 * @param s
	 * @param chr
	 * @return
	 */
	public static String nullObject2String(Object s, String chr) {
		String str = chr;
		try {
			str = s.toString();
		} catch (Exception e) {
			str = chr;
		}
		return str;
	}

	/**
	 * 将一个对象转换为String,如果
	 * 
	 * @param s
	 * @return
	 */
	public static Integer nullObject2Integer(Object s) {
		return new Integer(StaticMethod.nullObject2int(s));
	}

	/**
	 * 将一个对象转换为整形
	 * 
	 * @param s
	 * @return
	 */
	public static int nullObject2int(Object s) {
		String str = "";
		int i = 0;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	/**
	 * 将对象转换为整形
	 * 
	 * @param s
	 * @param in
	 * @return
	 */
	public static int nullObject2int(Object s, int in) {
		String str = "";
		int i = in;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = in;
		}
		return i;
	}

	public static Timestamp nullObject2Timestamp(Object s) {
		Timestamp str = null;
		try {
			str = Timestamp.valueOf(s.toString());
		} catch (Exception e) {
			str = null;
		}
		return str;
	}

	/**
	 * 字符转换函数如果字符串为null,返回为空,否则返回该字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String null2String(String s) {
		return s == null ? "" : s;
	}
	
	public static Object getValue(@SuppressWarnings("rawtypes") Map requestMap,String key){
		Object result = new Object();
		Object[] obj = (Object[]) requestMap.get(key);
		Preconditions.checkNotNull(obj);
		result = obj[0];
		return result;
	}
	
	public static <T> T checkNotNull(T reference) {
	    if (reference == null) {
	      throw new NullPointerException();
	    }
	    return reference;
	}
	public static String toNoDomDate(String date){
		if(date.indexOf(".")!=-1){
			date = date.substring(0, date.indexOf("."));
		}
		return date;
	}
}
