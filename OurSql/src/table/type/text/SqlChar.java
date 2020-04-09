package table.type.text;

import table.type.SqlType;

public class SqlChar implements Comparable<SqlChar>, SqlType {
    private String string=null;
    private int scale=-1;
    private int realLength=0;

    public SqlChar(){
    }
    public SqlChar(String str){
        changeRange(str);
    }

    public SqlChar(int l, String str){
        scale=l;
        changeRange(str);
    }

    public void changeRange(String str){
        if(scale==-1){
            string=str;
            return;
        }
        int difference=scale-str.length();
        if(difference<0){
            string=str.substring(0,scale);
            realLength=scale;
        }else if(difference==0){
            string=str;
            realLength=scale;
        }else{
            string=str;
            for(int i=0;i<difference;i++){
                string.concat(" ");
            }
            realLength=str.length();
        }
    }

    @Override
    public int compareTo(SqlChar o) {
        int re=this.string.compareTo(o.string);
        return re;
    }

    public void setString(String str) {
        changeRange(str);
    }

    public String getString() {
        return string;
    }

    public void setLength(int length) {
        this.scale= length;
    }

    public int getLength() {
        return scale;
    }

    public int getRealLength() {
        return realLength;
    }

    public Boolean hasBlankChars() {
        if(realLength<scale){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return string.toString();
    }

    @Override
    public void setValue(String o) {
        setString(o);
    }
}
