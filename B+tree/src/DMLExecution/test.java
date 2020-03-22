package DMLExecution;

import BTree.BPlusTree;
import BTree.CglibBean;
import BTree.Product;

import java.util.HashMap;
import java.util.Stack;

public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);
//        BTree.Product p;
        CglibBean bean = new CglibBean(propertyMap);

        for (int i = 10000; i >=0; i--) {
            bean.setValue("id", i);
            bean.setValue("name", "test");
            bean.setValue("address", "789");
            b.insert(bean,(Integer) bean.getValue("id"));
        }

        for (int i = 10000; i >=0; i--) {
            b.delete(i);
        }

        //condition代表and block
        //conditions代表OR块
        Stack condition = new Stack();
        Stack conditions=new Stack();
        Condition c1=new Condition("id","<","200");
        Condition c2=new Condition("id",">","100");
        condition.add(c1);
        condition.add(c2);
        conditions.add(condition);
        Table t=new Table(b);
        boolean delete=t.delete(conditions);
//        System.out.println("delete:"+delete);


    }
}
