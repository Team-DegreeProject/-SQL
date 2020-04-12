package storage.Storage;


import org.jdom2.Document;
import org.jdom2.Element;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.ColumnDescriptorList;

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
            DataTypeDescriptorElement.addContent(typeIdElement);
            int precision= typeDescriptor.getPrecision();
            Element precisionElement = new Element("precision").setText(String.valueOf(precision));
            DataTypeDescriptorElement.addContent(precisionElement);
            int scale= typeDescriptor.getScale();
            Element scaleElement = new Element("scale").setText(String.valueOf(scale));
            DataTypeDescriptorElement.addContent(scaleElement);
            boolean isNullable= typeDescriptor.isNullable();
            Element isNullableElement = new Element("isNullable").setText(String.valueOf(isNullable));
            DataTypeDescriptorElement.addContent(isNullableElement);
            boolean isPrimaryKey= typeDescriptor.isPrimaryKey();
            Element isPrimaryKeyElement = new Element("isPrimaryKey").setText(String.valueOf(isPrimaryKey));
            DataTypeDescriptorElement.addContent(isPrimaryKeyElement);

            //将dataDEscriptorELement添加到tableElement里边
            columnElement.addContent(DataTypeDescriptorElement);

            long autoincStart = singleColumn.getAutoincStart();
            Element autoincStartElement = new Element("autoincStart").setText(String.valueOf(autoincStart));
            columnElement.addContent(autoincStartElement);
            long autoincInc = singleColumn.getAutoincInc();
            Element autoincIncElement = new Element("autoincInc").setText(String.valueOf(autoincInc));
            columnElement.addContent(autoincIncElement);
            long autoincValue = singleColumn.getAutoincValue();
            Element autoincValueElement = new Element("autoincValue").setText(String.valueOf(autoincValue));
            columnElement.addContent(autoincValueElement);
            boolean autoincCycle = singleColumn.getAutoincCycle();
            Element autoincCycleElement = new Element("autoincCycle").setText(String.valueOf(autoincCycle));
            columnElement.addContent(autoincCycleElement);
            String columnDefaultValue = singleColumn.getDefaultInfo().toString();
            Element columnDefaultValueElement = new Element("columnDefaultValue").setText(String.valueOf(columnDefaultValue));
            columnElement.addContent(columnDefaultValueElement);
        }

        //存primarykey的名字
        for(int j = 0 ; j < tabledescriptor.getPrimaryKey().size(); j++){
            String primaryColumnName = tabledescriptor.getPrimaryKey().get(j).getColumnName();
        }
    }


}
