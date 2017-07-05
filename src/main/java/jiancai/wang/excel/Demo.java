package jiancai.wang.excel;


import jiancai.wang.excel.Common.DataPackage;
import jiancai.wang.excel.Common.Header;
import jiancai.wang.excel.exporter.ExportExecutor;
import jiancai.wang.excel.importer.ExcelImportExecutor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jiancai.wang on 2017/6/1.
 */
public class Demo {

    public static void main(String[] args) {

        String path = "D:/abc.acj/text.xls";
        DataPackage dataPackage = new DataPackage();
        Header field1 = new Header("field1", "java.lang.String");
        Header field2 = new Header("field2", "java.lang.Integer");
        Header field3 = new Header("field3", "java.util.Date");
        Header field4 = new Header("field4", "java.lang.String");
        Header field5 = new Header("field5", "java.lang.String");
        Header field6 = new Header("field6", "java.lang.String");
        Header field7 = new Header("field7", "java.lang.String");
        Header field8 = new Header("field8", "java.lang.String");
        List<Header> headers = Stream.of(field1, field2, field3, field4, field5, field6, field7, field8).collect(Collectors.toList());
        dataPackage.setHeaderList(headers);
        // 导入excel
        dataPackage = ExcelImportExecutor.executor(path, 3, dataPackage);
        // 分包导出
        List<DataPackage> dataPackages = ExcelImportExecutor.executor(path, 3, 200, dataPackage);
        // 导出excel
        ExportExecutor.executor(path, dataPackage);
    }
}
