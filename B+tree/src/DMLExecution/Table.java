package DMLExecution;

import BTree.BPlusTree;
import BTree.CglibBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Table {
    BPlusTree<CglibBean, Integer> b ;
    public Table(BPlusTree bt){
        b= bt;
    }

    public boolean delete(Stack condition){
        where(condition);

//        if(con.isEmpty()){
//            System.out.println("There is no product needed to be deleted.");
//            return false;
//        }
//        System.out.println(con.empty());
//            BPlusTree<Product, Integer> tmp1=(BPlusTree<Product, Integer>)(con.pop());
//            for(int i=0;i<300;i++){
//                Product t=tmp1.select(i);
//                if(t!=null){
//                    System.out.println(t.getId()+","+t.getPrice());
//                }
//
//            }

        return true;
    }
    /*
    处理where 传入的为or和and相关操作
    Stack<Condition>指的是并列的and操作
    Stack<Stack<Condition>>指的是并列的Or操作
    返回每个or之间所需要的合并的数据结构
     */
    public void where(Stack<HashMap> conditions){
        Stack<BPlusTree> output=new Stack();
//        while(!conditions.isEmpty()){
//            output.add(whereAnd(conditions.pop()));
//        }
//        return output;
        whereAnd(conditions.pop());
    }

    /*
        处理where中并列的and操作
        Stack<Condition> condition为并列的and操作存储为stack
        返回每个and块执行后形成的树
         */
    public List<Object> whereAnd(HashMap condition){
        List<Object> list=null;
        if(condition.containsKey('=')){
            CglibBean t=null;
            int equal=(int)condition.get('=');
            if(condition.containsKey('>')){
                int big= (int) condition.get('>');
                if(condition.containsKey('<')){
                    int small= (int) condition.get('<');
                    if(small>equal&&big<equal){
                        t=b.select(equal);
                    }else{
                        System.out.println("Wrong:small>equal&&big<equal");
                    }
                }else{
                    if(big<equal){
                        t=b.select(equal);
                    }else{
                        System.out.println("Wrong:big<equal");
                    }
                }
            }else{
                t=b.select(equal);
            }
            list = new ArrayList<>();
            if(t!=null){
                list.add(t);
            }
        }else if(condition.containsKey('<')){
            int small= (int) condition.get('<');
            if(condition.containsKey('>')){
                int big= (int) condition.get('>');
                if(big<=small){
                    list=b.getMiddleDatas(big,small);
                    System.out.println(big+","+small+","+list.isEmpty());
                }else{
                    list = new ArrayList<>();
                    System.out.println("Wrong:big<small");
                }
            }else{
                list=b.getSmallDatas(small);
            }
        }else{
            int big= (int) condition.get('>');
            list=b.getBigDatas(big);
        }
        for(int i=0;i<list.size();i++){
            CglibBean tmp= (CglibBean) list.get(i);
            System.out.println(tmp.getValue("id")+","+tmp.getValue("name")+","+tmp.getValue("address"));
        }
        return list;
        //        return re;
    }


    public  BPlusTree whereOr(Stack betweenAnd){
         return null;
    }

}
