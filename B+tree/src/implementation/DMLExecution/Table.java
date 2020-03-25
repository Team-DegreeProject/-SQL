package implementation.DMLExecution;

import BTree.BPlusTree;
import BTree.BPlusTreeTool;
import BTree.CglibBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table {
    BPlusTree<CglibBean, Integer> b ;
    public Table(BPlusTree bt){
        b= bt;
    }

    public boolean delete(HashMap condition){
        List list=where(condition);
        if(list.isEmpty()){
            System.out.println("There is no row needed to be deleted.");
            return false;
        }
        for(int i=0;i<list.size();i++){
            CglibBean d = (CglibBean) list.get(i);
            b.delete((Integer) d.getValue("id"));
        }
        System.out.println(list==null);
        return true;
    }

    /**
     * 处理where，目前只能实现单独全是or和单独全是and，后续or和and的嵌套等待实现
     * @param conditions
     * @return 所有符合条件的CglibBean
     */
    public List<Object> where(HashMap conditions){
        List list=null;
        while(!conditions.isEmpty()){
            HashMap<Character, Integer> conditionAnd= (HashMap<Character, Integer>) conditions.get("and");
            HashMap<Character, Integer> conditionOr= (HashMap<Character, Integer>) conditions.get("or");
            if(conditionAnd!=null){
               list=whereAnd(conditionAnd);
               conditions.remove("and");
            }else{
                list=whereOr(conditionOr);
                conditions.remove("or");
            }
        }
        return list;
    }

    /**
     *  对于where中的最小块and进行操作
     * @param condition 并列的><=
     * @return 所得的List<CglibBean>
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
        return list;
    }


//    /**
//     * 两个list的并集
//     * @param l1
//     * @param l2
//     * @return 并集
//     */
//    public  List mergeListOr(List l1,List l2){
//        if(l1!=null&&l2!=null){
//            l1.removeAll(l2);//l1中去掉两者共同有的数据
//            l2.addAll(l1);
//            return l2;
//        } else if (l1 == null&&l2==null) {
//            return null;
//        } else if(l1==null){
//            return l2;
//        }
//        return l1;
//    }
//
//    /**
//     * 两个list的交集
//     * @param l1
//     * @param l2
//     * @return 交集
//     */
//    public  List mergeListAnd(List l1,List l2){
//        if(l1!=null&&l2!=null){
//            l1.retainAll(l2);
//            return l1;
//        }else if(l1==null&&l2==null){
//            return null;
//        }else if(l1==null){
//            return l2;
//        }
//        return l1;
//    }

    /**
     * 对于where中的最小块or进行的操作
     * @param condition
     * @return
     */
    public List whereOr(HashMap condition){
        List<Object> list1=null;
        List<Object> list2=null;
        List<Object> list3=null;
        if(condition.containsKey('=')){
            list1=compareThreeType('=',(int)condition.get('='));
        }
        if(condition.containsKey('<')){
            list2=compareThreeType('<',(int)condition.get('<'));
            System.out.println(list2.isEmpty());
            list3=BPlusTreeTool.mergeListOr(list1,list2);
        }
        if(condition.containsKey('>')){
            list2=compareThreeType('>',(int)condition.get('>'));
            list3= BPlusTreeTool.mergeListOr(list3,list2);
        }
        return list3;
    }

    /**
     * 对于最小块Or中所需的比较拆分开返回list
     * @param c
     * @param num
     * @return 对应的list
     */
    public List compareThreeType(Character c,int num){
        List<Object> list=null;
        if(c=='='){
            CglibBean t=null;
            t=b.select(num);
            list = new ArrayList<>();
            if(t!=null){
                list.add(t);
            }
        }else if(c=='<'){
            list=b.getSmallDatas(num);
        }else{
            list=b.getBigDatas(num);
        }
        return list;
    }

}
