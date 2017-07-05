package jiancai.wang.excel.exporter;



import jiancai.wang.excel.Common.DataPackage;
import jiancai.wang.excel.Common.ExcelCell;
import jiancai.wang.excel.Common.ExcelContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by jiancai.wang on 2017/5/31.
 */
public class ExportConfigAdapter {

    private static final ExportConfigure.ExportType DEFAULT_EXPORT_TYPE = ExportConfigure.ExportType.EXCEL2007;

    public static ExportConfigure transformConfig(DataPackage dataPackage) {
        ExportConfigure.ExportType type = Objects.isNull(dataPackage.getDataType()) ? DEFAULT_EXPORT_TYPE : ExportConfigure.ExportType.getExportType(dataPackage.getDataType());
        return new ExportConfigure().setFileType(type);
    }

    public static ExcelContent transformContent(DataPackage dataPackage) {
        ExcelContent content = new ExcelContent();
        List<ExcelCell> header = dataPackage.getHeaderList().stream()
                .map(h -> new ExcelCell(h.getFieldName(), ExcelCell.CellType.getCellType(h.getFieldType())))
                .collect(Collectors.toList());
        List<Map<String, Object>> data = dataPackage.getDataList().stream()
                .map(row -> parse(row.toString(), header))
                .collect(Collectors.toList());
        content.setHeader(header);
        content.setData(data);
        return null;
    }

    private static Map<String, Object> parse(String row, List<ExcelCell> header) {
        Map<String, Object> map = new HashMap<>();
        String[] rowValue = row.split("\\n");
        for (int i = 0; i < rowValue.length; i++) {
            map.put(header.get(i).getField(), rowValue);
        }
        return map;
    }
}
