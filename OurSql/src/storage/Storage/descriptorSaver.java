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

        //将前边所有的element添加到table里边
        table.addContent(tableNameElement);
        table.addContent(tableSchemaElement);
        table.addContent(lockGranularityElement);


        //获取所有的columnDescriptor
        ColumnDescriptorList allDescriptor = tabledescriptor.getColumnDescriptorList();
        for (int i = 0; i < allDescriptor.size(); i++) {
            //columELement
            Element columnElement = new Element("columnDescriptors");

            //获取每一列的descriptor
            ColumnDescriptor singleColumn = allDescriptor.getColumnDescriptor(i);
            String columnName = singleColumn.getColumnName();
            Element columnNameElement = new Element("columnName").setText(columnName);

            int columnPosition = singleColumn.getPosition();
            Element columnPositionElement = new Element("columnPosition").setText(String.valueOf(columnPosition));

            //dataTypedescriptor里边的所有的属性
            DataTypeDescriptor typeDescriptor = singleColumn.getType();
            Element DataTypeDescriptorElement = new Element("DataTypeDescriptor");

            int typeId = typeDescriptor.getTypeId();
            Element typeIdElement = new Element("typeId").setText(String.valueOf(typeId));

            int precision= typeDescriptor.getPrecision();
            Element precisionElement = new Element("precision").setText(String.valueOf(precision));

            int scale= typeDescriptor.getScale();
            Element scaleElement = new Element("scale").setText(String.valueOf(scale));

            boolean isNullable= typeDescriptor.isNullable();
            Element isNullableElement = new Element("isNullable").setText(String.valueOf(isNullable));

            boolean primaryKey= typeDescriptor.isPrimaryKey();
            Element primaryKeyElement = new Element("primaryKey").setText(String.valueOf(primaryKey));

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
