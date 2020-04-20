package storage.Storage;

import execution.DMLTool;
import javafx.scene.control.Tab;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import table.BTree.BPlusTree;
import table.ColumnDescriptorList;
import table.Table;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlType;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class descriptorLoader {

    public HashMap loadPropertyFromFile(String tn){
        HashMap propertyMap = new HashMap();
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            String filepath = "data/" + tn + "/" + tn + "PropertyMap.xml";
            Document document1 = saxBuilder.build(new File(filepath));
            Element rootElement = document1.getRootElement();
            List<Element> elementList = rootElement.getChildren();
            for(Element eachElement : elementList){
                propertyMap.put(eachElement.getName(),eachElement.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return propertyMap;
    }


    public TableDescriptor loadDescriptorFromFile(String tn) {
        SAXBuilder saxBuilder = new SAXBuilder();
        TableDescriptor tableDescriptor = null;
        HashMap propertyMap = null;
        try {
            //首先读取propertyMap
            propertyMap = loadPropertyFromFile(tn);

            String filepath = "data/" + tn + "/" + tn + "Descriptor.xml";
            Document document1 = saxBuilder.build(new File(filepath));
            Element rootElement = document1.getRootElement();
            List<Element> elementList = rootElement.getChildren();

            //先得到最外层的节点们
            String tableName = rootElement.getChildText("tableName");
            int scheme = Integer.parseInt(rootElement.getChildText("schema"));
            char lockGranularity = rootElement.getChildText("lockGranularity").toCharArray()[0];
            //创建一个tabledescriptor
            ColumnDescriptorList columnDescriptorList = new ColumnDescriptorList();
            ColumnDescriptorList primaryKeyList = new ColumnDescriptorList();
            tableDescriptor = new TableDescriptor(tableName,lockGranularity,scheme,columnDescriptorList,primaryKeyList);

            //获得所有的columnDescriptor
            List<Element> columnDescriptors = rootElement.getChildren("columnDescriptors");

            for (Element eachColunm : columnDescriptors) {
                //除了dataType全部的部分
                String columnName = eachColunm.getChildText("columnName");
                int columnPosition = Integer.valueOf(eachColunm.getChildText("columnPosition"));
                long autoincStart = Long.valueOf(eachColunm.getChildText("autoincStart"));
                boolean autoincInc = Boolean.valueOf(eachColunm.getChildText("autoincInc"));
                long autoincValue = Long.valueOf(eachColunm.getChildText("autoincValue"));
                String comment = eachColunm.getChildText("comment");

                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                SqlType columnDefaultValue = DMLTool.convertToValue(columnName,eachColunm.getChildText("columnDefaultValue"),propertyMap);

                //DateTypeDescriptor
                Element dataTypeDescriptor = eachColunm.getChild("DataTypeDescriptor");
                int typeId = Integer.valueOf(dataTypeDescriptor.getChildText("typeId"));
                int precision = Integer.valueOf(dataTypeDescriptor.getChildText("precision"));
                int scale = Integer.valueOf(dataTypeDescriptor.getChildText("scale"));
                boolean isNullable = Boolean.getBoolean(dataTypeDescriptor.getChildText("isNullable"));
                boolean primaryKey = Boolean.getBoolean(dataTypeDescriptor.getChildText("isPrimaryKey"));;
                DataTypeDescriptor dataTypeDescriptor1 = new DataTypeDescriptor(typeId,isNullable,primaryKey);

                ColumnDescriptor columnDescriptor = new ColumnDescriptor(columnName,columnPosition,dataTypeDescriptor1,tableDescriptor,autoincStart,autoincInc);
                columnDescriptorList.add(columnDescriptor);


            }
            Element primaryKeyListElement = rootElement.getChild("primaryKey");
            List<Element> primaryKeyElements = primaryKeyListElement.getChildren("primaryColumnName");
            for(Element eachPrimaryKey : primaryKeyElements){
                String primaryKeyName = eachPrimaryKey.getText();
                ColumnDescriptor primaryKeyDescriptor = columnDescriptorList.getColumnDescriptor(primaryKeyName);
                primaryKeyList.add(primaryKeyDescriptor);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableDescriptor;
    }

    public Table loadFromFile(String tableName){
        try{
            TreeLoader tl = new TreeLoader();
            BPlusTree fileTree = tl.loadFromFile(tableName);

            TableDescriptor td = loadDescriptorFromFile(tableName);
            HashMap propertyMap = loadPropertyFromFile(tableName);

            Table resultTable = new Table(td,fileTree);
            return resultTable;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
