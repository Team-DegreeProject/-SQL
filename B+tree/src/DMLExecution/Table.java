package DMLExecution;

import BTree.BPlusTree;
import BTree.Product;
import java.util.Stack;

public class Table {
    BPlusTree<Product, Integer> b;
    public Table(BPlusTree bt){
        b= bt;
    }

    public boolean delete(Stack condition){
        Stack con=where(condition);
        if(con.isEmpty()){
            System.out.println("There is no product needed to be deleted.");
            return false;
        }
        while(!con.isEmpty()){
            Product p=(Product)(con.pop());
            b.delete(p.getId());
        }
        return true;
    }
    public Stack where(Stack condition){
        Stack re=new Stack();
        Condition c=(Condition)condition.pop();
        String type=c.getType();
        double value=Double.parseDouble(c.getValue());
        for(int i=0;i<10000; i++){
            Product p=b.select(i);
            if(type.equals("<")){
                if(p.getPrice()<value) {
                    System.out.println(p.getId() + "," + p.getName() +","+ p.getPrice());
                    re.add(p);
                }
            }else if(type.equals("=")){
                if(p.getPrice() == value) {
                    System.out.println(p.getId() + "," + p.getName() + ","+p.getPrice());
                    re.add(p);
                }
            }else if(type.equals(">")){
                if(p.getPrice()>value) {
                    System.out.println(p.getId() + "," + p.getName() +","+ p.getPrice());
                    re.add(p);
                }
            }
        }
        return re;
    }

}
