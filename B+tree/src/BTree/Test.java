package BTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {


        // 设置类成员属性
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);
//        BTree.Product p;
//        CglibBean bean = new CglibBean(propertyMap);
        long time1 = System.nanoTime();

        for (int i = 10000; i >=0; i--) {
            CglibBean bean = new CglibBean(propertyMap);
            bean.setValue("id", i);
            bean.setValue("name", "test");
            bean.setValue("address", "789");
            b.insert(bean,(Integer) bean.getValue("id"));
//            p = new BTree.Product(i, "test", 1.0 * i);
//            b.insert(p, p.getId());
        }
//        long time2 = System.nanoTime();

        CglibBean b1 = b.select(345);
        System.out.println(b1.getValue("id")+","+b1.getValue("name")+","+b1.getValue("address"));

//        b.getNodes(b.getRoot());
//        long time3 = System.nanoTime();

//        for (int i = 10000; i >=0; i--) {
//            b.delete(i);
//        }
//        long time4 = System.nanoTime();

//        System.out.println("插入耗时: " + (time2 - time1));
//        System.out.println("查询耗时: " + (time3 - time2));
//        System.out.println("删除耗时: " + (time4 - time3));


//        List array=b.getSmallDatas(300);
//        for(int i=0;i<array.size();i++){
//            CglibBean temp= (CglibBean) array.get(i);
//            System.out.println(temp.getValue("id")+","+temp.getValue("name")+","+temp.getValue("address"));
//        }
    }
}
