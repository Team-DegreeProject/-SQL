package com.ucd.oursql.sql.execution.data;

import com.ucd.oursql.sql.execution.FromStatement;
import com.ucd.oursql.sql.execution.WhereStatament;
import com.ucd.oursql.sql.parsing.Token;
import com.ucd.oursql.sql.table.Table;

import java.util.List;

public class UpdateDataStatement {

    public List statement;

    public UpdateDataStatement(List tokens){
        statement=tokens;
    }
    //UPDATE table SET column1 = value1, column2 = value2 WHERE condition;
    public boolean updateDataBasicImpl(){

        return true;
    }

    public String updateDataImpl() throws Exception {
        String tablename=((Token)statement.get(1)).image;
        Table table= FromStatement.from(tablename);
        List changes= (List) statement.get(3);
        List conditions= (List) statement.get(5);
        Table subTable= WhereStatament.whereImpl(table,conditions);
        table.updateTable(changes,subTable);
        String output=table.printTable(null);
        return output;
    }

}
