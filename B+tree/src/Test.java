import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {


        // 设置类成员属性
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);
//        Product p;
        CglibBean bean = new CglibBean(propertyMap);
        long time1 = System.nanoTime();

        for (int i = 10000; i >=0; i--) {
            bean.setValue("id", i);
            bean.setValue("name", "test");
            bean.setValue("address", "789");
            b.insert(bean,(Integer) bean.getValue("id"));
//            p = new Product(i, "test", 1.0 * i);
//            b.insert(p, p.getId());
        }
        long time2 = System.nanoTime();

        CglibBean b1 = b.select(345);

        long time3 = System.nanoTime();

        for (int i = 10000; i >=0; i--) {
            b.delete(i);
        }
        long time4 = System.nanoTime();

        System.out.println("插入耗时: " + (time2 - time1));
        System.out.println("查询耗时: " + (time3 - time2));
        System.out.println("删除耗时: " + (time4 - time3));
    }
}