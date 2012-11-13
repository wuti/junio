package com.cqtd.common.base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FooUtils {

	public static HttpServletRequest setAttributesForRequest(
			HttpServletRequest request) {
		String clause;
		String clauseValue;
		@SuppressWarnings("unchecked")
		Map<String, String[]> requestMap = request.getParameterMap();
		for (String mapKey : requestMap.keySet()) {
			// This operation is safe, view j2ee5 API please.
			clauseValue = (requestMap.get(mapKey))[0];
			if (clauseValue.trim().equals("")) {
				continue;
			} else if (mapKey.indexOf("RequestScope") != -1) {
				clause = mapKey.substring(0, mapKey.indexOf("RequestScope"));
				request.setAttribute(clause, clauseValue);
			} else {

			}
		}
		return request;
	}

	/**
	 * This is useful for lower vesion struts1
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {

		String beanNamePrex = clazz.getSimpleName().substring(0, 1)
				.toLowerCase()
				+ clazz.getSimpleName().substring(1,
						clazz.getSimpleName().length());
		try {
			// return new ClassPathXmlApplicationContext(
			// "applicationContext.xml").getBean(clazz);
			return (T) WebApplicationContextUtils
					.getRequiredWebApplicationContext(
							ServletActionContext.getServletContext()).getBean(
							beanNamePrex);
		} catch (Exception e) {
			System.out.println("Bean definition not exist");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Autofill requestBean, this method is more effective and reliable because
	 * of make use of org.apache.commons.beanutils
	 * 
	 * Keep up of: org.apache.commons.beanutils
	 * 
	 */
	public static <T> T setBeanFromRequestMap(T t,
			Map<String, String[]> requestMap) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String paraName;
		String paraValue;

		for (Object keyObject : requestMap.keySet()) {
			paraName = String.valueOf(keyObject);
			// This operation is safe, view j2ee5 API please.
			paraValue = ((String[]) requestMap.get(paraName))[0].toString();
			if (PropertyUtils.isWriteable(t, paraName)) {
				PropertyUtils.setProperty(t, paraName, paraValue);
				continue;
			}
		}
		return (T) t;
	}

	/**
	 * 
	 * Construct search sql from the requestMap
	 * 
	 * Map类型为：Map<String, String[]>
	 * 
	 * @param requestMap
	 * @return
	 */
	public static String getSqlFromRequestMap(Map<String, String[]> requestMap) {
		String clause;
		String clauseValue;
		StringBuilder sb = new StringBuilder();
		for (Object keyObject : requestMap.keySet()) {
			clause = String.valueOf(keyObject);
			// This operation is safe, view j2ee5 API please.
			clauseValue = ((String[]) requestMap.get(clause))[0].toString();
			if (clauseValue.equals("")) {
				continue;
			} else if (clause.indexOf("StringEqual") != -1) {
				clause = clause.substring(0, clause.indexOf("StringEqual"));
				sb.append(" and " + clause + " ='" + clauseValue.trim() + "' ");
			} else if (clause.indexOf("StringLike") != -1) {
				clause = clause.substring(0, clause.indexOf("StringLike"));
				sb.append(" and " + clause + " like '%" + clauseValue.trim()
						+ "%' ");
			} else if (clause.indexOf("DateGreaterThan") != -1) {
				clause = clause.substring(0, clause.indexOf("DateGreaterThan"));
				sb.append(" and " + clause + " >= to_date('"
						+ clauseValue.trim() + "','yyyy-mm-dd hh24:mi:ss') ");
			} else if (clause.indexOf("DateLessThan") != -1) {
				clause = clause.substring(0, clause.indexOf("DateLessThan"));
				sb.append(" and " + clause + " <= to_date('"
						+ clauseValue.trim() + "','yyyy-mm-dd hh24:mi:ss') ");
			} else if (clause.indexOf("SortOrder") != -1) {
				sb.append(" " + clause + " <= '" + clauseValue.trim() + "' ");
			} else {

			}
		}
		return sb.toString();
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
		InputStream inp = new FileInputStream("d:\\myResult.xlsx");
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(FooUtils.formatDouble(8.8944666664));

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("d:\\myResult.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}

}
