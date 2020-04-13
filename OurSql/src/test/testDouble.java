package test;

import table.type.number.SqlInt;
import table.type.text.SqlBlob;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class testDouble {
    public static void main(String[] args) {
        Double value=11111111111111.11111111;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);
        System.out.println(nf.format(value));
    }
}
