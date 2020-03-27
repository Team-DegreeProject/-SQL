package execution.database;

import table.ColumnDescriptorList;
import table.Table;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import static table.type.SqlConstantImpl.*;

import java.util.List;

import static table.TableSchema.BASE_TABLE_TYPE;

public class CreateDatabaseStatement{
    public static int id=0;
    List statement=null;
    public CreateDatabaseStatement(List l){
        statement=l;
    }
    public CreateDatabaseStatement(){}

    public Table createImpl() throws ClassNotFoundException {
        TableDescriptor td=null;
        if(statement==null){
            return null;
        }
        ColumnDescriptorList columns=new ColumnDescriptorList();
        String databaseName=(String)statement.get(2);
        DataTypeDescriptor dataType= new DataTypeDescriptor(INT);
        ColumnDescriptor columnId=new ColumnDescriptor("id",0,dataType);
        dataType= new DataTypeDescriptor(TABLE);
        ColumnDescriptor columnTable=new ColumnDescriptor("table",1,dataType);
        columns.add(columnId);
        columns.add(columnTable);
        td=new TableDescriptor(databaseName,BASE_TABLE_TYPE,columns);
        td.setTableInColumnDescriptor(td);
        td.printColumnName();
        Table table=new Table(td);
        return table;
    }

}
