package storage.Storage;


import org.jdom2.Document;
import org.jdom2.Element;
import table.ColumnDescriptorList;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class descriptorSaver {
    TableDescriptor tabledescriptor;

    public descriptorSaver(TableDescriptor desToBeSaved) {
        tabledescriptor = desToBeSaved;
    }

    public void desToXML() {
        String tableName = tabledescriptor.getTableName();
        Element table = new Element("table");

        String schema = tabledescriptor.getSchemaName();
        char lockGranularity = tabledescriptor.getLockGranularity();
        table.setAttribute("name", tableName);
        Element tableNameElement = new Element("tableName").setText(tableName);
        Element tableSchemaElement = new Element("schema").setText(schema);
        Element lockGranularityElement = new Element("lockGranularity").setText(String.valueOf(lockGranularity));
        Document document = new Document(table);
        //获取所有的columnDescriptor
        for (int i = 0; i < tabledescriptor.getColumnDescriptorList().size(); i++) {
            ColumnDescriptorList allDescriptor = tabledescriptor.getColumnDescriptorList();
            //获取每一列的descriptor
            ColumnDescriptor singleColumn = allDescriptor.getColumnDescriptor(i);
            String columnName = singleColumn.getColumnName();
            int columnPosition = singleColumn.getPosition();

            //dataTypedescriptor里边的所有的属性
            DataTypeDescriptor typeDescriptor = singleColumn.getType();
            int typeId = typeDescriptor.getTypeId();
            int precision= typeDescriptor.getPrecision();
            int scale= typeDescriptor.getScale();
            boolean isNullable= typeDescriptor.isNullable();
            boolean primaryKey= typeDescriptor.isPrimaryKey();

            long autoincStart = singleColumn.getAutoincStart();
            long autoincInc = singleColumn.getAutoincInc();
            long autoincValue = singleColumn.getAutoincValue();
            boolean autoincCycle = singleColumn.getAutoincCycle();
            Object columnDefaultValue = singleColumn.getDefaultInfo();

        }

        //存primarykey的名字
        for(int j = 0 ; j < tabledescriptor.getPrimaryKey().size(); j++){
            String primaryColumnName = tabledescriptor.getPrimaryKey().get(j).getColumnName();
        }
    }


}
