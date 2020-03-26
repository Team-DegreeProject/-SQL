package table.type;

import java.util.HashMap;
import java.util.Map;

public class SqlConstantImpl implements SqlConstant{

    Map sqlMap = new HashMap();

    public SqlConstantImpl(){
        constructMap();
    }

    private void constructMap(){
        sqlMap.put(100,"java.lang.Integer");
        sqlMap.put(101,"table.type.Char");
        sqlMap.put(102,"table.type.VarChar");
        sqlMap.put(103,"java.util.Date");
        sqlMap.put(104,"table.TableDescriptor");
    }


}
