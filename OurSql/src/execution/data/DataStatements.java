package execution.data;

import java.util.List;

public class DataStatements {

    public static void insertData(List tokens){
        try {
            InsertDataStatement insertDataStatement=new InsertDataStatement(tokens);
            insertDataStatement.insertDataImpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
