package table.type;

import java.util.HashMap;
import java.util.Map;

public class SqlConstantImpl implements SqlConstant{

    protected Map<Integer,String> sqlMap = new HashMap();

    public SqlConstantImpl(){ constructMap(); }

    public final static int INT=100;
    public final static int CHAR=101;
    public final static int VARCHAR=102;
    public final static int DATE=103;
    public final static int TABLE_DESCRIPTOR=104;

    private void constructMap(){
        sqlMap.put(INT,"java.lang.Integer");
        sqlMap.put(CHAR,"table.type.Char");
        sqlMap.put(VARCHAR,"table.type.VarChar");
        sqlMap.put(DATE,"java.util.Date");
        sqlMap.put(TABLE_DESCRIPTOR,"table.TableDescriptor");
    }




}
