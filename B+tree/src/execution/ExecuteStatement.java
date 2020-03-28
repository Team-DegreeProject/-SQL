package execution;

import execution.database.CreateDatabaseStatement;
import execution.table.CreateStatement;
import parsing.Token;
import system.User;
import system.UserAccessedDatabases;
import table.Database;
import table.Table;

import java.util.List;

public class ExecuteStatement {
    public static void delete(List tokens){
        System.out.println("tokens");
    }
    public static void createDatabase(List tokens){
        UserAccessedDatabases usa=setUser();
        CreateDatabaseStatement cds=new CreateDatabaseStatement(tokens);
        Database db= null;
        try {
            db = cds.createImpl();
            boolean insertAtable=usa.insertDatabase(db);
            Table databaseList=usa.getUserAccessedDatabase();
            databaseList.printTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
//        Token t= (Token) tokens.get(2);
//        System.out.println("image:"+t.image+",value:"+t.getValue()+";id:"+t.kind);
//        System.out.println("CreateTableTokens");

    }

    public static UserAccessedDatabases setUser(){
        User user=new User(0,"root");
        UserAccessedDatabases usa=user.getUserAccessedDatabases();
        usa.setUser(user);
        return usa;
    }


}
