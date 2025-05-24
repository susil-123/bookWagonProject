package com.booksWagon.pages;

import java.io.IOException;

import com.booksWagon.utils.ExcelUtility;

public class ExcelRoughWork {
	public static void main(String[] args) throws IOException {
		ExcelUtility objExcel = new ExcelUtility();
		String excelPath = "C:\\Users\\susil.k\\eclipse-workspace\\BooksWagonProject\\src\\test\\resources\\testData\\personalSettingsData.xlsx";
		int rowCount =  objExcel.getRowCount(excelPath,"personalSettingsSheet");
//		System.out.println(objExcel.getCellCount(excelPath,"personalSettingsSheet",1));
//		System.out.println(objExcel.getCellCount(excelPath,"personalSettingsSheet",2));
//		System.out.println(objExcel.getCellData(excelPath,"personalSettingsSheet",2,0));
//		System.out.println(objExcel.getCellData(excelPath,"personalSettingsSheet",3,0));
		for(int row=1;row<rowCount;row++) {
			int cellCount = objExcel.getCellCount(excelPath,"personalSettingsSheet",1);
			for(int column=0;column<cellCount;column++) {
				System.out.println(objExcel.getCellData(excelPath,"personalSettingsSheet",row,column));
			}
			System.out.println("");
		}
	}
}
