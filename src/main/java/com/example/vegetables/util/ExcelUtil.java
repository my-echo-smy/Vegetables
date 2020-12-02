package com.example.vegetables.util;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


import java.util.List;

/**
 * <p>
 * cn.gwssi.demo.utils
 * </p>
 * <p>
 * File: ExcelUtil.java 创建时间: 2020-01-13 20:19:15
 * </p>
 * <p>
 * Title: []_[]
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * 模块: -
 * </p>
 *
 * @author simengyuan
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 **/
public class ExcelUtil {
    public static void createComplaintExcel(String sheetName, List<String> cellNameList, SXSSFWorkbook wb) {
        SXSSFSheet sheet = wb.createSheet(sheetName);
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 13);
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        headerStyle.setLocked(true);
        headerStyle.setWrapText(false);
        headerStyle.setBorderBottom(BorderStyle.THIN); //下边框
        headerStyle.setBorderLeft(BorderStyle.THIN);//左边框
        headerStyle.setBorderTop(BorderStyle.THIN);//上边框
        headerStyle.setBorderRight(BorderStyle.THIN);//右边框

        SXSSFRow row = sheet.createRow(0);
        row.setHeight((short) 400);
        Cell c00 = row.createCell(0);
        c00.setCellValue(sheetName);
        c00.setCellStyle(headerStyle);
        if(cellNameList.size()!=1){
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cellNameList.size() - 1));
        }

        Row r2 = sheet.createRow(1);
        for (int i = 0; i < cellNameList.size(); i++) {
            Cell tempCell = r2.createCell(i);
            sheet.setColumnWidth(i, Math.max(cellNameList.get(i).getBytes().length * 256, 12 * 256));
            tempCell.setCellValue(cellNameList.get(i));
            tempCell.setCellStyle(headerStyle);
        }
    }

    public static void createExcel(String sheetName, List<String> cellNameList, SXSSFWorkbook wb) {
        SXSSFSheet sheet = wb.createSheet(sheetName);
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 13);
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        headerStyle.setLocked(true);
        headerStyle.setWrapText(false);
        headerStyle.setBorderBottom(BorderStyle.THIN); //下边框
        headerStyle.setBorderLeft(BorderStyle.THIN);//左边框
        headerStyle.setBorderTop(BorderStyle.THIN);//上边框
        headerStyle.setBorderRight(BorderStyle.THIN);//右边框

        SXSSFRow row = sheet.createRow(0);
        row.setHeight((short) 400);
        Cell c00 = row.createCell(0);
        c00.setCellValue("考勤");
        c00.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cellNameList.size() - 1));
        Row r2 = sheet.createRow(1);
        for (int i = 0; i < cellNameList.size(); i++) {
            Cell tempCell = r2.createCell(i);
            sheet.setColumnWidth(i, Math.max(cellNameList.get(i).getBytes().length * 256, 12 * 256));
            tempCell.setCellValue(cellNameList.get(i));
            tempCell.setCellStyle(headerStyle);
        }
    }


    public static SXSSFWorkbook createExcelData(SXSSFWorkbook excel, List<String> excelData, int sheetAt, int rowIndex, int columnSum) {
        SXSSFRow row = excel.getSheetAt(sheetAt).createRow(rowIndex);
        Font headerFont = excel.createFont();
        headerFont.setFontHeightInPoints((short) 11);
        CellStyle cellStyle = excel.createCellStyle();
        cellStyle.setFont(headerFont);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        cellStyle.setLocked(true);
        cellStyle.setWrapText(false);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        SXSSFCell cell = null;
        for (int i = 0; i < columnSum; i++) {
            excel.getSheetAt(sheetAt).setColumnWidth(0, 3766);
            if (i >= excelData.size()) {
                  cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue("");
            } else {
                  cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(excelData.get(i));
            }
        }
        return excel;
    }

    public static SXSSFWorkbook createFileExcelDataB(SXSSFWorkbook excel, List<String> excelData,
                                                    int sheetAt, int rowIndex, int columnSum, CellStyle cellStyle) {
        SXSSFSheet sheetAt1 = excel.getSheetAt(sheetAt);
        SXSSFRow row = sheetAt1.createRow(rowIndex);
        SXSSFCell cell = null;
        for (int i = 0; i < columnSum; i++) {
            excel.getSheetAt(sheetAt).setColumnWidth(0, 3766);
            if (i >= excelData.size()) {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue("");
            } else {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(excelData.get(i));

            }
        }
        return excel;
    }

    public static CellStyle createFileExcelDataA(SXSSFWorkbook excel) {
        Font headerFont = excel.createFont();
        headerFont.setFontHeightInPoints((short) 11);
        CellStyle cellStyle = excel.createCellStyle();
        cellStyle.setFont(headerFont);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        cellStyle.setLocked(true);
        cellStyle.setWrapText(false);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        return cellStyle;
    }


}

