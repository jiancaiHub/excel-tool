package jiancai.wang.excel.Common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
public class DataPackage implements Serializable {

    private String name;

    private String dataType;

    /**
     * 数据包信息的表头信息
     */
    private List<Header> headerList;
    /**
     * 数据体
     */
    private List<Object> dataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<Header> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<Header> headerList) {
        this.headerList = headerList;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public Object getValue( String fieldName ){
        int fieldIndex = -1;
        for ( int i = 0; i < headerList.size(); i++ ) {
            if ( headerList.get( i ).getFieldName().equals( fieldName ) ) {
                fieldIndex = i;
                break;
            }
        }
        if ( fieldIndex == -1 ) {
            return null;
        }
        return dataList.get( fieldIndex );
    }

    public Object getArrayValue( int index, String fieldName ){
        Object values = dataList.get( index );
        if ( values instanceof List ) {
            List<Object> list = (List<Object>) values;
            int fieldIndex = -1;
            for ( int i = 0; i < headerList.size(); i++ ) {
                if ( headerList.get( i ).getFieldName().equals( fieldName ) ) {
                    fieldIndex = i;
                    break;
                }
            }
            if ( fieldIndex == -1 ) {
                return null;
            }
            return list.get( fieldIndex );
        }
        throw new RuntimeException( "data content error." );
    }
}
