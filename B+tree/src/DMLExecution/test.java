package DMLExecution;

import BTree.BPlusTree;
import BTree.CglibBean;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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


        HashMap<Character, Integer> condition=new HashMap();
        Stack conditions=new Stack();
        addCondition(condition,'<',300);
        addCondition(condition,'>',100);
        addCondition(condition,'>',200);
        System.out.println(condition.get('>')+","+condition.get('<'));
        conditions.add(condition);
        Table t=new Table(b);
        boolean delete=t.delete(conditions);
        System.out.println(delete);

         List list=b.getDatas();
         for(int i=0;i<list.size();i++){
             CglibBean c=(CglibBean)list.get(i);
             System.out.println(c.getValue("id")+","+c.getValue("address"));
         }


    }

    public static void addCondition(HashMap condition,char t,int num){
        if(t=='>'){
            if(condition.get('>')==null){
                condition.put('>',num);
            }else{
                int g=(int)condition.get('>');
                if(num>g){
                    condition.replace('>',num);
                }
            }
        }else if(t=='<'){
            if(condition.get('<')==null){
                condition.put('<',num);
            }else{
                int g=(int)condition.get('<');
                if(num<g){
                    condition.replace('<',num);
                }
            }
        }else if(t=='='){
            if(condition.get('=')==null){
                condition.put('=',num);
            }else{
                System.out.println("Warning:There are two =");
            }
        }

    }
}
