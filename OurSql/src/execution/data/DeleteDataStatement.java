package execution.data;

import execution.FromStatement;
import execution.WhereStatament;
import parsing.Token;
import table.Table;
import table.type.SqlType;

import java.util.HashMap;
import java.util.List;

public class DeleteDataStatement {

    List statement;

    public DeleteDataStatement(List tokens){
        statement=tokens;
    }


    //1.1 SQL 删除表中的一行
    //1.1.1 DELETE FROM departments WHERE department_id = 16;
    //1.1.2 DELETE FROM departments WHERE department_name = ‘a’;
    public boolean deleteDataImpl() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String tablename=((Token)statement.get(2)).image;
        Table table= FromStatement.from(tablename);
        List condition= (List) statement.get(4);
        String attribute=((Token)condition.get(0)).image;
        int type=((Token)condition.get(1)).kind;
        HashMap propertyMap=table.getPropertyMap();
        String str= ((Token) condition.get(2)).image;
        Class c= (Class) propertyMap.get(attribute);
        SqlType value=(SqlType)c.newInstance();
        value.setValue(str);
//        ColumnDescriptor cd=td.getPrimaryKey().getColumnDescriptor(name);
        Table change= WhereStatament.compare(table,attribute,type,value);
        change.printTable();
        table.deleteRows(change);
        table.printTable();
        return true;
    }

}
