package test;

import execution.database.CreateDatabaseStatement;
import execution.table.CreateStatement;
import system.User;
import system.UserAccessedDatabases;
import system.UserAccessedTable;
import table.BTree.BPlusTree;
import table.BTree.BPlusTreeTool;
import table.BTree.CglibBean;
import table.Table;
import table.TableDescriptor;
import table.type.VarChar;
import static table.type.SqlConstantImpl.*;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        User user=new User(0,"root");
        UserAccessedDatabases usa=user.getUserAccessedDatabases();
        usa.setUser(user);
        Table t=create();
        System.out.println("------------------");
        boolean insertAtable=usa.insertDatabase(t);
        Table databaseList=usa.getUserAccessedDatabase();
        databaseList.printTable();
//        BPlusTree b=table.getTree();
//        List l=b.getDatas();
//        CglibBean c= (CglibBean) l.get(0);
//        VarChar username= (VarChar) c.getValue("user");
//        System.out.println(username.getString());
//        TableDescriptor td=(TableDescriptor)c.getValue("table");
//        System.out.println(td.getName());


    }

    public  static List createDatabaseStatment() {
        List list=new ArrayList();
        list.add("CREATE");
        list.add("DATABASE");
        list.add("testDatabase");
        return list;
    }

    public static Table create() throws ClassNotFoundException {
        List list=createDatabaseStatment();
        CreateDatabaseStatement c=new CreateDatabaseStatement(list);
        Table t=c.createImpl();
        return t;
    }


}
