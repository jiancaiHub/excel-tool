package jiancai.wang.excel.exporter;

import jiancai.wang.excel.Common.DataPackage;
import jiancai.wang.excel.Common.ExcelContent;
import jiancai.wang.excel.exception.ExportException;
import jiancai.wang.excel.exporter.impl.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiancai.wang on 2017/5/31.
 */
public class ExportExecutor {

    private static final Logger log = LoggerFactory.getLogger(ExportExecutor.class);

    private static void executor(String filePath, ExportConfigure configure, ExcelContent content) throws ExportException {
        switch (configure.getFileType()) {
            case EXCEL2007:
                new ExcelExporter().export(filePath, configure, content);
                break;
            case CSV:
                break;
        }
    }

    public static void executor(String filePath, DataPackage dataPackage) {
        try {
            executor(filePath, ExportConfigAdapter.transformConfig(dataPackage), ExportConfigAdapter.transformContent(dataPackage));
        } catch (ExportException e) {
            log.error("failed to export content. msg: " + e.getMessage());
        }
    }
}
