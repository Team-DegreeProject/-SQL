package table.type.date;


import table.type.SqlType;

import java.sql.Date;

public class SqlDate extends Date implements SqlType {

    public SqlDate(int year, int month, int day) {
        super(year, month, day);
    }

    @Override
    public void setValue(Object o) {

    }
}
