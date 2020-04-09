package table.type.date;
import table.type.SqlType;

import java.sql.Date;
public class SqlYear extends Date implements SqlType {

    public SqlYear(int year) {
        super(year,0, 0);
    }


    @Override
    public void setValue(String o) {

    }
}
