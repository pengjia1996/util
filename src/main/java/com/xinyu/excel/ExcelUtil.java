package com.xinyu.excel;


import com.xinyu.util.QwyUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.text.DecimalFormat;

/**
 * @author 马家立
 * @version 创建时间：2020年6月26日上午11:55:45
 * @Description:TODO POI操作Excel相关工具类
 */
public class ExcelUtil {


    /**
     * 将工作表的所有宽度设置成自适应
     * 已第一行的列数为标准
     *
     * @param sheet 工作表
     * @throws Exception
     */
    public static void autoSizeColumn(Sheet sheet) {
        if (!QwyUtil.isNullAndEmpty(sheet) && !QwyUtil.isNullAndEmpty(sheet.getRow(0))) {
            // 为空时所有列的宽度都设置成自适应
            // 列号从1开始
            Integer colNum = (int) sheet.getRow(0).getLastCellNum();
            // logger.info("colIndex=" + colNum);
            autoSizeColumn(sheet, colNum - 1);
        }
    }

    /**
     * 自适应宽度(中文支持)
     *
     * @param sheet    工作表
     * @param colIndex 列索引(0开始)
     * @throws Exception
     */
    private static void autoSizeColumn(Sheet sheet, Integer colIndex) {
        try {
            for (int index = 0; index < colIndex; index++) {
                int columnWidth = 0;
                int lastRowNum = sheet.getLastRowNum();
                // 默认列宽为为未设置时的列宽
                int defaultColumnWidth = sheet.getColumnWidth(index);
                for (int rowIndex = 0; rowIndex <= lastRowNum; rowIndex++) {
                    Row currentRow = sheet.getRow(rowIndex);
                    if (!QwyUtil.isNullAndEmpty(currentRow) && currentRow.getCell(index) != null) {
                        Cell currentCell = currentRow.getCell(index);
                        // 判断是否是合并单元格,是不处理
                        CellRangeAddress ca = getCellRangeAddress(sheet, currentCell);
                        if (QwyUtil.isNullAndEmpty(ca) || ca.getFirstColumn() == ca.getLastColumn()) {
                            if (currentCell.getCellType() == CellType.STRING) {
                                int length = getCellValue(currentCell).getBytes().length;
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            } else {
                                int length = getCellValue(currentCell).length();
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            }
                        }

                    }

                }

                if (columnWidth == 0) {
                    columnWidth = defaultColumnWidth;
                } else {
                    columnWidth = columnWidth * 256 + 60;
                    // 不能大于 255*256
                    if (columnWidth > 255 * 256) {
                        columnWidth = 255 * 256;
                    }
                }

                sheet.setColumnWidth(index, columnWidth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param cell 单元格
     * @return String 单元格的值
     * @throws Exception
     * @Title:getCellValue
     * @author:彭嘉
     * @date:2020年8月12日 下午6:10:01
     * @Description: 获取单元格的值(会根据单元格值的类型转成string)
     */
    private static String getCellValue(Cell cell) throws Exception {
        // 单元格内容
        String value = "";
        // 如果单元格为空或者单元格里面的数据为空则返回
        // 判断数据类型
        if (cell == null || CellType.BLANK == cell.getCellType()) {
            value = "";
        } else {
            switch (cell.getCellType()) {
                case BLANK:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    value = new DecimalFormat("0.##").format(cell.getNumericCellValue());
                    break;
                case STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                default:
                    value = cell.getStringCellValue();
                    break;
            }
        }
        return value;
    }

    /**
     * @param sheet 该单元格所在的工作表
     * @param cell  要判断的单元格
     * @return CellRangeAddress
     * @throws Exception
     * @author 彭嘉
     * @date 2021年5月6日 下午7:42:57
     * @description 判断单元格是否在合并单元格内, 是返回CellRangeAddress对象
     */
    private static CellRangeAddress getCellRangeAddress(Sheet sheet, Cell cell) throws Exception {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            boolean inRange = ca.isInRange(cell);
            if (inRange) {
                return ca;
            }
        }
        return null;
    }


    /**
     * 创建列
     *
     * @param row     行
     * @param content 内容
     * @param line    第几列
     * @param style   样式
     * @param type    money类型写入内容/100
     * @author pengjia
     * @date 2021/5/9 17:47
     */
    public static void creatCell(XSSFRow row, String content, int line, XSSFCellStyle style, String type) {
        XSSFCell cell = row.createCell(line);
        if ("money".equals(type)) {
            // 货币值
            if (!QwyUtil.isNullAndEmpty(content)) {
                cell.setCellValue(Double.parseDouble(content) / 100);
            }
        } else if ("int".equals(type)) {
            // 数值
            if (!QwyUtil.isNullAndEmpty(content)) {
                cell.setCellValue(Integer.parseInt(content));
            }
        } else {
            cell.setCellValue(content);
        }
        cell.setCellStyle(style);
    }


    /**
     * @param xssfRow--行
     * @param content--内容
     * @param line--第几列
     * @param style--样式
     * @throws Exception void
     * @Title:creatCell
     * @Description:TODO 创建列, 写入数据
     * @author:马家立
     * @date:2020-7-6 12:03:54
     */
    public static void creatCell(XSSFRow xssfRow, String content, int line, XSSFCellStyle style) throws Exception {
        // 创建列
        XSSFCell cell = xssfRow.createCell(line);
        // 写入内容
        if ((null != content) && !"".equals(content)) {
            cell.setCellValue(content);
        }
        // 设置样式
        cell.setCellStyle(style);
    }

    /**
     * @param wb     工作表对象
     * @param isBold 是否加粗
     * @param isRed  是否红色字体
     * @return
     * @throws Exception XSSFCellStyle 居中货币格式
     * @Title:getMoneyStyle
     * @author:蒋夏生
     * @date:2020年4月24日 下午3:58:36
     * @一个方法干多件事或超过150行的扣分
     * @Description:TODO(获取货币单元格格式，默认垂直居中)
     * @参数说明不写的扣分
     * @代码千万行注释第一行
     * @命名不规范同事泪两行
     */
    public static XSSFCellStyle getMoneyStyle(XSSFWorkbook wb, boolean isBold, boolean isRed) throws Exception {
        XSSFCellStyle style = wb.createCellStyle();
        // poi相关jar包升级方法修改
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        XSSFDataFormat format = wb.createDataFormat();
        // 加粗字体
        XSSFFont font = wb.createFont();
        // 是否是红色字体
        if (isRed) {
            font.setColor(Font.COLOR_RED);
        }
        // 定义货币格式
        if (isBold) {
            // 加粗货币格式
            style.setDataFormat(format.getFormat("¥#,##0.00"));
            font.setBold(true);
        } else {
            style.setDataFormat(format.getFormat("¥#,##0.00"));
        }
        style.setFont(font);
        return style;
    }

    /**
     * @param workbook
     * @return XSSFCellStyle
     * @throws Exception
     * @Title:getPOIStyle
     * @Description:TODO 获取POI-Excel默认样式
     * @author:马家立
     * @date:2020-7-6 11:33:21
     */
    public static XSSFCellStyle getPOIStyle(XSSFWorkbook workbook) throws Exception {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        // 加粗字体
        XSSFFont font = workbook.createFont();
        // 字体大小
        font.setFontHeight(12);
        // 字体颜色
        // font.setColor(Font.COLOR_RED);
        // 字体加粗
        // font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * @param workbook
     * @return XSSFCellStyle
     * @throws Exception
     * @Title:getPOITitleStyle
     * @Description:TODO 获取POI-Excel标题样式
     * @author:马家立
     * @date:2020-7-6 11:34:12
     */
    public static XSSFCellStyle getPOITitleStyle(XSSFWorkbook workbook) throws Exception {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        // 加粗字体
        XSSFFont font = workbook.createFont();
        // 字体大小
        font.setFontHeight(15);
        // 字体颜色
        // font.setColor(Font.COLOR_RED);
        // 字体加粗
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * @param cell--一个单元格的对象
     * @param rowNum--行数
     * @param lineNum--列数
     * @return String
     * @throws Exception
     * @Title:getRightTypeCell
     * @author:马家立
     * @date:2020年6月26日 上午11:55:55
     * @Description:TODO 返回该单元格相应的类型的值
     */
    public static String getRightTypeCell(Cell cell, int rowNum, int lineNum) throws Exception {
        // 单元格内容
        String value = "";
        // 如果单元格为空或者单元格里面的数据为空则返回
        // 判断数据类型
        if (cell == null || CellType.BLANK == cell.getCellType()) {
            value = "";
        } else {
            switch (cell.getCellType()) {
                case BLANK:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    /**
                     * --#与0的区别{#：没有则为空；0：没有则补0}
                     */
                    // 没有则自动补.00--100则为100.00;100.00则为100.00;1.05则为1.05;1.5则为1.50
                    // result = new DecimalFormat("0.00").format(cell.getNumericCellValue());
                    // 100则为100;100.00则为100;1.05则为1.05;1.5则为1.5
                    value = new DecimalFormat("0.##").format(cell.getNumericCellValue());
                    break;
                case STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                default:
                    value = cell.getStringCellValue();
                    break;
            }
        }
        return value;
    }

    /**
     * @param wb     工作表
     * @param isBold 是否加粗
     * @param isRed  是否是红色
     * @param size   字体大小
     * @return
     * @throws Exception XSSFCellStyle
     * @Title:getTextStyle
     * @author:蒋夏生
     * @date:2020年4月24日 下午4:07:38
     * @一个方法干多件事或超过150行的扣分
     * @Description:TODO(获取文本样式，默认垂直居中)
     * @参数说明不写的扣分
     * @代码千万行注释第一行
     * @命名不规范同事泪两行
     */
    public static XSSFCellStyle getTextStyle(XSSFWorkbook wb, boolean isBold, boolean isRed, Integer size)
            throws Exception {
        XSSFCellStyle style = wb.createCellStyle();
        // poi相关jar包升级方法修改
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        // 加粗字体
        XSSFFont font = wb.createFont();
        // 字体大小
        if (!QwyUtil.isNullAndEmpty(size)) {
            font.setFontHeight(size);
        }
        // 是否是红色字体
        if (isRed) {
            font.setColor(Font.COLOR_RED);
        }
        // 是否加粗
        if (isBold) {
            font.setBold(true);
        }
        style.setFont(font);
        return style;
    }

    /**
     * @param sheet 该单元格所在的工作表
     * @param cell  要判断的单元格
     * @return boolean true
     * @throws Exception
     * @Title:isMergedCell
     * @author:彭嘉
     * @date:2020年8月10日 下午5:18:47
     * @Description: 判断单元格是否是合并的单元格
     */
    public static boolean isMergedCell(Sheet sheet, Cell cell) throws Exception {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            boolean inRange = ca.isInRange(cell);
            if (inRange) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param sheet  工作表
     * @param row    行
     * @param column 列
     * @return boolean
     * @Title:isMergedRegion
     * @author:彭嘉
     * @date:2020年8月10日 下午4:16:31
     * @Description: 判断指定的单元格是否是合并单元格
     */
    public static boolean isMergedCell(Sheet sheet, int row, int column) throws Exception {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param sheet 页
     * @param array 需要合并的单元格
     * @throws Exception void
     * @Title:MergeCell
     * @author:蒋夏生
     * @date:2020年4月16日 下午2:35:01
     * @一个方法干多件事或超过150行的扣分
     * @Description:TODO(合并单元格)
     * @参数说明不写的扣分
     * @代码千万行注释第一行
     * @命名不规范同事泪两行
     */
    public static void mergeCell(XSSFSheet sheet, String[] array) throws Exception {
        for (String content : array) {
            String[] tempArr = content.split("-");
            CellRangeAddress region = new CellRangeAddress(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1]),
                    Short.parseShort(tempArr[2]), Short.parseShort(tempArr[3]));
            // 参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列但应注意两个构造方法的参数不是一样的，具体使用哪个取决于POI的不同版本。
            sheet.addMergedRegion(region);
        }
    }

    /**
     * @param sheet--页
     * @param size--列总数 void
     * @Title:setSizeColumn
     * @author:蒋夏生
     * @date:2020年4月16日 下午4:28:00
     * @Description:TODO(自适应宽度(中文支持))
     */
    public static void setSizeColumn(XSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth2 = sheet.getColumnWidth(columnNum);
            int columnWidth = columnWidth2 / 256;

            int lastRowNum = sheet.getLastRowNum();
            for (int rowNum = 0; rowNum < lastRowNum; rowNum++) {
                XSSFRow currentRow;
                // 当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

}

