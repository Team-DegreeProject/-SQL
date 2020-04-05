package execution.table;

import parsing.Token;
import table.ColumnDescriptorList;
import table.Table;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlConstant;
import java.util.List;

import static table.TableSchema.*;

public class CreateTableStatement implements SqlConstant {
    List statement=null;
    ColumnDescriptorList columns;
    public CreateTableStatement(List l){
        statement=l;
        columns=new ColumnDescriptorList();
    }

    public Table createImpl(){
        TableDescriptor td=null;
        if(statement==null){
            return null;
        }
        String tableName=((Token)statement.get(2)).image;
        List<List> attributes= (List) statement.get(3);
        for(int i=0;i<attributes.size();i++){
            analyseOneRow(attributes.get(i));
//            List att= (List) attributes.get(i);
//            String columnName=((Token)att.get(0)).image;
//            int type=(Integer) att.get(1);
//            DataTypeDescriptor dataType= new DataTypeDescriptor(type);
//            ColumnDescriptor column=new ColumnDescriptor(columnName,i,dataType);
//            columns.add(column);
        }
//        String[] pk=null;
//        td=new TableDescriptor(tableName,BASE_TABLE_TYPE,columns,pk);
//        td.setTableInColumnDescriptor(td);
//        td.printColumnName();
//        ColumnDescriptor cd=td.getColumnDescriptorList().getColumnDescriptor(1);
//        Table table=new Table(td);
//        return table;
        return null;
    }

    public void analyseOneRow(List tokens){
        String columnName=((Token)tokens.get(0)).image;
        for(int i=1;i<tokens.size();i++){
            Token t= (Token) tokens.get(i);
            System.out.println(t.kind);
        }
    }

//    public Object checkType(){
//
//    }

}
