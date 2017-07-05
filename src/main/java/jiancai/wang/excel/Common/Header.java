package jiancai.wang.excel.Common;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/14.
 */
public class Header implements Serializable {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段类型名称
     */
    private String fieldType;

    public Header(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public Header() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
