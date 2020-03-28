package execution.table;

import table.BTree.BPlusTree;
import table.BTree.BPlusTreeTool;
import table.BTree.CglibBean;

import java.util.List;

import static parsing.SqlParserConstants.*;

public class WhereStatament {

    public static BPlusTree whereAnd(BPlusTree b1,BPlusTree b2){
        BPlusTree returnTree= BPlusTreeTool.mergeTreeAnd(b1,b2);
        return returnTree;
    }


    public static BPlusTree whereOr(BPlusTree b1,BPlusTree b2){
        BPlusTree returnTree=BPlusTreeTool.mergeTreeOr(b1,b2);
        return returnTree;
    }

    public static BPlusTree compare(BPlusTree b, String attribute, int type, Comparable compare, String primaryKey){
        BPlusTree returnTree=new BPlusTree();
        List btree=b.getDatas();
        switch (type){
            case EQ:{
                for(int i=0;i<btree.size();i++){
                    CglibBean temp= (CglibBean) btree.get(i);
                    Comparable c= (Comparable) temp.getValue(attribute);
                    if(c.compareTo(compare)==0){
                        returnTree.insert(temp, (Comparable) temp.getValue(primaryKey));
                    }
                }
                break;
            }
            case LESS_THAN_OPERATOR:
                for(int i=0;i<btree.size();i++){
                    CglibBean temp= (CglibBean) btree.get(i);
                    Comparable c= (Comparable) temp.getValue(attribute);
                    if(c.compareTo(compare)<0){
                        returnTree.insert(temp, (Comparable) temp.getValue(primaryKey));
                    }
                }
                break;
            case GREATER_THAN_OPERATOR:
                for(int i=0;i<btree.size();i++){
                    CglibBean temp= (CglibBean) btree.get(i);
                    Comparable c= (Comparable) temp.getValue(attribute);
                    if(c.compareTo(compare)>0){
                        returnTree.insert(temp, (Comparable) temp.getValue(primaryKey));
                    }
                }
                break;
        }
        return returnTree;
    }
}
