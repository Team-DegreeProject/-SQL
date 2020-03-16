package DMLExecution;

import BTree.BPlusTree;
import BTree.Product;

import java.util.Stack;

public class test {
    public static void main(String[] args) {
        BPlusTree<Product, Integer> b = new BPlusTree<>(4);
        Product p;
        for (int i = 0; i < 10000; i++) {
            p = new Product(i, "test", 1.0 * i);
            b.insert(p, p.getId());
        }
        Stack condition = new Stack();
        Condition c1=new Condition("Price","=","5000");
        condition.add(c1);
        Table t=new Table(b);
        boolean delete=t.delete(condition);
        System.out.println("delete:"+delete);
        b.select(5000);


    }
}
