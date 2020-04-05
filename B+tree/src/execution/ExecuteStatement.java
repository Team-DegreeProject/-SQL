package execution;

import execution.database.*;
import execution.table.CreateTableStatement;
import execution.table.DropTableStatement;
import system.User;
import system.UserAccessedDatabases;
import table.Database;
import java.util.List;

public class ExecuteStatement {
    public static User user;//%%
    public static UserAccessedDatabases uad=setUser();//%%
    public static Database db=null;
    public static void delete(List tokens){
        System.out.println("tokens");
    }
    public static void createDatabase(List tokens){
        try {
            CreateDatabaseStatement cds=new CreateDatabaseStatement(tokens);
            cds.createDatabaseImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void renameDatabase(List tokens){
        try {
            RenameDatabaseStatement renameDatabaseStatement=new RenameDatabaseStatement(tokens);
            renameDatabaseStatement.renameDatabaseImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void showDatabase(List tokens){
        ShowDatabaseStatement sds=new ShowDatabaseStatement();
        sds.showDatabaseStatementImpl();
    }

    public static void dropDatabase(List tokens){
        try {
            DropDatabaseStatement dds=new DropDatabaseStatement(tokens);
            dds.dropDatabaseStatementImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void useDatabase(List tokens){
        try {
            UseDatabaseStatement uds=new UseDatabaseStatement(tokens);
            uds.useDatabaseStatementImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UserAccessedDatabases setUser(){
        user=new User(0,"root");
        uad=user.getUserAccessedDatabases();
        uad.setUser(user);
        return uad;
    }

    public static void createTable(List tokens){
        CreateTableStatement createTableStatement=new CreateTableStatement(tokens);
        createTableStatement.createImpl();
    }

    public static void dropTable(List tokens){
        try {
            DropTableStatement dropTableStatement=new DropTableStatement(tokens);
            dropTableStatement.dropTableImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
