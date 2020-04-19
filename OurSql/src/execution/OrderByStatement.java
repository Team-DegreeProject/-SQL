package execution;


import parsing.Token;
import table.BTree.BPlusTree;
import table.BTree.BPlusTreeTool;
import table.BTree.CglibBean;
import table.Table;

import java.util.HashMap;
import java.util.List;

import static parsing.SqlParserConstants.ASC;
import static parsing.SqlParserConstants.DESC;

public class OrderByStatement {

    public static List orderByImpl(Table table, List<List<Token>> lists){
        if(lists==null){
            System.out.println("case null");
            return null;
        }
        System.out.println("case change");
        List datas=table.getTree().getDatas();
        BPlusTree temp=new BPlusTree();
        HashMap propertyMap=table.getPropertyMap();
        String name=lists.get(0).get(0).image;
//        System.out.println("name:==========="+name);
        for(int i=0;i<datas.size();i++){
            CglibBean c= (CglibBean) datas.get(i);
            temp.insert(c, (Comparable) c.getValue(name));
        }
        List list=temp.getDatas();
//        checkAsc()
//        System.out.println("==========");
//        BPlusTreeTool.printBPlusTree(temp,table.getTd());
//        System.out.println("===========");
        return list;
    }

    public static boolean checkAsc(List<Token> lists){
        for(int i=0;i<lists.size();i++){
            Token o=lists.get(i);
            if(o.kind==ASC){
                return true;
            }else if(o.kind==DESC){
                return false;
            }
        }
        return true;
    }
}
