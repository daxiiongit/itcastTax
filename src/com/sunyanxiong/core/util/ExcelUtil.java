package com.sunyanxiong.core.util;

import com.sunyanxiong.nsfw.user.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Daxiong on 2016/9/13 0013.
 *
 * 导出用户列表到 Excel
 */
public class ExcelUtil {

    public static void exportUserExcel(List<User> userList, ServletOutputStream outputStream){

        try {

            // 2.导出数据
            // (1)创建工作薄
            Workbook workbook = new HSSFWorkbook();
            // (2)创建工作表
            Sheet sheet = workbook.createSheet("用户列表");

            // 创建第一行
            Row row1 = sheet.createRow(0);
            // 获取第一行第一列单元格
            Cell cell1 = row1.createCell(0);

             /*特殊设置第一行第一列标题
             * */
            // 单元格合并
            CellRangeAddress rangeAddress = new CellRangeAddress(0,0,0,4);
            sheet.addMergedRegion(rangeAddress);
            // 设置默认宽
            sheet.setDefaultColumnWidth(25);
            // 单元格居中显示
            HSSFCellStyle style1 = (HSSFCellStyle) workbook.createCellStyle();
            style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 设置字体为黑体，字号为26
            HSSFFont font1 = (HSSFFont) workbook.createFont();
            font1.setFontName(HSSFFont.FONT_ARIAL);
            font1.setFontHeightInPoints((short)26);
            // 设置背景色
            style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style1.setFillBackgroundColor(HSSFColor.YELLOW.index);
            style1.setFillForegroundColor(HSSFColor.WHITE.index);


            // 将字体格式用于样式中
            style1.setFont(font1);
            // 将样式用于单元格中
            cell1.setCellStyle(style1);
            // 给该单元格设置值
            cell1.setCellValue("用户列表");


            /*
            *  设置第二行第二列行标题
            * */
            Row row2 = sheet.createRow(1);
            HSSFCellStyle style2 = (HSSFCellStyle) workbook.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFFont font2 = (HSSFFont) workbook.createFont();
            font2.setFontName(HSSFFont.FONT_ARIAL);
            font2.setFontHeightInPoints((short)18);
            style2.setFont(font2);

            // 定义一个列表题数组
            String [] titles = {"用户名","帐号","所属部门","性别","电子邮箱"};
            for (int i = 0;i < titles.length;i ++){
                Cell cell2 = row2.createCell(i);
                cell2.setCellStyle(style2);
                cell2.setCellValue(titles[i]);
            }

            /*
            * 列表项格式设置
            * */
            HSSFCellStyle stylex = (HSSFCellStyle) workbook.createCellStyle();
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            stylex.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
            for (int j = 0;j < userList.size();j ++){
                Row rowx = sheet.createRow(j + 2);
                // 第一列
                Cell cellx1 = rowx.createCell(0);
                cellx1.setCellStyle(stylex);
                cellx1.setCellValue(userList.get(j).getName());

                // 第二列
                Cell cellx2 = rowx.createCell(1);
                cellx2.setCellStyle(stylex);
                cellx2.setCellValue(userList.get(j).getAccount());

                // 第三列
                Cell cellx3 = rowx.createCell(2);
                cellx3.setCellStyle(stylex);
                cellx3.setCellValue(userList.get(j).getDept());

                // 第四列
                Cell cellx4 = rowx.createCell(3);
                cellx4.setCellStyle(stylex);
                cellx4.setCellValue(userList.get(j).isGender() ? "男" : "女");

                // 第五列
                Cell cellx5 = rowx.createCell(4);
                cellx5.setCellStyle(stylex);
                cellx5.setCellValue(userList.get(j).getEmail());

            }


            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
