package execution.data;

import execution.FromStatement;
import execution.WhereStatament;
import javafx.scene.control.Tab;
import parsing.Token;
import table.Table;

import java.util.List;

//1.1 SQL 删除表中的一行
//1.1.1 DELETE FROM departments WHERE department_id = 16;
//1.1.2 DELETE FROM departments WHERE department_name = ‘a’;
public class DeleteDataStatement {

    List statement;

    public DeleteDataStatement(List tokens){
        statement=tokens;
    }

    public boolean deleteDataImpl() throws ClassNotFoundException {
        String tablename=((Token)statement.get(2)).image;
        Table table= FromStatement.from(tablename);
        List condition= (List) statement.get(4);
        String attribute= (String) condition.get(0);
        int type=(int)condition.get(1);
        Comparable value= (Comparable) condition.get(2);
        Table change= WhereStatament.compare(table,attribute,type,value);
        table.deleteRows(change);
        table.printTable();
        return true;
    }

}
