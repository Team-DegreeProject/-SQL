package execution.data;

import execution.FromStatement;
import execution.WhereStatament;
import javafx.scene.control.Tab;
import parsing.Token;
import table.Table;

import java.util.List;

public class SelectDataStatement {

    public List statement;

    public SelectDataStatement(List tokens){
        statement=tokens;
    }

    public boolean selectDataImpl() throws ClassNotFoundException {
        List<List<Token>> tablenames= (List<List<Token>>) statement.get(3);
        String tablename= tablenames.get(0).get(0).image;
        Table table= FromStatement.from(tablename);
        List<List<Token>> columns= (List<List<Token>>) statement.get(1);
        Table show=table.selectSomeColumns(columns);
        table.printTable();
        System.out.println("==========================");
        System.out.println("==========================");
        show.printTable();
        return true;
    }

}
