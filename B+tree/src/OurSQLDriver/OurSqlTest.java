package OurSQLDriver;


import BPlusTree.BPlusTree;
import BPlusTree.Product;

public class OurSqlTest {
    public OurSqlTest(){
        BPlusTree<Product, Integer> b = new BPlusTree<>(4);
        System.out.println("connected to btree");
    }

}
