package com.foo.common.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.foo.common.base.pojo.FooGoodsModel;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class FooUtilsExcelHelper2 {
	public void getExcels() {

	}

	public static void main(String[] args) throws InvalidFormatException,
			FileNotFoundException, IOException {

		String myResultFileName = "d:\\result.xls";
		String myFileDirectory = "d:\\/temp/";

		List<FooGoodsModel> myResultList = Lists.newArrayList();
		File myDirectory = new File(myFileDirectory);
		for (File file : myDirectory.listFiles()) {

			// 保存结果数据集的list
			List<FooGoodsModel> myList = Lists.newArrayList();

			FooGoodsModel headModel = new FooGoodsModel();
			headModel = new FooGoodsModel();
			headModel.setSellsRank("NAME");
			headModel.setGoodsLabel("ACCESS_NUMBER");
			headModel.setGoodsName("MAC");
			headModel.setGoodsUnit("PASSWORD");
			myList.add(headModel);

			Workbook wb = WorkbookFactory.create(new FileInputStream(new File(
					myFileDirectory + file.getName())));
			// Sheet sheet = wb.getSheetAt(0);

			// 计算出需要统计的write的行数

			Row row = null;
			Cell cell = null;
			// 第一行为标题栏，跳过

			for (int mySheet = 0; mySheet < wb.getNumberOfSheets(); mySheet++) {

				Sheet sheet = wb.getSheetAt(mySheet);

				for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
					row = sheet.getRow(i);

					headModel = new FooGoodsModel();
					for (int k = 0; k < row.getLastCellNum(); k++) {
						if (k >= 4) {
							break;
						}
						cell = row.getCell(k);

						if (cell == null) {
							break;
						}

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if (k == 0) {
								headModel.setSellsRank(cell
										.getRichStringCellValue().getString());
							} else if (k == 1) {
								headModel.setGoodsLabel(cell
										.getRichStringCellValue().getString());
							} else if (k == 2) {
								headModel.setGoodsName(cell
										.getRichStringCellValue().getString());
							} else if (k == 3) {
								headModel.setGoodsUnit(cell
										.getRichStringCellValue().getString());
							} else {

							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
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
					String xxx = Strings.nullToEmpty(headModel.getSellsRank())
							.trim();
					if (xxx.contains("资阳")) {
						myResultList.add(headModel);
					}
				}
			}// 结束一个工作表的读取
		}// 结束读取全部的excel表格数据

		System.out.println(myResultList.size());

		// 数据准备完毕，开始写excel表格
		int rowIndex = 0;
		InputStream inp = new FileInputStream(myResultFileName);
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		for (FooGoodsModel fooGoodsModel : myResultList) {
			Row row = sheet.createRow(rowIndex++);
			Cell cell = null;
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(fooGoodsModel.getSellsRank());
			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(fooGoodsModel.getGoodsLabel());
			cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(fooGoodsModel.getGoodsName());
			cell = row.createCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(fooGoodsModel.getGoodsUnit());
		}
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(myResultFileName);
		wb.write(fileOut);
		fileOut.close();
	}
}
