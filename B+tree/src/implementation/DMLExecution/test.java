package implementation.DMLExecution;

import BTree.BPlusTree;
import BTree.CglibBean;

import java.util.HashMap;
import java.util.List;

public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        BPlusTree<CglibBean, Integer> b = new BPlusTree<>(4);
//        BTree.Product p;

        for (int i = 500; i >=0; i--) {
            CglibBean bean = new CglibBean(propertyMap);
            bean.setValue("id", i);
            bean.setValue("name", "test");
            bean.setValue("address", "789");
            b.insert(bean,(Integer) bean.getValue("id"));
        }


        HashMap<Character, Integer> condition1=new HashMap();
        HashMap<String,HashMap> conditions=new HashMap();;
//        DMLTool.andCondition(condition1,'<',300);
//        DMLTool.andCondition(condition1,'>',100);
//        DMLTool.andCondition(condition1,'>',200);
//        System.out.println(condition1.get('>')+","+condition1.get('<'));
//        conditions.put("and",condition1);

        HashMap<Character, Integer> condition2=new HashMap();
        DMLTool.orCondition(condition2,'<',100);
        DMLTool.orCondition(condition2,'>',400);
        DMLTool.orCondition(condition2,'<',200);
        conditions.put("or",condition2);

        Table t=new Table(b);
        boolean delete=t.delete(conditions);
        System.out.println(delete);

         List list=b.getDatas();
         for(int i=0;i<list.size();i++){
             CglibBean c=(CglibBean)list.get(i);
             System.out.println(c.getValue("id")+","+c.getValue("address"));
         }


    }

//    public static void addCondition(HashMap condition,char t,int num){
//        if(t=='>'){
//            if(condition.get('>')==null){
//                condition.put('>',num);
//            }else{
//                int g=(int)condition.get('>');
//                if(num>g){
//                    condition.replace('>',num);
//                }
//            }
//        }else if(t=='<'){
//            if(condition.get('<')==null){
//                condition.put('<',num);
//            }else{
//                int g=(int)condition.get('<');
//                if(num<g){
//                    condition.replace('<',num);
//                }
//            }
//        }else if(t=='='){
//            if(condition.get('=')==null){
//                condition.put('=',num);
//            }else{
//                System.out.println("Warning:There are two =");
//            }
//        }
//
//    }
//
//    public static void orCondition(HashMap condition,char t,int num){
//        if(t=='>'){
//            if(condition.get('>')==null){
//                condition.put('>',num);
//            }else{
//                int g=(int)condition.get('>');
//                if(num<g){
//                    condition.replace('>',num);
//                }
//            }
//        }else if(t=='<'){
//            if(condition.get('<')==null){
//                condition.put('<',num);
//            }else{
//                int g=(int)condition.get('<');
//                if(num>g){
//                    condition.replace('<',num);
//                }
//            }
//        }else if(t=='='){
//            if(condition.get('=')==null){
//                condition.put('=',num);
//            }else{
//                System.out.println("Warning:There are two =");
//            }
//        }
//
//    }
}
