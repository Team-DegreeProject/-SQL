package system;

import table.BTree.BPlusTree;
import table.BTree.CglibBean;
import table.ColumnDescriptorList;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;

import java.util.HashMap;
import java.util.List;

import static table.TableSchema.BASE_TABLE_TYPE;
import static table.TableSchema.SYSTEM_TABLE_TYPE;

public class UserTableList {
    public static void createTable(TableDescriptor table) throws ClassNotFoundException {
        BPlusTree b;
        HashMap propertyMap = new HashMap();
        propertyMap.put("tableName", Class.forName("java.lang.String"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
//        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);
    }

    public void UserAccessableTable(){
        TableDescriptor table=null;
        String tableName="UserPermissionScope";
        ColumnDescriptorList columns=new ColumnDescriptorList();
        DataTypeDescriptor id= new DataTypeDescriptor(100,false);//int
        ColumnDescriptor column=new ColumnDescriptor("id",0,id);
        columns.add(column);
        DataTypeDescriptor t= new DataTypeDescriptor(104,false);//int
        column=new ColumnDescriptor("table",0,t);
        columns.add(column);
        table=new TableDescriptor(tableName,SYSTEM_TABLE_TYPE,columns);
        table.setTableInColumnDescriptor(table);
        table.printColumnName();
    }


}
