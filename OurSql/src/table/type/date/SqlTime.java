package table.type.date;

import table.type.SqlType;

import java.sql.Time;

public class SqlTime extends Time implements SqlType {

    public SqlTime(int hour, int minute, int second) {
        super(hour, minute, second);
    }



    @Override
    public void setValue(String o) {

    }
}
