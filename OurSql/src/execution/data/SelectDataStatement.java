package execution.data;

import execution.FromStatement;
import execution.InnerJoinStatement;
import execution.OrderByStatement;
import parsing.Token;
import table.Table;
import java.util.ArrayList;
import java.util.List;

import static parsing.SqlParserConstants.*;

public class SelectDataStatement {

    public List statement;

    public SelectDataStatement(List tokens){
        statement=tokens;
    }

    public boolean selectDataImpl() throws ClassNotFoundException {
        List<List<Token>> tablenames= (List<List<Token>>) statement.get(3);
//        String tablename= tablenames.get(0).get(0).image;
//        Table table= FromStatement.from(tablename);
        Table table= InnerJoinStatement.innerJoinImpl(tablenames);
        List<List<Token>> columns= getColumns();
        Table show=table.selectSomeColumns(tablenames,columns);

        List<List<Token>> orderbys=getOrderByLists();
        List datas=OrderByStatement.orderByImpl(show,orderbys);

        show.printTable(datas);
        table.printTable(null);
        return true;
    }



    public List<List<Token>> getColumns(){
        Object o=statement.get(1);
        if(o instanceof Token){
            if(((Token) o).kind==ASTERISK ||((Token) o).kind==ALL){
                return changeReturnList(o);
            }
        }else if(o instanceof List){
            return (List<List<Token>>) o;
        }
        return null;
    }

    public List changeReturnList(Object o){
        List ret=new ArrayList();
        List re=new ArrayList();
        re.add(o);
        ret.add(re);
        return ret;
    }

    public List getOrderByLists(){
        for(int i=0;i<statement.size();i++){
            Object o=statement.get(i);
            if(o instanceof Token){
                if(((Token) o).kind==ORDER_BY){
                    return (List) statement.get(i+1);
                }
            }
        }
        return null;
    }



}
