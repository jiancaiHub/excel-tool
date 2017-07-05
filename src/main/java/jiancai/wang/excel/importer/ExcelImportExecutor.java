package jiancai.wang.excel.importer;


import jiancai.wang.excel.Common.DataPackage;
import jiancai.wang.excel.Common.ExcelContent;
import jiancai.wang.excel.Common.Header;
import jiancai.wang.excel.exception.ImportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiancai.wang on 2017/5/31.
 */
public class ExcelImportExecutor {

    private static final Logger log = LoggerFactory.getLogger(ExcelImportExecutor.class);

    private static ExcelContent executor(String inputExcelPath, ExcelImportConfig configure) throws ImportException {
        return new ExcelImporter().importExcel(inputExcelPath, configure);
    }

    public static DataPackage executor(String filePath, Integer startNum, DataPackage dataPackage) {
        try {
            dataPackage.setDataList(executor(filePath, ExcelImportConfigAdapter.transformConfig(dataPackage).setStartRowNum(startNum)).getDataValue());
        } catch (ImportException e) {
            log.error("failed import excel, msg: " + e.getMessage());
        }
        return dataPackage;
    }

    @Deprecated
    public static List<DataPackage> executor(String filePath, Integer startNum, Integer packageSize, DataPackage dataPackage) {
        List<DataPackage> packageList = null;
        try {
            packageList = ExcelImportConfigAdapter.transformPackage(executor(filePath, ExcelImportConfigAdapter.transformConfig(dataPackage).setStartRowNum(startNum).setPageSize(packageSize)));
        } catch (ImportException e) {
            log.error("failed import excel, msg: " + e.getMessage());
        }
        return packageList;
    }

    public static List<DataPackage> execute(InputStream inputStream, String suffix, int startNum, int packageSize, List<Header> headers) {
        List<DataPackage> packageList;
        try {
            ExcelImporter excelImporter = new ExcelImporter();
            ExcelContent excelContent = excelImporter.importExcelFromStream(inputStream,
                    ExcelImportConfigAdapter.transformConfig(headers)
                            .setStartRowNum(startNum)
                            .setPageSize(packageSize),
                    suffix);

            packageList = ExcelImportConfigAdapter.transformPackage(excelContent);
        } catch (ImportException e) {
            log.error("failed import excel, msg: " + e.toString());
             packageList = new ArrayList<>();
        }
        return packageList;
    }

    /**
     * 获取文件后缀(xlsx或xls)
     * @param strFileName
     * @return
     */
    public static String getExcelSuffix(String strFileName) {
        if (strFileName.endsWith(".xlsx")) {
            return ".xlsx";
        } else if (strFileName.endsWith(".xls")) {
            return ".xls";
        } else {
            // 目前暂时将其它类型扩展名的文件强制按照xls格式进行处理
            return ".xls";
        }
    }
}
