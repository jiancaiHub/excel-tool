package jiancai.wang.excel.Common;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jiancai.wang on 2017/5/27.
 */
public class ExcelCell {

    public enum CellType {

        BOOLEAN(Boolean.class.getName()),
        INT(Integer.class.getName()),
        LONG(Long.class.getName()),
        FLOAT(Float.class.getName()),
        DOUBLE(Double.class.getName()),
        STRING(String.class.getName()),
        DATE(Date.class.getName());

        String type;

        CellType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static CellType getCellType_old(String fieldType) {
            switch (fieldType) {
                case "java.lang.Boolean":
                    return BOOLEAN;
                case "java.lang.Integer":
                    return INT;
                case "java.lang.Long":
                    return LONG;
                case "java.lang.Float":
                    return FLOAT;
                case "java.lang.Double":
                    return DOUBLE;
                case "java.lang.String":
                    return STRING;
                case "java.util.Date":
                    return DATE;
                default:
                    throw new IllegalArgumentException("Can't support argument type '" + fieldType + "'. required " +
                            Stream.of(values()).map(CellType::toString).collect(Collectors.joining(", ", "[", "]")));
            }
        }

        public static CellType getCellType(String fieldType) {
            switch (fieldType.toUpperCase()) {
                case "BOOLEAN":
                case "BOOL":
                    return BOOLEAN;
                case "INTEGER":
                case "INT":
                    return INT;
                case "LONG":
                case "TIMESTAMP":
                    return LONG;
                case "FLOAT":
                    return FLOAT;
                case "DOUBLE":
                    return DOUBLE;
                case "STRING":
                case "VARCHAR":
                    return STRING;
                case "DATE":
                case "TIME":
                    return DATE;
                default:
                    throw new IllegalArgumentException("Can't support argument type '" + fieldType + "'. required " +
                            Stream.of(values()).map(CellType::toString).collect(Collectors.joining(", ", "[", "]")));
            }
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private String field;
    private CellType type;
    private Object value;


    public ExcelCell(String field, CellType cellType) {
        this.field = field;
        this.type = cellType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
}
