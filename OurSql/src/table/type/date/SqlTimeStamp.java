package table.type.date;

import table.type.SqlType;

import java.sql.Timestamp;

public class SqlTimeStamp extends Timestamp implements SqlType {

    public SqlTimeStamp(int year, int month, int date, int hour, int minute, int second, int nano) {
        super(year, month, date, hour, minute, second, nano);
    }



    @Override
    public void setValue(String o) {

    }
}
