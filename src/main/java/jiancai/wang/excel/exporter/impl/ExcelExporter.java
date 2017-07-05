package jiancai.wang.excel.exporter.impl;


import jiancai.wang.excel.Common.ExcelContent;
import jiancai.wang.excel.exception.ExportException;
import jiancai.wang.excel.exporter.FileExporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jiancai.wang on 2017/5/27.
 */
public class ExcelExporter extends FileExporter {

    private Integer sheetIndex = 0;

    @Override
    protected void exportContent(ExcelContent excelContent, OutputStream outputStream) throws ExportException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(Objects.isNull(excelContent.getSheetName()) ? "sheet1" : excelContent.getSheetName());
        final Map<String, String> headerMap = new HashMap<>();
        try {
            if (!excelContent.getData().isEmpty()) {
                throw new IllegalArgumentException("export data is empty.");
            }
            excelContent.getHeader().stream()
                    .forEach(cell -> headerMap.put(cell.getField(), cell.getType().getType()));
            createTitle(excelContent.getData().get(0), sheet);
            excelContent.getData().stream().forEach(rowData -> createRow(rowData, sheet, headerMap));
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new ExportException("failed to export data." + e);
        }
    }

    private void createRow(Map<String, Object> rowData, Sheet sheet, Map<String, String> headerMap) {
        Row title = sheet.createRow(sheetIndex++);
        int i = 1;
        for (String key : rowData.keySet()) {
            Cell cell = title.createCell(i++);
            setValue(cell, rowData.get(key), headerMap.getOrDefault(key, String.class.getName()));
        }
    }

    private void setValue(Cell cell, Object value, String type) {
        switch (type) {
            case "java.lang.Boolean":
                cell.setCellValue((Boolean) value);
                break;
            case "java.lang.Integer":
                cell.setCellValue((Double) value);
                break;
            case "java.lang.Float":
                cell.setCellValue((Double) value);
                break;
            case "java.lang.Long":
                cell.setCellValue((Double) value);
                break;
            case "java.lang.Double":
                cell.setCellValue((Double) value);
                break;
            case "java.util.Date":
                cell.setCellValue((Date) value);
                break;
            default:
                break;
        }
    }

    private void createTitle(Map<String, Object> rowData, Sheet sheet) {
        Row title = sheet.createRow(sheetIndex++);
        int i = 1;
        for (String key : rowData.keySet()) {
            Cell cell = title.createCell(i++);
            cell.setCellValue(key);
        }
    }
}
