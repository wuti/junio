package com.foo.common.base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FooUtils {

	public static void log(Object message) {
		System.out.println(message);
	}

	public static String parseMagnetStr(String source) {
		int magnetProtocolLength = 60;

		int x = source.indexOf("magnet:?xt=", 0);

		if (x == -1) {
			System.out.print("No magnet exist，reutrn");
			return "";
		}
		return source.substring(x, x + magnetProtocolLength);

	}

	/**
	 * 转化为年到秒的时间格式
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toDateFromYear2Second(DateTime dateTime) {
		return dateTime.toString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 转化为年到秒的时间格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateFromYear2Second(String date) {
		return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
				.parseDateTime(date).toDate();
	}

	/**
	 * 转化为年到天的时间格式
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toDateFromYear2Day(DateTime dateTime) {
		return dateTime.toString("yyyy-MM-dd");
	}

	/**
	 * 转化为年到天的时间格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateFromYear2Day(String date) {
		return DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date)
				.toDate();
	}

	/**
	 * 转化为年到分钟的时间格式
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toDateFromYear2Minute(DateTime dateTime) {
		return dateTime.toString("yyyy-MM-dd HH:mm");
	}

	/**
	 * 转化为年到分钟的时间格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateFromYear2Minute(String date) {
		return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")
				.parseDateTime(date).toDate();
	}

	/**
	 * 读入HttpServletResponse,输出失败的Json字符串到前台,全浏览器兼容
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void printJsonSuccessMsg(HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding(Charsets.UTF_8.toString());
		response.setContentType("text/plain");
		response.getWriter().write(
				new Gson().toJson(new ImmutableMap.Builder<String, String>()
						.put("success", "true").put("msg", "ok").build()));
	}

	public static void printJsonMessage(HttpServletResponse response,
			String message) throws IOException {
		response.setCharacterEncoding(Charsets.UTF_8.toString());
		response.setContentType("text/plain");
		response.getWriter().write(
				new Gson().toJson(new ImmutableMap.Builder<String, String>()
						.put("success", "true").put("msg", "ok")
						.put("message", message).build()));
	}

	/**
	 * 读入HttpServletResponse,将Json格式写出对象到HttpServletResponse
	 * 
	 * 只传一个参数将直接把对象写出到HttpServletResponse，
	 * 传二个参数则会把对象以键值对的方式写出到HttpServletResponse，
	 * 
	 * @param response
	 * @param object
	 *            ,可变参数，限制于2个参数之内
	 * @throws IOException
	 */
	public static void printJsonObject(HttpServletResponse response,
			Object... object) throws IOException {
		boolean myObjectLength = object.length >= 1 && object.length <= 2;
		Preconditions.checkArgument(myObjectLength,
				"Object Length should be between 1 and 2");
		response.setCharacterEncoding(Charsets.UTF_8.toString());
		response.setContentType("text/plain");
		Map<String, Object> myResultMap = Maps.newHashMap();
		myResultMap.put("success", "true");
		myResultMap.put("msg", "ok");
		if (object.length == 2) {
			myResultMap.put(object[0].toString(), object[1]);
			response.getWriter().write(new Gson().toJson(myResultMap));
		} else {
			response.getWriter().write(new Gson().toJson(object[0]));
		}
	}

	/**
	 * 
	 * 读入HttpServletResponse,将Json格式写出对象到HttpServletResponse 此方法能够序列化null字段
	 * 只传一个参数将直接把对象写出到HttpServletResponse
	 * ，传二个参数则会把对象以键值对的方式写出到HttpServletResponse，
	 * 
	 * @author Liuchang
	 * @since Dec,2011
	 * 
	 * @param response
	 * @param object
	 *            ,可变参数，限制于2个参数之内
	 * @throws IOException
	 */
	public static void printJsonObjectSerializeNulls(
			HttpServletResponse response, Object... object) throws IOException {
		boolean myObjectLength = object.length >= 1 && object.length <= 2;
		Preconditions.checkArgument(myObjectLength,
				"Object Length should be between 1 and 2");
		response.setCharacterEncoding(Charsets.UTF_8.toString());
		Map<String, Object> myResultMap = Maps.newHashMap();
		response.setContentType("text/plain");
		myResultMap.put("success", "true");
		myResultMap.put("msg", "ok");
		// serializeNulls 序列化null字段
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (object.length == 2) {
			myResultMap.put(object[0].toString(), object[1]);
			response.getWriter().write(gson.toJson(myResultMap));
		} else {
			response.getWriter().write(gson.toJson(object[0]));
		}
	}

	/**
	 * 如果列表不为空，那么返回列表本身；如果列表为空，则返回空列表
	 */
	public static <T> List<T> nullToEmpty(List<T> list) {
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return Lists.newArrayList();
		}
	}

	public static String formatDouble(String result) {
		return String.format("%1$.2f", Double.parseDouble(result));
	}

	public static String formatDouble(double result) {
		return String.format("%1$.2f", result);
	}

	/**
	 * 判定是否是数字
	 * 
	 * @param code
	 *            例如：12.23
	 * @return
	 */
	public static boolean isNum(String code) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher isNum = pattern.matcher(code);
		return isNum.matches();
	}

	/**
	 * 判断是否是字符
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isCharacter(String code) {
		Pattern pattern = Pattern.compile("[A-Za-z]*");
		Matcher isNum = pattern.matcher(code);
		return isNum.matches();
	}

	/**
	 * @param myFormula
	 *            :传入的公式，四则表达式（即加减乘除），结果保存小数点后2位
	 * @param argumentMap
	 *            : 键(key)：变量名;值(value)：变量值
	 * 
	 * @throws IllegalArgumentException
	 *             :公式所含量变量与可变参数所含变量不匹配
	 */
	public static String getFormulaValue(String myFormula,
			Map<String, String> argumentMap) {
		try {
			return new FooUtilsFomulaHelper(myFormula, argumentMap).getResult();
		} catch (NumberFormatException e) {
			System.out.println("BreakPoint-yunNan-公式中的变量名和传入Map参数中的变量名不匹配");
			return "0";
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println("242\n");
	}

}
