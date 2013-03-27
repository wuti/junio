package com.foo.example.util;

import static com.foo.common.base.utils.FooUtils.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.foo.common.base.pojo.FooGoodsModel;
import com.google.common.collect.Lists;

public class ItmsOrderHelper {
	public static void main(String[] args) throws InvalidFormatException,
			FileNotFoundException, IOException {

		String myFileDirectory = "d:\\/zznode/tmp/1/";

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

					// row的非空检查，非常重要，因为excel永远不会是很标准的,Start
					if (row == null
							|| row.getCell(0) == null
							|| (row.getCell(0).getCellType() != Cell.CELL_TYPE_STRING)
							|| row.getCell(0).getRichStringCellValue()
									.equals("")) {
						continue;
					}
					// row的非空检查，非常重要，因为excel永远不会是很标准的,End

					// 标准业务逻辑,Start
					// 标准业务逻辑,End

					for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
						// line1 :商品条码
						// line2：商品名称
						// line4: 进价
						// line5: 零售价
						// line6: 销售数量
						// line8: 销售金额
						if (cellNum >= 8) {
							continue;
						}

						cell = row.getCell(cellNum);

						if (cell == null) {
							continue;
						}

						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							System.out.print(cell.getRichStringCellValue()
									.getString() + " ");
							if (cellNum == 1) {

							} else {

							}
						}

					}
					// 每一行结束时候，如果在myNewRow范围内，就把这个对象保存到list中
					if (rowNum <= maxRowNum) {
						System.out.println();
						myList.add(headModel);
					}
				}// 结束一个工作表的读取
					// 追加到已有的list后
				myResultList.addAll(myList);
			}// 处理单个sheet结束
		}// 结束读取全部的excel表格数据
	}
}
