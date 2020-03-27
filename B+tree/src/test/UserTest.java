package test;

import system.User;
import system.UserAccessedTable;
import table.BTree.BPlusTree;
import table.BTree.BPlusTreeTool;
import table.BTree.CglibBean;
import table.Table;
import table.TableDescriptor;
import table.type.VarChar;

import java.util.List;

public class UserTest {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        User user=new User(0,"root");
        UserAccessedTable usa=user.getUserAccessedTable();
        usa.setUser(user);
        Table t=TestCreate.create();
        System.out.println("------------------");
        boolean insertAtable=usa.insertTable(t);
        usa.printUserAccessedTable();
        Table table=usa.getUserAccessedTable();
        BPlusTree b=table.getTree();
        List l=b.getDatas();
        CglibBean c= (CglibBean) l.get(0);
        VarChar username= (VarChar) c.getValue("user");
        System.out.println(username.getString());
        TableDescriptor td=(TableDescriptor)c.getValue("table");
        System.out.println(td.getName());


    }


}
