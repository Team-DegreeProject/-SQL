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

    public TableDescriptor createImpl(){
        TableDescriptor table=null;
        if(statement==null){
            return null;
        }
        String tableName=(String)statement.get(0);
        ColumnDescriptorList columns=new ColumnDescriptorList();
        List attribute= (List) statement.get(1);
        for(int i=0;i<attribute.size();i++){
            List att= (List) attribute.get(i);
            String columnName=(String)att.get(0);
            int type=(int)att.get(1);
            DataTypeDescriptor dataType= new DataTypeDescriptor(type);
            ColumnDescriptor column=new ColumnDescriptor(columnName,i,dataType);
            columns.add(column);
        }
        table=new TableDescriptor(tableName,BASE_TABLE_TYPE,columns);
        table.setTableInColumnDescriptor(table);
        table.printColumnName();
        ColumnDescriptor cd=table.getColumnDescriptorList().getColumnDescriptor(1);
        return table;
    }






}
