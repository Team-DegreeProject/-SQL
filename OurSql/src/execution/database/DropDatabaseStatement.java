package execution.database;

import execution.ExecuteStatement;
import execution.WhereStatament;
import table.Table;

import java.util.List;

import static parsing.SqlParserConstants.EQ;


public class DropDatabaseStatement {
    List statement=null;
    public DropDatabaseStatement(){}
    public DropDatabaseStatement(List tokens){
        statement=tokens;
    }
    public String dropDatabaseStatementImpl() throws ClassNotFoundException {
        if(statement==null){
            return "Drop Database Wrong!";
        }
        String databaseName=((Token)statement.get(2)).image;
        Table delete=WhereStatament.compare(ExecuteStatement.uad.getUserAccessedDatabase(),"databasename",EQ,databaseName);
        ExecuteStatement.uad.getUserAccessedDatabase().deleteRows(delete);
        String output=ExecuteStatement.uad.printUserAccessedDatabase();
        return output;
    }
}
