package table.type.date;

import table.type.SqlType;
import java.time.Year;

public class SqlYear implements SqlType {

    private Year year=null;

    public SqlYear(){}

    public SqlYear(String s){
        this.year=Year.parse(s);
    }

    @Override
    public void setValue(String o) {
        this.year=Year.parse(o);
    }


    @Override
    public int compareTo(Object o) {
        int i=year.compareTo(((SqlYear)o).getYear());
        return i;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year.toString();
    }
}
