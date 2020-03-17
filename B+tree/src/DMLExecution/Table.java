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
        Stack<BPlusTree> con=where(condition);

        if(con.isEmpty()){
            System.out.println("There is no product needed to be deleted.");
            return false;
        }
        System.out.println(con.empty());
            BPlusTree<Product, Integer> tmp1=(BPlusTree<Product, Integer>)(con.pop());
            for(int i=0;i<300;i++){
                Product t=tmp1.select(i);
                if(t!=null){
                    System.out.println(t.getId()+","+t.getPrice());
                }

            }

        return true;
    }
    /*
    处理where 传入的为or和and相关操作
    Stack<Condition>指的是并列的and操作
    Stack<Stack<Condition>>指的是并列的Or操作
    返回每个or之间所需要的合并的数据结构
     */
    public Stack<BPlusTree> where(Stack<Stack<Condition>> conditions){
        Stack<BPlusTree> output=new Stack();
        while(!conditions.isEmpty()){
            output.add(whereAnd(conditions.pop()));
        }
        return output;
    }

    /*
        处理where中并列的and操作
        Stack<Condition> condition为并列的and操作存储为stack
        返回每个and块执行后形成的树
         */
    public  BPlusTree whereAnd(Stack<Condition> condition){
        BPlusTree<Product, Integer> re = b;
        while(!condition.isEmpty()) {
            Condition c=(Condition)condition.pop();
            String type=c.getType();
            double value=Double.parseDouble(c.getValue());
            BPlusTree<Product, Integer> tmp = new BPlusTree<>(4);
            for (int i = 0; i < 300; i++) {
                Product p = re.select(i);
                if(p!=null) {
                    if (type.equals("<")) {
                        if (p.getPrice() < value) {
//                        System.out.println(p.getId() + "," + p.getName() + "," + p.getPrice());
                            tmp.insert(p, p.getId());
                        }
                    } else if (type.equals("=")) {
                        if (p.getPrice() == value) {
//                        System.out.println(p.getId() + "," + p.getName() + "," + p.getPrice());
                            tmp.insert(p, p.getId());
                        }
                    } else if (type.equals(">")) {
                        if (p.getPrice() > value) {
//                        System.out.println(p.getId() + "," + p.getName() + "," + p.getPrice());
                            tmp.insert(p, p.getId());
                        }
                    }
                }
            }
            re=tmp;
        }
        return re;
    }


    public  BPlusTree whereOr(Stack betweenAnd){
         return null;
    }

}
