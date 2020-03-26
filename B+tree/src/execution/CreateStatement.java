package execution;

import table.ColumnDescriptorList;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlConstant;

import java.util.List;

import static table.TableSchema.*;

public class CreateStatement implements SqlConstant {
    List statement=null;
    public CreateStatement(List l){
        statement=l;
    }
    public CreateStatement(){}

    public void createImpl(){
        System.out.println("here");
        TableDescriptor table=null;
        if(statement==null){
            return;
        }
        String tableName=(String)statement.get(0);
        ColumnDescriptorList columns=new ColumnDescriptorList();
        List attribute= (List) statement.get(1);
        for(int i=0;i<attribute.size();i++){
            List att= (List) attribute.get(i);
            String columnName=(String)att.get(0);
            int type=(int)att.get(1);
            DataTypeDescriptor dataType= new DataTypeDescriptor(type);
            ColumnDescriptor column=new ColumnDescriptor(columnName,i,dataType,table);
            columns.add(column);
        }
        table=new TableDescriptor(tableName,BASE_TABLE_TYPE,columns);
        result(table);
    }

    public void result(TableDescriptor t){
        String[] columns=t.getColumnNamesArray();
        System.out.println(columns.length);
        for(int i=0;i<columns.length;i++){
            System.out.println(columns[i]);
        }

    }




}
