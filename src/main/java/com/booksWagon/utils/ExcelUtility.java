package com.booksWagon.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet st;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlSheet);
		int rowCount = st.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount+1;
	}
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlSheet);
		row = st.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlSheet);
		row = st.getRow(rowNum);
		
		try {
			cell = row.getCell(colNum);
		}catch(NullPointerException e) {
			wb.close();
			fi.close();
			return "";			
		}
		
//		cell = row.getCell(colNum);
//		if(cell==null) {
//			wb.close();
//			fi.close();
//			return "";
//		}
		String data = cell.toString();
		wb.close();
		fi.close();
		return data;
	}
	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		st = wb.getSheet(xlSheet);
		row = st.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
