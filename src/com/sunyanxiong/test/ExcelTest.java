package com.sunyanxiong.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Daxiong on 2016/9/13 0013.
 */
public class ExcelTest {

    // 测试写入数据到工作薄,
    /*HSSFWorkBook 对象对03版本及以前版本支持良好，如果需要支持更高版本:
     1.需要将 HSSFWorkBook 对象换成 XSSFWorkBook 对象，
     2.同时将需要保存的工作薄的名称后缀从 .xls 改为 .xlsx*/

    @Test
    public void testWriteExcel() throws  Exception{

        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 创建工作表，工作表数据工作薄
        HSSFSheet sheet = workbook.createSheet();

        // 创建行，行数据工作表,第三行
        HSSFRow row = sheet.createRow(2);

        // 创建单元格，单元格数据行，第三行第三列
        HSSFCell cell = row.createCell(2);

        // 设置需要写入的数据
        cell.setCellValue("Hello World!");

        // 创建需要保存到指定文件中
        FileOutputStream outputStream = new FileOutputStream("F:\\itcast\\Log\\测试工作薄.xls");

        // 写入操作
        workbook.write(outputStream);

        // 关闭输出流
        outputStream.close();

    }

    // 测试读书工作薄中的数据并输入到控制台
    @Test
    public void testReadExcel() throws Exception{
        // 读取excel文件
        FileInputStream inputStream = new FileInputStream("F:\\itcast\\Log\\测试工作薄.xls");

        // 将文件转化为 workBook 对象
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // 通过 workBook 对象获取到工作表
        HSSFSheet sheet = workbook.getSheetAt(0);

        // 通过工作表获取到行
        HSSFRow row = sheet.getRow(2);

        // 通过行获取到单元格
        HSSFCell cell = row.getCell(2);

        // 获取到相应单元格的值
        System.out.println(cell.getStringCellValue());

    }

}
