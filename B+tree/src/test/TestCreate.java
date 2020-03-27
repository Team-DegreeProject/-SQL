package test;

import execution.table.CreateStatement;
import table.Table;

import java.util.ArrayList;
import java.util.List;

public class TestCreate {
    public static void main(String[] args) throws ClassNotFoundException {
        Table t=create();
        System.out.println("----------------");
    }

    public  static List createStatment() {
        List list=new ArrayList();
        List variant=new ArrayList();
        List v1=new ArrayList();
        List v2=new ArrayList();
        List v3=new ArrayList();
        v1.add("id");
        v1.add(100);
        v2.add("name");
        v2.add(101);
        v2.add("3");
        v3.add("addr");
        v3.add(102);
        v3.add("5");
        variant.add(v1);
        variant.add(v2);
        variant.add(v3);
        list.add("testTable");
        list.add(variant);
        return list;
    }

    public static Table create() throws ClassNotFoundException {
        List list=createStatment();
        CreateStatement c=new CreateStatement(list);
        Table t=c.createImpl();
        return t;
    }

}
