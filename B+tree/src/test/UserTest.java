package test;

import system.User;
import system.UserAccessedTable;
import table.Table;
import table.TableDescriptor;

public class UserTest {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        User user=new User(0,"root");
        UserAccessedTable usa=user.getUserAccessedTable();
        usa.setUser(user);
        Table t=TestCreate.create();
        boolean insertAtable=usa.insertTable(t);
        System.out.println(insertAtable);
        usa.printUserAccessedTable();

    }


}
