package DMLExecution;

import BTree.BPlusTree;
import BTree.Product;

import java.util.Stack;

public class test {
    public static void main(String[] args) {
        BPlusTree<Product, Integer> b = new BPlusTree<>(4);
        Product p;
        for (int i = 0; i < 300; i++) {
            p = new Product(i, "test", 1.0 * i);
            b.insert(p, p.getId());
        }
        Stack condition = new Stack();
        Stack conditions=new Stack();
        Condition c1=new Condition("Price","<","200");
        Condition c2=new Condition("Price",">","100");
        condition.add(c1);
        condition.add(c2);
        conditions.add(condition);
        Table t=new Table(b);
        boolean delete=t.delete(conditions);
//        System.out.println("delete:"+delete);


    }
}
