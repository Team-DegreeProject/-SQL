package table.type.date;


import table.type.SqlType;

import java.sql.Date;

public class SqlDate implements SqlType {



    @Override
    public void setValue(String o) {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
