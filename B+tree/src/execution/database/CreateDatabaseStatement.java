package execution.database;

import parsing.Token;
import table.Database;

import java.util.List;

public class CreateDatabaseStatement{
    List statement=null;
    public CreateDatabaseStatement(List l){
        statement=l;
    }
    public CreateDatabaseStatement(){}

    public Database createImpl() throws ClassNotFoundException {
//        TableDescriptor td=null;
        if(statement==null){
            return null;
        }
        String databaseName=  ((Token)statement.get(2)).image;
        Database db=new Database(databaseName);
//        ColumnDescriptorList columns=new ColumnDescriptorList();
//        String databaseName=(String)statement.get(2);
//        DataTypeDescriptor dataType= new DataTypeDescriptor(INT);
//        ColumnDescriptor columnId=new ColumnDescriptor("id",0,dataType);
//        dataType= new DataTypeDescriptor(TABLE);
//        ColumnDescriptor columnTable=new ColumnDescriptor("table",1,dataType);
//        dataType= new DataTypeDescriptor(STRING);
//        ColumnDescriptor columnTableName=new ColumnDescriptor("tablename",2,dataType);
//        columns.add(columnId);
//        columns.add(columnTable);
//        columns.add(columnTableName);
//        td=new TableDescriptor(databaseName,BASE_TABLE_TYPE,columns);
//        td.setTableInColumnDescriptor(td);
//        td.printColumnName();
//        Table table=new Table(td);
        return db;
    }

}
