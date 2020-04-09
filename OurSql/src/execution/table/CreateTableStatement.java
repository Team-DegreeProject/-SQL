package execution.table;

import execution.ExecuteStatement;
import parsing.Token;
import table.ColumnDescriptorList;
import table.Table;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlConstant;

import java.util.ArrayList;
import java.util.List;

import static execution.DMLTool.analyseOneRow;
import static table.TableSchema.BASE_TABLE_TYPE;

public class CreateTableStatement implements SqlConstant {

    List statement;
    ColumnDescriptorList columns;
    ColumnDescriptorList primaryKeys;

    public CreateTableStatement(List l){
        statement=l;
        columns=new ColumnDescriptorList();
        primaryKeys=new ColumnDescriptorList();
    }

    public Table createImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DataTypeDescriptor tp=new DataTypeDescriptor(PRIMARY_KEY,false);
        ColumnDescriptor columnp=new ColumnDescriptor("primary key",0,tp);
        columns.add(columnp);
        TableDescriptor td=null;
        if(statement==null){
            return null;
        }
        String tableName=((Token)statement.get(2)).image;
        List<List> attributes= (List) statement.get(3);
        for(int i=0;i<attributes.size();i++){
            DataTypeDescriptor dataTypeDescriptor=analyseOneRow(1,attributes.get(i));
            String columnName=((Token)attributes.get(i).get(0)).image;
//            List att= (List) attributes.get(i);
//            String columnName=((Token)att.get(0)).image;
//            int type=(Integer) att.get(1);
//            DataTypeDescriptor dataType= new DataTypeDescriptor(type);
            ColumnDescriptor column=new ColumnDescriptor(columnName,i+1,dataTypeDescriptor);
            columns.add(column);
            if(dataTypeDescriptor.isPrimaryKey()){
                primaryKeys.add(column);
            }
        }
        td=new TableDescriptor(tableName,BASE_TABLE_TYPE,columns,primaryKeys);
        td.setTableInColumnDescriptor(td);
        Table table=new Table(td);
        ExecuteStatement.db.insertTable(table);
        td.printColumnName();
        table.printTable();
        return table;
    }


}
