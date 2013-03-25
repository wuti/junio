package com.foo.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.foo.common.base.pojo.FooGoodsModel;
import com.google.common.collect.Lists;
import static com.foo.common.base.utils.FooUtils.log;

public class ItmsOrderHelper {
	public static void main(String[] args) throws InvalidFormatException,
			FileNotFoundException, IOException {

		String myFileDirectory = "d:\\/temp/june/";

		List<FooGoodsModel> myResultList = Lists.newArrayList();
		File myDirectory = new File(myFileDirectory);
		for (File file : myDirectory.listFiles()) {

			// 保存结果数据集的list
			List<FooGoodsModel> myList = Lists.newArrayList();

			FooGoodsModel headModel = null;

			Workbook wb = WorkbookFactory.create(new FileInputStream(new File(
					myFileDirectory + file.getName())));

			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				Sheet sheet = wb.getSheetAt(sheetIndex);
				// 计算出需要统计的write的行数
				int maxRowNum = sheet.getLastRowNum();
				log("begin to deal with sheetIndex:" + sheetIndex
						+ " with possible max num of:" + maxRowNum);
				Row row = null;
				Cell cell = null;
				// 一般说来，一个excel的第一行总是标题，跳过
				for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
					row = sheet.getRow(rowNum);
					headModel = new FooGoodsModel();
					for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
						// line1 :商品条码
						// line2：商品名称
						// line4: 进价
						// line5: 零售价
						// line6: 销售数量
						// line8: 销售金额
						if (cellNum != 1 && cellNum != 2 && cellNum != 4
								&& cellNum != 5 && cellNum != 6 && cellNum != 8) {
							continue;
						}

						cell = row.getCell(cellNum);

						// if (cell == null) {
						// continue;
						// }

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if (cellNum == 1) {
								headModel.setGoodsLabel(cell
										.getRichStringCellValue().getString());
							} else if (cellNum == 2) {
								headModel.setGoodsName(cell
										.getRichStringCellValue().getString());
							} else {
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (cellNum == 4) {
								headModel.setGoodsBid("");
							} else {
							}
							if (DateUtil.isCellDateFormatted(cell)) {
							} else {
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							// System.out.print(cell.getBooleanCellValue() +
							// "-");
							break;
						case Cell.CELL_TYPE_FORMULA:
							// System.out.print(cell.getCellFormula() + "-");
							break;
						default:
							// System.out.print("");
						}
					}
					// 每一行结束时候，如果在myNewRow范围内，就把这个对象保存到list中
					if (rowNum <= maxRowNum) {
						myList.add(headModel);
					}
				}// 结束一个工作表的读取
					// 追加到已有的list后
				myResultList.addAll(myList);
			}// 处理单个sheet结束
		}// 结束读取全部的excel表格数据
	}
}
