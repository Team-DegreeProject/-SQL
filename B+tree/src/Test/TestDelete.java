package Test;

import DMLExecution.Delete;
import Table.BTree.BPlusTree;
import Table.BTree.BPlusTreeTool;
import Table.BTree.CglibBean;
import Table.Type.SqlConstant;

import java.util.HashMap;

public class TestDelete implements SqlConstant {
    public static void main(String[] args) throws ClassNotFoundException {
        testWhereAndOr();
    }
    public static void testcompare() throws ClassNotFoundException {
        BPlusTree b=createTree();
//        List list=Delete.compare(b,"address",EQ,"300");
//        BPlusTreeTool.printList(list,"address");
//        List list=Delete.compare(b,"id",EQ,300);
//        BPlusTreeTool.printList(list,"id");
//        List list=Delete.compare(b,"id",LESS_THAN_OPERATOR,300);
//        BPlusTreeTool.printList(list,"id");
        BPlusTree list= Delete.compare(b,"address",GREATER_THAN_OPERATOR,"300","id");
        BPlusTreeTool.printBPlusTree(list,"address");
    }

    public static void testWhereAndOr() throws ClassNotFoundException {
        BPlusTree b=createTree();
        BPlusTree b1=Delete.compare(b,"id",GREATER_THAN_OPERATOR,400,"id");
        BPlusTree b2=Delete.compare(b,"id",LESS_THAN_OPERATOR,100,"id");
        BPlusTree b3=Delete.whereOr(b1,b2);
        BPlusTreeTool.printBPlusTree(b3,"id");

    }

    public  static  BPlusTree createTree() throws ClassNotFoundException {
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);

        for (int i = 500; i >=0; i--) {
            CglibBean bean = new CglibBean(propertyMap);
            bean.setValue("id", i);
            bean.setValue("name", Integer.toString(i+1));
            bean.setValue("address", Integer.toString(i+2));
            b.insert(bean,(Integer) bean.getValue("id"));
        }
        return b;
    }

}
