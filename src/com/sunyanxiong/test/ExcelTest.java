package com.sunyanxiong.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

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

    // 一段代码同时读取 03 版本和 07 版本的Excel
    @Test
    public void testReadExcel03And07() throws Exception{

        String fileName = "F:\\itcast\\Log\\测试工作薄.xls";
        // 判断是否为 Excel 文件
        // 采用正则表达式来判断该文件是 Excel 03 版本的还是 07 版本的
        if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
            boolean isExcel03 = fileName.matches("^.+\\.(?i)(xls)$");
            // 获取指定文件输入流
            FileInputStream inputStream = new FileInputStream(fileName);

            // 获取工作薄
            Workbook workbook = isExcel03 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
            // 获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 获取行
            Row row = sheet.getRow(2);
            // 获取单元格
            Cell cell = row.getCell(2);
            // 打印信息
            System.out.println(cell);

        }
    }

    // 测试插件设置Excel的样式
    @Test
    public void testExcelStyle() throws Exception{

        /*
        *   1.合并单元格：
        *       合并单元格需要将一行中多列合并为一列，多行合并为一行，因为合并的是单元格，所以需要定位到需要合并的单元格才能合并。
        *   2.将合并后的数据居中显示
        *       首先创建 HSSFCellStyle 对象，然后设置单元格相应的样式，再应用雨单元格对象中
        *   3.设置字体大小：
        *       先设置字体样式，再将字体应用于样式对象中，最后将样式应用于单元格对象中。
        *   4.设置背景的颜色
        *
        * */

        // 创建工作薄
        Workbook workbook = new HSSFWorkbook();

        // 创建工作表
        Sheet sheet = workbook.createSheet("合并单元格");

        // 创建行
        Row row = sheet.createRow(1);

        /*创建单元格*/
        Cell cell = row.createCell(1);

        // 创建需要合并单元格的范围，也就是 第几行到第几行，第几列到第几列
        CellRangeAddress rangeAddress = new CellRangeAddress(1,1,1,4);
        sheet.addMergedRegion(rangeAddress);

        /*设置单元格样式*/
        HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
        // 设置单元格水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

        /*设置字体大小和颜色*/
        HSSFFont font = (HSSFFont) workbook.createFont();
        // 设置字体的名字
        font.setFontName(HSSFFont.FONT_ARIAL);
        // 设置字体颜色
        font.setColor(HSSFColor.RED.index);
        // 设置字体大小
        font.setFontHeightInPoints((short)16);

        /*设置单元格背景颜色*/
        // 先设置单元格背景颜色模式为前景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格前景色
        style.setFillForegroundColor(HSSFColor.YELLOW.index);
        // 设置单元格背景色
        style.setFillBackgroundColor(HSSFColor.GREEN.index);

        // 在合并后的单元格中写入值
        cell.setCellValue("我是合并后的单元格");

        // 将单元格样式应用于该单元格对象中
        cell.setCellStyle(style);

        // 将字体样式应用于样式中，再将样式应用于单元格对象中
        style.setFont(font);

        // 设置写入数据到指定文件中
        FileOutputStream outputStream = new FileOutputStream("F:\\itcast\\Log\\工作薄样式.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

}
