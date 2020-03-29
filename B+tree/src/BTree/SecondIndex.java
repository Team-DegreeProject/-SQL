package BTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecondIndex <T, V extends Comparable<V>>{
    private Map<T, List<V>> map;
    public Map<T, List<V>> createIndex(List<T> data,String primaryKey,String key){
        for(int i = 0 ; i < data.size() ; i++) {
            CglibBean bean=(CglibBean) data.get(i);
            Object tempPrimary=bean.getValue(primaryKey);
            Object tempKey=bean.getValue(key);
            if(map.get(key)==null){
                List list=new ArrayList();
                list.add(tempPrimary);
                map.put((T)tempKey,list);
            }else{
                List list=map.get(key);
                list.add(tempPrimary);
            }
        }
        return map;
    }
}
