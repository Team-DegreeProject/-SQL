public class Test {
    public static void main(String[] args){

        BPlusTree<Product, Integer> b = new BPlusTree<>(4);
        Product p;
        long time1 = System.nanoTime();

        for (int i = 15; i >=0; i--) {
            p = new Product(i, "test", 1.0 * i);
            b.insert(p, p.getId());
        }
        b.getNodes(b.getRoot());
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        long time2 = System.nanoTime();

//        Product p1 = b.select(345);

        long time3 = System.nanoTime();

        for (int i = 15; i >=0; i--) {
            b.delete(i);
            System.out.println("delete: "+i);
            b.getNodes(b.getRoot());
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
        }
        long time4 = System.nanoTime();

        System.out.println("插入耗时: " + (time2 - time1));
        System.out.println("查询耗时: " + (time3 - time2));
        System.out.println("删除耗时: " + (time4 - time3));
    }
}