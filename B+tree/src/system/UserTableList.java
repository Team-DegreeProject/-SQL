package system;

import table.BTree.BPlusTree;
import table.BTree.CglibBean;
import table.ColumnDescriptorList;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlConstantImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static table.TableSchema.BASE_TABLE_TYPE;
import static table.TableSchema.SYSTEM_TABLE_TYPE;

public class UserTableList{
    private List userTableList;
    public UserTableList(){userTableList=new ArrayList();}



    public void UserAccessableTable(){
        TableDescriptor table=null;
        String tableName="UserPermissionScope";
        ColumnDescriptorList columns=new ColumnDescriptorList();
        DataTypeDescriptor id= new DataTypeDescriptor(100,false);//int
        ColumnDescriptor column=new ColumnDescriptor("id",0,id);
        columns.add(column);
        DataTypeDescriptor user= new DataTypeDescriptor(102,false);//int
        column=new ColumnDescriptor("user",1,user);
        columns.add(column);
        table=new TableDescriptor(tableName,SYSTEM_TABLE_TYPE,columns);
        table.setTableInColumnDescriptor(table);
        DataTypeDescriptor t= new DataTypeDescriptor(104,false);//int
        column=new ColumnDescriptor("table",2,t);
        columns.add(column);
        table=new TableDescriptor(tableName,SYSTEM_TABLE_TYPE,columns);
        table.setTableInColumnDescriptor(table);
        table.printColumnName();
    }




}
