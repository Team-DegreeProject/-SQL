package table.type;

public class Char implements Comparable<Char> {
    private String string=null;
    private int scale=-1;
    private int realLength=0;

    public Char(){

    }

    public Char(int l,String str){
        scale=l;
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
    public int compareTo(Char o) {
        int re=this.string.compareTo(o.string);
        return re;
    }

    public void setString(String string) {
        this.string = string;
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


}
